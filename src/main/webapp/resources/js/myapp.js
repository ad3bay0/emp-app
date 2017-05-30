


$("#btn-add").click(function(){
	
	$("#myform").attr("action","addPerson");
	$("#myform").submit();
	
});

$("#btn-update").click(function(){
	
	$("#myform").attr("action","updatePerson");
	$("#myform").submit();
	
});