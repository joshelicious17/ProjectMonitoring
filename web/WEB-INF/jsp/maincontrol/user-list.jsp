<%@ page import="workflowsystem.data.maincontrol.User" %>
<jsp:useBean id="users" scope="request" type="java.util.List" />
<%@ include file="/WEB-INF/jsp/top.inc" %>   
	<!-- Navigation -->
	<nav>
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
				List User
			</h1>
		</header>
		<table border="0" id="wfTable" class="display">
			<thead>
				<tr>
					<th width="10%">User ID</th>
					<th width="15%">User Name</th>
					<th width="25%">User Fullname</th>
					<th width="25%">Client Code</th>
					<th width="15%">Access Type</th>
				</tr>
			</thead>
			<tbody>
				<% 
					Iterator it = users.iterator();
					while (it.hasNext())
					{
						User user = (User) it.next();
				%>
				<tr>
					<td width="10%"><%=user.getUserId()%></td>
					<% if (accessInfo.get("user-view-viewlink") != null) { %>
						<% if ((Boolean)accessInfo.get("user-view-viewlink")) { %>
						<td width="15%"><a class="process-guide" href="user-view.do?userId=<%=user.getUserId()%>"><%=user.getUserName()%></a></td>
					<% } }
					   else { %>
					    <td width="15%"><%=user.getUserName()%></td>
				    <% } %>
					<td width="25%"><%=user.getUserFullname()%></td>
					<td width="25%"><%=user.getCompanyCode()%></td>
					<td width="15%"><%=user.getAccessTypeCode()%></td>
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
					<% if (accessInfo.get("user-create-createbutton") != null) { %>
						<% if ((Boolean)accessInfo.get("user-create-createbutton")) { %>						
							<td align="right">
								<button type="button" onclick="location.href='user-create.do'" class="main-button" 
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