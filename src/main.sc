require: patterns.sc

require: changePinCode.sc
    
theme: /
        
    state: Routing
        event!: noMatch
        if: !$session.firstRequest
            script:
                $jsapi.startSession()
                $session.firstRequest = true
                // FIXME По сценарию подразумевается только перед ChooseOption, 
                // но логичнее в начале сессии
                $reactions.answer("Здравствуйте!")
        script:
            var res = $nlp.match($request.query, "/ChangePinCode")
            if (!_.isEmpty(res) && res.targetState) {
                $reactions.transition(res.targetState)
            } else {
                $reactions.transition("/NoMatch")
            }
        
    state: GladToTalk
        a: Приятно было пообщаться. Всегда готов помочь вам снова ☺
        go!: /StopSession
        
    state: NoMatch || noContext = true
        # FIXME В сценарии не описано что должно происходить при этих событиях
        event!: lengthLimit
        event!: timeLimit
        event!: nluSystemLimit
        a: Извините, я не понимаю.
        script:
            // FIXME Добавлен таймаут как в ChooseOption на случай, если вместо выбора опции будет NoMatch
            $reactions.timeout({ interval: "1 hour", targetState: "/StopSession" });
            
    state: StopSession
        script:
            $jsapi.stopSession()