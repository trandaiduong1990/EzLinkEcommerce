
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
<body onload="initializeMainDiv();">
	<div class="container">

		<div class="wrapper">

			<div class="content">
				<div class="modal-header">
					<div>
						<div style="width: 150px; float: left;">
							<img src="${request.contextPath}img/wirecard.jpg"
								style="max-width: 120px; max-height: 120px;" />
						</div>
						<div
							style="width: 280px; float: left; border-left: 1px #dfdfdf solid; padding-left: 30px;">
							<p style="font-size: 20px; padding: 0px;">Transaction Notice</p>
						</div>
						<div style="clear: both;"></div>
					</div>
					<hr />
				</div>
				<div class="modal-body">
					<div style="width: 100%; text-align: center;">
						<p style="color: #1abc9c; font-size: 20px;">Payment
							Reciept</p>
							
	<table class="center" cellpadding="10">	
	<tr><td style="text-align: right;">Merchant No : </td><td><div id="merchNo"></div></td></tr>
	<tr></tr>	
	<tr><td style="text-align: right;">Order No : </td><td><div id="orderNo"></div></td></tr>
	<tr></tr>		
	<tr><td style="text-align: right;">Amount (SGD) : </td><td><div id="amount"></div></td></tr>
	<tr><td style="text-align: right;">Date Time : </td><td><div id="dateTime"></div></td></tr>
	<tr></tr>	




</table>	



						<div style="clear: both;"></div>
					</div>
					<!-- 
					<table class="center" cellpadding="10">
						<tr>
							<td style="text-align: right;">Merchant Name:</td>
							<td>Wirecard</td>
						</tr>
						<tr>
							<td style="text-align: right;">Nets Reference Code:</td>
							<td>2014122410088744</td>
						</tr>
						<tr>
							<td style="text-align: right;">Date Time:</td>
							<td>25/12/2014 15:46:34</td>
						</tr>
						<tr>
							<td style="text-align: right;"><span>Transaction
									Amount:</span></td>
							<td><span style="color: #e74c3c; font-size: 20px;">$34.44</span></td>
						</tr>
					</table>
					 -->
				</div>
				<div class="modal-footer">
					<hr class="title" />

					<div style="text-align: center;">
						<a href="#" class="button">Print</a> <a href="#"
							class="button red" onclick="javascript:closeMe();">Close</a>
					</div>
					<div style="clear: both;"></div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</body>
<script>
function closeMe()
{
    window.opener = self;
    window.close();
}

function initializeMainDiv() {
	
	document.getElementById("merchNo").innerHTML = 
    window.opener.document.getElementById("eMerchantNo").value;
	/* document.getElementById("merchRefNo").innerHTML = 
    window.opener.document.getElementById("e_MerchantTranxRefNo").value; */
	document.getElementById("orderNo").innerHTML =
    window.opener.document.getElementById("eOrderNo").value;
	document.getElementById("amount").innerHTML = 
    window.opener.document.getElementById("eAmount").value;
	document.getElementById("dateTime").innerHTML = 
    window.opener.document.getElementById("eTransactionDateTime1").value;
	 
	 
	//window.opener.document.getElementId("payment").submit();
	//alert("Moving to Merxhant site..");
	window.opener.document.forms[0].submit();
	
	
   
}
</script>
</html>
