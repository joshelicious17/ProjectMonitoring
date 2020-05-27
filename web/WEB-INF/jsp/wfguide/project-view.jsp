<%@ page import="workflowsystem.data.wfguide.Workflow" %>
<jsp:useBean id="workflows" scope="request" type="java.util.List" />
<jsp:useBean id="accessInfoList" scope="request" type="java.util.Map" class="java.util.HashMap" />
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
				<a class="process-guide" href="project-list.do">List Project</a> > 
				View Project
			</h1>
		</header>
		<div class="contents-normal">
			<table width="80%">
				<tr>
					<td width="20%">Project ID</td>
					<td colspan="3">${project.projectId}</td>
				</tr>
				<tr>
					<td>Project Name</td>
					<td colspan="3">${project.projectName}</td>

				</tr>
				<tr>
					<td>Project Description</td>
					<td colspan="3">${project.projectDescription}</td>
				</tr>
				<tr>
					<td>Client</td>
					<td>${project.companyCode}</td> 	
					<td width="4%"> - </td>	
					<td width="60%">${project.companyName}</td>	
				</tr>
				<tr>
					<td>Status</td>
					<td colspan="3">${project.projectStatus}</td>
				</tr>
				<% if (accessInfo.get("project-edit-editbutton") != null) { %>
					<% if ((Boolean)accessInfo.get("project-edit-editbutton")) { %>
						<tr>
							<td class="main-buttons">
								<button type="button" onclick="location.href='project-edit.do?projectId=${project.projectId}'" class="main-button" 
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
				<h3>List Work</h3>
			</header>
			<div>
				<table border="0" id="wfTable" class="display">
					<thead>
						<tr>
							<th width="15">Work ID</th>
							<th width="35%">Work Name</th>
							<th width="30%">Project Name</th>
							<th width="10%">Order</th>
							<th width="10%">Phase Code</th>
						</tr>
					</thead>
					<tbody>
					<% 
						Iterator it = workflows.iterator();
						while (it.hasNext())
						{
							Workflow workflow = (Workflow) it.next();
					%>
					<tr>
						<td width="15%"><%=workflow.getWorkflowId()%></td>
						<% if (accessInfo.get("workflow-view-viewlink") != null) { %>
							<% if ((Boolean)accessInfo.get("workflow-view-viewlink")) { %>
								<td width="35%"><a href="workflow-view.do?workflowId=<%=workflow.getWorkflowId()%>"><%=workflow.getWorkflowName()%></a></td>
							<% }
					   	   	else { %>
					   	    <td width="35%"><%=workflow.getWorkflowName()%></td>
					   	    <% } 
						} else { %>
					   	    <td width="35%"><%=workflow.getWorkflowName()%></td>
					   	<% } %>
						<td width="30%"><%=workflow.getProjectName()%></td>
						<td width="10%"><%=workflow.getWorkflowSequence()%></td>
						<td width="10%"><%=workflow.getPhaseCode()%></td>
					</tr>	
					<% 	} %>
					</tbody>
			  </table>
			  <% if (accessInfo.get("workflow-create-createbutton") != null) { %>
			  		<% if ((Boolean)accessInfo.get("workflow-create-createbutton")) { %>
			  			<div class="main-buttons">
			  				<button type="button" onclick="location.href='workflow-create.do?projectId=${project.projectId}'" class="main-button" 
							onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
							Create
							</button>
						</div>
			  <% } } %>
			</div>
		</div>
		<div>
			<!-- main buttons -->
			<table class="main-buttons" >
				<tr>
					<td width="25%" align="left">
						<button type="button" onclick="location.href='project-list.do'" class="main-button" 
							onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
							Previous page
						</button>
					</td>
					<% if ((Boolean)accessInfo.get("project-view-deletebutton")) { %>
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
				"Are you sure you want to delete project?", 
				{
					yesButtonText: "Yes",
					noButtonText: "No",
					onYes: "ModalPopupsConfirmYes()",
					onNo: "ModalPopupsConfirmNo()"
				}
			);
		}
		function ModalPopupsConfirmYes() {
			window.location = "project-delete.do?projectId=${project.projectId}"
			ModalPopups.Close("idConfirm1");
		}
		function ModalPopupsConfirmNo() {
			
			ModalPopups.Cancel("idConfirm1");
		}
</script>
<%@ include file="/WEB-INF/jsp/bottom.inc" %>
