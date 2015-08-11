package com.wirecard.ezecom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.wirecard.ezecom.constants.StringConstants;
import com.wirecard.ezecom.dto.EMerchantDetailsDto;
import com.wirecard.ezecom.dto.ETranxLogDto;
import com.wirecard.ezecom.dto.MerchantDto;
import com.wirecard.ezecom.mapper.dao.EMerchantDetailsDtoMapper;
import com.wirecard.ezecom.mapper.dao.ETranxLogDtoMapper;
import com.wirecard.ezecom.mapper.dao.MerchantDtoMapper;
import com.wirecard.ezecom.service.ValidationService;
import com.wirecard.ezecom.util.ApplicationUtil;
import com.wirecard.ezecom.validator.EMerchantDetailsValidator;
import com.wirecard.ezecom.validator.ETransactionValidator;

import java.sql.SQLException;
import java.util.*;

/**
@author WCCTTI-JANAHAN
 */

@Service
public class ValidationServiceImpl implements ValidationService {
	
	
	@Autowired
	private ETranxLogDtoMapper objETranxLogDtoMapper;
	@Autowired
	private EMerchantDetailsDtoMapper objEMerchantDetailsDtoMapper;

	
	
	
	

	public String valdateAccessCode(String merchantNo,String accessCode) {
		try{
		EMerchantDetailsDto objEMerchantDetailsDto=objEMerchantDetailsDtoMapper.getMerchantByMerchantId(merchantNo);
		
		if(objEMerchantDetailsDto==null){
			return StringConstants.Validation.INVALID_MERCHANT;
		}
		if(objEMerchantDetailsDto.getAccessCodeSecreatKeyVal().equalsIgnoreCase(StringConstants.Validation.ACCESS_CODE_SECURED_KEY_VALUE_YES)){
			String accessCodeToCompare=ApplicationUtil.get_SHA_256_SecurePassword(accessCode,objEMerchantDetailsDto.getAccessCodeSecretSalt() );
			//System.out.println("AccessCode from Form : "+accessCodeToCompare);
			if(objEMerchantDetailsDto.getAccessCodeSecreatKey().equals(accessCodeToCompare)){
				System.out.println("AccessCode from Form : "+accessCodeToCompare+"From DB :"+objEMerchantDetailsDto.getAccessCodeSecreatKey()+ " MATCHED !!!");
				return StringConstants.Validation.VALID_ACCESS_CODE;
			}
			else{
				System.out.println("AccessCode from Form : "+accessCodeToCompare+"From DB :"+objEMerchantDetailsDto.getAccessCodeSecreatKey()+ " NOT  MATCHED !!!");
				return StringConstants.Validation.INVALID_ACCESS_CODE;
			}
			
			
		}
		else{
			if(objEMerchantDetailsDto.getAccessCode().equals(accessCode)){
				return StringConstants.Validation.VALID_ACCESS_CODE;
			}
			else{
				return StringConstants.Validation.INVALID_ACCESS_CODE;
			}
		}
		}
		catch(SQLException e){
			return StringConstants.ResponseCodes.ERROR_CODE;
		}
		
		
	}

	public String isUniqueTransaction(String merchantNo,
			String merchantTranxRefNo, String orderNo) {
		ETranxLogDto objETranxLogDto;
		/*
		List<ETranxLogDto> objETranxLogDtoList;
		try {
			objETranxLogDtoList = objETranxLogDtoMapper.isUniqueTransaction(merchantNo, merchantTranxRefNo, orderNo);
		
		//System.out.println("+++++++++++++++=TRANSACTION LIST SIZE++++++++++++++++"+objETranxLogDtoList.size());
		if(objETranxLogDtoList.size()==0){
			//return true;
			return StringConstants.Validation.UNIQUE_TRANSCTION;
		}
		//return false;
		return StringConstants.Validation.NON_UNIQUE_TRANSACTION;
		} catch (SQLException e) {
			return StringConstants.ResponseCodes.ERROR_CODE;
		}
		*/
		
		try {
			objETranxLogDto=objETranxLogDtoMapper.isUniqueMerchantRefNUmber(merchantNo, merchantTranxRefNo);
			if(null==objETranxLogDto){
				//return true;
				System.out.println("+++UNIQUE MER TRANX REF NO++++++++++++++++++++");
				return StringConstants.Validation.UNIQUE_TRANSCTION;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		
			return StringConstants.ResponseCodes.ERROR_CODE;
		}
		System.out.println("+++NON UNIQUE MER TRANX REF NO++++++++++++++++++++");
		return StringConstants.Validation.NON_UNIQUE_TRANSACTION;
	}

	public String valdateMerchantNumber(String merchantNo) {
		EMerchantDetailsDto objEMerchantDetailsDto;
		try {
			objEMerchantDetailsDto = objEMerchantDetailsDtoMapper.getMerchantByMerchantId(merchantNo);
		
		if(objEMerchantDetailsDto==null){
			return StringConstants.Validation.INVALID_MERCHANT;
		}
		return StringConstants.Validation.VALID_MERCHANT; 
		} catch (SQLException e) {
			return StringConstants.ResponseCodes.ERROR_CODE; 
		}
	}

	public boolean checkPaymentStatus(String merchantNo,
			String merchantTranxRefNo, String orderNo, double amount, Date dateNow) {
		
		ETranxLogDto objETranxLogDto;
		try {
			objETranxLogDto=objETranxLogDtoMapper.checkTransactionStatus(merchantNo, merchantTranxRefNo,orderNo,amount);
			if(null==objETranxLogDto){
				//return true;
				System.out.println("+++UNIQUE MER TRANX REF NO++++++++++++++++++++");
				return false;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		
			return false;
		}
		if(objETranxLogDto.getTranxStatus().equals(StringConstants.Validation.TRANX_STATUS_RECIEPT) 
				&& 
				objETranxLogDto.getResponseCode().equals(StringConstants.ResponseCodes.SUCCESS_CODE))
		{
			// Update tranx_status is from 'R' to 'S'
			objETranxLogDto.setDatetime(dateNow);
			int update = objETranxLogDtoMapper.updateTranxStatus(objETranxLogDto);
			if(update == 1) {
				System.out.println("+++++UPDATE TRANX_STATUS SUCCESS+++++++++++++++++++++++");
				return true;
			} else {
				System.out.println("+++++UPDATE TRANX_STATUS FAIL+++++++++++++++++++++++");
				return false;
			}
		}
		else{
			return false;
		}
		
	}

}
