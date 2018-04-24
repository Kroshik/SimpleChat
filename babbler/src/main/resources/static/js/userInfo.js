var user_close;
var user_edit;
var user_cancel;

var edit_user_flag = false;
$(document).ready(function () {
    user_cancel = $('#edit_user_cancel').detach();
    var margin_top = ($(window).innerHeight() - 421) / 2;
    $('#modal-user').css('margin-top', margin_top > 0 ? margin_top : 0);
});

$(window).resize(function () {
    var margin_top = ($(window).innerHeight() - 421) / 2;
    $('#modal-user').css('margin-top', margin_top > 0 ? margin_top : 0);
});

$('#edit_user').click(function () {
    user_close = $('#user_close').detach();
    user_edit = $('#edit_user').detach();
    user_cancel.appendTo('#user_action');
    edit_user_flag = true;

});

$('#edit_user_cancel').click(function () {
    user_edit.appendTo('#user_action');
    user_close.appendTo('#user_action');
    user_cancel = $('#edit_user_cancel').detach();
    edit_user_flag = false;
});

$('#id-info').on('hide.bs.modal', function () {
    if (edit_user_flag === true) {
        user_edit.appendTo('#user_action');
        user_close.appendTo('#user_action');
        user_cancel = $('#edit_user_cancel').detach();
    }
    edit_user_flag = false;

    $('.additional_link').each(function () {
        $(this).css('display', 'none');
    });
    $('#more-link').parent().css('display', 'block');
});

$('#id-info').on('show.bs.modal', function () {
    /*Достаем инициалы*/
    var initials = $('.peer_modal_profile_name').html();
    var first = initials.split(" ")[0].substring(0, 1);
    var second = initials.split(" ")[1].substring(0, 1);
    $('#user-initials').html(first + second);
});

/*Переключалка уведомлений*/
$('.modal_section_toggle_wrap').click(function () {
    $('a.modal_section_toggle_wrap').toggleClass('checkbox_on');
});

/*Дополнительные ссылки*/
$('#more-link').click(function () {
    $('.additional_link').each(function () {
        $(this).css('display', 'block');
    });
    $('#more-link').parent().css('display', 'none');
});