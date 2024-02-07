require: patterns.sc

require: changePinCode.sc
    
theme: /

    state: Routing
        event!: match
        event!: noMatch
        event!: lengthLimit
        event!: timeLimit
        event!: nluSystemLimit
        if: !$session.firstRequest
            script:
                $jsapi.startSession()
                $session.firstRequest = true
                $reaction.answer("Здравствуйте!")
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
        script:
            $jsapi.stopSession()