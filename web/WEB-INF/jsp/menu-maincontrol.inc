<%@ page import="java.util.Iterator"%>
<%@ page import="workflowsystem.data.maincontrol.AccessTypeDetail"%>
<jsp:useBean id="accessTypeDetails" scope="request"	type="java.util.List" />
<jsp:useBean id="modules" scope="request" type="java.util.List" />
<jsp:useBean id="accessInfo" scope="request" type="java.util.Map" class="java.util.HashMap" />
<% session.removeAttribute("task"); %>
<% session.removeAttribute("taskPrereqs"); %> 
<% session.removeAttribute("taskPrereqIdsToBeDeleteInDb"); %> 
<% session.removeAttribute("taskAssignedUsers"); %> 
<% session.removeAttribute("taskUserIdsToBeDeleteInDb"); %> 
<% session.removeAttribute("taskDetailedTasks"); %>
<% session.removeAttribute("errors"); %>

<%
	Iterator it1 = modules.iterator();
	while (it1.hasNext()) {
		Module module = (Module) it1.next();
		
		if (module.getModuleName().equals("Control"))
		{
%>
			<li class="selected"><a href="<%out.println(module.getUrlPattern());%>"><% out.println(module.getModuleName()); %></a>
<%
		}
		else {
%>
			<li><a href="<%out.println(module.getUrlPattern());%>"><% out.println(module.getModuleName()); %></a> 
<%
		}

 		if (module.getModuleName().equals("Control")) {
 			Iterator it2 = accessTypeDetails.iterator();
 			Boolean firstPassFlag = false;
 			while (it2.hasNext()) {
 				AccessTypeDetail accessTypeDetail = (AccessTypeDetail) it2.next();
 				if (accessTypeDetail.getModuleName().equals("Control") && 
 					accessTypeDetail.getWithinTheMenu()) {
 					if (!firstPassFlag) {
 						firstPassFlag = true;
 %>
						<ul>
<%
					}
%>
					<li><a href="<%out.println(accessTypeDetail.getUrlPattern());%>"><% out.println(accessTypeDetail.getProgramName()); %></a></li>
<%
				}
			}
			if (firstPassFlag) {
%>
				</ul> 
<%
 			}
 		}
%>		</li>
<%
	}
%>