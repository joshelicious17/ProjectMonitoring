<%@page import="workflowsystem.data.wfguide.TaskPrereq"%>
<%@ page import="workflowsystem.data.wfguide.Task" %>
<jsp:useBean id="tasks" scope="request" type="java.util.List" />
<%@ include file="/WEB-INF/jsp/top.inc" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
				<a class="process-guide" href="project-view.do?projectId=${workflow.projectId}">View Project </a> > 
				View Work
			</h1>
		</header>

		<form method="post">
			<div class="contents-normal">
				<table width="80%">
					<tr>
						<td width="20%">ワークフローＩＤ</td>
						<td width="80%">${workflow.workflowId}</td>
					</tr>
					<tr>
						<td>Work Name</td>
						<td>${workflow.workflowName}</td>
					</tr>
					<tr>
						<td>Project Name</td>
						<td>${workflow.projectName}</td>
					</tr>
					<tr>
						<td>Order</td>
						<td>${workflow.workflowSequence}</td>
					</tr>
					<tr>
						<td>Phase</td>
						<td>${workflow.phaseCode}</td>
					</tr>
					<% if (accessInfo.get("workflow-edit-editbutton") != null) { %>
						<% if ((Boolean)accessInfo.get("workflow-edit-editbutton")) { %>
							<tr>
								<td class="main-buttons">
									<button type="button" onclick="location.href='workflow-edit.do?workflowId=${workflow.workflowId}'" class="main-button" 
									onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
									Edit
									</button>
								</td>
							</tr>
					<% } } %>
				</table>
			</div> 
			<div id="mainContent">
				<header>
					<h3>List Task</h3>
				</header>
				<div>
					<table id="wfTable" class="display">
						<thead>
							<tr>
								<th width="15%">Task ID</th>
								<th width="50%">Task Name</th>			
								<th width="35%">Task Prerequisite ID</th>
							</tr>
						</thead>
						<tbody>
							<% 
								Iterator it = tasks.iterator();
							    int i = 0;
								while (it.hasNext())
								{
									Task task = (Task) it.next();
									String x = "${taskPrereqs[" + i + "]}";
							%>
 							<c:set var="index1" value="<%=i%>" /> 
 							<tr>
								<td width="15%"><%=task.getTaskId()%></td>
								<% if (accessInfo.get("task-view-viewlink") != null) { %>
									<% if ((Boolean)accessInfo.get("task-view-viewlink")) { %>
										<td width="50%"><a class="process-guide" href="task-view.do?taskId=<%=task.getTaskId()%>"><%=task.getTaskName()%></a></td>
									<% }
					   	   		   	else { %>
					   	   		   		<td width="50%"><%=task.getTaskName()%></td>
					   	   		   	<% } 
					   	   		} else { %>
				   	   		   		<td width="50%"><%=task.getTaskName()%></td>
				   	   		   	<% } %>
								<td width="25%">
							      <c:forEach varStatus="row" items="${taskPrereqs}">
								      <c:if test="${row.index == index1}">
								      	<c:forEach var="prereq" items="${taskPrereqs[row.index]}">
											<c:if test="${!empty prereq}">
			        							 ${prereq}<br/>
											</c:if>
								      	</c:forEach>
									  </c:if>
								  </c:forEach>
								</td>
							</tr>	
							<% 	
							i++;
							} %>
						</tbody>
				  	</table>
				  	<% if (accessInfo.get("task-create-createbutton") != null) { %>
				  		<% if ((Boolean)accessInfo.get("task-create-createbutton")) { %>
				  			<div class="main-buttons">
				  				<button type="button" onclick="location.href='task-create.do?workflowId=${workflow.workflowId}'" class="main-button" 
				  					onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
				  					Create
				  				</button>
				  			</div>
					<% } } %>
				</div>
			</div>
		</form>
		<div>
			<!-- main buttons -->
			<table class="main-buttons">
				<tr>
					<td width="25%" align="left">
						<button type="button" onclick="location.href='project-view.do?projectId=${workflow.projectId}'" class="main-button" 
							onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
							Previous page
						</button>
					</td>
					<% if ((Boolean)accessInfo.get("workflow-view-deletebutton")) { %>
					<td width="75%" align="right">
						<button type="button" onclick="javascript:ModalPopupsConfirm()" class="main-button" 
							onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
							Delete
						</button>
					</td>
					<% } %>	
				</tr>
			</table>
		</div>
	</section>
	
<script type="text/javascript">		
		function ModalPopupsConfirm() {
			ModalPopups.Confirm("idConfirm1",
				"Confirm delete information",
				"Are you sure you want to delete work?", 
				{
					yesButtonText: "Yes",
					noButtonText: "No",
					onYes: "ModalPopupsConfirmYes()",
					onNo: "ModalPopupsConfirmNo()"
				}
			);
		}
		function ModalPopupsConfirmYes() {
			window.location = "workflow-delete.do?workflowId=${workflow.workflowId}"
			ModalPopups.Close("idConfirm1");
		}
		function ModalPopupsConfirmNo() {
			
			ModalPopups.Cancel("idConfirm1");
		}
</script>
<%@ include file="/WEB-INF/jsp/bottom.inc" %>
