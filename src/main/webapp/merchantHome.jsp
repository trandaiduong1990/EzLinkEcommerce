<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="${request.contextPath}css/layout.css" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.11.0.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>

</head>
<script type = "text/javascript" >
    history.pushState(null, null, 'merchantHome.jsp');
    window.addEventListener('popstate', function(event) {
    history.pushState(null, null, 'merchantHome.jsp');
    });
    </script>
<body>
	<div class="container">

		<div class="wrapper">

			<div class="content">
				<div class="" style="width: 20%; float: left;">
					<div>

						<div class="logo">MyShop</div>
					</div>
					<div class="slidebar">
						<ul>
							<li>Privacy Policy</li>
							<li>Security Guidelines</li>
							<li>Customer Service</li>
						</ul>
					</div> 
				</div>

				<div class=""
					style="width: 78%; float: right; padding-left: 20px; border-left: 1px #dfdfdf solid;">
					<p class="title">Merchant Receipt</p>
					
					<div style="clear: both;"></div>
					<br /> <br />
					
					<div id="roundedrect" style="border: 2px solid; border-radius: 25px; ">
					<table>
<tr>
<td><B>Merchant Number :</B></td><td><%=request.getParameter("eMerchantName") %></td>
</tr>
<tr>
<td><B>Access Code :</B></td><td><%=request.getParameter("eAccessCode")%></td>
</tr>
<tr>
<td><B>Order Number :</B></td><td><%=request.getParameter("eOrderNo")%></td>
</tr>
<tr>
<td><B>Amount :</B></td><td><%=request.getParameter("eAmount")%></td>
</tr>
<tr>
<td><B>Date Time :</B></td><td><%=request.getParameter("eTransactionDateTime1")%></td>
</tr>

</table>
					
					
	</div>				
					
					
					
					
					<br /> <br />
				</div>
				<div style="clear: both;"></div>
			</div>
		</div>
		<div class="footer">&copy; 2014 - Wirecard Singapore</div>
	</div>
</body>



</html>