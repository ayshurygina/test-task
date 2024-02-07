require: patterns.sc

require: changePinCode.sc
    
theme: /
        
    state: Routing
        q!: $regexp</start>
        event!: match
        # FIXME В сценарии не описано что должно происходить при этих событиях
        event!: noMatch
        event!: lengthLimit
        event!: timeLimit
        event!: nluSystemLimit
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
                $reactions.transition("/StopSession")
            }
        
    state: GladToTalk
        a: Приятно было пообщаться. Всегда готов помочь вам снова ☺
        go!: /StopSession
        
    state: StopSession
        a: Конец
        script:
            $jsapi.stopSession()