﻿<jsp:useBean id="errors" scope="request" type="java.util.Map" class="java.util.HashMap" />

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
				Create Client
			</h1>
		</header>
		<form method="post">
			<div class="contents-normal">
				<table width="80%">
				  <tr>
					 <td>Client ID</td>
					 <td>${company.companyId}</td>
				  </tr>
				  <tr>
					 <td>Client Code</td>
					 <td><input type="text" name="companyCode" value="${company.companyCode}" size="8" maxlength="8" placeholder="(1-8 characters)"/>
						<%
						   if (errors.containsKey("companyCode")) {
							  out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("companyCode") + "</div>");
						   }
						%>
					 </td>
				  </tr>
				  <tr>
					 <td>Client Name</td>
					 <td><input type="text" name="companyName" value="${company.companyName}" size="30" maxlength="30" placeholder="1-30 characters)"/>
						<%
						   if (errors.containsKey("companyName")) {
							  out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("companyName") + "</div>");
						   }
						%>
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
			<input type="hidden" name="id" value="${company.companyId}" />
		</form>
	</section>


<%@ include file="/WEB-INF/jsp/bottom.inc" %>
