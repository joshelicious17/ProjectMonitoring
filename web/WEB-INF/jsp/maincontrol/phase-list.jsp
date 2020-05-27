<%@ page import="workflowsystem.data.maincontrol.Phase" %>
<jsp:useBean id="phases" scope="request" type="java.util.List" />
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
				List Phase
			</h1>
		</header>
		<table border="0" id="wfTable" class="display">
			<thead>
				<tr>
					<th width="20%">Phase ID</th>
					<th width="30%">Phase Code</th>
					<th width="50%">Phase Name</th>			
				</tr>
			</thead>
			<tbody>
				<% 
					Iterator it = phases.iterator();
					while (it.hasNext())
					{
						Phase phase = (Phase) it.next();
				%>
				<tr>
					<td width="20%"><%=phase.getPhaseId()%></td>
					<% if (accessInfo.get("phase-view-viewlink") != null) { %>
						<% if ((Boolean)accessInfo.get("phase-view-viewlink")) { %>
							<td width="30%"><a class="process-guide" href="phase-view.do?phaseId=<%=phase.getPhaseId()%>"><%=phase.getPhaseCode()%></a></td>
						<% }
						else { %>
					    	<td width="30%"><%=phase.getPhaseCode()%></td>
						<% } 
					} else { %>
					    <td width="30%"><%=phase.getPhaseCode()%></td>
				    <% } %>
					<td width="50%"><%=phase.getPhaseName()%></td>
				</tr>	
				<% 	} %>
			</tbody>		
		</table>
		<div>
			<!-- main buttons -->
			<table class="main-buttons">
				<tr>
					<td align="left">
						<button type="button" onclick="location.href='control-main.do'" class="main-button" 
							onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
							Previous page
						</button>
					</td>	
					<% if (accessInfo.get("phase-create-createbutton") != null) { %>
						<% if ((Boolean)accessInfo.get("phase-create-createbutton")) { %>											
							<td align="right">
								<button type="button" onclick="location.href='phase-create.do'" class="main-button" 
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