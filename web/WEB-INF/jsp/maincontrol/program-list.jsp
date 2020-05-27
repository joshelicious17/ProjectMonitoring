<%@ page import="workflowsystem.data.maincontrol.Program" %>
<jsp:useBean id="programs" scope="request" type="java.util.List" />
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
				List Program
			</h1>
		</header>
		<table border="0" id="wfTable" class="display">
			<thead>
				<tr>
					<th width="15%">Program ID</th>
					<th width="22%">Program Name</th>
					<th width="23%">Module Name</th>
					<th width="25%">Java Servlet Reference</th>
					<th width="25%">URL Pattern</th>
				</tr>
			</thead>
			<tbody>
				<% 
					Iterator it = programs.iterator();
					while (it.hasNext())
					{
						Program program = (Program) it.next();
				%>
				<tr>
					<td width="15%"><%=program.getProgramId()%></td>
					<% if (accessInfo.get("program-view-viewlink") != null) { %>
						<% if ((Boolean)accessInfo.get("program-view-viewlink")) { %>
							<td width="22%"><a class="process-guide" href="program-view.do?programId=<%=program.getProgramId()%>"><%=program.getProgramName()%></a></td>
						<% }
						else { %>
							<td width="22%"><%=program.getProgramName()%></td>
						<% } 
					} else { %>
					    <td width="22%"><%=program.getProgramName()%></td>
				    <% } %>
					<td width="23%"><%=program.getModuleName()%></td>
					<td width="25%"><%=program.getJavaServletReference()%></td>
					<td width="25%"><%=program.getUrlPattern()%></td>
				</tr>	
				<% 	} %>
			</tbody>		
		</table>
		<div>
			<!-- main buttons -->
			<table class="main-buttons">
				<tr>
					<td align="left">
						<button type="button" onclick="location.href='accesstype-menu.do'" class="main-button" 
							onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
							Previous page
						</button>
					</td>
					<% if (accessInfo.get("program-create-createbutton") != null) { %>	
						<% if ((Boolean)accessInfo.get("program-create-createbutton")) { %>						
							<td align="right">
							<button type="button" onclick="location.href='program-create.do'" class="main-button" 
							onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
							Create
							</button>
							</td>
					<% } } %>					
				</tr>
			</table>
		</div>
	</section>


<%@ include file="/WEB-INF/jsp/bottom.inc" %>