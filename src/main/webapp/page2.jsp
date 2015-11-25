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
<body>
	<div class="container">

		<div class="wrapper">

			<div class="content">
			 <form id="loadingForm" method="post" action="/ezecom/payez.htm">
			<!-- <form id="loadingForm" method="post" action="/ezecom-1.0.0-BUILD-SNAPSHOT/index.htm">-->
				<input type="hidden" name="eMerchantName" value="WIRECARDMERCHANT" />
			  	<input type="hidden" name="eMerchantNo" value="989887870000014" />
			  	<!-- <input type="hidden" name="eMerchantNo" value="500099990000002" /> -->
			  	<input type="hidden" name="eAccessCode" value="T111" />
			  	<input type="hidden" name="eOrderNo" value="77778" />
			  	<input type="hidden" name="eCurrency" value="702" />
			  	<input type="hidden" name="eTransactionCode" value="PAY" />
			  	<input type="hidden" name="eTransactionDateTime" value="24112015174700" />
			  	<input type="hidden" name="ePay" value="EZ" />
			  	<!-- <input type="hidden" name="eAmount" value="1.5"/> -->
 			  	<input type="hidden" name="eAmount" value="${param.eAmount}"/> 
			  	<!-- <input type="hidden" name="eHashAlgorithm" value="SHA256" />
			  	<input type="hidden" name="eHash" value="f8032ac2df0a7e8621a04ff88988e84c06ee04fda4582d623bd0e4d2ccc37e49"/> -->
			
				<br /> <br /> <br /> <br />
				
				<div style="text-align: center;">
					<p style="font-size: 25px;">Moving to Wirecard QR code Payment Gateway ..</p>
					<div style="text-align: center;">
						<img src="${request.contextPath}img/loader.gif" />
					</div>
					<p>Please keep this page open.</p>
				</div>
				<!-- <div style="text-align: center;">
					<div id="progressBar" class="round-pink">
						<div style="width: 0px;">0%&nbsp;</div>
					</div>
				</div> -->
				</form>
				<br /> <br /> <br /> <br />
			</div>
		</div>
	</div>
</body>
<script>
	function progress(percent, $element) {
		var progressBarWidth = percent * $element.width() / 100;
		$element.find('div').animate({
			width : progressBarWidth
		}, 500).html(percent + "%&nbsp;");
	}

	var max_time = 0;
	var cinterval = setInterval(countdown_timer, 1000);
	function countdown_timer() {
		// decrease timer
		max_time++;

		if (max_time < 2) {
			var percent = max_time * 20;
			progress(percent, $('#progressBar'));
		} else if (max_time == 2) {
			clearInterval(cinterval);
			//var url = "page3";
			//var url ="http://localhost:8080/ezecom/index.jsp"
			//$("form#loadingForm").attr('action', url);
			document.forms[0].eOrderNo.value = Math.floor((Math.random() * 900000) + 100000);
			$("form#loadingForm").submit();
		}

	}
</script>
</html>