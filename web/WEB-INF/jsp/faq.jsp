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
				FAQ
			</h1>
		</header>
			<table><br>
				<dl>
					<dt>SAMPLE</dt>			
					<dt>SAMPLE、</dt>
					<dt>SAMPLE。</dt><br>
					<dt>SAMPLE</dt>					
					<ul style="padding-left:20px">
						<li>■ SAMPLE</li>
						<li>■ SAMPLE</li>
						<li>■ SAMPLE</li>
						<li>■ SAMPLE</li>
						<li>■ SAMPLE</li>
					</ul>
				</dl>
			</table>
	</section>
<%@ include file="bottom.inc" %>