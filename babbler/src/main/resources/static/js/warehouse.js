$(document).ready(function ($) {
  $(".disable-click").click(function() {
        return false;
    });
    $("#StockItemsTable").DataTable({
        "order": [[ 2, "desc" ]]
    });
    $("#StockItemsAuditTable").DataTable({
        "order": [[ 2, "desc" ]]
    });

});