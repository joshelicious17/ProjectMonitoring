<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="top.inc" %>
	<!-- Navigation -->
	<nav>
		<ul>
			<li><a href="home.do">HOME</a></li>
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
				Access denied.
			</h1>
		</header>
		<table>
			<tr>
				<td width="75%" align="right">
					<div class="main-buttons">
						<button type="button" onclick="goBack()" class="main-button" 
							onmouseover="this.className='main-button-over'" onmouseout="this.className='main-button'">
							Previous page
						</button>
					</div>
				</td>			
			</tr>
		</table>
	</section>
<%@ include file="bottom.inc" %>