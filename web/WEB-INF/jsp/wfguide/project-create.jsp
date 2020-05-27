<jsp:useBean id="errors" scope="request" type="java.util.Map" class="java.util.HashMap" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/jsp/top.inc" %>   
<!-- Navigation -->
	<nav>
		<ul>
			<li><a href="home.do">HOME</a></li>
			<%@ include file="/WEB-INF/jsp/menu-wfguide.inc" %>
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
			<h1>Create Project</h1>
		</header>
		<form method="post">
			<div class="contents-normal">
				<table width="80%">
					<tr>
						<td width="20%">Project ID</td>
						<td width="80%">${project.projectId}</td>
					</tr>
					<tr>
						<td>Project Name</td>
						<td><input type="text" name="projectName" value="${project.projectName}" size="12" maxlength="12" placeholder="(1-12 characters)"/>
						<%
						   if (errors.containsKey("projectName")) {
							   out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("projectName") + "</div>");
						   }
						%>
						</td>
					</tr>
					<tr>
						<td>Project Description</td>
						<td><input type="text" name="projectDescription" value="${project.projectDescription}" size="60" maxlength="60" placeholder="(1-60 characters)"/>
						<%
						   if (errors.containsKey("projectDescription")) {
							   out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("projectDescription") + "</div>");
						   }
						%>
						</td>
					</tr>			
					<tr>
						<td>Client Name</td>
						<td>
							<select name="companyCode">
								<option value="" ${project.companyCode != company.companyCode ? 'selected' : ''}></option>		
								<c:forEach items="${companies}" var="company">
									<option value="${company.companyCode}" ${project.companyCode == company.companyCode ? 'selected' : ''}>${company.companyName}</option>
								</c:forEach>
							</select>
						<%
						   if (errors.containsKey("companyCode")) {
							  out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("companyCode") + "</div>");
						   }
						%>
						</td>
					</tr>			
					<tr>
						<td>Status</td>
						<td>
							<select name="projectStatus">						
								<option value="Proposal" ${project.projectStatus == 'Proposal' ? 'selected' : ''}>Proposal</option>
								<option value="Planning" ${project.projectStatus == 'Planning' ? 'selected' : ''}>Planning</option>
								<option value="In progress" ${project.projectStatus == 'In progress' ? 'selected' : ''}>In progress</option>
								<option value="Pending" ${project.projectStatus == 'Pending' ? 'selected' : ''}>Pending</option>
								<option value="Stopped" ${project.projectStatus == 'Stopped' ? 'selected' : ''}>Stopped</option>
								<option value="Completed" ${project.projectStatus == 'Completed' ? 'selected' : ''}>Completed</option>
							</select>
						<%
						   if (errors.containsKey("projectStatus")) {
							  out.println("<span class=\"error\">" + errors.get("projectStatus") + "</span>");
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
			
			<input type="hidden" name="projectId" value="${project.projectId}" />
		</form>
	</section>	


<%@ include file="/WEB-INF/jsp/bottom.inc" %>
