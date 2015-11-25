
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>

<title>My Shop</title>
<link type="text/css" rel="stylesheet"
	href="${request.contextPath}css/layout.css" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.11.0.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>

</head>
<body class="body">
	<div class="header">
		<div class="logo">MyShop</div>

	</div>
	<div class="topnav">
		<div class="menu">
			<ul>
				<li><a href="default.asp">New Arrivals</a></li>
				<li><a href="news.asp">Clothing</a></li>
				<li><a href="contact.asp">Shoe</a></li>
				<li><a href="about.asp">Accessories</a></li>
				<li><a href="about.asp">Apartment</a></li>
				<li><a href="about.asp">Be The Buyer</a></li>
				<li><a href="about.asp">Vintage</a></li>
				<li class="last"><a href="about.asp">Style Gallery</a></li>
			</ul>
		</div>
	</div>
	<div class="container">

		<div class="wrapper">

			<div class="content">
				
				<!-- <form method="post" action="http://localhost:8080/ezecom/index.jsp"> -->
					<div>
						<table>
							<tr>
								<td><img src="${request.contextPath}img/shoping_cart.png" /></td>
								<td><p style="font-size: 20px;">Error Details :</p></td>
							</tr>
						</table>
					</div>

					<div>
					
					
												
												<table>
							<tr>
							<td>Merchant Number :</td><td><%=request.getParameter("eMerchantName") %></td>
							</tr>
							<tr>
							<td>Access Code :</td><td><%=request.getParameter("eAccessCode")%></td>
							</tr>
							<tr>
							<td>Oreder Number :</td><td><%=request.getParameter("eOrderNo")%></td>
							</tr>
							<tr>
							<td>Merchant Tranx. Ref No :</td><td><%=request.getParameter("e_MerchantTranxRefNo")%></td>
							</tr>
							<tr>
							<td>Amount :</td><td><%=request.getParameter("eAmount")%></td>
							</tr>
							<tr>
							<td>Remarks :</td><td><%=request.getParameter("eRemarks")%></td>
							</tr>
							
							<tr>
							
							<td>Response Code : </td><td><%=request.getParameter("eRC") %></td>
							</tr>
							</table>
					

				<div style="clear: both;"></div>
			</div>


		</div>

	</div>
	<div class="footer">&copy; 2014 - Wirecard Singapore</div>
	</div>
</body>
<script>
	$(document).ready(function() {

		calSubtotal();

		$("#quantity1").focusout(function() {
			var quantity1Str = $("#quantity1").val();

			if (quantity1Str != null && quantity1Str != '') {
				calSubtotal();
			} else {
				alert("Invalid Number!");
			}

		});

		$("#quantity2").focusout(function() {

			var quantity2Str = $("#quantity2").val();

			if (quantity2Str != null && quantity2Str != '') {
				calSubtotal();
			} else {
				alert("Invalid Number!");
			}

		});
	});

	function calSubtotal() {

		var quantity1Str = $("#quantity1").val();
		var price1Str = $("#price1").html();

		var quantity2Str = $("#quantity2").val();
		var price2Str = $("#price2").html();

		if (quantity1Str != null && quantity1Str != '' && quantity2Str != null
				&& quantity2Str != '') {
			var price1Num = parseFloat(price1Str);
			var quantity1Num = parseFloat(quantity1Str);

			var price2Num = parseFloat(price2Str);
			var quantity2Num = parseFloat(quantity2Str);
			
			var subtotal1 = (price1Num * quantity1Num);
			var subtotal2 = (price2Num * quantity2Num);

			var total = (price1Num * quantity1Num)
					+ (price2Num * quantity2Num);

			$("#subtotal1").html(subtotal1);
			$("#subtotal2").html(subtotal2);
			$("#total").html(total);
		}
	}

	function ezLink() {
		//var url = "page2";
	//var url ="http://localhost:8080/ezecom/index.jsp"
	//var url ="/ezecom-1.0.0-BUILD-SNAPSHOT/index.jsp"
		
		$("form#shoppingCartForm").attr('action', url);
		
		$("form#shoppingCartForm").submit();
	}
</script>
</html>
