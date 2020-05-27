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
				<a class="process-guide" href="phase-list.do">List Phase</a> > 
				View Phase
			</h1>
		</header>
		<form method="post">
			<div class="contents-normal">
				<table width="80%">
					<tr>
						<td>Phase ID</td>
						<td>${phase.phaseId}</td>
					</tr>
					<tr>
						<td>Phase Code</td>
						<td>${phase.phaseCode}</td>
					</tr>
					<tr>
						<td>Phase Name</td>
						<td>${phase.phaseName}</td>
					</tr>
					<% if (accessInfo.get("phase-edit-editbutton") != null) { %>
						<% if ((Boolean)accessInfo.get("phase-edit-editbutton")) { %>
							<tr>
								<td class="main-buttons">
									<button type="button" onclick="location.href='phase-edit.do?phaseId=${phase.phaseId}'" class="main-button" 
										onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
										Edit
									</button>
								</td>
							</tr>
					<% } }%>
				</table>
			</div>
		</form>
		<div>
			<!-- main buttons -->
			<table class="main-buttons">
				<tr>
					<td width="25%" align="left">
						<button type="button" onclick="location.href='phase-list.do'" class="main-button" 
							onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
							Previous Page
						</button>
					</td>	
					<% if ((Boolean)accessInfo.get("phase-view-deletebutton")) { %>															
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
				"Are you sure you want to delete phase?", 
				{
					yesButtonText: "Yes",
					noButtonText: "No",
					onYes: "ModalPopupsConfirmYes()",
					onNo: "ModalPopupsConfirmNo()"
				}
			);
		}
		function ModalPopupsConfirmYes() {
			window.location = "phase-delete.do?phaseId=${phase.phaseId}"
			ModalPopups.Close("idConfirm1");
		}
		function ModalPopupsConfirmNo() {
			
			ModalPopups.Cancel("idConfirm1");
		}
</script>

<%@ include file="/WEB-INF/jsp/bottom.inc" %>
