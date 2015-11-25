package com.wirecard.ezecom.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wirecard.ezecom.constants.StringConstants;
import com.wirecard.ezecom.dto.EErrorLogDto;
import com.wirecard.ezecom.dto.ETranxCodeDto;
import com.wirecard.ezecom.dto.ETranxLogDto;
import com.wirecard.ezecom.dto.MerchantDto;
import com.wirecard.ezecom.form.ItemBean;
import com.wirecard.ezecom.mapper.dao.EErrorLogDtoMapper;
import com.wirecard.ezecom.mapper.dao.ETranxCodeDtoMapper;
import com.wirecard.ezecom.mapper.dao.ETranxLogDtoMapper;
import com.wirecard.ezecom.mapper.dao.MerchantDtoMapper;
import com.wirecard.ezecom.service.UnSuccessfullValidationService;

/**
@author WCCTTI-JANAHAN
 */
@Service
public class UnSuccessfullValidationServiceImpl implements
		UnSuccessfullValidationService {
	@Autowired
	private ETranxLogDtoMapper objETranxLogDtoMapper;
	@Autowired
	private EErrorLogDtoMapper objEErrorLogDtoMapper;
	@Autowired
	private ETranxCodeDtoMapper objETranxCodeDtoMapper;
	@Autowired
	private MerchantDtoMapper objMerchantDtoMapper;
	@Autowired
	private EErrorLogDto objEErrorLogDto;

	public String insertETranxLog(ETranxLogDto objETranxLogDto) {
		String resCode=objETranxLogDto.getResponseCode();
		System.out.println("++UNSUCCESSFULL - RES CODE+++" + resCode);
		int returnValue;
		try{
		Date dateTime = new Date();
		
		objETranxLogDto.setDatetime(dateTime);
		
		
		objETranxLogDto.setCurrency((short) 702);
		objETranxLogDto.setProcessStatus("N");

	      Random randomno = new Random();
//	      objETranxLogDto.setRrn(String.valueOf(100000 + randomno.nextInt(900000)));
	      
	      objETranxLogDto.setChannel("WB");
	      if(null==objETranxLogDto.getOrderInfo()){
	      objETranxLogDto.setOrderInfo("UNSCUCCESSFULL " +objETranxLogDto.getResponseCode() );
	      }
	      
	      
//	      objETranxLogDto.setApprovalCode("A01234");
	     
	      if(StringConstants.ResponseCodes.REQUIRED_FIELD_MISSING.equals(objETranxLogDto.getResponseCode())){
				//insert into eerorlog	
		    	   try{
		    		   objEErrorLogDto=createETranxLogDto(objETranxLogDto);
		    		   objEErrorLogDto.setOrderInfo(StringConstants.ErrorRemarks.FIELD_MISSING);
		    		   //objEErrorLogDto.setResponseCode(StringConstants.ResponseCodes.INVALID_MERCHANT);
		    			returnValue=objEErrorLogDtoMapper.insert(objEErrorLogDto);
		    			System.out.println("++RETURN VALUE+++"+returnValue);
		    			return StringConstants.ResponseCodes.REQUIRED_FIELD_MISSING;
		    		      }catch(Exception e){
		    		    	  System.out.println("++INSERTION ERROR -REQUIRED_FIELD_MISSING +++");
		    		    	  e.printStackTrace();
		    		    	  resCode=StringConstants.ResponseCodes.ERROR_CODE; 
		    		    	  return resCode; 
		    		      }
				}
	      
	      if(StringConstants.ResponseCodes.INVALID_TRANSACTION_DATE.equalsIgnoreCase(objETranxLogDto.getResponseCode())){
	    	//insert into eerorlog	
	    	   try{
	    		   objEErrorLogDto=createETranxLogDto(objETranxLogDto);
	    		   //objEErrorLogDto.setResponseCode(StringConstants.ResponseCodes.INVALID_MERCHANT);
	    			returnValue=objEErrorLogDtoMapper.insert(objEErrorLogDto);
	    			System.out.println("++RETURN VALUE+++"+returnValue);
	    			if(returnValue !=1){
	    				throw new Exception();
	    			}
	    			return StringConstants.ResponseCodes.INVALID_TRANSACTION_DATE;
	    		      }catch(Exception e){
	    		    	  System.out.println("++INSERTION ERROR -INVALID_TRANSACTION_DATE +++");
	    		    	  e.printStackTrace();
	    		    	  resCode=StringConstants.ResponseCodes.ERROR_CODE; 
	    		    	  return resCode; 
	    		      }
	      }
	      if(StringConstants.ResponseCodes.INVALID_AMOUNT.equalsIgnoreCase(objETranxLogDto.getResponseCode())){
		    	//insert into eerorlog	
		    	   try{
		    		   objEErrorLogDto=createETranxLogDto(objETranxLogDto);
		    		   //objEErrorLogDto.setResponseCode(StringConstants.ResponseCodes.INVALID_MERCHANT);
		    			returnValue=objEErrorLogDtoMapper.insert(objEErrorLogDto);
		    			System.out.println("++RETURN VALUE+++"+returnValue);
		    			if(returnValue !=1){
		    				throw new Exception();
		    			}
		    			return StringConstants.ResponseCodes.INVALID_AMOUNT;
		    		      }catch(Exception e){
		    		    	  System.out.println("++INSERTION ERROR -INVALID_TRANSACTION_DATE +++");
		    		    	  e.printStackTrace();
		    		    	  resCode=StringConstants.ResponseCodes.ERROR_CODE; 
		    		    	  return resCode; 
		    		      }
		      }
	      
	      if(StringConstants.ResponseCodes.INVALID_MERCHANT.equals(objETranxLogDto.getResponseCode())){
			//insert into eerorlog	
	    	   try{
	    		   objEErrorLogDto=createETranxLogDto(objETranxLogDto);
	    		   //objEErrorLogDto.setResponseCode(StringConstants.ResponseCodes.INVALID_MERCHANT);
	    			returnValue=objEErrorLogDtoMapper.insert(objEErrorLogDto);
	    			System.out.println("++RETURN VALUE+++"+returnValue);
	    			if(returnValue !=1){
	    				throw new Exception();
	    			}
	    			return StringConstants.ResponseCodes.INVALID_MERCHANT;
	    		      }catch(Exception e){
	    		    	  System.out.println("++INSERTION ERROR -INVALID_MERCHANT +++");
	    		    	  e.printStackTrace();
	    		    	  resCode=StringConstants.ResponseCodes.ERROR_CODE; 
	    		    	  return resCode; 
	    		      }
			}
	      if(StringConstants.ResponseCodes.NON_UNIQUE_TRANSACTION.equals(objETranxLogDto.getResponseCode())){
				//insert into eerorlog	
		    	   try{
		    		   objEErrorLogDto=createETranxLogDto(objETranxLogDto);
		    		   objEErrorLogDto.setOrderInfo(StringConstants.ErrorRemarks.NON_UNIQUE_TRANSACTION);
		    		   //objEErrorLogDto.setResponseCode(StringConstants.ResponseCodes.INVALID_MERCHANT);
		    			returnValue=objEErrorLogDtoMapper.insert(objEErrorLogDto);
		    			System.out.println("++RETURN VALUE+++"+returnValue);
		    			return StringConstants.ResponseCodes.NON_UNIQUE_TRANSACTION;
		    		      }catch(Exception e){
		    		    	  System.out.println("++INSERTION ERROR -INVALID_MERCHANT +++");
		    		    	  e.printStackTrace();
		    		    	  resCode=StringConstants.ResponseCodes.ERROR_CODE; 
		    		    	  return resCode; 
		    		      }
				}
	      
			else {
				// VALID MERCHANT
				try {
					returnValue = objETranxLogDtoMapper.insert(objETranxLogDto);
					System.out.println("++RETURN VALUE+++" + returnValue);
					return objETranxLogDto.getResponseCode();
				} catch (Exception e) {
					System.out.println("++INSERTION ERROR+++");
					resCode = StringConstants.ResponseCodes.ERROR_CODE;
					e.printStackTrace();
					return resCode;
				}

			}
	      
	}catch(Exception e){
			return StringConstants.ResponseCodes.ERROR_CODE;
		}
		
		
	}
	
	private EErrorLogDto createETranxLogDto(ETranxLogDto objETranxLogDto) {
		
		objEErrorLogDto.setTranxcode(objETranxLogDto.getTranxcode());
		objEErrorLogDto.setDatetime(new Date());
		objEErrorLogDto.setOrderInfo(objETranxLogDto.getOrderInfo());
		objEErrorLogDto.setMerchantNo(objETranxLogDto.getMerchantNo());
		objEErrorLogDto.setOrderNo(objETranxLogDto.getOrderNo());
		objEErrorLogDto.setAmount(objETranxLogDto.getAmount());
		objEErrorLogDto.setSecurehashType(objETranxLogDto.getSecurehashType());
		objEErrorLogDto.setVersion(objETranxLogDto.getVersion());
		objEErrorLogDto.setChannel(objETranxLogDto.getChannel());
		objEErrorLogDto.setCurrency((short) 702);
		objEErrorLogDto.setRrn(objETranxLogDto.getRrn());
		objEErrorLogDto.setTranxStatus(objETranxLogDto.getTranxStatus());
		objEErrorLogDto.setApprovalCode(objETranxLogDto.getApprovalCode());
		
		objEErrorLogDto.setResponseCode(objETranxLogDto.getResponseCode());
		

		return objEErrorLogDto;
	}

}
