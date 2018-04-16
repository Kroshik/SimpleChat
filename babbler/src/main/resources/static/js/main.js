$('#logo_menu').on('show.bs.dropdown', function () {
   $('#navbar_logo').css('background', 'url("../image/logo2.png") no-repeat');
});

$('#logo_menu').on('hide.bs.dropdown', function () {
    $('#navbar_logo').css('background', 'url("../image/logo.png") no-repeat');
});