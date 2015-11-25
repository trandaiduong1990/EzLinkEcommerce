<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Merchant's Page..!  
</h1>

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
</body>
</html>
