<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!doctype html>
<html>
<head>
	<title>PROJECT MONITORING</title>
</head>
	<style type="text/css">
		<%@include file="resource/demo_table_jui.css" %>
		<%@include file="resource/normalize.css" %>
		<%@include file="resource/jquery-ui-1.9.2.custom.css" %>
		<%@include file="resource/style.css" %>
	</style>
	<script src="http://java.com/js/deployJava.js"></script>
	<script type="text/javascript" src="js/modalpopups.js" language="javascript"></script>
	<script type="text/javascript" language="javascript" src="js/jquery.js"></script> 
	<script type="text/javascript" language="javascript" src="js/jquery.dataTables.js"></script> 
	<script type="text/javascript" language="javascript" src="js/jquery-ui-1.9.2.custom.js"></script> 
	<script type="text/javascript">
	
			$(document).ready(function() {
				$('#wfTable').dataTable( {
					"bJQueryUI": true,
					"sPaginationType": "full_numbers",
					"oLanguage": {
				    	"sProcessing":   "Processing . . .",
				    	"sLengthMenu":   "_MENU_ Show results",
				    	"sZeroRecords":  "No data",
				    	"sInfo":         " _TOTAL_ pending _START_ from _END_ until shown",
				    	"sInfoEmpty":    " 0 pending 0 from 0 until shown",
				    	"sInfoFiltered": "（All _MAX_ extracted）",
				    	"sInfoPostFix":  "",
				    	"sSearch":       "Search:",
				    	"sUrl":          "",
				    	"oPaginate": {
				        	"sFirst":    "First",
				        	"sPrevious": "Previous",
				        	"sNext":     "Next",
				        	"sLast":     "Last"
					    }
					}
				} );
			} );
			
			$(document).ready(function() {
				$('#wfTable2').dataTable( {
					"bJQueryUI": true,
					"sPaginationType": "full_numbers",
					"oLanguage": {
					"sProcessing":   "Processing . . .",
					"sLengthMenu":   "_MENU_ Show results",
					"sZeroRecords":  "No data",
					"sInfo":         " _TOTAL_ pending _START_ from _END_ until shown",
					"sInfoEmpty":    " 0 pending 0 from 0 until shown",
					"sInfoFiltered": "（All _MAX_ extracted）",
					"sInfoPostFix":  "",
					"sSearch":       "Search:",
					"sUrl":          "",
					"oPaginate": {
						"sFirst":    "First",
						"sPrevious": "Previous",
						"sNext":     "Next",
						"sLast":     "Last"
					    }
					}
				} );
			} );
			
			
			$(document).ready(function() {
				$('#wfTable3').dataTable( {
					"bJQueryUI": true,
					"sPaginationType": "full_numbers",
					"oLanguage": {
					"sProcessing":   "Processing . . .",
					"sLengthMenu":   "_MENU_ Show results",
					"sZeroRecords":  "No data",
					"sInfo":         " _TOTAL_ pending _START_ from _END_ until shown",
					"sInfoEmpty":    " 0 pending 0 from 0 until shown",
					"sInfoFiltered": "（All _MAX_ extracted）",
					"sInfoPostFix":  "",
					"sSearch":       "Search:",
					"sUrl":          "",
					"oPaginate": {
						"sFirst":    "First",
						"sPrevious": "Previous",
						"sNext":     "Next",
						"sLast":     "Last"
					    }
					}
				} );
			} );

			$(document).ready(function() {
				$('#wfTableInp').dataTable( {
					"bJQueryUI": true,
					"sPaginationType": "full_numbers",
					"oLanguage": {
					"sProcessing":   "Processing . . .",
					"sLengthMenu":   "_MENU_ Show results",
					"sZeroRecords":  "No data",
					"sInfo":         " _TOTAL_ pending _START_ from _END_ until shown",
					"sInfoEmpty":    " 0 pending 0 from 0 until shown",
					"sInfoFiltered": "（All _MAX_ extracted）",
					"sInfoPostFix":  "",
					"sSearch":       "Search:",
					"sUrl":          "",
					"oPaginate": {
						"sFirst":    "First",
						"sPrevious": "Previous",
						"sNext":     "Next",
						"sLast":     "Last"
					    }
					}
				} );
			} );

			$(document).ready(function() {
				$('#wfTableOut').dataTable( {
					"bJQueryUI": true,
					"sPaginationType": "full_numbers",
					"oLanguage": {
					"sProcessing":   "Processing . . .",
					"sLengthMenu":   "_MENU_ Show results",
					"sZeroRecords":  "No data",
					"sInfo":         " _TOTAL_ pending _START_ from _END_ until shown",
					"sInfoEmpty":    " 0 pending 0 from 0 until shown",
					"sInfoFiltered": "（All _MAX_ extracted）",
					"sInfoPostFix":  "",
					"sSearch":       "Search:",
					"sUrl":          "",
					"oPaginate": {
						"sFirst":    "First",
						"sPrevious": "Previous",
						"sNext":     "Next",
						"sLast":     "Last"
					    }
					}
				} );
			} );
			function goBack()
		  	{
		  		window.history.back()
		  	}

			$(document).ready(function(){
			    $(".tabbable").find(".tab").hide();
			    $(".tabbable").find(".tab").first().show();
			    $(".tabbable").find(".tabs li").first().find("a").addClass("active");
			    $(".tabbable").find(".tabs").find("a").click(function(){
			        tab = $(this).attr("href");
			        $(".tabbable").find(".tab").hide();
			        $(".tabbable").find(".tabs").find("a").removeClass("active");
			        $(tab).show();
			        $(this).addClass("active");
			        return false;
			    });
			});

    </script>
<body>
<div id="holder">
	<header>
		<table>
			<tr>
				<td>
				 	<img src="images/logo.jpg" style="padding: 0; margin" >
			 	</td>
				<td width="85%" align="right">
					<div class="welcome-bar">ようこそ<b>${userFullName}</b>&nbsp;|&nbsp;<a href="logout">ログアウト</a></div>
				</td>
			</tr>
		</table>
	</header>
