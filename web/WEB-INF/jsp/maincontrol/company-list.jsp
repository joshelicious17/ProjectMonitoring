<%@ page import="workflowsystem.data.maincontrol.Company" %>
<jsp:useBean id="companies" scope="request" type="java.util.List" />
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
				List Client
			</h1>
		</header>
		<table border="0" id="wfTable" class="display">
			<thead>
				<tr>
					<th width="20%">Client ID</th>
					<th width="30%">Client Code </th>
					<th width="50%">Client Name</th>			
				</tr>
			</thead>
			<tbody>
				<% 
					Iterator it = companies.iterator();
					while (it.hasNext())
					{
						Company company = (Company) it.next();
				%>
				<tr>
					<td width="20%"><%=company.getCompanyId()%></td>
					<% if (accessInfo.get("company-view-viewlink") != null) { %>
						<% if ((Boolean)accessInfo.get("company-view-viewlink")) { %>
							<td width="30%"><a class="process-guide" href="company-view.do?companyId=<%=company.getCompanyId()%>"><%=company.getCompanyCode()%></a></td>
						<% } else { %>
							<td width="30%"><%=company.getCompanyCode()%></td>
					    <% }
					} else { %>
					    <td width="30%"><%=company.getCompanyCode()%></td>
				    <% } %>
					<td width="50%"><%=company.getCompanyName()%></td>
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
					<% if (accessInfo.get("company-create-createbutton") != null) { %>	
						<% if ((Boolean)accessInfo.get("company-create-createbutton")) { %>					
							<td align="right">
								<button type="button" onclick="location.href='company-create.do'" class="main-button" 
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