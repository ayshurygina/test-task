patterns:
    $changeInf = (изменить/*менять/сменить/поменчть)
    $correctInf = (исправить/скорректировать/настроить)
    $createInf = (создать/установить/сделать/издать/поставить)
    $restoreInf = (восстановить/восмтановить)
    $resetInf = (сбросить/скинуть)
    
    $checkInf = (проверить/смотреть/посмотреть)
    $learnInf = (узнать/разузнать/выяснить)
    $recallInf = (вспомнить/припомнить)
    
    $changeSyns = ($changeInf/$correctInf/$createInf/$restoreInf/$resetInf)
    $verbForPin = ($changeSyns/$learnInf/$checkInf/$recallInf/(чтоб/чтобы) (сделал*/зделал*))
    
    $notInstalling = не устанавливает*
    $notChanging = не (*меняет/*меняется)
    
    $cannot = не (могу/получается/выходит/дает/удается)
    $coudnt = не (смог/смогла/получилось/не вышло/дали/дал/удалось)
    $forget = (забыл*/не (*помню/вспомнить))
    
    $tell = (подскажи*/скажи*)
    
    $enterInf = (зайти/войти)
    
    $blocked = (заблокировали/заблокирован*/заблокировалась)
    
    $installed = (установили/установлен*/установился)
    $lost = ([был*] (утерян/потерян/забыт)/потерялся/забылся)
    $changed = (поменяли/заменили/сменили)
    $changedForCard = ($changed/перевыпустили/поменялась/заменилась/сменилась/перевыпустилась)
    $changedForPin = ($changed/поменялся/заменился/сменился)
    
    $getInf = получить
    $getPast = получил*
    
    $wrong = (не (правильн*/корректн*/~верный/верно)/неправильн*/некорректн*/неверн*)
    
    $pin = (пин/pin/(пин/pin) (~код/кот)/пинкод*/пинкот*/pin cod*/pincod*/pinкод*/pinкот*/пинк код)
    $code = [(4/4х/4 x/четырех) значн*/четырехзначн*] ~код [доступа]
    $password = ~пароль
    
    $changeN = (~смена/~изменение)
    $installation = ~установка
    $control = ~управление
    
    $app = ([моб/мобильн*] ~приложение/(моб/мобильн*) ~банк)
    $appNom = ([моб/мобильн*] приложение/(моб/мобильн*) банк)
    $appGen = ([моб/мобильн*] приложения/(моб/мобильн*) банка)
    $appDat = ([моб/мобильн*] приложению/(моб/мобильн*) банку)
    
    $toOpenApp = ({([для/от] (входа [в]/запуска/открытия/разблокировки/активации)) $app}
        | {((при/на) (входе [в]/запуске/открытии/разблокировке/активации)) $app}
        | {([в/во] (вход/запуск/открытие/разблокировка/активация)) ((в/во) $app)})
    
    $forApp = ([от/для] $appGen/(в/на) $appNom/к $appDat)
    
    $card = [кредитн*/дебетов*] (~карта/карточк*)
    
    $threeWords = $oneWord [$oneWord [$oneWord]]
    
    $atm = ~банкомат
    
    $absent = (нет/нету/отсутствует)
    
    $need = (нужен/требуется/необходим)
    
    $me = (я/мы)
    $my = (~мой/~свой)
    
    $login = (~логин/логмн)
    
    $button = (кнопк*/кнопочк*)
    
    $know = (знаю/знаем)