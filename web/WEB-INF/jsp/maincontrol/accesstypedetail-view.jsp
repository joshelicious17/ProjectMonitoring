<%@ include file="/WEB-INF/jsp/top.inc" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
				<a class="process-guide" href="accesstype-view.do?accessTypeId=${accessTypeDetail.accessTypeId}">View Access Type </a> > 
				View Access Type Details
			</h1>
		</header>
		<form method="post">
			<div class="contents-normal">
				<table width="80%">
					<tr>
						<td width="20%">Access Type Detail ID</td>
						<td width="80%">${accessTypeDetail.accessTypeDetailId}</td>
					</tr>
					<tr>
						<td>Access Type ID</td>
						<td>${accessTypeDetail.accessTypeId}</td>
					</tr>
					<tr>
						<td>Program Name</td>
						<td>${accessTypeDetail.programName}</td>
					</tr>
					<tr>
						<td>Access Right</td>
						<td>${accessTypeDetail.accessRight}</td>
					</tr>
					<% if (accessInfo.get("accesstypedetail-edit-editbutton") != null) { %>
						<% if ((Boolean)accessInfo.get("accesstypedetail-edit-editbutton")) { %>
							<tr>
								<td class="main-buttons">
									<button type="button" onclick="location.href='accesstypedetail-edit.do?accessTypeDetailId=${accessTypeDetail.accessTypeDetailId}'" class="main-button" 
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
						<button type="button" onclick="location.href='accesstype-view.do?accessTypeId=${accessTypeDetail.accessTypeId}'" class="main-button" 
							onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
							Previous page
						</button>
					</td>
					<% if ((Boolean)accessInfo.get("accesstypedetail-view-deletebutton")) { %>
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
	</section>
	
<script type="text/javascript">		
		function ModalPopupsConfirm() {
			ModalPopups.Confirm("idConfirm1",
				"Confirm delete information",
				"Are you sure you want to delete access type detail?",
				{
					yesButtonText: "Yes",
					noButtonText: "No",
					onYes: "ModalPopupsConfirmYes()",
					onNo: "ModalPopupsConfirmNo()"
				}
			);
		}
		function ModalPopupsConfirmYes() {
			window.location = "accesstypedetail-delete.do?accessTypeDetailId=${accessTypeDetail.accessTypeDetailId}"
			ModalPopups.Close("idConfirm1");
		}
		function ModalPopupsConfirmNo() {
			
			ModalPopups.Cancel("idConfirm1");
		}
</script>
<%@ include file="/WEB-INF/jsp/bottom.inc" %>
