$(document).ready(function () {
    var innerHeight = $(window).innerHeight();

    $('.im_right_col_wrap').height(innerHeight - 50);
    $('.im_dialogs_col').height(innerHeight - 50 - $('.im_dialog_panel').outerHeight());

    $('.im_history_wrap').height(innerHeight - 190);

    var margin = innerHeight - 260 - $('.im_history_scrollbar_wrap').height();
    $('.im_history_messages').css('margin-top', margin > 0 ? margin : 0);

    /*Размер кнопки информации о пользователе*/
    $('.navbar_user').width($('.center_left').width() - $('.navbar_menu').width() - $('#navbar_search').width() - 60);

    /*прокрутка вниз*/
    var element = document.getElementById('history_wrap');
    element.scrollTop = element.scrollHeight - element.clientHeight;

    /*Random background*/
    /*====================================================================================================*/
    $('.peer_initials').each(function () {
        var c = '#' + rand() + rand() + rand();
        $(this).css('background', c);

        /*Достаем инициалы*/
        if ($(this).hasClass('im_dialog_photo')) {
            var initials = $(this).parent().siblings('div.im_dialog_message_wrap').children('.im_dialog_peer').children('span').html();
            var first = initials.split(" ")[0].substring(0, 1);
            var second = initials.split(" ")[1].substring(0, 1);
            $(this).html(first + second);
        }
    });
});

$('#logo_menu').on('show.bs.dropdown', function () {
    $('#navbar_logo').css('background', 'url("../image/logo2.png") no-repeat');
});

$('#logo_menu').on('hide.bs.dropdown', function () {
    $('#navbar_logo').css('background', 'url("../image/logo.png") no-repeat');
});

$(window).resize(function () {
    var innerHeight = $(window).innerHeight();
    /*Высота элементов относительно текущего размера страницы*/
    $('.im_right_col_wrap').height(innerHeight - 50);
    $('.im_dialogs_col').height(innerHeight - 50 - $('.im_dialog_panel').outerHeight());

    /*Размер кнопки информации о пользователе*/
    $('.navbar_user').width($('.center_left').width() - $('.navbar_menu').width() - $('#navbar_search').width() - 60);

    /*Не все так просто с изменением окна. нужно учитывать размер формы отправки сообщений*/
    /*Работает. Но проблемы могут быть тут при изменениях размера. Смотреть сюда*/
    resizeHeights();

    /*Смещение вниз, если мало сообщений , чтобы было прижато к низу все равно*/
    var margin = innerHeight - 260 - $('.im_history_scrollbar_wrap').height();
    $('.im_history_messages').css('margin-top', margin > 0 ? margin : 0);

    /*Прокрутка автоматически вниз*/
    var element = document.getElementById('history_wrap');
    element.scrollTop = element.scrollHeight - element.clientHeight;

});

/*очиста формы поиска*/
$('#clear-dialogs-search').click(function () {

    $('#dialog-search').val('');

});

/*Фокусировка при нажатии поиска на топбаре*/
$('#navbar_search').click(function () {

    $('#dialog-search').focus();

});

/*переключение диалогов*/
$('.leftnav .contact').click(function () {
    $('.leftnav .contact').removeClass('active');
    $(this).addClass('active');
});


/*random background for avatar*/
/*====================================================================================================*/
function rand() {
    var c = parseInt(Math.random() * 255).toString(16);
    return ("" + c).length == 1 ? '0' + c : c;

};