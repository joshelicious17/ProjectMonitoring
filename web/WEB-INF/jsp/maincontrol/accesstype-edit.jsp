<jsp:useBean id="errors" scope="request" type="java.util.Map" class="java.util.HashMap" />

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
				<a class="process-guide" href="accesstype-menu.do">Access Menu</a> >
				<a class="process-guide" href="accesstype-list.do">List Access</a> >
				Edit Access Type
			</h1>
		</header>
		<% String notdeleteflag = (String) request.getAttribute("notdeleteflag");
		   if (notdeleteflag == "true") { %>
			<div id="initialPopup">
			    <div class="overlay"></div>
			    <div class="popupContent" onclick="hideInitialPopup()">
			        Popup
			    </div>
			</div>
		<% } %>
		<form method="post">
			<div class="contents-normal">
				<table width="80%">
					<tr>
						<td>Access Type ID</td>
						<td>${accessType.accessTypeId}</td>
					</tr>
					<tr>
						<td>Access Type Code</td>
						<td><input type="text" name="accessTypeCode" value="${accessType.accessTypeCode}" size="8" maxlength="8" placeholder="(1-8 characters)" />
						<%
						   if (errors.containsKey("accessTypeCode")) {
							  out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("accessTypeCode") + "</div>");
						   }
						%>
						</td>
					</tr>
					<tr>
						<td>Access Type Name</td>
						<td><input type="text" name="accessTypeName" value="${accessType.accessTypeName}" size="30" maxlength="30" placeholder="(1-8 characters)" />
						<%
						   if (errors.containsKey("accessTypeName")) {
							  out.println("<div class=\"flash error\" id=\"flash_error\">" + errors.get("accessTypeName") + "</div>");
						   }
						%>
						</td>
					</tr>
				</table>
			</div>
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
			<input type="hidden" name="companyId" value="${accessType.accessTypeId}" />
		</form>
	</section>
<script type="text/javascript">		
	function hideInitialPopup() {
	    document.getElementById("initialPopup").style.display = "none";
	}
</script>

<%@ include file="/WEB-INF/jsp/bottom.inc" %>
