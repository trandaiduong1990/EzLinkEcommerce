package com.wirecard.ezecom.service;

import java.util.Date;
import java.util.List;

import com.wirecard.ezecom.dto.EMerchantDetailsDto;
import com.wirecard.ezecom.dto.MerchantDto;

/**
@author WCCTTI-JANAHAN
 */

public interface ValidationService {
	
	//public List<MerchantDto> getMerchant();
	public String valdateMerchantNumber(String merchantNo);
	public String valdateAccessCode(String merchantNo,String accessCode);
	public String isUniqueTransaction(String merchantNo,String orderNo);
	public boolean checkPaymentStatus(String merchantNo,String orderNo,double amount, Date dateNow);
	public String getReturnUrl(String merchantNo);

}
