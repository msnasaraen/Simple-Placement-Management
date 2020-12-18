$(document).ready(function() {

    getUser();
    getInventory();


});

function getUser() {

    var d = new Date(),
        n = d.getTime();

    ajaxObj = {
        type: "GET",
        url: "http://localhost:8081/placements/rest/getUser",
        data: "ts=" + n,
        contentType: "application/json",
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR.responseText);
        },
        success: function(data) {
            // console.log(data);
            var html_string = "";
            if (data.status == "success") {
                $('#username').html(data.username);
            } else {
                window.open("student-register.html", "_self");

            }

        },
        complete: function(XMLHttpRequest) {
            // console.log( XMLHttpRequest.getAllResponseHeaders() );
        },
        dataType: "json" // request JSON
    };

    return $.ajax(ajaxObj);

}

function getInventory() {

    var d = new Date(),
        n = d.getTime();

    ajaxObj = {
        type: "GET",
        url: "http://localhost:8081/placements/rest/allcompanies",
        data: "ts=" + n,
        contentType: "application/json",
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR.responseText);
        },
        success: function(data) {
            //console.log(data);
            var html_string = "";

            $.each(data, function(index1, val1) {
                //console.log(val1);

                html_string = html_string + templateGetInventory(val1);
            });

            $('#content').html("<br><br><table border='1'> 	<p id='userpageheading' value='SignUp'> Details</p> <tr><td id='image'>Name</td><td id='image'>Branch</td><td id='image'>CGPA</td><td id='image'>Date of Visit</td></tr>" + html_string + "</table>");
        },
        complete: function(XMLHttpRequest) {
            //console.log( XMLHttpRequest.getAllResponseHeaders() );
        },
        dataType: "json" //request JSON
    };

    return $.ajax(ajaxObj);
}

function templateGetInventory(param) {
    return '<tr>' +
        '<td ' + 'id=' + 'detail' + '>' + param.name + '</td>' +
        '<td ' + 'id=' + 'detail' + '>' + param.branch + '</td>' +
        '<td ' + 'id=' + 'detail' + '>' + param.cgpa + '</td>' +
        '<td ' + 'id=' + 'detail' + '>' + param.date + '</td>' +
        '</tr>';
}