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
		<!-- Main content area -->
		<div class="submenu-button">
			<header>Access Type Menu</header>
			<ul>
				<li><a href="program-list.do">List Program</a></li>
				<li><a href="accesstype-list.do">List Access Type</a></li>
			</ul>
		</div>
	</section>
<%@ include file="/WEB-INF/jsp/bottom.inc" %>