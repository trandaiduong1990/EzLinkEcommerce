package com.wirecard.ezecom.form;

import java.util.Date;



/**
@author WCCTTI-JANAHAN
 */

public class ItemBean {
	
	private String merchantNo,accessCode,orderNo,merchantTranxRefNo,remarks,transactionCode,merchantName;
	private String dateTime,payBy,hashValue,hashAlgo,version,responseCode,errorRemark;
	
	private double amount;
	private Date validatedMerchantTranxDate;
	private byte[] imgQRData;
	
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
	
	
	
	
	public String getMerchantTranxRefNo() {
		return merchantTranxRefNo;
	}
	public void setMerchantTranxRefNo(String merchantTranxRefNo) {
		this.merchantTranxRefNo = merchantTranxRefNo;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
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
	
	
	public String getErrorRemark() {
		return errorRemark;
	}
	public void setErrorRemark(String errorRemark) {
		this.errorRemark = errorRemark;
	}
	


}
