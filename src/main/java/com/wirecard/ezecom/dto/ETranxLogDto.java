package com.wirecard.ezecom.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class ETranxLogDto {
    private String tranxlogid;

    private Date datetime;

    private Date merchantTranxDate;

    private String version;

    private String tranxcode;

    private String merchantNo;

    private String orderNo;

    private String orderInfo;

    private double amount;

    private int currency;

    private String securehashType;

    private String responseCode;

    private String approvalCode;

    private String rrn;

    private String channel;

    private String mcc;

    private String tranxStatus;

    private String processStatus;

    private Date processDatetime;

    private String processResponse;

    public String getTranxlogid() {
        return tranxlogid;
    }

    public void setTranxlogid(String tranxlogid) {
        this.tranxlogid = tranxlogid;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Date getMerchantTranxDate() {
        return merchantTranxDate;
    }

    public void setMerchantTranxDate(Date merchantTranxDate) {
        this.merchantTranxDate = merchantTranxDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTranxcode() {
        return tranxcode;
    }

    public void setTranxcode(String tranxcode) {
        this.tranxcode = tranxcode;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }


    public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getCurrency() {
		return currency;
	}

	public void setCurrency(int currency) {
		this.currency = currency;
	}

	public String getSecurehashType() {
        return securehashType;
    }

    public void setSecurehashType(String securehashType) {
        this.securehashType = securehashType;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getTranxStatus() {
        return tranxStatus;
    }

    public void setTranxStatus(String tranxStatus) {
        this.tranxStatus = tranxStatus;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public Date getProcessDatetime() {
        return processDatetime;
    }

    public void setProcessDatetime(Date processDatetime) {
        this.processDatetime = processDatetime;
    }

    public String getProcessResponse() {
        return processResponse;
    }

    public void setProcessResponse(String processResponse) {
        this.processResponse = processResponse;
    }
}