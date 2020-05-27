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
				<a class="process-guide" href="program-list.do">List Program</a> > 
				View Program
			</h1>
		</header>
		<form method="post">
			<div class="contents-normal">
				<table width="80%">
					<tr>
						<td>Program ID</td>
						<td>${program.programId}</td>
					</tr>
					<tr>
						<td>Program Name</td>
						<td>${program.programName}</td>
					</tr>
					<tr>
						<td>Module Name</td>
						<td>
							${program.moduleName}
							&nbsp;&nbsp;&nbsp;<input type="checkbox" name="withinTheMenu" value="1" ${program.withinTheMenu ? 'checked' : ''} disabled>&nbsp;Within the menu
						</td>
					</tr>
					<tr>
						<td>Java Servlet Reference</td>
						<td>${program.javaServletReference}</td>
					</tr>
					<tr>
						<td>URL Pattern</td>
						<td>${program.urlPattern}</td>
					</tr>
					<% if (accessInfo.get("program-edit-editbutton") != null) { %>
						<% if ((Boolean)accessInfo.get("program-edit-editbutton")) { %>
							<tr>
								<td class="main-buttons">
									<button type="button" onclick="location.href='program-edit.do?programId=${program.programId}'" class="main-button" 
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
						<button type="button" onclick="location.href='program-list.do'" class="main-button" 
							onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
							Previous page
						</button>
					</td>	
					<% if ((Boolean)accessInfo.get("program-view-deletebutton")) { %>						
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
					"Are you sure you want to delete program?", 
					{
						yesButtonText: "Yes",
						noButtonText: "No",
						onYes: "ModalPopupsConfirmYes()",
						onNo: "ModalPopupsConfirmNo()"
					}
				);
			}
			function ModalPopupsConfirmYes() {
				window.location = "program-delete.do?programId=${program.programId}"
				ModalPopups.Close("idConfirm1");
			}
			function ModalPopupsConfirmNo() {
				
				ModalPopups.Cancel("idConfirm1");
			}
	</script>

<%@ include file="/WEB-INF/jsp/bottom.inc" %>
