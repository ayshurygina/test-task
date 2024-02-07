theme: /ChangePinCode

    state: ChooseOption
        q: [~мой] ($pin/$code/$password)
        q: * ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) [$threeWords] ($pin/$code/$password) *
        q: * ($pin/$code/$password) [$threeWords] ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) *
        a: Сейчас расскажу порядок действий.
            Выберите, что именно планируете сделать:
            1. Поменять пароль для входа в приложение.
            2. Поменять PIN-код от карты.
            Пожалуйста, отправьте цифру, соответствующую вашему выбору.
        script:
            // FIXME В сценарии не указано что означает "Нет ответа". В течение какого времени?
            $reactions.timeout({ interval: "1 hour", targetState: "/StopSession" });
            
    
    state: App
        q: (1/$app) || fromState = "/ChangePinCode/ChooseOption", onlyThisState = true
        
        q: * ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) [$threeWords] [в] $app * || fromState = "/ChangePinCode/ChooseOption", onlyThisState = true
        q: * $app [$threeWords] ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) * || fromState = "/ChangePinCode/ChooseOption", onlyThisState = true
        
        q: [$threeWords] ($pin/$code/$password) [$threeWords] [в] $app [$threeWords] || fromState = "/ChangePinCode/ChooseOption", onlyThisState = true
        q: [$threeWords] [в] $app [$threeWords] ($pin/$code/$password) [$threeWords] || fromState = "/ChangePinCode/ChooseOption", onlyThisState = true
        
        q: ($pin/$code/$password) [$oneWord] [в] $app
        q: [в] $app [$oneWord] ($pin/$code/$password)
        
        q: * ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) [$threeWords] ($pin/$code/$password) [$threeWords] [в] $app *
        q: * ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) [$threeWords] [в] $app [$threeWords] ($pin/$code/$password) *
        q: * ($pin/$code/$password) [$threeWords] [в] $app [$threeWords] ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) *
        q: * ($pin/$code/$password) [$threeWords] ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) [$threeWords] [в] $app *
        q: * $app [$threeWords] ($pin/$code/$password) [$threeWords] ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) *
        q: * $app [$threeWords] ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) [$threeWords] ($pin/$code/$password) *
        
        q: * $tell [$oneWord] ($pin/$code/$password) [$oneWord] [в] $app *
        q: * $tell [$oneWord] [в] $app [$oneWord] ($pin/$code/$password) *
        q: * ($pin/$code/$password) [$oneWord] [в] $app [$oneWord] $tell *
        q: * $app [$oneWord] ($pin/$code/$password) [$oneWord] $tell *
        
        
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
        
        q: * ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) [$threeWords] [в/на/от] $card * || fromState = "/ChangePinCode/ChooseOption", onlyThisState = true
        q: * $card [$threeWords] ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) * || fromState = "/ChangePinCode/ChooseOption", onlyThisState = true
        
        q: [$threeWords] ($pin/$code/$password) [$threeWords] [в/на/от] $card [$threeWords] || fromState = "/ChangePinCode/ChooseOption", onlyThisState = true
        q: [$threeWords] [в/на/от] $card [$threeWords] ($pin/$code/$password) [$threeWords] || fromState = "/ChangePinCode/ChooseOption", onlyThisState = true
        
        q: ($pin/$code/$password) [$oneWord] [в/на/от] $card
        q: [в/на/от] $card [$oneWord] ($pin/$code/$password)
        
        q: * ($changeN/$installation) [$oneWord] ($pin/$code/$password) [$oneWord] [в/на/от] $card *
        q: * ($changeN/$installation) [$oneWord] [в/на/от] $card [$oneWord] ($pin/$code/$password) *
        q: * ($pin/$code/$password) [$oneWord] [в/на/от] $card [$oneWord] ($changeN/$installation)  *
        q: * ($pin/$code/$password) [$oneWord] ($changeN/$installation) [$oneWord] [в/на/от] $card *
        
        q: * ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) [$threeWords] ($pin/$code/$password) [$threeWords] [в/на/от] $card *
        q: * ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) [$threeWords] [в/на/от] $card [$threeWords] ($pin/$code/$password) *
        q: * ($pin/$code/$password) [$threeWords] [в/на/от] $card [$threeWords] ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) *
        q: * ($pin/$code/$password) [$threeWords] ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) [$threeWords] [в/на/от] $card *
        q: * $card [$threeWords] ($pin/$code/$password) [$threeWords] ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) *
        q: * $card [$threeWords] ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) [$threeWords] ($pin/$code/$password) *
        
        q: * $tell [$oneWord] ($pin/$code/$password) [$threeWords] [в/на/от] $card *
        q: * $tell [$oneWord] [в/на/от] $card [$threeWords] ($pin/$code/$password) *
        q: * ($pin/$code/$password) [$threeWords] [в/на/от] $card [$oneWord] $tell *
        q: * $card [$threeWords] ($pin/$code/$password) [$oneWord] $tell *
        
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
        
        q: * $changeSynsInf [$oneWord] ($pin/$code/$password) [$threeWords] {$cannot $enterInf ([в/на/от] $card)} *
        q: * ($pin/$code/$password) [$oneWord] $changeSynsInf [$threeWords] {$cannot $enterInf ([в/на/от] $card)} *
        q: * {$cannot $enterInf ([в/на/от] $card)} [$threeWords] $changeSynsInf [$oneWord] ($pin/$code/$password) *
        q: * {$cannot $enterInf ([в/на/от] $card)} [$threeWords] ($pin/$code/$password) [$oneWord] $changeSynsInf *
        
        q: * {$blocked $card} * ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) [$threeWords] ($pin/$code/$password) *
        q: * {$blocked $card} * ($pin/$code/$password) [$threeWords] ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) *
        q: * ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) [$threeWords] ($pin/$code/$password) * {$blocked $card} *
        q: * ($pin/$code/$password) [$threeWords] ([$cannot] $changeSynsInf/$learnInf/$recallInf/$checkInf/$forget/$notChanging) * {$blocked $card} *
        
        q: * $atm [$threeWords] $wrong [$oneWord] ($pin/$code/$password) *
        q: * $atm [$threeWords] ($pin/$code/$password) [$oneWord] $wrong *
        q: * $wrong [$oneWord] ($pin/$code/$password) [$threeWords] $atm *
        q: * ($pin/$code/$password) [$oneWord] $wrong [$threeWords] $atm *
        
        q: * ($getPast/$changed) [$oneWord] $card [$threeWords] $absent ($pin/$code/$password) *
        q: * ($getPast/$changed) [$oneWord] $card [$threeWords] ($pin/$code/$password) $absent *
        q: * $card [$oneWord] ($getPast/$changed) [$threeWords] $absent ($pin/$code/$password) *
        q: * $card [$oneWord] ($getPast/$changed) [$threeWords] ($pin/$code/$password) $absent *
        q: * $absent ($pin/$code/$password) [$threeWords] ($getPast/$changed) [$oneWord] $card  *
        q: * $absent ($pin/$code/$password) [$threeWords] $card [$oneWord] ($getPast/$changed) *
        q: * ($pin/$code/$password) $absent [$threeWords] $card [$oneWord] ($getPast/$changed) *
        q: * ($pin/$code/$password) $absent [$threeWords] ($getPast/$changed) [$oneWord] $card *
        
        q: * ($getPast/$changed) [$oneWord] $card * $installed [$threeWords] ($pin/$code/$password) *
        q: * ($getPast/$changed) [$oneWord] $card * ($pin/$code/$password) [$threeWords] $installed *
        q: * $card [$oneWord] ($getPast/$changed) * $installed [$threeWords] ($pin/$code/$password) *
        q: * $card [$oneWord] ($getPast/$changed) * ($pin/$code/$password) [$threeWords] $installed *
        q: * $installed [$threeWords] ($pin/$code/$password) * ($getPast/$changed) [$oneWord] $card *
        q: * $installed [$threeWords] ($pin/$code/$password) * $card [$oneWord] ($getPast/$changed) *
        q: * ($pin/$code/$password) [$threeWords] $installed * ($getPast/$changed) [$oneWord] $card *
        q: * ($pin/$code/$password) [$threeWords] $installed * $card [$oneWord] ($getPast/$changed) *
        a: Это можно сделать в приложении:
            1. На экране "Мои деньги" в разделе "Карты" нажмите на нужную.
            2. Выберите вкладку "Настройки".
            3. Нажмите "Сменить пин-код".
            4. И введите комбинацию, удобную вам.
            5. Повторите ее.
        a: И все готово!
            Пин-код установлен, можно пользоваться. ☺ 
        go!: /GladToTalk