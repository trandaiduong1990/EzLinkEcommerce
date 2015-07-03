
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
				 <form id="shoppingCartForm" method="post"> 
				<!-- <form method="post" action="http://localhost:8080/ezecom/index.jsp"> -->
					<div>
						<table>
							<tr>
								<td><img src="${request.contextPath}img/shoping_cart.png" /></td>
								<td><p style="font-size: 20px;">Your Shopping Bag</p></td>
							</tr>
						</table>
					</div>

					<div>
						<table class="bagTable">
							<thead>
								<tr>

									<th>Product</th>
									<th>Unit Price</th>
									<th>Quantity</th>
									<th>Subtotal</th>
									<th>Move To</th>
								</tr>

							</thead>
							<tbody>

								<tr>

									<td>
										<div style="float: left">
											<img class="product"
												src="${request.contextPath}img/lzd-4442.jpg" />
										</div>
										<div style="float: left;">
											<b>LZD Pointed Toe D'orsay Pumps</b>
										</div>
									</td>
									<td>S$ <span id="price1">0.01</span></td>
									<td><input type="text" value="1" class="quantity"
										id="quantity1" /></td>
									<td>S$ <span id="subtotal1">0.01</span></td>
									<td><a href="#" style="color:#333 !important;">Remove</a></td>

								</tr>
								<tr>

									<td>
										<div style="float: left">
											<img class="product"
												src="${request.contextPath}img/lzd-5499.jpg" />
										</div>
										<div style="float: left;">
											<b>LZD Trapeze Tote With Stud Details GREY</b>
										</div>
									</td>
									<td>S$ <span id="price2">0.02</span></td>
									<td><input type="text" value="1" class="quantity"
										id="quantity2" /></td>
									<td>S$ <span id="subtotal2">0.02</span></td>
									<td><a href="#" style="color:#333 !important;">Remove</a></td>

								</tr>


							</tbody>
						</table>
						<br /> <br />

						<div class="subtotal">
							<p style="font-size: 15px;">
								<span>Subtotal (SGD) : </span> <input type="text" name="e_Amount" id="e_Amount"
									readonly="true"/>
									
							</p>
							<a href="#" class="button red" onclick="ezLink();">EZ-Link
								Checkout</a> <a href="#" class="button">Checkout</a>
						</div>
	 <!-- 
	 <table>
	
	  <tr>
        <td>Amount : </td>
        <td><input type="text" name="e_Amount" /></td>
    </tr>
   
    <tr>
        <td>Hash Value : </td>
        <td><input type="text" name="e_HashValue" /></td>
    </tr>
    
    <tr>
    
        <td colspan="2">
            <input type="submit" value="Check Out"/>
        </td>
        
       <td></td><td>
        <a href="#" class="button red" onclick="ezLink();">EZ-Link
								Checkout</a>
								
								 <a href="#" class="button">Checkout</a>
								 </td>
    </tr>
	</table>
	--><!-- 
	<input type="hidden" name="e_MerchantName" value="WIRECARDMERCHANT" />
  	<input type="hidden" name="e_MerchantNo" value="989887870000014" />
  	<input type="hidden" name="e_AccessCode" value="T111" />
  	<input type="hidden" name="e_OrderNo" value="1" />
  	<input type="hidden" name="e_MerchantTranxRefNo" value="1" />
  	<input type="hidden" name="e_TransactionCode" value="COT" />
  	<input type="hidden" name="e_DateTime" value="12-11-2012 121212" />
  	<input type="hidden" name="e_PayBy" value="eZlink" />
  	<input type="hidden" name="e_HashAlgo" value="SHA256" />
  	   -->
	 
	 					
				</form>
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
			//$("#e_Amount").html(total);
			$("#e_Amount").val(total);
		}
	}

	function ezLink() {
		var url = "page2.jsp";
	//var url ="http://localhost:8080/ezecom/index.jsp"
		
		$("form#shoppingCartForm").attr('action', url);
		
		$("form#shoppingCartForm").submit();
	}
</script>
</html>
