$(document).ready(function(){
    $("#alertSuccess").hide();
    $("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
    // Clear status messages
    $("#alertSuccess").text(""); 
    $("#alertSuccess").hide(); 
    $("#alertError").text(""); 
    $("#alertError").hide();

    // Form validation
    var status = validateItemForm();

    // If not valid
    if (status != true) {
        $("#alertError").text(status); 
        $("#alertError").show(); 
        return;
    }
    
    // If valid
    // Generate the card and append
    var student = getStudentCard(   $("#txtName").val().trim(), 
                                    $('input[name="rdoGender"]:checked').val(),
                                    $("#ddlYear").val()
                                ); 

    $("#colStudents").append(student);

    //success alert
    $("#alertSuccess").text("Saved successfully.");
    $("#alertSuccess").show(); 

    //clearing the form 
    $("#formStudent")[0].reset();
    
});

// REMOVE ============================================
$(document).on("click",".remove",function(event){
    //identifying the card containing the remove button and remove it
    $(this).closest(".student").remove();

    //show alert
    $("#alertSuccess").text("Removed successfully");
    $("#alertSuccess").show();

})

// FORM VALIDATION ============================================
function validateItemForm() {

    //NAME
    if ($("#txtName").val().trim() == "") {
        return "Insert Student Name";
    }

    //GENDER
    if ($('input[name="rdoGender"]:checked').length === 0) {
        return "Select Gender";
    }

    //YEAR
    if ($("#ddlYear").val() == "0") {
        return "Select Year";
    }

    return true
}

// DISPLAY CARD ============================================
function getStudentCard(name, gender, year) { 
    var student = ""; 

    var title = (gender == "Male") ? "Mr." : "Ms."; 
 
    var yearNumber = ""; 
    
    switch (year) { 
        case "1": 
            yearNumber = "1st"; 
            break; 
        
        case "2": 
            yearNumber = "2nd"; 
            break; 
        
        case "3": 
            yearNumber = "3rd"; 
            break; 
        
        case "4": 
            yearNumber = "4th"; 
            break; 
    }

    //Generate card 
    
    student += "<div class=\"student card bg-light m-2\" style=\"max-width: 10rem;  float: left;\">"; 
    student += "<div class=\"card-body\">"; 
    student += title + " " + name + ","; 
    student += "<br>"; 
    student += yearNumber + " year"; 
    student += "</div>"; 
    student += "<input type=\"button\" value=\"Remove\" class=\"btn btn-danger remove\">"; 
    student += "</div>";
 
    return student; 
} 
