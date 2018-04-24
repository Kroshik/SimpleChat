var set = new Set();
var countDelete = 0;
$(".chat_form").each(function (index) {
    $(this).on("click", function () {
        var clicked = $(this); // jQuery wrapper for clicked element
        if (clicked.hasClass('active')){
            countDelete--;
        } else {
            countDelete++;
        }

        if (countDelete>0){
            $('.im_media_attach').css('display', 'none');
            $('#removeMessage').css('display', 'block');
        }else {
            $('#removeMessage').css('display', 'none');
            $('.im_media_attach').css('display', 'block');
        }

        var messageId = clicked.find("#messageId").val();
        if (set.has(messageId)) {
            set.delete(messageId);
        } else {
            set.add(messageId);
        }
        clicked.toggleClass("active");
    });
});

$("#removeMessage").click(function () {
    $.ajax({
        url: "/deleteMessage",
        type: 'POST',
        data: JSON.stringify({"setDeleted": Array.from(set)}),
        dataType: "html",
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function (data) {
            set.clear();
            $(".chat_form.active").each(function (index) {
                var clicked = $(this); // jQuery wrapper for clicked element
                clicked.remove();
            });
            return false;
        }
    });
    location.reload();
});

/*Отправка формы или ее наполнение*/
$('.rich_message').keydown(function (e) {
    if (e.keyCode === 13 && !e.shiftKey) {
        var area = $('#input_message');
        var value = area.val();
        if (value !== "") {
            area.val(value.replace(/<br>/g, "\n"));
            $('#send_form').submit();
        }
        e.preventDefault();
    }
});


$('.rich_message').keyup(function (e) {
    if (e.keyCode === 13 && !e.shiftKey) {
        e.preventDefault();
        return;
    }
    var text = this.innerHTML;
    $('#input_message').val(text);
});

$('.im_submit_send').click(function (e) {
    var area = $('#input_message');
    var value = area.val();
    if (value !== "") {
        area.val(value.replace(/<br>/g, "\n"));
        $('#send_form').submit();
    }
    else {
        e.preventDefault();
        return;
    }
});

$('.rich_message').on('input', function () {
    resizeHeights();

    /*прокрутка вниз*/
    var element = document.getElementById('history_wrap');
    element.scrollTop = element.scrollHeight - element.clientHeight;
});


/*Устарело*/
/*Костыль в виде запоминания размера поля ввода*/
// var lastTextHeight = $(window).innerHeight() > 600 ? 50 : 20;


/*Изменение размера при написании собщения*/
function resizeHeights() {
    var textHeight = $('.rich_message').height();
    var diff = $(window).innerHeight() >= 600 ? 50 : 20;
    diff -= textHeight;
    $('.im_history_wrap').height(innerHeight - 190 + diff);
    var sendForm = $('.im_send_form_wrap');
    sendForm.height(textHeight + 47);


    /*Устарело*/

    // var lastHistoryHeight = $('.im_history_wrap').height();
    // var textHeight = $('.rich_message').height();
    // var diff = 0;
    // diff = lastTextHeight - textHeight;
    // lastTextHeight = textHeight;
    //
    // var innerHeight = $(window).innerHeight();
    // if (innerHeight >= 452) {
    //     $('.im_history_wrap').height(lastHistoryHeight + diff);
    // } else {
    //     /*HERE!!! костыль от проблемы когда при маленьком окне большой текст и его стираешь, чтобы не плыла верстка*/
    //     diff - 452 + innerHeight;
    //     $('.im_history_wrap').height(lastHistoryHeight - 452 + innerHeight + diff);
    // }
    //    sendForm.height(textHeight + 47);

}


/*Фича чтоб вставлял только текст без форматирования*/
$(document).on('paste', '[contenteditable]', function (e) {
    e.preventDefault();
    var text = (e.originalEvent || e).clipboardData.getData('text/plain');
    window.document.execCommand('insertText', false, text);
});


$('.icon_attach').click(function () {
   $('#mediaAttach').click();
});