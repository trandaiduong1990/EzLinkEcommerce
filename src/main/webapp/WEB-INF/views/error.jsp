<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "javax.servlet.RequestDispatcher" %>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	
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
	
	
	


<style>
.error {
	color: #ff0000;
}
.errorblock{
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding:8px;
	margin:16px;
}

</style>

 <script type="text/javascript"> 
 function myWait(){
	 //alert("before"+document.getElementById("errorForm").name);
	 timeout = window.setTimeout(myRedirect, 2000);
	 //alert("after");
 }
 
 $( document ).ready(function() {
	 myRedirect();
});
 
 function myRedirect(){
	 //alert("Redirecting to merchant's site");
	 var returnUrl = document.forms[0].returnUrl.value;
	 if(returnUrl != '') {
		 document.forms[0].action = returnUrl;
		 document.forms[0].submit();
	 }
	 
// window.location.replace("/ezecom/response.jsp");

	 
 }
 </script>
	
	
</head>
<body >
	<div class="container">

		<div class="wrapper">

			<div class="content">
			<!-- 	<form id="payment">  -->
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

				
				
	<div class="" style="width: 50%; margin: 0px auto;">			
<form:form name="errorForm" method="post"  commandName="item" >
<%-- <form:form name="errorForm" method="post"  commandName="item" action="/ezecom/merchanterror.jsp"> --%>
 <form:errors path="*" cssClass="errorblock" element="div" />
 
 <tr>
<td>Response Code :</td><td>${item.responseCode}</td>
</tr>
<input type="hidden" name="eRC" value="${item.responseCode}" />
<input type="hidden" name="eMerchantName" value="${item.merchantName}" />
<input type="hidden" name="eMerchantNo" value="${item.merchantNo}" />
<input type="hidden" name="eAccessCode" value="${item.accessCode}" />
<input type="hidden" name="eOrderNo" value="${item.orderNo}" />
<input type="hidden" name="eTransactionDateTime" value="${item.dateTime}" />
<input type="hidden" name="ePay" value="${item.payBy}" />
<input type="hidden" name="eHash" value="${item.hashValue}" />
<input type="hidden" name="eHashAlgorithm" value="${item.hashAlgo}" />
<input type="hidden" name="eAmount" value="${item.amount}" />
<input type="hidden" name="eRemarks" value="${item.errorRemark}" />
<input type="hidden" name="eTranxRefNo" value="${item.tranxRefNo}" />
<input type="hidden" name="returnUrl" value="${item.returnUrl}" />


 

</form:form>
				
				
	</div>			
				
					<div style="clear: both;"></div>
				<!--</form>-->
			</div>
		</div>
		<div class="footer">&copy; 2014 - Wirecard Singapore</div>
	</div>
</body>
<script>
	function submit() {
		var url = "page4";
		$("form#payment").attr('action', url);
		$("form#payment").submit();
	}
</script>
</html>