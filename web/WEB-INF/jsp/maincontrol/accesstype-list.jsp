<%@ page import="workflowsystem.data.maincontrol.AccessType" %>
<jsp:useBean id="accessTypes" scope="request" type="java.util.List" />
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
				List Access Type
			</h1>
		</header>
		<table border="0" id="wfTable" class="display">
			<thead>
				<tr>
					<th width="20%">Access Type ID</th>
					<th width="30%">Access Type Code</th>
					<th width="50%">Access Type Name</th>			
				</tr>
			</thead>
			<tbody>
				<% 
					Iterator it = accessTypes.iterator();
					while (it.hasNext())
					{
						AccessType accessType = (AccessType) it.next();
				%>
				<tr>
					<td width="20%"><%=accessType.getAccessTypeId()%></td>
					<% if (accessInfo.get("accesstype-view-viewlink") != null ) { %>
						<% if ((Boolean)accessInfo.get("accesstype-view-viewlink")) { %>
							<td width="30%"><a class="process-guide" href="accesstype-view.do?accessTypeId=<%=accessType.getAccessTypeId()%>"><%=accessType.getAccessTypeCode()%></a></td>
						<% }
						   else { %>
					    <td width="30%"><%=accessType.getAccessTypeCode()%></td>
					    <% } 
					 } else { %>
					    <td width="30%"><%=accessType.getAccessTypeCode()%></td>
					    <% } %>
					<td width="50%"><%=accessType.getAccessTypeName()%></td>
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
					<% if (accessInfo.get("accesstype-create-createbutton") != null) { %>
						<% if ((Boolean)accessInfo.get("accesstype-create-createbutton")) { %>											
							<td align="right">
								<button type="button" onclick="location.href='accesstype-create.do'" class="main-button" 
								onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
								Create
								</button>
							</td>	
					<% 	} } %>						
				</tr>
			</table>
		</div>
	</section>


<%@ include file="/WEB-INF/jsp/bottom.inc" %>