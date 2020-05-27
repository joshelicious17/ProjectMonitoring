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
				<a class="process-guide" href="user-list.do">List User</a> > 
				View User
			</h1>
		</header>
		<form method="post">
			<div class="contents-normal">
				<table width="80%">
				  <tr>
						<td>User ID</td>
						<td>${user.userId}</td>
					</tr>
					<tr>
						<td>User Name</td>
						<td>${user.userName}</td>
					</tr>
					<tr>
						<td>User Fullname</td>
						<td>${user.userFullname}</td>
					</tr>
					<tr>
						<td>Email Address</td>
						<td>${user.userEmail}</td>
					</tr>
					<tr>
						<td>Client Code</td>
						<td>${user.companyCode}</td>
					</tr>
					<tr>
						<td>User Access Type</td>
						<td>${user.accessTypeCode}</td>
					</tr>
					<% if (accessInfo.get("user-edit-editbutton") != null ) { %>
						<% if ((Boolean)accessInfo.get("user-edit-editbutton")) { %>
							<tr>
								<td class="main-buttons">
									<button type="button" onclick="location.href='user-edit.do?userId=${user.userId}'" class="main-button" 
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
						<button type="button" onclick="location.href='user-list.do'" class="main-button" 
							onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
							Previous page
						</button>
					</td>
					<% if ((Boolean)accessInfo.get("user-view-deletebutton")) { %>										
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
				"Are you sure you want to delete user?",  
				{
					yesButtonText: "Yes",
					noButtonText: "No",
					onYes: "ModalPopupsConfirmYes()",
					onNo: "ModalPopupsConfirmNo()"
				}
			);
		}
		function ModalPopupsConfirmYes() {
			window.location = "user-delete.do?userId=${user.userId}"
			ModalPopups.Close("idConfirm1");
		}
		function ModalPopupsConfirmNo() {
			
			ModalPopups.Cancel("idConfirm1");
		}
</script>

<%@ include file="/WEB-INF/jsp/bottom.inc" %>
