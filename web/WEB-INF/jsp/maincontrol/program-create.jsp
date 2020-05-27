<jsp:useBean id="errors" scope="request" type="java.util.Map" class="java.util.HashMap" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
				<a class="process-guide" href="accesstype-menu.do">Access Type Menu</a> >
				Create Program
			</h1>
		</header>
		<form method="post">
			<div class="contents-normal">
				<table>
				  <tr>
					 <td>Program ID</td>
					 <td width="80%">${program.programId}</td>
				  </tr>
				  <tr>
					 <td>Program Name</td>
					 <td><input type="text" name="programName" value="${program.programName}" size="30" maxlength="30" placeholder="(1-30 characters)"/>
						<%
						   if (errors.containsKey("programName")) {
							  out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("programName") + "</div>");
						   }
						%>
					 </td>
				  </tr>
				  <tr>
				  	 <td>Module Name</td>
					 <td>
						<select name="moduleName">
							<option value="" ${program.moduleName != module.moduleName ? 'selected' : ''}></option>		
							<c:forEach items="${moduleList}" var="module">
								<option value="${module.moduleName}" ${program.moduleName == module.moduleName ? 'selected' : ''}>${module.moduleName}</option>
							</c:forEach>
						</select>
						&nbsp;&nbsp;&nbsp;<input type="checkbox" name="withinTheMenu" value="1">&nbsp;Within the menu
					 <%
					   if (errors.containsKey("moduleName")) {
						  out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("moduleName") + "</div>");
					   }
					 %>
					 </td>
				  </tr>
				  <tr>
					 <td>Java Servlet Reference</td>
					 <td><input type="text" name="javaServletReference" value="${program.javaServletReference}" size="100" maxlength="100" placeholder="(1-100 characters)"/>
						<%
						   if (errors.containsKey("javaServletReference")) {
							  out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("javaServletReference") + "</div>");
						   }
						%>
					 </td>
				  </tr>  
				  <tr>
					 <td>URL Pattern</td>
					 <td><input type="text" name="urlPattern" value="${program.urlPattern}" size="30" maxlength="30" placeholder="(1-30 characters)"/>
						<%
						   if (errors.containsKey("urlPattern")) {
							  out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("urlPattern") + "</div>");
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
			<input type="hidden" name="id" value="${programId.programId}" />
		</form>
	</section>


<%@ include file="/WEB-INF/jsp/bottom.inc" %>
