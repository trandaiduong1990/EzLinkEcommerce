<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<div class="container">

		<div class="wrapper">

			<div class="content">
				<form id="payment" method="post" action="/ezecom/merchantHome.jsp">
					<div class="" style="width: 20%; float: left;">
						<div>
							<img src="${request.contextPath}img/wirecard.jpg"
								style="max-width: 200px; max-height: 200px;" />
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
						<p style="font-size: 20px; align: center;"><B>Debit from EZ-Link
							card</B></p>
						<p style="color: #C70821;">
							if you are using a POP-UP BLOCKER, please add the following site
							as your allowed sites. Otherwise, the relevant transaction pages
							from Wirecard cannot be displayed, and your transaction request
							cannot be processed.
						</p>
						<div class="link">
							<ul>
								<li>1. www.wirecard.com</li>
								<!-- <li>2. www.dbsd2pay.dbs.com (for DBS/POSB Account holders)</li>
								<li>3. uniservices1.uobgroup.com (for UOB Account holders)</li>
								<li>4. www.citibank.com.sg (for Citibank Account holders)</li>
								<li>5. www.ocbc.com (for OCBC Account holders)</li>
								<li>6. www.plus.com.sg (for Plus! Account holders)</li> -->
							</ul>
						</div>
						<p class="title"><B>Step 1: Verify Transaction Information</B></p>
						<div id="roundedrect" style="border: 2px solid; border-radius: 25px; ">
						<table cellpadding="5px">
							<tr>
								<td style="text-align: left;"><B>Merchant Name:</B></td>
								<td style="text-align: right;">${item.merchantName}</td>
								<td style="text-align: right;"><B>Merchant NO:</B></td>
								<td style="text-align: right;">${item.merchantNo}</td>
							</tr>
							
							
							<tr>
								<td style="text-align: left;"><B>Merchant Tranx. Ref No :</B></td>
								<td>${item.merchantTranxRefNo}</td>
								<td style="text-align: right;"><B>Oreder Number :</B></td>
								<td>${item.orderNo}</td>
							</tr>
							
							<tr>
								<td style="text-align: left;"><B>Amount (SGD) :</B></td>
								<td>${item.amount}</td>
							</tr>
							
							
						</table>
						</div>
						<div style="clear: both;"></div>
						<p class="title"><B>Step 2: Please scan QR-CODE using Wirecard QR Code Mobile Application</B></p>
			
						<p style="color: #348017;" align="center">
							<B>if you don't have Wirecard QR code Android application, please download it from <a>here</a></B>
						</p>			
						 <div style="clear: both;"></div> 
						<div style="text-align: center;">
							<img src="data:image/jpg;base64,<c:out value='${item.imgQRData}'/>" />
						</div>
						
						
				<input type="hidden" name="e_MerchantName" value="${item.merchantName}" />
			  	<input type="hidden" name="e_MerchantNo" value="${item.merchantNo}" />
			  	<input type="hidden" name="e_AccessCode" value="${item.accessCode}" />
			  	
			  	<input type="hidden" name="e_OrderNo" value="${item.orderNo}" />
			  	<input type="hidden" name="e_MerchantTranxRefNo" value="${item.merchantTranxRefNo}" />
			  	<input type="hidden" name="e_TransactionCode" value="COT" />
			  	<input type="hidden" name="e_DateTime" value="12-11-2012 121212" />
			  	<input type="hidden" name="e_PayBy" value="eZlink" />
			  	<input type="hidden" name="e_HashAlgo" value="SHA256" />
			  	<input type="hidden" name="e_Amount" value="${item.amount}"/>
			  	<input type="hidden" name="e_HashValue" value="${param.e_HashValue}"/>
			  	

						<p class="title"><B>Step 3: Once Payment completed via Mobile Application
						Please Click on Next button</B></p>
						</form>
						<form id="status" method="post" action="/ezecom/check.htm"> 
						<!--<form id="status" method="post" action="/ezecom-1.0.0-BUILD-SNAPSHOT/check.htm"> -->
						<table>
						
						 <tr><td colspan="2"><div  id="info" align="center" style="color: red;font-size: 12pt;"></div></td></tr>
						
						
						</table>
						<input type="hidden" name="e_MerchantTranxRefNo" id="e_MerchantTranxRefNo" value="${item.merchantTranxRefNo}" />
						<input type="hidden" name="e_MerchantNo" id="e_MerchantNo" value="${item.merchantNo}" />
						<input type="hidden" name="e_OrderNo" id="e_OrderNo" value="${item.orderNo}" />
						<input type="hidden" name="e_Amount" id="e_Amount" value="${item.amount}"/>
						<div style="text-align: center;">
							<a href="#" class="button red" onclick="submit();">Next</a> <a
								href="#" class="button">Cancel</a>
						</div>
						
						</form>
						<!-- 
						<form id="reciept" method="post" action="/page5.jsp" target="TheWindow">
						
						<input type="hidden" name="e_MerchantTranxRefNo" id="e_MerchantTranxRefNo" value="${item.merchantTranxRefNo}" />
						<input type="hidden" name="e_MerchantNo" id="e_MerchantNo" value="${item.merchantNo}" />
						<input type="hidden" name="e_OrderNo" id="e_OrderNo" value="${item.orderNo}" />
						<input type="hidden" name="e_Amount" id="e_Amount" value="${item.amount}"/>
						
						
						</form>
						 -->
						<div style="clear: both;"></div>
					</div>
					<div style="clear: both;"></div>
				
			</div>
		</div>
		<div class="footer">&copy; 2014 - Wirecard Singapore</div>
	</div>
</body>

<script>
	function submit() {
		//var url = "page4";
		//alert("test");
		checkPaymentStatus(false);
		//var url="http://localhost:8080/ezecom/status.jsp";
		//$("form#status").attr('action', url);
		//$("form#status").submit();
	}
	
	var timeout = false;
	var checkStatus = true;
	
	$(document).ready(function() {
		var interval = setInterval(function(){ if(checkStatus==true) {checkPaymentStatus(true); }}, 5000);
		setTimeout(function(){ 
			timeout = true;
			clearInterval(interval);
			$('#info').html("<B>STATUS: PAYMENT TIMEOUT.<B>"); }, 130000);
	});
	//$(document).ready(function() {
		//$('#status').submit(
			//function(event) {
				function checkPaymentStatus(auto){
					//alert("test2");
					
					var merchantTranxRefNo=$('#e_MerchantTranxRefNo').val();
					var merchantNo=$('#e_MerchantNo').val();
					var orderNo=$('#e_OrderNo').val();
					var amount=$('#e_Amount').val();
					//alert("merchantNo : "+merchantNo);
					/*
					var data = 'firstname='
						+ encodeURIComponent('myfirst')
						+ '&amp;lastname='
						+ encodeURIComponent('myLast');
						alert($("#status").attr("action"));*/
				$.ajax({
					url : $("#status").attr("action"),
					data :"merchantTranxRefNo=" + merchantTranxRefNo +"&merchantNo=" + merchantNo +"&orderNo=" + orderNo +"&amount=" + amount,
					type : "POST",
	 
					success : function(response) {
						if(response=="1"){
							checkStatus = false;
						//$('#info').html("STATUS :   PAYMENT COMPLETED SUCCESSFULLY " +response);
						$('#info').html("STATUS :   PAYMENT COMPLETED SUCCESSFULLY ");
						printWindow();
						
						//var url="/ezecom/merchantHome.jsp";
						//$("form#payment").attr('action', url);
						//$("form#payment").submit();
						
						//alert(response);
						}
						else if(auto == false){
							$('#info').html("<B>STATUS :  YOUR PAYMENT HAS NOT COMPLETED YET - PLEASE PROCEEED WITH STEP 2 AGAIN </B> ");	
						}
					},
					error : function(xhr, status, error) {
						alert( "failed" );
						//alert(xhr.responseText);
						if(auto == false) {
							$('#info').html("<B>FAILED - PLEASE PROCEED WITH STEP 2 AGAIN </B> ");
						}
					}
					
				});
						
				return false;
				}
			//});
		//});
	
				function printWindow() {
					//alert("clear interval");
					
					var w = window.open('page5.jsp', 'mywindow', 'width=800, height=500');
					//var url="/ezecom/merchantHome.jsp";
					//$("form#payment").attr('action', url);
					//$("form#payment").submit();
					//window.open('', 'TheWindow');
					//document.getElementById('reciept').submit();
					
					//alert("Redirecting to Merchant...");
					//var url="/ezecom/merchantHome.jsp";
					//	$("form#payment").attr('action', url);
					//	$("form#payment").submit();
					
				}
				
				
</script>
</html>