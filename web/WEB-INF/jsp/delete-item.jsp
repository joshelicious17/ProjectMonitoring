<%@ include file="top.inc" %>
<%@ include file="middle.inc" %>

<br/><br/>
<table cellpadding="0" cellspacing="10" valign="middle" border="0" width="780" align="left">
	<tr>
		<td align="center">Are you sure you want to delete records?</td>
	</tr>
	<tr>
		<td align="center">
			<form method="post">
			   <input type="submit" name="delete-button" value="DELETE" />
			   <input type="submit" name="cancel-button" value="CANCEL" />   
			   <input type="hidden" name="id" value="${params['id']}" />
			</form>
		</td>
	</tr>
</table>



<%@ include file="bottom.inc" %>