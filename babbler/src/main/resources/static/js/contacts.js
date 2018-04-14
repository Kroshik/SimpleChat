$(document).ready(function () {
    $('#myModal_body').height($(window).innerHeight() - 247);
})
$(window).resize(function () {

    $('#myModal_body').height($(window).innerHeight() - 247);

    if ($(window).height() <= '248') {
        $('#myModal').css('margin-top', 43 - (248 - $(window).innerHeight())/2);
    }
});
