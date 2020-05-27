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
			<h1>
				<a class="process-guide" href="project-list.do">List Project </a> > 
				<a class="process-guide" href="project-view.do?projectId=${project.projectId}">View Project</a> > 
				Create Work
			</h1>
		</header>
	
		<form method="post">
			<div class="contents-normal">
				<table width="80%">
					<tr>
						<td width="20%">Work ID</td>
						<td width="80%">${workflow.workflowId}</td>
					</tr>
					<tr>
						<td>Work Name</td>
						<td><input type="text" name="workflowName" value="${workflow.workflowName}" size="30" maxlength="30" placeholder="(1-30 characters)"/>
						<%
						   if (errors.containsKey("workflowName")) {
							  out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("workflowName") + "</div>");
						   }
						%>
						</td>
					</tr>
					<tr>
						<td>Project Name</td>
						<td>
							${project.projectName}
							<input type="hidden" name="projectId" value="${project.projectId}" />
							<input type="hidden" name="projectName" value="${project.projectName}" />
						</td>
					</tr>
					<tr>
						<td>Order</td>
						<td><input type="text" name="workflowSequence" value="${workflow.workflowSequence}" size="5" maxlength="5" placeholder="1,2,.."/>
						<%
						   if (errors.containsKey("workflowSequence")) {
							  out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("workflowSequence") + "</div>");
						   }
						%>
						</td>
					</tr>
					<tr>
						<td>Phase</td>
						<td>
							<select name="phaseCode">						
								<c:forEach items="${phases}" var="phase">
									<option value="${phase.phaseCode}" ${workflow.phaseCode == phase.phaseCode ? 'selected' : ''}>${phase.phaseName}</option>
								</c:forEach>
							</select>
						<%
						   if (errors.containsKey("phaseCode")) {
							  out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("phaseCode") + "</div>");
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
			<input type="hidden" name="workflowId" value="${workflow.workflowId}" />
		</form>
	</section>


<%@ include file="/WEB-INF/jsp/bottom.inc" %>
