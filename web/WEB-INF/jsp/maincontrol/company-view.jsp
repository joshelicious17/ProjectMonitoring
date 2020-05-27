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
				<a class="process-guide" href="company-list.do">List Client</a> > 
				View Client
			</h1>
		</header>
		<form method="post">
			<div class="contents-normal">
				<table width="80%">
					<tr>
						<td>Client ID</td>
						<td>${company.companyId}</td>
					</tr>
					<tr>
						<td>Client Code</td>
						<td>${company.companyCode}</td>
					</tr>
					<tr>
						<td>Client Name</td>
						<td>${company.companyName}</td>
					</tr>
					<% if (accessInfo.get("company-edit-editbutton") != null) { %>
						<% if ((Boolean)accessInfo.get("company-edit-editbutton")) { %>
							<tr>
								<td class="main-buttons">
									<button type="button" onclick="location.href='company-edit.do?companyId=${company.companyId}'" class="main-button" 
									onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
									Edit
									</button>
								</td>
							</tr>
					<% } } %>
				</table>
			</div>
		</form>
		<div>
			<!-- main buttons -->
			<table class="main-buttons">
				<tr>
					<td width="25%" align="left">
						<button type="button" onclick="location.href='company-list.do'" class="main-button" 
							onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
							Previous page
						</button>
					</td>	
					<% if ((Boolean)accessInfo.get("company-view-deletebutton")) { %>					
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
					"Are you sure you want to delete client?",
					{
						yesButtonText: "Yes",
						noButtonText: "No",
						onYes: "ModalPopupsConfirmYes()",
						onNo: "ModalPopupsConfirmNo()"
					}
				);
			}
			function ModalPopupsConfirmYes() {
				window.location = "company-delete.do?companyId=${company.companyId}"
				ModalPopups.Close("idConfirm1");
			}
			function ModalPopupsConfirmNo() {
				
				ModalPopups.Cancel("idConfirm1");
			}
	</script>

<%@ include file="/WEB-INF/jsp/bottom.inc" %>
