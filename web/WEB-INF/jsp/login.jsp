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
		<form name="login" action="" method="post" accept-charset="utf-8">
			<div>
				<label for="usermail">Username</label>
				<input type="text" name="username" id="username" placeholder="Username" maxlength="8" required>
			</div>
			<div>
				<label for="password">Password</label>
				<input type="password" name="password" id="password" placeholder="Password" maxlength="100" required>
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
				<a href="forget-password.do">Forgot password? Click here!</a>
			</div>

		</form>
	</div>	
	</div>
</body>

<%@ include file="bottom.inc" %>