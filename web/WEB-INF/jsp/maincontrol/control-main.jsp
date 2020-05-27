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
		<div class="submenu-button">
			<header>User</header>
			<ul>
				<li><a href="user-list.do">Master User</a></li>
				<li><a href="user-create.do">Create</a></li>
			</ul>
		</div>
		<div class="submenu-button">
			<header>Client</header>
			<ul>
				<li><a href="company-list.do">Master Client</a></li>
				<li><a href="company-create.do">Create</a></li>
			</ul>
		</div>
		<div class="submenu-button">
			<header>Phase</header>
			<ul>
				<li><a href="phase-list.do">Master Phase</a></li>
				<li><a href="phase-create.do">Create</a></li>
			</ul>
		</div>
	</section>

<%@ include file="/WEB-INF/jsp/bottom.inc" %>
