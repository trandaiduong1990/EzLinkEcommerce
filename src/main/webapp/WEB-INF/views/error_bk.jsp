<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "javax.servlet.RequestDispatcher" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Item Check Out</title>

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
	 timeout = window.setTimeout(myRedirect, 4000);
	 //alert("after");
 }
 function myRedirect(){
	 alert("Redirecting to merchant's site");
	 document.forms[0].submit();
// window.location.replace("/ezecom/response.jsp");

	 
 }
 </script>

</head>
<body onLoad="myWait()">


<form:form name="errorForm" method="post"  commandName="item" action="/ezecom/merchant.jsp">
 <form:errors path="*" cssClass="errorblock" element="div" />
 
 <tr>
<td>Response Code :</td><td>${item.responseCode}</td>
</tr>
<input type="hidden" name="e_responseCode" value="${item.responseCode}" />
<input type="hidden" name="e_MerchantName" value="${item.merchantName}" />
<input type="hidden" name="e_MerchantNo" value="${item.merchantNo}" />
<input type="hidden" name="e_AccessCode" value="${item.accessCode}" />
<input type="hidden" name="e_OrderNo" value="${item.orderNo}" />
<input type="hidden" name="e_MerchantTranxRefNo" value="${item.merchantTranxRefNo}" />
<input type="hidden" name="e_DateTime" value="${item.dateTime}" />
<input type="hidden" name="e_PayBy" value="${item.payBy}" />
<input type="hidden" name="e_HashValue" value="${item.hashValue}" />
<input type="hidden" name="e_HashAlgo" value="${item.hashAlgo}" />
<input type="hidden" name="e_Amount" value="${item.amount}" />
<input type="hidden" name="e_remarks" value="${item.errorRemark}" />


 

</form:form>

<%--


 // Thread.sleep(10000); // sleep 10 seconds

//RequestDispatcher rd = request.getRequestDispatcher("/response.jsp");
 // rd.include(request, response);
//rd.forward(request, response);


//response.setHeader("Refresh", "60; URL=/response.jsp");

--%>

</body>
</html>