$(document).ready(function () {
    $('.im_history_wrap').height($(window).innerHeight() - 190);
    /*Фон для главного окна*/
    choose_dialog_position();


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

    /*Hardcode для выделения диалога после перезагрузки*/
    $('.im_dialog').each(function () {
        var activeElement = $(this).attr('href');
        if (activeElement === location.pathname) {
            $(this).addClass('active');
        }
    });


    resizeHeights();
    dawn_offset_and_resize_elements();
});

$('#logo_menu').on('show.bs.dropdown', function () {
    $('#navbar_logo').css('background', 'url("../image/logo2.png") no-repeat');
});

$('#logo_menu').on('hide.bs.dropdown', function () {
    $('#navbar_logo').css('background', 'url("../image/logo.png") no-repeat');
});

$(window).resize(function () {
    /*Работает. Но проблемы могут быть тут при изменениях размера. Смотреть сюда*/
    /*Не все так просто с изменением окна. нужно учитывать размер формы отправки сообщений*/
    resizeHeights();

    dawn_offset_and_resize_elements();

    /*Фон для главного окна*/
    choose_dialog_position();

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

function choose_dialog_position() {
    /*Фон для главного окна*/
    if ($('#choose_dialog')) {
        $('#choose_dialog').css('padding-top', ($(window).innerHeight() - 50) / 3);
        /*Если захотим бахнуть другой цвет фона то это понадобится. оставлю опка тут*/
        // $('#choose_dialog').height( ($(window).innerHeight() - 50) / 3 * 2 );
    }
}

function dawn_offset_and_resize_elements() {
    var innerHeight = $(window).innerHeight();
    /*Высота элементов относительно текущего размера страницы*/
    $('.im_right_col_wrap').height(innerHeight - 50);
    $('.im_dialogs_col').height(innerHeight - 50 - $('.im_dialog_panel').outerHeight());

    /*Смещение вниз, если мало сообщений , чтобы было прижато к низу все равно*/
    var margin = innerHeight - 260 - $('.im_history_scrollbar_wrap').height();
    $('.im_history_messages').css('margin-top', margin > 0 ? margin : 0);

    /*Размер кнопки информации о пользователе*/
    $('.navbar_user').width($('.center_left').width() - $('.navbar_menu').width() - $('#navbar_search').width() - 62);

    /*Прокрутка автоматически вниз*/
    var element = document.getElementById('history_wrap');
    if (element != null) {
        element.scrollTop = element.scrollHeight - element.clientHeight;
    }
}

$('#send_form').submit(function (event) {
    var form = $('#send_form');
    var formData = new FormData(this);
    $.ajax({
        method: form.attr('method'),
        url: form.attr('action'),
        enctype: form.attr('enctype'),
        data: formData,
        mimeType: 'application/json',
        dataType: 'html',
        processData: false,
        contentType: false,
        context: $('.im_history_messages'),
        success: function (data) {
            var message = $.parseJSON(data);
            var textMessage = String(message.textMessage);

            this.append('<div id="selectMessage" class="chat_form" role="button" onclick="choseMessageForDelete(this)">\n' +
                '                                <img src="/img/avatar/men.jpg" alt="Avatar" style="width:100%;">\n' +
                '                                <input id="messageId" type="hidden" value=' + message.id + '>\n' +
                '                                <p >' + message.nameUser + '</p>\n' +
                '                                <div wrap="on">' + textMessage.replace(/[\n]/g, '<br/>') + '</div>\n' +
                '                                <p src=' + message.file + '></p>\n' +
                '                                <span class="time-left">' + message.dateMessage + '</span>\n' +
                '                            </div>');
            form.trigger("reset");
            $('.rich_message').html('');
            dawn_offset_and_resize_elements();
            resizeHeights();
        },
        error: function (jqXHR, exception) {
            if (jqXHR.status === 0) {
                alert('НЕ подключен к интернету!');
            } else if (jqXHR.status == 404) {
                alert('НЕ найдена страница запроса [404])');
            } else if (jqXHR.status == 500) {
                alert('НЕ найден домен в запросе [500].\n' + jqXHR.responseText);
            } else if (exception === 'parsererror') {
                alert("Ошибка в коде: \n" + jqXHR.responseText);
            } else if (exception === 'timeout') {
                alert('Не ответил на запрос.');
            } else if (exception === 'abort') {
                alert('Прерван запрос Ajax.');
            } else {
                alert('Неизвестная ошибка:\n' + jqXHR.responseText);
            }
        }
    });

    return false;
});
