<jsp:useBean id="errors" scope="request" type="java.util.Map" class="java.util.HashMap" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/WEB-INF/jsp/top.inc" %>  
	<!-- Navigation -->
	<nav>
	<nav>
		<ul>
			<li><a href="home.do">HOME</a></li>
			<%@ include file="/WEB-INF/jsp/menu-maincontrol.inc" %>
		</ul>
	</nav>
	
	<aside>
		<header>OTHER LINKS</header>
		<ul>
			<li><a href="faq.do">FAQ</a></li>
		</ul>
	</aside>
	<section>
		<header>
			<h1>
				<a class="process-guide" href="control-main.do">Control</a> > 
				<a class="process-guide" href="user-list.do">List User </a> >
				Edit User
			</h1>
		</header>
		<form method="post">
			<div class="contents-normal">
				<table width="80%">
					<tr>
						<td>User ID</td>
						<td>${user.userId}</td>
					</tr>
					<tr>
						<td>User Name</td>
<!-- 						<td>${user.userName}</td>
 -->
						 <td><input type="text" name="userName" value="${user.userName}" size="8" maxlength="8" placeholder="（1～8桁）"/>
							<%
							   if (errors.containsKey("userName")) {
								  out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("userName") + "</div>");
							   }
							%>
						 </td>
					</tr>
					<tr>
						<td>User Fullname</td>
						<td><input type="text" name="userFullname" value="${user.userFullname}" size="30" maxlength="30" />
							<%
							   if (errors.containsKey("userFullname")) {
								  	out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("userFullname") + "</div>");
							   }
							%>
						</td>
					</tr>
					<tr>
						<td>Email Address</td>
						<td><input type="text" name="userEmail" value="${user.userEmail}" size="30" maxlength="30" />
							<%
							   if (errors.containsKey("userEmail")) {
								  	out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("userEmail") + "</div>");
							   }
							%>
						</td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="Password" name="userPassword" value="${user.userPassword}" size="30" maxlength="30" />
							<%
								if (errors.containsKey("userPassword")) {
								  	out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("userPassword") + "</div>");
								}
							%>
						</td>
					</tr>
					<tr>
						<td>Confirm Password</td>
						<td><input type="Password" name="confirmUserPassword" value="${user.userPassword}" size="30" maxlength="30" />
							<%
								if (errors.containsKey("confirmUserPassword")) {
								  	out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("confirmUserPassword") + "</div>");
								}
							%>
						</td>
					</tr>      
					<tr>
						<td>Client Code</td>
						<td>
							<select name="companyCode">						
								<c:forEach items="${companies}" var="company">
									<option value="${company.companyCode}" ${user.companyCode == company.companyCode ? 'selected' : ''}>${company.companyName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>Access Type</td>
						<td>
							<select name="accessTypeCode">						
								<c:forEach items="${accessTypes}" var="accessType">
									<option value="${accessType.accessTypeCode}" ${user.accessTypeCode == accessType.accessTypeCode ? 'selected' : ''}>${accessType.accessTypeName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
			</div>
		  	<div class="main-buttons">
				<button type="submit" name="submit-button" class="main-button" 
					onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
					Save
				</button>
				<button type="submit" name="cancel-button" class="main-button" 
					onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
					Cancel
				</button>
		  	</div>
			<input type="hidden" name="userId" value="${user.userId}" />
		</form>
	</section>


<%@ include file="/WEB-INF/jsp/bottom.inc" %>
