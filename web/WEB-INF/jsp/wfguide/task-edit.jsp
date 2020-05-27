<%@ page import="workflowsystem.data.wfguide.TaskPrereq" %>
<%@ page import="workflowsystem.data.wfguide.Task" %>
<jsp:useBean id="taskPrereqs" scope="request" type="java.util.List" />
<jsp:useBean id="taskDocumentInputs" scope="request" type="java.util.List" />
<jsp:useBean id="taskDocumentOutputs" scope="request" type="java.util.List" />
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
				<a class="process-guide" href="project-view.do?projectId=${task.projectId}">View Project </a> > 
				<a class="process-guide" href="workflow-view.do?workflowId=${task.workflowId}">View Work </a> > 
				Edit Task
			</h1>
		</header>
	
		<form method="post">
			<div class="contents-normal">
				<table width="80%">
					<tr>
						<td width="20%">Task ID</td>
						<td width="80%">${task.taskId}</td>
					</tr>
					<tr>
						<td>Task Name</td>
						<td><input type="text" name="taskName" value="${task.taskName}" size="60" maxlength="30" placeholder="(1-30 characters)"/>
						<%
						   if (errors.containsKey("taskName")) {
							  out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("taskName") + "</div>");
						   }
						%>
						</td>
					</tr>			
					<tr>
						<td valign="top">Task Description</td>
						<td>
							<!--<input type="text" name="taskWorkPoints" value="${task.taskWorkPoints}" size="30" />-->
							<textarea style="resize: none;" rows="5" name="taskWorkPoints" cols="35" maxlength="255" placeholder="(1-255 characters)">${task.taskWorkPoints}</textarea>
						<%
						   if (errors.containsKey("taskWorkPoints")) {
							  out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("taskWorkPoints") + "</div>");
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
			<input type="hidden" name="workflowId" value="${task.workflowId}" />
			<input type="hidden" name="projectId" value="${task.projectId}" />
			<input type="hidden" name="taskTotalHours" value="${task.taskTotalHours}" />
			<input type="hidden" name="taskProgress" value="${task.taskProgress}" />
			<input type="hidden" name="taskDeadline" value="${task.taskDeadline}" />
			<input type="hidden" name="taskPlannedHours" value="${task.taskPlannedHours}" />
			
			<input type="hidden" name="selectedTaskIdChild" id="selectedTaskIdChild" value="">
		</form>
	</section>


<%@ include file="/WEB-INF/jsp/bottom.inc" %>
