$(document).ready(function() {
	
	getInventory();
	

});

function getInventory() {
	
	var d = new Date()
		, n = d.getTime();
	
	ajaxObj = {  
			type: "GET",
			url: "http://localhost:8081/HibernateWebapp/rest/logout", 
			data: "ts="+n,
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) { 
				//console.log(data);
				var html_string = "";
				window.open("student-register.html","_self");
			},
			complete: function(XMLHttpRequest) {
				//console.log( XMLHttpRequest.getAllResponseHeaders() );
				var html_string = "";
				window.open("student-register.html","_self");
			}, 
			dataType: "json" //request JSON
		};
		
	return $.ajax(ajaxObj);
}

