<%@ page import="workflowsystem.data.maincontrol.AccessTypeDetail" %>
<jsp:useBean id="accessTypeDetailsList" scope="request" type="java.util.List" />
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
				<a class="process-guide" href="accesstype-list.do">List Access Type</a> > 
				View Access Type
			</h1>
		</header>
		<div class="contents-normal">
			<table width="80%">
				<tr>
					<td width="33%">Access Type ID</td>
					<td>${accessType.accessTypeId}</td>
				</tr>
				<tr>
					<td>Access Type Code</td>
					<td>${accessType.accessTypeCode}</td>
				</tr>
				<tr>
					<td>Access Type Name</td>
					<td>${accessType.accessTypeName}</td>
				</tr>
				<% if (accessInfo.get("accesstype-edit-editbutton") != null) { %>
					<% if ((Boolean)accessInfo.get("accesstype-edit-editbutton")) { %>
						<tr>
							<td class="main-buttons">
								<button type="button" onclick="location.href='accesstype-edit.do?accessTypeId=${accessType.accessTypeId}'" class="main-button" 
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
				<h3>List Access Type Details</h3>
			</header>
			<div>
				<table border="0" id="wfTable" class="display">
					<thead>
						<tr>
							<th width="15">Access Type Detail ID</th>
							<th width="30%">Program Name</th>
							<th width="25%">Access Right</th>
							<th width="30%">Java Servlet Reference</th>
						</tr>
					</thead>
					<tbody>
					<% 
						Iterator it = accessTypeDetailsList.iterator();
						while (it.hasNext())
						{
							AccessTypeDetail accessTypeDetail = (AccessTypeDetail) it.next();
					%>
					<tr>
						<td width="15%"><%=accessTypeDetail.getAccessTypeDetailId()%></td>
						<% if (accessInfo.get("accesstypedetail-view-viewlink") != null) { %>
							<% if ((Boolean)accessInfo.get("accesstypedetail-view-viewlink")) { %>
								<td width="30%"><a href="accesstypedetail-view.do?accessTypeDetailId=<%=accessTypeDetail.getAccessTypeDetailId()%>"><%=accessTypeDetail.getProgramName()%></td>
							<% }
					   	   else { %>
					   	    <td width="30%"><%=accessTypeDetail.getProgramName()%></td>
					   	    <% } 
						} else { %>
				   	    <td width="30%"><%=accessTypeDetail.getProgramName()%></td>
				   	    <% } %>
						<td width="25%"><%=accessTypeDetail.getAccessRight()%></td>
						<td width="30%"><%=accessTypeDetail.getJavaServletReference()%></td>
					</tr>	
					<% 	} %>
					</tbody>
			  </table>
			  <% if (accessInfo.get("accesstypedetail-create-createbutton") != null) { %>
			  	<% if ((Boolean)accessInfo.get("accesstypedetail-create-createbutton")) { %>
			  		<div class="main-buttons">
			  			<button type="button" id="createButton" class="main-button" 
						onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
						Create
						</button>
					</div>
			  <% } } %>
			</div>
		</div>
		<div>
			<!-- main buttons -->
			<table class="main-buttons">
				<tr>
					<td width="25%" align="left">
						<button type="button"  onclick="location.href='accesstype-list.do'" class="main-button" 
							onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
							Previous page
						</button>
					</td>	
					<% if ((Boolean)accessInfo.get("accesstype-view-deletebutton")) { %>					
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
		<% 
		   String message = (String) request.getSession().getAttribute("message");
		   if (message != null) {
			   out.println("<div class=\"flash error\" id=\"flash_error\">" + message + "</div>");
		   }
		%>
	</section>
	<script type="text/javascript">		
			function ModalPopupsConfirm() {
				ModalPopups.Confirm("idConfirm1",
						"Confirm delete information",
						"Are you sure you want to delete access type?",
					{
						yesButtonText: "Yes",
						noButtonText: "No",
						onYes: "ModalPopupsConfirmYes()",
						onNo: "ModalPopupsConfirmNo()"
					}
				);
			}
			function ModalPopupsConfirmYes() {
				window.location = "accesstype-delete.do?accessTypeId=${accessType.accessTypeId}"
				ModalPopups.Close("idConfirm1");
			}
			function ModalPopupsConfirmNo() {
				
				ModalPopups.Cancel("idConfirm1");
			}
			
				document.getElementById("createButton").onclick = function () {
			    	if (${programs.isEmpty()}) {
				        alert("All programs were added");
				    }
			    	else
			    		{
			    		location.href ="accesstypedetail-create.do?accessTypeId=${accessType.accessTypeId}";
			    	}
			    };
	</script>

<%@ include file="/WEB-INF/jsp/bottom.inc" %>
