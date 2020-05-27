<jsp:useBean id="errors" scope="request" type="java.util.Map" class="java.util.HashMap" />

<%@ include file="/WEB-INF/jsp/top.inc" %>   
	<!-- Navigation -->
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
				Create Phase
			</h1>
		</header>
		<form method="post">
			<div class="contents-normal">
				<table width="80%">
				  <tr>
					 <td>Phase ID</td>
					 <td>${phase.phaseId}</td>
				  </tr>
				  <tr>
					 <td>Phase Code</td>
					 <td><input type="text" name="phaseCode" value="${phase.phaseCode}" size="8" maxlength="8" placeholder="(1-8 characters)"/>
						<%
						   if (errors.containsKey("phaseCode")) {
							  out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("phaseCode") + "</div>");
						   }
						%>
					 </td>
				  </tr>
				  <tr>
					 <td>Phase Name</td>
					 <td><input type="text" name="phaseName" value="${phase.phaseName}" size="30" maxlength="30" placeholder="(1-8 characters)"/>
						<%
						   if (errors.containsKey("phaseName")) {
							  out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("phaseName") + "</div>");
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
			<input type="hidden" name="id" value="${phase.phaseId}" />
		</form>
	</section>


<%@ include file="/WEB-INF/jsp/bottom.inc" %>
