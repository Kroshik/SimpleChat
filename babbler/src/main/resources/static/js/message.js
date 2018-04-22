var set = new Set();
$( ".chat_form" ).each(function(index) {
    $(this).on("click", function(){
       var clicked = $(this); // jQuery wrapper for clicked element
       var messageId = clicked.find("#messageId").val();
       if (set.has(messageId)) {
            set.delete(messageId);
       } else {
            set.add(messageId);
       }
       clicked.toggleClass("active");
    });
});

$( "#removeMessage" ).click(function(){
    $.ajax({
        url:"/deleteMessage",
        type: 'POST',
        data:  JSON.stringify({ "setDeleted": Array.from(set) }),
        dataType: "html",
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function(data){
            set.clear();
            $( ".chat_form.active" ).each(function(index) {
                   var clicked = $(this); // jQuery wrapper for clicked element
                   clicked.remove();
            });
            return false;
        }
    });
});
