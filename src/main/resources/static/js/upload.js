$(document).ready(function() {
	$("#btnSubmit").click(function(event) {
		// stop submit the form, we will post it manually.
		event.preventDefault();
		uploadFilesUsingAjax();
	});

});

function uploadFilesUsingAjax() {
	// Get form
	var form = $('#fileUploadForm')[0];
	var data = new FormData(form);
	data.append("CustomField", "This is some extra data, testing");
	$("#btnSubmit").prop("disabled", true);
	var preference = $("#preference").val();
	var baseApiUrl = "/api/v1/documents"
	var apiUrl = "";

	if (preference === "1") {
		apiUrl = baseApiUrl + "/upload";

	} else if (preference === "2") {
		apiUrl = baseApiUrl + "/upload/multi";

	} else if (preference === "3") {
		apiUrl = baseApiUrl + "/upload/multi/model";

	}
	

	$.ajax({
		type : "POST",
		enctype : 'multipart/form-data',
		url : apiUrl,
		data : data,
		processData : false,
		contentType : false,
		cache : false,
		timeout : 600000,
		success : function(data) {

			$("#result").text(data);
			$("#result").addClass("btn-primary")
			console.log("SUCCESS : ", data);
			$("#btnSubmit").prop("disabled", false);

		},
		error : function(e) {
			alert("Error : "  + e.responseText);
			$("#result").text(e.responseText);
			console.log("ERROR : ", e);
			$("#btnSubmit").prop("disabled", false);

		}
	});

}
