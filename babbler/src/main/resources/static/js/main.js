$(document).ready(function($) {
    $(".clickable-row").click(function() {
        window.location = $(this).attr("href");
    });
    $(".btn-edit").click(function() {
        var form = $(this).closest(".editable-form");
        if (form.hasClass("readonly")) {
            form.find("input[readonly]").removeAttr("readonly");
            form.find(".btn-save").removeClass("hidden");
            form.removeClass("readonly")
        } else {
            form.addClass("readonly");
            form.find("input").prop("readonly", true);
            form.find(".btn-save").addClass("hidden");
        }
    });
    $('.selectpicker').selectpicker({
        style: 'btn-info',
        size: 4
    });
});