<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--<--%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Merchant Home</title>
</head>
<body>

<!-- a href="http://localhost:8080/ezecom/">Click Here to Test GET request</a--> 

  
  <!-- <form method="post" action="http://localhost:8080/ezecom/index.jsp"> 
  <form method="post" action="/ezecom/index.htm">-->
  <form method="post" action="/index.htm">

    <table>
    <tr>
        <td>Merchant No. : </td>
        <td><input type="text" name="e_MerchantNo" /></td> 
    </tr>
    <tr>
        <td>Access Code :</td>
        <td><input type="text" name="e_AccessCode" /></td> 
    </tr>
    <tr>
        <td>Order Number : </td>
        <td><input type="text" name="e_OrderNo" /></td> 
    </tr>
    <tr>
        <td>Merchant Tranx. Ref. Number : </td>
        <td><input type="text" name="e_MerchantTranxRefNo" /></td>
    </tr>
    <tr>
        <td>Transaction Code : </td>
        <td><input type="text" name="e_TransactionCode" /></td>
    </tr>
    <tr>
        <td>Amount : </td>
        <td><input type="text" name="e_Amount" /></td>
    </tr>
    <tr>
        <td>Date Time (dd-MM-yyyy HHmmss) : </td>
        <td><input type="text" name="e_DateTime" /></td>
    </tr>
    <tr>
        <td>Pay By : </td>
       <!-- <td><input type="text" name="e_PayBy" /></td>-->
       <td><input type="radio" name="e_PayBy" value="eZlink" checked="checked"/>EzLink</td>
       <td><input type="radio" name="e_PayBy" value="PayPal" />PayPal</td>
       
    </tr>
    <tr>
        <td>Hash Value : </td>
        <td><input type="text" name="e_HashValue" /></td>
    </tr>
    <tr>
        <td>Hash Algo : </td>
        <td><input type="text" name="e_HashAlgo" /></td>
    </tr>
    
    <tr>
        <td colspan="2">
            <input type="submit" value="Check Out"/>
        </td>
    </tr>
</table>
  <input type="hidden" name="e_MerchantName" value="WIRECARDMERCHANT" />
  <input type="hidden" name="e_Version" value="1.0" />   
</form>

</body>
</html>