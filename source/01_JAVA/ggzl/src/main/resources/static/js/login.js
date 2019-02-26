function doSubmit(){
	//加密密码
	var password = $("#password").val();
	$("#password").val($.md5(password));
	document.getElementById("loginfrom").submit();
}