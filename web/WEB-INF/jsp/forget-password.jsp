<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
	<title>PROJECT MONITORING</title>
</head>
<style type="text/css">
	<%@include file="resource/normalize.css" %>
	<%@include file="resource/style.css" %>
</style>
	
<body>
<div id="holder">
	 <div>
	 	<img src="images/logo.jpg" style="padding: 0; margin" >
	 </div>
	 <div>
	 	<img src="images/blue.jpg" style="padding: 0; margin" >
	 </div>
	<div class="container">
	<div id="content" >
		<form name="forgetpassword" action="" method="post" accept-charset="utf-8">
			<div>
				<label for="useremail">Please enter your email address.</label>
				<br /><br />
				<input type="text" name="userEmail" id="useremail" placeholder="youremail@address.com" maxlength="30" required>
				<% 
				   String message = (String) request.getAttribute("message");
				   if (message != null) {
					   out.println("<div class=\"flash error\" id=\"flash_error\">" + message + "</div>");
				   }
				%>
			</div>
			<div>				
				<input type="submit" value="OK">
			</div>
			<div>				
				<a href="login.do">Back to login</a>
			</div>
		</form>
	</div>	
	</div>
</body>

<%@ include file="bottom.inc" %>