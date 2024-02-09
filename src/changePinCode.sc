theme: /ChangePinCode

    state: ChooseOption
        q!: [$my] [$login] [и] ($pin/$code/$password)
        q!: {~какой ($pin/$code/$password)}
        
        q!: * {~какой (у (меня/нас)) ($pin/$code/$password)} *
        
        q!: * как {[мне/нам/я могу/мы можем] $getInf ([$login] [и] ($pin/$code/$password))} *
        
        q!: ($changeN/$control/$installation) [$oneWord] [$my] [$login] [и] ($pin/$code/$password)
        q!: [$my] [$login] [и] ($pin/$code/$password) [$oneWord] ($changeN/$control/$installation)
        
        q!: * {($cannot/$coudnt) $verbForPin ($pin/$code/$password)} *
        q!: * {никак (не $verbForPin) ($pin/$code/$password)} *
        
        q!: * ($changedForPin/$lost/$installed) [$oneWord] [$my] [$login] [и] ($pin/$code/$password) *
        q!: * ($pin/$code/$password) [$oneWord] ($changedForPin/$lost/$installed) *
        
        q!: * ([как] $verbForPin/$forget/$notInstalling/$notChanging) [$oneWord] [$my] [$login] [и] ($pin/$code/$password) *
        q!: * ($pin/$code/$password) [$oneWord] ([как] $verbForPin/$forget/$notInstalling/$notChanging) *
        a: Сейчас расскажу порядок действий.
            Выберите, что именно планируете сделать:
            1. Поменять пароль для входа в приложение.
            2. Поменять PIN-код от карты.
            Пожалуйста, отправьте цифру, соответствующую вашему выбору.
        script:
            // FIXME В сценарии не указано что означает "Нет ответа". В течение какого времени?
            $reactions.timeout({ interval: "1 hour", targetState: "/StopSession" });
            
    
        state: App
            q: (1/$app) 
            
            q: [$threeWords] ([как] $verbForPin/$forget/$notInstalling/$notChanging) [$threeWords] {($pin/$code/$password) ($forApp/$toOpenApp)} [$threeWords] 
            q: [$threeWords] {($pin/$code/$password) ($forApp/$toOpenApp)} [$threeWords] ([как] $verbForPin/$forget/$notInstalling/$notChanging) [$threeWords]
            
            q: [$threeWords] ($pin/$code/$password) [$threeWords] {($pin/$code/$password) ($forApp/$toOpenApp)} [$threeWords] 
            q: [$threeWords] {($pin/$code/$password) ($forApp/$toOpenApp)} [$threeWords] ($pin/$code/$password) [$threeWords] 
            
            q!: {($pin/$code/$password) ($forApp/$toOpenApp)}
            q!: {~какой ($pin/$code/$password) ($forApp/$toOpenApp)}
            
            q!: * {~какой (у (меня/нас)) ($pin/$code/$password) ($forApp/$toOpenApp)} *
            
            q!: * как {[мне/нам/я могу/мы можем] $getInf ($pin/$code/$password) ($forApp/$toOpenApp)} *
            
            q!: * {($cannot/$coudnt) $verbForPin ($pin/$code/$password) ($forApp/$toOpenApp)} *
            q!: * {никак (не $verbForPin) ($pin/$code/$password) ($forApp/$toOpenApp)} *
            
            q!: * ($changedForPin/$lost/$installed) [$oneWord] {($pin/$code/$password) ($forApp/$toOpenApp)} *
            q!: * {($pin/$code/$password) ($forApp/$toOpenApp)} [$oneWord] ($changedForPin/$lost/$installed) *
            
            q!: * ($changeN/$control/$installation) [$oneWord] {($pin/$code/$password) ($forApp/$toOpenApp)} *
            q!: * {($pin/$code/$password) ($forApp/$toOpenApp)} [$oneWord] ($changeN/$control/$installation) *
            
            q!: * ([как] $verbForPin/$forget/$notInstalling/$notChanging) [$threeWords] {($pin/$code/$password) $forApp} *
            q!: * {($pin/$code/$password) $forApp} [$threeWords] ([как] $verbForPin/$forget/$notInstalling/$notChanging) *
            
            q!: * ([как] $verbForPin/$forget/$notInstalling/$notChanging) [$threeWords] ($pin/$code/$password) [$threeWords] $toOpenApp *
            q!: * ([как] $verbForPin/$forget/$notInstalling/$notChanging) [$threeWords] $toOpenApp [$threeWords] ($pin/$code/$password) *
            q!: * ($pin/$code/$password) [$threeWords] $toOpenApp [$threeWords] ([как] $verbForPin/$forget/$notInstalling/$notChanging) *
            q!: * ($pin/$code/$password) [$threeWords] ([как] $verbForPin/$forget/$notInstalling/$notChanging) [$threeWords] $toOpenApp *
            q!: * $toOpenApp [$threeWords] ($pin/$code/$password) [$threeWords] ([как] $verbForPin/$forget/$notInstalling/$notChanging) *
            q!: * $toOpenApp [$threeWords] ([как] $verbForPin/$forget/$notInstalling/$notChanging) [$threeWords] ($pin/$code/$password) *
            
            q!: * $tell [$oneWord] {($pin/$code/$password) $forApp} *
            q!: * {($pin/$code/$password) $forApp} [$oneWord] $tell *
            
            q!: * $tell [$oneWord] ($pin/$code/$password) [$oneWord] $toOpenApp *
            q!: * $tell [$oneWord] $toOpenApp [$oneWord] ($pin/$code/$password) *
            q!: * ($pin/$code/$password) [$oneWord] $toOpenApp [$oneWord] $tell *
            q!: * $toOpenApp [$oneWord] ($pin/$code/$password) [$oneWord] $tell *
            
            q!: * (вопрос* [задать]/спросить/спрошу/спросим/$learnInf) {(у (меня/нас)) $installed ($pin/$code/$password) ($forApp/$toOpenApp)} *
            
            q!: * (вопрос* [задать]/спросить/спрошу/спросим/$learnInf) [насчет/по [поводу]] $app [$threeWords] ([как] $verbForPin/$forget/$notInstalling/$notChanging) [$threeWords] ($pin/$code/$password) *
            q!: * (вопрос* [задать]/спросить/спрошу/спросим/$learnInf) [насчет/по [поводу]] $app [$threeWords] ($pin/$code/$password) [$threeWords] ([как] $verbForPin/$forget/$notInstalling/$notChanging) *
            
            q!: * $app * {((для/от) (входа [в]/запуска/открытия/разблокировки/активации)) [мне/нам] $need ($pin/$code/$password)} *
            q!: * {((для/от) (входа [в]/запуска/открытия/разблокировки/активации)) [мне/нам] $need ($pin/$code/$password)} * $app * 
            
            q!: {(смени*/измени*) {($pin/$code/$password) ($forApp/$toOpenApp)}}
            
            q!: * {((при/на) (странице входа/входе [в]/запуске/открытии/разблокировке/активации)) ([в] $app) $absent $button {([чтоб/чтобы] $resetInf/[для] ~сброс) ($pin/$code/$password)}} *
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
            q: (2/$card) 
            
            q: [$threeWords] ([как] $verbForPin/$forget/$notInstalling/$notChanging) [$threeWords] [в/на/от/по] [$my] $card [$threeWords]
            q: [$threeWords] $card [$threeWords] ([как] $verbForPin/$forget/$notInstalling/$notChanging) [$threeWords] 
            
            q: [$threeWords] ($pin/$code/$password) [$threeWords] [в/на/от/по] [$my] $card [$threeWords] 
            q: [$threeWords] [в/на/от/по] [$my] $card [$threeWords] ($pin/$code/$password) [$threeWords] 
            
            q!: ($pin/$code/$password) [$oneWord] [в/на/от/по] [$my] $card
            q!: [в/на/от/по] [$my] $card [$oneWord] ($pin/$code/$password)
            
            q!: {~какой ($pin/$code/$password) ([в/на/от/по] [$my] $card)}
            
            q!: * {~какой (у (меня/нас)) ($pin/$code/$password) ([в/на/от/по] [$my] $card)} *
            
            q!: * как {[мне/нам/я могу/мы можем] $getInf ($pin/$code/$password) ([в/на/от/по] [$my] $card)} *
            
            q!: * {($cannot/$coudnt) $verbForPin ($pin/$code/$password) ([в/на/от/по] [$my] $card)} *
            q!: * {никак (не $verbForPin) ($pin/$code/$password) ([в/на/от/по] [$my] $card)} *
            
            q!: * ($changedForPin/$lost/$installed) [$oneWord] ($pin/$code/$password) [$oneWord] [в/на/от/по] [$my] $card *
            q!: * ($changedForPin/$lost/$installed) [$oneWord] [в/на/от/по] [$my] $card [$oneWord] ($pin/$code/$password) *
            q!: * ($pin/$code/$password) [$oneWord] [в/на/от/по] [$my] $card [$oneWord] ($changedForPin/$lost/$installed)  *
            q!: * ($pin/$code/$password) [$oneWord] ($changedForPin/$lost/$installed) [$oneWord] [в/на/от/по] [$my] $card *
            
            q!: * ($changeN/$control/$installation) [$oneWord] ($pin/$code/$password) [$oneWord] [в/на/от/по] [$my] $card *
            q!: * ($changeN/$control/$installation) [$oneWord] [в/на/от/по] [$my] $card [$oneWord] ($pin/$code/$password) *
            q!: * ($pin/$code/$password) [$oneWord] [в/на/от/по] [$my] $card [$oneWord] ($changeN/$control/$installation)  *
            q!: * ($pin/$code/$password) [$oneWord] ($changeN/$control/$installation) [$oneWord] [в/на/от/по] [$my] $card *
            
            q!: * ([как] $verbForPin/$forget/$notInstalling/$notChanging) [$threeWords] ($pin/$code/$password) [$threeWords] [в/на/от/по] [$my] $card *
            q!: * ([как] $verbForPin/$forget/$notInstalling/$notChanging) [$threeWords] [в/на/от/по] [$my] $card [$threeWords] ($pin/$code/$password) *
            q!: * ($pin/$code/$password) [$threeWords] [в/на/от/по] [$my] $card [$threeWords] ([как] $verbForPin/$forget/$notInstalling/$notChanging) *
            q!: * ($pin/$code/$password) [$threeWords] ([как] $verbForPin/$forget/$notInstalling/$notChanging) [$threeWords] [в/на/от/по] [$my] $card *
            q!: * $card [$threeWords] ($pin/$code/$password) [$threeWords] ([как] $verbForPin/$forget/$notInstalling/$notChanging) *
            q!: * $card [$threeWords] ([как] $verbForPin/$forget/$notInstalling/$notChanging) [$threeWords] ($pin/$code/$password) *
            
            q!: * $tell [$oneWord] ($pin/$code/$password) [$threeWords] [в/на/от/по] [$my] $card *
            q!: * $tell [$oneWord] [в/на/от/по] [$my] $card [$threeWords] ($pin/$code/$password) *
            q!: * ($pin/$code/$password) [$threeWords] [в/на/от/по] [$my] $card [$oneWord] $tell *
            q!: * $card [$threeWords] ($pin/$code/$password) [$oneWord] $tell *
            
            q!: * (вопрос* [задать]/спросить/спрошу/спросим/$learnInf) {(у (меня/нас)) $installed ($pin/$code/$password) ([в/на/от/по] [$my] $card)} *
            
            q!: * (вопрос* [задать]/спросить/спрошу/спросим/$learnInf) [насчет/по [поводу]] [$my] $card [$threeWords] ([как] $verbForPin/$forget/$notInstalling/$notChanging) [$threeWords] ($pin/$code/$password) *
            q!: * (вопрос* [задать]/спросить/спрошу/спросим/$learnInf) [насчет/по [поводу]] [$my] $card [$threeWords] ($pin/$code/$password) [$threeWords] ([как] $verbForPin/$forget/$notInstalling/$notChanging) *
            
            q!: * {$changeSyns {($pin/$code/$password) ([в/на/от/по] [$my] $card)} ((в/через) $app)} *
            
            q!: * $forget [$threeWords] {($pin/$code/$password) ([в/на/от/по] [$my] $card)} * $changeSyns [$oneWord] (в/через) $app *
            q!: * $forget [$threeWords] {($pin/$code/$password) ([в/на/от/по] [$my] $card)} * (в/через) $app [$oneWord] $changeSyns *
            q!: * {($pin/$code/$password) ([в/на/от/по] [$my] $card)} [$threeWords] $forget * $changeSyns [$oneWord] (в/через) $app *
            q!: * {($pin/$code/$password) ([в/на/от/по] [$my] $card)} [$threeWords] $forget * (в/через) $app [$oneWord] $changeSyns *
            q!: * $changeSyns [$oneWord] (в/через) $app * $forget [$threeWords] {($pin/$code/$password) ([в/на/от/по] [$my] $card)} *
            q!: * (в/через) $app [$oneWord] $changeSyns * $forget [$threeWords] {($pin/$code/$password) ([в/на/от/по] [$my] $card)} *
            q!: * $changeSyns [$oneWord] (в/через) $app * {($pin/$code/$password) ([в/на/от/по] [$my] $card)} [$threeWords] $forget *
            q!: * (в/через) $app [$oneWord] $changeSyns * {($pin/$code/$password) ([в/на/от/по] [$my] $card)} [$threeWords] $forget *
            
            q!: * ($cannot/$coudnt) {($pin/$code/$password) ([в/на/от/по] [$my] $card)} [$oneWord] ((в/через) $app) *
            q!: * ($cannot/$coudnt) {[$oneWord] ((в/через) $app)} {($pin/$code/$password) ([в/на/от/по] [$my] $card)} *
            q!: * {($pin/$code/$password) ([в/на/от/по] [$my] $card)} {[$oneWord] ((в/через) $app)} ($cannot/$coudnt) *
            q!: * ((в/через) $app) [$oneWord] {($pin/$code/$password) ([в/на/от/по] [$my] $card)} ($cannot/$coudnt) *
            
            q!: * $changeSyns [$oneWord] ($pin/$code/$password) [$threeWords] {($cannot/$coudnt) $enterInf ([в/на/от/по] [$my] $card)} *
            q!: * ($pin/$code/$password) [$oneWord] $changeSyns [$threeWords] {($cannot/$coudnt) $enterInf ([в/на/от/по] [$my] $card)} *
            q!: * {($cannot/$coudnt) $enterInf ([в/на/от/по] [$my] $card)} [$threeWords] $changeSyns [$oneWord] ($pin/$code/$password) *
            q!: * {($cannot/$coudnt) $enterInf ([в/на/от/по] [$my] $card)} [$threeWords] ($pin/$code/$password) [$oneWord] $changeSyns *
            
            q!: * {$blocked $card} * ([как] $verbForPin/$forget/$notInstalling/$notChanging) [$threeWords] ($pin/$code/$password) *
            q!: * {$blocked $card} * ($pin/$code/$password) [$threeWords] ([как] $verbForPin/$forget/$notInstalling/$notChanging) *
            q!: * ([как] $verbForPin/$forget/$notInstalling/$notChanging) [$threeWords] ($pin/$code/$password) * {$blocked $card} *
            q!: * ($pin/$code/$password) [$threeWords] ([как] $verbForPin/$forget/$notInstalling/$notChanging) * {$blocked $card} *
            
            q!: * $atm [$threeWords] $wrong [$oneWord] ($pin/$code/$password) *
            q!: * $atm [$threeWords] ($pin/$code/$password) [$oneWord] $wrong *
            q!: * $wrong [$oneWord] ($pin/$code/$password) [$threeWords] $atm *
            q!: * ($pin/$code/$password) [$oneWord] $wrong [$threeWords] $atm *
            
            q!: * ($getPast/$changedForCard) [$oneWord] $card [$threeWords] $absent ($pin/$code/$password) *
            q!: * ($getPast/$changedForCard) [$oneWord] $card [$threeWords] ($pin/$code/$password) $absent *
            q!: * $card [$oneWord] ($getPast/$changedForCard) [$threeWords] $absent ($pin/$code/$password) *
            q!: * $card [$oneWord] ($getPast/$changedForCard) [$threeWords] ($pin/$code/$password) $absent *
            q!: * $absent ($pin/$code/$password) [$threeWords] ($getPast/$changedForCard) [$oneWord] $card  *
            q!: * $absent ($pin/$code/$password) [$threeWords] $card [$oneWord] ($getPast/$changedForCard) *
            q!: * ($pin/$code/$password) $absent [$threeWords] $card [$oneWord] ($getPast/$changedForCard) *
            q!: * ($pin/$code/$password) $absent [$threeWords] ($getPast/$changedForCard) [$oneWord] $card *
            
            q!: * ($getPast/$changedForCard) [$oneWord] [$my] $card * $installed [$threeWords] ($pin/$code/$password) *
            q!: * ($getPast/$changedForCard) [$oneWord] [$my] $card * ($pin/$code/$password) [$threeWords] $installed *
            q!: * $card [$oneWord] ($getPast/$changedForCard) * $installed [$threeWords] ($pin/$code/$password) *
            q!: * $card [$oneWord] ($getPast/$changedForCard) * ($pin/$code/$password) [$threeWords] $installed *
            q!: * $installed [$threeWords] ($pin/$code/$password) * ($getPast/$changedForCard) [$oneWord] [$my] $card *
            q!: * $installed [$threeWords] ($pin/$code/$password) * $card [$oneWord] ($getPast/$changedForCard) *
            q!: * ($pin/$code/$password) [$threeWords] $installed * ($getPast/$changedForCard) [$oneWord] [$my] $card *
            q!: * ($pin/$code/$password) [$threeWords] $installed * $card [$oneWord] ($getPast/$changedForCard) *
        
            q!: * (оплат*/заплат*) [$oneWord] $card * ($pin/$code/$password) [$threeWords] (не (подошел/сработал)/$wrong) *
            q!: * (оплат*/заплат*) [$oneWord] $card * (не (подошел/сработал)/$wrong) [$threeWords] ($pin/$code/$password) *
            
            q!: * как [я могу/мы можем/мне/нам] [его] проверить * {$me $know ($pin/$code/$password) ([в/на/от/по] [$my] $card)} *
            q!: * {$me $know ($pin/$code/$password) ([в/на/от/по] [$my] $card)} * как [я могу/мы можем/мне/нам] [его] проверить *
            a: Это можно сделать в приложении:
                1. На экране "Мои деньги" в разделе "Карты" нажмите на нужную.
                2. Выберите вкладку "Настройки".
                3. Нажмите "Сменить пин-код".
                4. И введите комбинацию, удобную вам.
                5. Повторите ее.
            a: И все готово!
                Пин-код установлен, можно пользоваться. ☺ 
            go!: /GladToTalk