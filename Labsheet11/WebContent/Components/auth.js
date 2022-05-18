$(document).ready(function() {
	$("#alertError").text(""); 
    $('#alertError').hide();
})

//Login onclick
$(document).on("click", "#btnLogin", function(event) {
	
	// Clear alerts---------------------
	$("#alertError").text(""); 
	$("#alertError").hide();
	
	// Form validation
	var status = validateLoginForm(); 
	
	// if not valid
	if (status != true) {
		$("#alertError").text(status); 
		$("#alertError").show(); 
		return;
	}
	
	// If valid send formdata to servlet
	$.ajax({
   		url : "AuthAPI",
   		type : "POST",
		data : $("#formLogin").serialize(), 
		dataType : "text",
		complete : function(response, status) {
			onLoginComplete(response.responseText, status); 
		}
	}); 
});

function onLoginComplete(response, status) {
	
	if (status == "success") {
		//parsing response to json
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			// Redirect the valid user-----------------
			document.location = "items.jsp"; 
			
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show(); 
		}
		
	} else if (status == "error") {
		$("#alertError").text("Error while login."); 
		$("#alertError").show();
		
	} else {
		$("#alertError").text("Unknown error while login.");
		$("#alertError").show(); 
	}
	
	$("#hidItemIDSave").val("");
	$("#formItem")[0].reset(); 
}	

//validationns
function validateLoginForm() {

	// USERNAME
	if ($("#txtUsername").val().trim() == "") {
		return "Insert Username."; 
	}
	
	// PASSWORD
	if ($("#txtPassword").val().trim() == "") {
		return "Insert Password.";	
	}
	
	return true;
}

// Logout onclick
$(document).on("click", "#btnLogout", function(event) {
	$.ajax({
		url	: "AuthAPI",
		type : "DELETE",
		data : "",
		dataType : "text",
		complete : function(response, status) {
			onLogoutComplete(response.responseText, status); 
		}
	}); 
});

function onLogoutComplete(response, status) {
	if (status == "success") {
		if (response.trim() == "success") {
			//Redirect to index page
			document.location = "index.jsp";
		} 
	}
 }