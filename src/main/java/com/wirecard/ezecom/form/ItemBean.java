package com.wirecard.ezecom.form;

import java.util.Date;



/**
@author WCCTTI-JANAHAN
 */

public class ItemBean {
	
	private String merchantNo,accessCode,orderNo,remarks,transactionCode,merchantName,currency;
	private String dateTime,payBy,hashValue,hashAlgo,responseCode,errorRemark, tranxRefNo;
	
	private String amount;
	private String returnUrl;
	private Date validatedMerchantTranxDate;
	private byte[] imgQRData;
	
	
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getPayBy() {
		return payBy;
	}
	public void setPayBy(String payBy) {
		this.payBy = payBy;
	}
	public String getHashValue() {
		return hashValue;
	}
	public void setHashValue(String hashValue) {
		this.hashValue = hashValue;
	}
	public String getHashAlgo() {
		return hashAlgo;
	}
	public void setHashAlgo(String hashAlgo) {
		this.hashAlgo = hashAlgo;
	}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	public String getTransactionCode() {
		return transactionCode;
	}
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public Date getValidatedMerchantTranxDate() {
		return validatedMerchantTranxDate;
	}
	public void setValidatedMerchantTranxDate(Date validatedMerchantTranxDate) {
		this.validatedMerchantTranxDate = validatedMerchantTranxDate;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getImgQRData() {
		//return imgQRData;
		return new String(this.imgQRData);
	}
	public void setImgQRData(byte[] imgQRData) {
		this.imgQRData = imgQRData;
	}
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getErrorRemark() {
		return errorRemark;
	}
	public void setErrorRemark(String errorRemark) {
		this.errorRemark = errorRemark;
	}
	public String getTranxRefNo() {
		return tranxRefNo;
	}
	public void setTranxRefNo(String tranxRefNo) {
		this.tranxRefNo = tranxRefNo;
	}
	


}
