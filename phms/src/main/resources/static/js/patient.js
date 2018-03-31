$(document).ready(function ($) {
    var ctxExp = $("<canvas/>");
    ctxExp.appendTo("#experiments-canvas-holder");
    var experimentsConditionChart = new Chart(ctxExp, {
        type: 'line',
        data: {
            labels: conditionHistory.map(function (value) { return value.createdAt }),
            datasets: [{
                label: "Mental",
                fill: false,
                data: conditionHistory.map(function (value) { return value.mental }),
                borderColor: "blue"
            }, {
                label: "Physical",
                fill: false,
                data: conditionHistory.map(function (value) { return value.physical }),
                borderColor: "green"
            }]
        },
        options: {}
    });
    var ctxCond = $("<canvas/>");
    ctxExp.appendTo("#experiments-canvas-holder");
    var experimentsConditionChart = new Chart(ctxExp, {
        type: 'line',
        data: {
            labels: getLabels(conditionHistory),
            datasets: getDatasets(conditionHistory)
        },
        options: {}
    });
    $("#table-patient-condition-history").DataTable({
        "order": [[ 2, "desc" ]]
    });
    $("#table-patient-experiments").DataTable({
        "order": [[ 2, "desc" ]]
    });
    $('#experiment-picker').on('change', function(){
        var selected = $(this).find("option:selected").index();
        if (selected === 0) {
            experimentsConditionChart.config.data.datasets = getDatasets(conditionHistory);
            experimentsConditionChart.config.data.labels = getLabels(conditionHistory);
        } else {
            var startDate = experiments[selected].startDate;
            var endDate = experiments[selected].endDate;
            var experimentContitions = conditionHistory
                .filter(function (value) { return value.createdAt >= startDate || value.createdAt <= endDate});
            experimentsConditionChart.config.data.datasets = getDatasets(experimentContitions);
            experimentsConditionChart.config.data.labels = getLabels(experimentContitions);
        }
        experimentsConditionChart.update();
    });
});

function getDatasets(data) {
    return [{
        label: "Mental",
        fill: false,
        data: data.map(function (value) { return value.mental }),
        borderColor: "lightblue"
    }, {
        label: "Physical",
        fill: false,
        data: data.map(function (value) { return value.physical }),
        borderColor: "pink"
    }];
}

function getLabels(data) {
    return data.map(function (value) { return value.createdAt });
}