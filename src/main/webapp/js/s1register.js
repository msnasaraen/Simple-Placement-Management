
$(document).ready(function() {
	//console.log("ready");
	
	var $post_example = $('#post_exampleas');
	
	$('#submit_it').click(function(e) {
		e.preventDefault();
		var jsObj = $post_example.serializeObject()
			, ajaxObj = {};
		
		alert(jsObj.cars);
		ajaxObj = {  
			type: "POST",
			url: "http://localhost:8081/placements/rest/sregister", 
			data: JSON.stringify(jsObj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
			},
			success: function(data) { 
				var data1=data.status;
					if(data1=="Success")
						{
					window.open("student-details.html","_self");
						}
					else
						alert("username already taken");
					$('#div_ajaxResponse').text( data.status );
				
			},
			complete: function(XMLHttpRequest) {
			}, 
			dataType: "json" 
		};
		
		$.ajax(ajaxObj);
	});
	

});

