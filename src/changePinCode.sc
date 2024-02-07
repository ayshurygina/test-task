theme: /ChangePinCode

    state: ChooseOption
        q: * ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) [$threeWords] ($pin/$code/$password) *
        q: * ($pin/$code/$password) [$threeWords] ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) *
        a: Сейчас расскажу порядок действий.
            Выберите, что именно планируете сделать:
            1. Поменять пароль для входа в приложение.
            2. Поменять PIN-код от карты.
            Пожалуйста, отправьте цифру, соответствующую вашему выбору.
        script:
            // FIXME Не указано что значит "Нет ответа". В течение какого времени?
            $reactions.timeout({ interval: "1 hour", targetState: "/StopSession" });
            
    
    state: App
        q: (1/$app) || fromState = "/ChangePinCode/ChooseOption", onlyThisState = true
        
        q: * ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) [$threeWords] [в] $app * || fromState = "/ChangePinCode/ChooseOption", onlyThisState = true
        q: * $app [$threeWords] ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) * || fromState = "/ChangePinCode/ChooseOption", onlyThisState = true
        
        q: [$threeWords] ($pin/$code/$password) [$threeWords] [в] $app [$threeWords]
        q: [$threeWords] [в] $app [$threeWords] ($pin/$code/$password) [$threeWords]
        
        q: * ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) [$threeWords] ($pin/$code/$password) [$threeWords] [в] $app *
        q: * ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) [$threeWords] [в] $app [$threeWords] ($pin/$code/$password) *
        q: * ($pin/$code/$password) [$threeWords] [в] $app [$threeWords] ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) *
        q: * ($pin/$code/$password) [$threeWords] ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) [$threeWords] [в] $app *
        q: * $app [$threeWords] ($pin/$code/$password) [$threeWords] ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) *
        q: * $app [$threeWords] ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) [$threeWords] ($pin/$code/$password) *
        
        a: Смена пароля от приложения возможна несколькими способами:
            1. на экране "Профиль" выберите "Изменить код входа в приложение".
            2. введите SMS-код.
            3. придумайте новый код для входа в приложение и повторите его.
        script:
            $reactions.timeout({ interval: 2, targetState: "./Variant2" });
            
        state: Variant2
            a: Либо нажмите на кнопку "Выйти" на странице ввода пароля для входа в приложение.
                После чего нужно будет заново пройти регистрацию:
                1. ввести полный номер карты (если оформляли ранее, иначе номер телефона и дату рождения),
                2. указать код из смс-код,
                3. придумать новый пароль для входа.
            script:
                $reactions.timeout({ interval: 2, targetState: "/GladToTalk" });
                
        
    state: Card
        q: (2/$card) || fromState = "/ChangePinCode/ChooseOption", onlyThisState = true
        
        q: * ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) [$threeWords] [в/на/от] $card * || fromState = "/ChangePinCode/ChooseOption", onlyThisState = true
        q: * $card [$threeWords] ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) * || fromState = "/ChangePinCode/ChooseOption", onlyThisState = true
        
        q: [$threeWords] ($pin/$code/$password) [$threeWords] [в/на/от] $card [$threeWords]
        q: [$threeWords] [в/на/от] $card [$threeWords] ($pin/$code/$password) [$threeWords]
        
        q: * ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) [$threeWords] ($pin/$code/$password) [$threeWords] [в/на/от] $card *
        q: * ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) [$threeWords] [в/на/от] $card [$threeWords] ($pin/$code/$password) *
        q: * ($pin/$code/$password) [$threeWords] [в/на/от] $card [$threeWords] ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) *
        q: * ($pin/$code/$password) [$threeWords] ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) [$threeWords] [в/на/от] $card *
        q: * $card [$threeWords] ($pin/$code/$password) [$threeWords] ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) *
        q: * $card [$threeWords] ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) [$threeWords] ($pin/$code/$password) *
        
        q: * {$changeSynsInf {($pin/$code/$password) ([в/на/от] $card)} ((в/через) $app)} *
        
        q: * $forget [$threeWords] {($pin/$code/$password) ([в/на/от] $card)} * $changeSynsInf [$oneWord] (в/через) $app *
        q: * $forget [$threeWords] {($pin/$code/$password) ([в/на/от] $card)} * (в/через) $app [$oneWord] $changeSynsInf *
        q: * {($pin/$code/$password) ([в/на/от] $card)} [$threeWords] $forget * $changeSynsInf [$oneWord] (в/через) $app *
        q: * {($pin/$code/$password) ([в/на/от] $card)} [$threeWords] $forget * (в/через) $app [$oneWord] $changeSynsInf *
        q: * $changeSynsInf [$oneWord] (в/через) $app * $forget [$threeWords] {($pin/$code/$password) ([в/на/от] $card)} *
        q: * (в/через) $app [$oneWord] $changeSynsInf * $forget [$threeWords] {($pin/$code/$password) ([в/на/от] $card)} *
        q: * $changeSynsInf [$oneWord] (в/через) $app * {($pin/$code/$password) ([в/на/от] $card)} [$threeWords] $forget *
        q: * (в/через) $app [$oneWord] $changeSynsInf * {($pin/$code/$password) ([в/на/от] $card)} [$threeWords] $forget *
        
        q: * $cannot {($pin/$code/$password) ([в/на/от] $card)} [$oneWord] ((в/через) $app) *
        q: * $cannot {[$oneWord] ((в/через) $app)} {($pin/$code/$password) ([в/на/от] $card)} *
        q: * {($pin/$code/$password) ([в/на/от] $card)} {[$oneWord] ((в/через) $app)} $cannot *
        q: * ((в/через) $app) [$oneWord] {($pin/$code/$password) ([в/на/от] $card)} $cannot *
        
        q: * {$blocked $card} * ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) [$threeWords] ($pin/$code/$password) *
        q: * {$blocked $card} * ($pin/$code/$password) [$threeWords] ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) *
        q: * ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) [$threeWords] ($pin/$code/$password) * {$blocked $card} *
        q: * ($pin/$code/$password) [$threeWords] ($changeSynsInf/$learnInf/$recallInf/$forget/$notChanging) * {$blocked $card} *
        
        q: * $atm [$threeWords] $wrong [$oneWord] ($pin/$code/$password) *
        q: * $atm [$threeWords] ($pin/$code/$password) [$oneWord] $wrong *
        q: * $wrong [$oneWord] ($pin/$code/$password) [$threeWords] $atm *
        q: * ($pin/$code/$password) [$oneWord] $wrong [$threeWords] $atm *
        
        a: Это можно сделать в приложении:
            1. На экране "Мои деньги" в разделе "Карты" нажмите на нужную.
            2. Выберите вкладку "Настройки".
            3. Нажмите "Сменить пин-код".
            4. И введите комбинацию, удобную вам.
            5. Повторите ее.
        a: И все готово!
            Пин-код установлен, можно пользоваться. ☺ 
        go!: /GladToTalk