require: patterns.sc

require: changePinCode.sc

init:
    bind("preProcess", function($context) {
        if (!$context.session.firstRequest) {
            $jsapi.startSession()
            $context.session.firstRequest = true
            // FIXME По сценарию выглядит так будто приветствие должно быть только перед ChooseOption, 
            // но кажется, что подразумевалось в начале сессии
            $reactions.answer("Здравствуйте!")
        }
    });
    
theme: /
    
    state: Start
        # FIXME Для теста в виджете
        q!: $regex</start>
        a: Начнем
        
    state: GladToTalk
        a: Приятно было пообщаться. Всегда готов помочь вам снова ☺
        go!: /StopSession
        
    state: NoMatch || noContext = true
        # FIXME В сценарии не описано что должно происходить при этих событиях
        event!: noMatch
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