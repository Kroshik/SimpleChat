$(document).ready(function () {
    $('#add_contact_modal').css('margin-top', ($(window).innerHeight() - 364) / 2);
});

$(window).resize(function () {
    $('#add_contact_modal').css('margin-top', ($(window).innerHeight() - 364) / 2);
});

$('#modal_add_contact').on('hide.bs.modal', function () {
    $('#modal_window').css('z-index', '1060');
});

$('.input_field').on('focus', function () {
    $(this).parent().css('border-bottom', '2px solid #8b0ea3');
    $(this).parent().children('.input_label').css('transform', 'scale(.9)');
});

$('.input_field').on('blur', function () {
    $(this).parent().css('border-bottom', '1px solid #e6e6e6');
    if ($(this).val() === '') {
        $(this).parent().children('.input_label').css('transform', '');
    }
});

$('#btn_cancel').click(function () {
    $('.input_field').val('');
    $('.input_label').css('transform', '');
});