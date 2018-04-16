var close;
var edit;
var cancel;
var del;
var count = 0;

var edit_flag = false;
$(document).ready(function () {
    $('#myModal_body').height($(window).innerHeight() - 247);
    cancel = $('#edit_cancel').detach();
    del = $('#del_btn').detach();
});

$(window).resize(function () {
    $('#myModal_body').height($(window).innerHeight() - 247);
    if ($(window).height() <= '248') {
        $('#myModal').css('margin-top', 43 - (248 - $(window).innerHeight()) / 2);

    }

});

$('#clear').click(function () {

    $('.contacts_search_field').val('');

});

$('#edit_contact').click(function () {
    close = $('#contact_close').detach();
    edit = $('#edit_contact').detach();
    cancel.appendTo('#contact_action');
    del.appendTo('#footer_contact');
    $('#new_contact').css('visibility', 'hidden');
    edit_flag = true;
    $('#counts_delete').html(count);
});

$('#edit_cancel').click(function () {
    edit.appendTo('#contact_action');
    close.appendTo('#contact_action');
    cancel = $('#edit_cancel').detach();
    $('#del_btn').css('opacity', '0.65');
    del = $('#del_btn').detach();
    $('#new_contact').css('visibility', 'visible');
    edit_flag = false;
    $('.contact').removeClass('active');
    count = 0;
});

$('.contact').click(function () {
    if (edit_flag === true) {
        count = $('#counts_delete').html();

        if ($(this).hasClass('active')) {
            $(this).removeClass('active');
            count -= 1;
            $('#counts_delete').html(count);
        } else {
            $(this).addClass('active');
            count++;
            $('#counts_delete').html(count);
        }

        if (count >=1) {
            $('#del_btn').css('opacity', '1');
        }else {
            $('#del_btn').css('opacity', '0.65');
        }
    }
});

$('#new_contact').click(function () {
   $('#modal_window').css('z-index', '1030');
});

$('#modal_contacts').on('hide.bs.modal', function () {
    if (edit_flag === true){

        edit.appendTo('#contact_action');
        close.appendTo('#contact_action');
        cancel = $('#edit_cancel').detach();
        $('#del_btn').css('opacity', '0.65');
        del = $('#del_btn').detach();
        $('#new_contact').css('visibility', 'visible');
        $('.contact').removeClass('active');
    }
    edit_flag = false;
    count = 0;
});

/*Пока хз как сделать через функцию, т.к. переменные не инициализированны и свойсва прикрепить у переменных нет

function removeClassActive() {
    edit.appendTo('#contact_action');
    close.appendTo('#contact_action');
    cancel = $('#edit_cancel').detach();
    $('#del_btn').css('opacity', '0.65');
    del = $('#del_btn').detach();
    $('#new_contact').css('visibility', 'visible');
    edit_flag = false;
    $('.contact').removeClass('active');
    count = 0;
}*/
