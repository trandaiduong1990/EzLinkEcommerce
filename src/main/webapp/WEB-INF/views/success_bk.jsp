<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>

</head>
<body>
<h1>
	Success..!  
</h1>

<table >
<tr>
<td>Merchant Number :</td><td>${item.merchantNo}</td>
</tr>
<tr>
<td>Access Code :</td><td>${item.accessCode}</td>
</tr>
<tr>
<td>Oreder Number :</td><td>${item.orderNo}</td>
</tr>
<tr>
<td>Merchant Tranx. Ref No :</td><td>${item.merchantTranxRefNo}</td>
</tr>
<tr>
<td>Amount :</td><td>${item.amount}</td>
</tr>
<tr>
<td>Remarks :</td><td>${item.remarks}</td>
</tr>
<tr>
<td>Response Code :</td><td>${item.responseCode}</td>
</tr>
<tr>
<td>QR Image : </td>
<td><img src="data:image/jpg;base64,<c:out value='${item.imgQRData}'/>" /></td>
</tr>

</table>

</body>
</html>
