theme: /ChangePinCode

    state: ChooseOption
        q: * {($changeInf/$correctInf) * ($pin/$code)} *
        a: Сейчас расскажу порядок действий.
            Выберите, что именно планируете сделать:
            1. Поменять пароль для входа в приложение.
            2. Поменять PIN-код от карты.
            Пожалуйста, отправьте цифру, соответствующую вашему выбору.
        script:
            // FIXME Не указано что значит "Нет ответа". В течение какого времени?
            $reactions.timeout({ interval: "1 hour", targetState: "/StopSession" });
            
    
    state: App
        q: 1 || fromState = "/ChangePinCode/ChooseOption", onlyThisState = true
        q: $app || fromState = "/ChangePinCode/ChooseOption", onlyThisState = true
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
        q: 2 || fromState = "/ChangePinCode/ChooseOption", onlyThisState = true
        q: $card || fromState = "/ChangePinCode/ChooseOption", onlyThisState = true
        a: Это можно сделать в приложении:
            1. На экране "Мои деньги" в разделе "Карты" нажмите на нужную.
            2. Выберите вкладку "Настройки".
            3. Нажмите "Сменить пин-код".
            4. И введите комбинацию, удобную вам.
            5. Повторите ее.
        a: И все готово!
            Пин-код установлен, можно пользоваться. ☺ 
        go!: /GladToTalk