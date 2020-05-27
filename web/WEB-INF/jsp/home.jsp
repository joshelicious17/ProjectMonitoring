<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="top.inc" %>
	<!-- Navigation -->
	<nav>
		<ul>
			<li class="selected"><a href="home.do">HOME</a></li>
			<%@ include file="menu-home.inc" %>
		</ul>
	</nav>
	<aside>
		<header>OTHER LINKS</header>
		<ul>
			<li><a href="faq.do">FAQ</a></li>
		</ul>
	</aside>

	<section>
		<!-- Main content area -->
		<header>
			<h1>
				SAMPLE . . .
		</header>
		<div class="submenu-button">
			<header>New</header>
			<ul>
				<li><a href="project-create.do">Project</a></li>
				<li><a href="user-create.do">User</a></li>
				<li><a href="company-create.do">Company</a></li>
				<li><a href="phase-create.do">Phase</a></li>
				<li><a href="accesstype-menu.do">Access Menu</a></li>
			</ul>
		</div>
	</section>
<%@ include file="bottom.inc" %>