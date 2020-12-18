$(document)
    .ready(
        function() {
            // console.log("ready");


            var $post_example = $('#post_exampleadmin');

            $('#submit_it')
                .click(
                    function(e) {
                        e.preventDefault();

                        var jsObj = $post_example
                            .serializeObject(),
                            ajaxObj = {};

                        ajaxObj = {
                            type: "POST",
                            url: "http://localhost:8081/placements/rest/alogin",
                            data: JSON.stringify(jsObj),
                            contentType: "application/json",
                            error: function(jqXHR, textStatus,
                                errorThrown) {
                                console
                                    .log("Error " + jqXHR
                                        .getAllResponseHeaders() + " " + errorThrown);
                            },
                            success: function(data) {
                                var data1 = data.status;
                                if (data1 == "Success") {
                                    window.open("company-addDetails.html",
                                        "_self");
                                } else
                                    alert("user not exist");
                                $('#div_ajaxResponse').text(
                                    data.status);

                            },
                            complete: function(XMLHttpRequest) {},
                            dataType: "json"
                        };

                        $.ajax(ajaxObj);
                    });

        });