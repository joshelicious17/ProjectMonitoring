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
	<div id="content">
		<div>
			<label for="userpassword">Please enter new password.<br /><br /></label>
		</div>
		<form name="resetpassword" action="" method="post" accept-charset="utf-8">
			<div>
				<tr>
					<td><input type="password" name="userPassword" id="password"　size="30" maxlength="30" placeholder="Password"/>
						<% 
						   String userPassword = (String) request.getAttribute("userPassword");
						   if (userPassword != null) {
							   out.println("<div class=\"flash error\" id=\"flash_error\">" + userPassword + "</div>");
						   }
						%>
					</td>
				</tr>
				<tr>
					<td><input type="password" name="confirmUserPassword" id="password"　size="30" maxlength="30" placeholder="Confirm password" />
						<% 
						   String confirmUserPassword = (String) request.getAttribute("confirmUserPassword");
						   if (confirmUserPassword != null) {
							   out.println("<div class=\"flash error\" id=\"flash_error\">" + confirmUserPassword + "</div>");
						   }
						%>
					</td>
				</tr>
			</div>
			<div>				
				<input type="submit" value="Reset">
			</div>
			<div>				
				<a href="login.do">Back to login</a>
			</div>
			<input type="hidden" name="resetPasswordKey" value="${resetPasswordKey}" />
		</form>
	</div>
</body>

<%@ include file="bottom.inc" %>