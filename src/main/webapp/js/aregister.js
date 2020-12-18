$(document)
    .ready(
        function() {
            // console.log("ready");


            var $post_example = $('#post_exampleadminreg');

            $('#submit_it')
                .click(
                    function(e) {
                        e.preventDefault();

                        var jsObj = $post_example
                            .serializeObject(),
                            ajaxObj = {};

                        ajaxObj = {
                            type: "POST",
                            url: "http://localhost:8081/placements/rest/aregister",
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

                                window.open("company-addDetails.html",
                                    "_self");




                            },
                            complete: function(XMLHttpRequest) {},
                            dataType: "json"
                        };

                        $.ajax(ajaxObj);
                    });

        });