<%@ page import="workflowsystem.data.wfguide.Project" %>
<jsp:useBean id="projects" scope="request" type="java.util.List" />
<%@ include file="/WEB-INF/jsp/top.inc" %>   
<!-- Navigation -->
	<nav>
		<ul>
			<li><a href="home.do">ホーム</a></li>
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
			<h1>Project List</h1>
		</header>
		<table border="0" id="wfTable" class="display">
			<thead>
				<tr>
					<th width="12%">Project ID</th>
					<th width="18%">Project Name</th>
					<th width="40%">Project Description</th>
					<th width="15%">Client Code</th>			
					<th width="15%">Status</th>
				</tr>
			</thead>
			<tbody>
				<% 
					Iterator it = projects.iterator();
					while (it.hasNext())
					{
						Project project = (Project) it.next();
				%>
				<tr>
					<td width="12%"><%=project.getProjectId()%></td>
					<% if (accessInfo.get("project-view-viewlink") != null) { %>
						<% if ((Boolean)accessInfo.get("project-view-viewlink")) { %>
							<td width="18%"><a class="process-guide" href="project-view.do?projectId=<%=project.getProjectId()%>"><%=project.getProjectName()%></a></td>
						<% }
						else { %>
					    	<td width="18%"><%=project.getProjectName()%></td>
					    <% } 
					} else { %>
				    	<td width="18%"><%=project.getProjectName()%></td>
				    <% } %>
					<td width="40%"><%=project.getProjectDescription()%></td>
					<td width="15%"><%=project.getCompanyCode()%></td>
					<td width="15%"><%=project.getProjectStatus()%></td>
				</tr>	
				<% 	} %>
			</tbody>		
	  	</table>
		<!-- main buttons -->
		<% if (accessInfo.get("project-create-createbutton") != null) { %>
			<% if ((Boolean)accessInfo.get("project-create-createbutton")) { %>
				<table>
					<tr>
						<td width="75%" align="right">
							<div class="main-buttons">
								<button type="button" onclick="location.href='project-create.do'" class="main-button" 
								onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
								Create
								</button>
							</div>
						</td>			
					</tr>
				</table>
		<% 	} } %>
	</section>

<%@ include file="/WEB-INF/jsp/bottom.inc" %>
