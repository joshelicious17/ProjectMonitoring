<%@ page import="workflowsystem.data.wfguide.TaskPrereq" %>
<%@ page import="workflowsystem.data.wfguide.Task" %>
<jsp:useBean id="taskPrereqs" scope="request" type="java.util.List" />
<jsp:useBean id="taskDocumentInputs" scope="request" type="java.util.List" />
<jsp:useBean id="taskDocumentOutputs" scope="request" type="java.util.List" />
<jsp:useBean id="tasks" scope="request" type="java.util.List" />
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
				View Task
			</h1>
		</header>

		<form name="taskEditForm" method="post">
			<div class="contents-normal">
				<table width="80%">
					<tr>
						<td width="20%">Task ID</td>
						<td width="80%">${task.taskId}</td>
					</tr>
					<tr>
						<td>Task Name</td>
						<td>
							<input type="text" name="taskName" value="${task.taskName}" size="30" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td valign="top">Task Description</td>
						<td colspan="3" >
							<textarea style="resize: none;" rows="5" name="taskWorkPoints" cols="35" maxlength="300" disabled="disabled">${task.taskWorkPoints}</textarea>
						</td>
					</tr>
					<% if (accessInfo.get("task-edit-editbutton") != null) { %>
						<% if ((Boolean)accessInfo.get("task-edit-editbutton")) { %>		
							<tr>
								<td class="main-buttons">
									<button type="button" onclick="location.href='task-edit.do?taskId=${task.taskId}'" class="main-button" 
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
					<h3>List Task Prerequisite</h3>
				</header>
				<div>
					<table id="wfTable" class="display">
						<thead>
							<tr>
								<th width="20%">Task ID</th>
								<th width="70%">Task Name</th>		
								<th width="10%">Remove</th>		
							</tr>
						</thead>
						<tbody>
							<% 
								Iterator it = taskPrereqs.iterator();
								while (it.hasNext())
								{
									TaskPrereq taskPrereq = (TaskPrereq) it.next();
							%>
							<tr>
								<td width="20%"><%=taskPrereq.getTaskIdChild()%></td>
								<td width="70%"><%=taskPrereq.getTaskNameChild()%></td>
								<td width="10%">
								<% if ((Boolean)accessInfo.get("task-view-prereqdellink")) { %>
									<a name="prereqId" class="process-guide" href="taskprereq-delete.do?taskPrereqId=<%=taskPrereq.getTaskPrereqId()%>">Remove</a>
								<% } %>
								</td>
							</tr>	
							<% 	} %>
						</tbody>
					</table>
				</div>
				<% if ((Boolean)accessInfo.get("task-view-prereqaddbutton")) { %>
				<div class="main-buttons">
					<button type="button" onclick="javascript:ModalPopupsCustom1()" class="main-button" 
						onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
						Add
					</button>
				</div>
				<% } %>
			</div>

			<input type="hidden" name="workflowId" value="${task.workflowId}" />
			<input type="hidden" name="projectId" value="${task.projectId}" />
			<input type="hidden" name="taskName" value="${task.taskName}" />
			<input type="hidden" name="taskTotalHours" value="${task.taskTotalHours}" />
			<input type="hidden" name="taskProgress" value="${task.taskProgress}" />
			<input type="hidden" name="taskDeadline" value="${task.taskDeadline}" />
			<input type="hidden" name="taskPlannedHours" value="${task.taskPlannedHours}" />
			<input type="hidden" name="taskWorkPoints" value="${task.taskWorkPoints}" />
			<input type="hidden" name="selectedTaskIdChild" id="selectedTaskIdChild" value="">

		</form>
		<div>
			<!-- main buttons -->
			<table class="main-buttons">
				<tr>
					<td width="25%" align="left">
						<button type="button" onclick="location.href='workflow-view.do?workflowId=${task.workflowId}'" class="main-button" 
							onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
							Previous page
						</button>
					</td>
					<% if ((Boolean)accessInfo.get("task-view-deletebutton")) { %>
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
				"Are you sure you want to delete task?", 
				{
					yesButtonText: "Yes",
					noButtonText: "No",
					onYes: "ModalPopupsConfirmYes()",
					onNo: "ModalPopupsConfirmNo()"
				}		
			);
		}
		function ModalPopupsConfirmYes() {
			window.location = "task-delete.do?taskId=${task.taskId}"
			ModalPopups.Close("idConfirm1");
		}
		function ModalPopupsConfirmNo() {
			
			ModalPopups.Cancel("idConfirm1");
		}
		function ModalPopupsCustom1() {
			if (${tasks.isEmpty()}){
				alert("Currently there are no other task and prerequisite task.");
				ModalPopups.Close("idCustom1")
				document.taskEditForm.submit();
			}
			ModalPopups.Custom("idCustom1",
				"Please select prerequisite task.",	
				"<div style='padding: 25px;'>" + 
				"<table>" + 
				"<tr><td>タスク</td>" + 
					"<td><select name='taskIdChild' id='taskIdChild'>" +
						"<c:forEach items='${tasks}' var='task'>" +
							"<option value='${task.taskId}' >${task.taskName}</option>" +
						"</c:forEach>" +
					"</select></td>" +
				"</tr>" + 				
				"</table>" + 
				"</div>" ,
				{
					width: 500,
					buttons: "ok,cancel",
					okButtonText: "Select",
					cancelButtonText: "Cancel",
					onOk: "ModalPopupsCustom1Save()",
					onCancel: "ModalPopupsCustom1Cancel()"
				}
			);					
		}
		function ModalPopupsCustom1Save() {
			var e = document.getElementById("taskIdChild");
			var taskIdChild = e.options[e.selectedIndex].value;	
			document.getElementById('selectedTaskIdChild').value = taskIdChild;
			document.taskEditForm.submit();
			ModalPopups.Close("idCustom1");
		}
		
		function ModalPopupsCustom1Cancel() {			
			ModalPopups.Cancel("idCustom1");
		}
		// For deleting Task Prereq
		function DelPrereqConfirm() {
			ModalPopups.Confirm("idConfirmDelPrereq",
					"Confirm delete information",
					"Are you sure you want to delete prerequisite task?", 
				{
					yesButtonText: "Yes",
					noButtonText: "No",
					onYes: "DelPrereqConfirmYes()",
					onNo: "DelPrereqConfirmNo()"
				}
			);
		}
		function DelPrereqConfirmYes() {
			alert("taskPrereqId " + document.taskEditForm.prereqId.value);
			var mom = document.taskEditForm.prereqId.value; //captures the name the user enters
			window.location = "taskprereq-delete.do?taskPrereqId=taskPrereqId";
			ModalPopups.Close("idConfirmDelPrereq");
		}
		function DelPrereqConfirmNo() {
			ModalPopups.Cancel("idConfirmDelPrereq");
		}
		
</script>

<%@ include file="/WEB-INF/jsp/bottom.inc" %>
