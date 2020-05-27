<jsp:useBean id="errors" scope="request" type="java.util.Map" class="java.util.HashMap" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
				<a class="process-guide" href="accesstype-list.do">List Access type</a> > 
				<a class="process-guide" href="accesstype-view.do?accessTypeId=${accessTypeDetail.accessTypeId}">View Access Type</a> > 
				Edit Access Type Details
			</h1>
		</header>
		<form method="post">
			<div class="contents-normal">
				<table width="80%">
					<tr>
						<td width="20%">Access Type Detail ID</td>
						<td width="80%">${accessTypeDetail.accessTypeDetailId}</td>
						<input type="hidden" name="accessTypeDetailId" value="${accessTypeDetail.accessTypeDetailId}" />
					</tr>
					<tr>
						<td width="20%">Access Type ID</td>
						<td width="80%">${accessTypeDetail.accessTypeId}</td>
						<input type="hidden" name="accessTypeId" value="${accessTypeDetail.accessTypeId}" />
					</tr>
					<tr>
						<td>Program Name</td>
						<td>
							<select name="programName">						
								<c:forEach items="${programs}" var="program">
									<option value="${program.programName}" ${accessTypeDetail.programName == program.programName ? 'selected' : ''}>${program.programName}</option>
								</c:forEach>
							</select>
						<%
						   if (errors.containsKey("programName")) {
							  out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("programName") + "</div>");
						   }
						%>
						</td>
					</tr>
					<tr>
						<td>Access Right</td>
						<td>
							<select name="accessRight">						
								<option value="Full access" ${accessTypeDetail.accessRight == 'Full access' ? 'selected' : ''}>Full access</option>
								<option value="Write only" ${accessTypeDetail.accessRight == 'Write only' ? 'selected' : ''}>Write only</option>
								<option value="Read only" ${accessTypeDetail.accessRight == 'Read only' ? 'selected' : ''}>Read only</option>
								<option value="Not available" ${accessTypeDetail.accessRight == 'Not available' ? 'selected' : ''}>Not available</option>
							</select>
						<%
						   if (errors.containsKey("accessRight")) {
							  out.println("<span class=\"error\">" + errors.get("accessRight") + "</span>");
						   }
						%>
						</td>
					</tr>			
				</table>
			</div>
			<!-- main buttons -->
		  	<div class="main-buttons">
				<button type="submit" name="submit-button" class="main-button" 
					onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
					Save
				</button>
				<button type="submit" name="cancel-button" class="main-button" 
					onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
					Cancel
				</button>
		  	</div>
		</form>
	</section>

<%@ include file="/WEB-INF/jsp/bottom.inc" %>
