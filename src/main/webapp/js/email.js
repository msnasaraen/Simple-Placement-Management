$(document).ready(function() {

	getUser();

	var $post_example = $('#mail');

	$('#submit_it')
			.click(
					function(e) {
						e.preventDefault();

						var jsObj = $post_example
								.serializeObject(), ajaxObj = {};

						ajaxObj = {
							type : "POST",
							url : "http://localhost:8081/placements/rest/email",
							data : JSON.stringify(jsObj),
							contentType : "application/json",
							error : function(jqXHR, textStatus,
									errorThrown) {
								console
										.log("Error "
												+ jqXHR
														.getAllResponseHeaders()
												+ " "
												+ errorThrown);
							},
							success : function(data) {
								var data1 = data.status;
								if (data1 == "success") {
									alert(data1);
								} else
									alert("user not exist");
								$('#div_ajaxResponse').text(
										data.status);

							},
							complete : function(XMLHttpRequest) {
							},
							dataType : "json"
						};

						$.ajax(ajaxObj);
					});


});

function getUser() {

	var d = new Date(), n = d.getTime();

	ajaxObj = {
		type : "GET",
		url : "http://localhost:8081/placements/rest/getUser",
		data : "ts=" + n,
		contentType : "application/json",
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR.responseText);
		},
		success : function(data) {
			// console.log(data);
			var html_string = "";
			if(data.status=="success")
				{
					$('#username').html(data.username);
				}
			else
				{
				window.open("student-register.html","_self");

				}

		},
		complete : function(XMLHttpRequest) {
			// console.log( XMLHttpRequest.getAllResponseHeaders() );
		},
		dataType : "json" // request JSON
	};

	return $.ajax(ajaxObj);

}




