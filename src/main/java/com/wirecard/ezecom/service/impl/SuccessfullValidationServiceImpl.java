package com.wirecard.ezecom.service.impl;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wirecard.ezecom.constants.StringConstants;
import com.wirecard.ezecom.dto.EErrorLogDto;
import com.wirecard.ezecom.dto.ETranxDetailsDto;
import com.wirecard.ezecom.dto.ETranxLogDto;
import com.wirecard.ezecom.dto.MerchantDto;
import com.wirecard.ezecom.mapper.dao.EErrorLogDtoMapper;
import com.wirecard.ezecom.mapper.dao.ETranxCodeDtoMapper;
import com.wirecard.ezecom.mapper.dao.ETranxDetailsDtoMapper;
import com.wirecard.ezecom.mapper.dao.ETranxLogDtoMapper;
import com.wirecard.ezecom.mapper.dao.MerchantDtoMapper;
import com.wirecard.ezecom.service.SuccessfullValidationService;

/**
@author WCCTTI-JANAHAN
 */
@Service
public class SuccessfullValidationServiceImpl implements SuccessfullValidationService {
	
	@Autowired
	private ETranxLogDtoMapper objETranxLogDtoMapper;
	@Autowired
	private ETranxDetailsDtoMapper objETranxDetailsDtoMapper;
	@Autowired
	private ETranxCodeDtoMapper objETranxCodeDtoMapper;
	@Autowired
	private MerchantDtoMapper objMerchantDtoMapper;
	@Autowired
	private ETranxDetailsDto objETranxDetailsDto;
	@Autowired
	private EErrorLogDtoMapper objEErrorLogDtoMapper;
	@Autowired
	private EErrorLogDto objEErrorLogDto;

	public ETranxLogDto insertETranxLog(ETranxLogDto objETranxLogDto) {
		//String resCode=StringConstants.ResponseCodes.SUCCESS_CODE;
		int returnValue;
		try{
		Date dateTime = new Date();
		
		objETranxLogDto.setDatetime(dateTime);
		
		
		MerchantDto objMerchantDto=objMerchantDtoMapper.getMCCByMerchantId(objETranxLogDto.getMerchantNo());
		objETranxLogDto.setMcc(objMerchantDto.getMccCode());
		
		
		
		
		objETranxLogDto.setCurrency((short) 702);


	      Random randomno = new Random();
	      objETranxLogDto.setRrn(String.valueOf(randomno.nextInt()));
	      
	      objETranxLogDto.setChannel("WB");
	      objETranxLogDto.setOrderInfo("WEB");
	      
	      
	      objETranxLogDto.setApprovalCode("A01234");
		}
		catch(Exception e){
			objETranxLogDto.setResponseCode(StringConstants.ResponseCodes.ERROR_CODE);
			e.printStackTrace();
		}
	     
	      //Transaction code validation
	      System.out.println("**************RESPONSE CODE*******B4***********"+objETranxLogDto.getResponseCode());
	      if(objETranxLogDto.getTranxcode().equalsIgnoreCase(StringConstants.Validation.TRANX_CODE_PAY)||
	    			objETranxLogDto.getTranxcode().equalsIgnoreCase(StringConstants.Validation.TRANX_CODE_EQUERY)){
	    	  if(!((StringConstants.ResponseCodes.INVALID_HASH_CODE.equalsIgnoreCase(objETranxLogDto.getResponseCode()))||
	    			(StringConstants.ResponseCodes.INVALID_ACCESS_CODE.equalsIgnoreCase(objETranxLogDto.getResponseCode()))  
	    			  )){
	    	  objETranxLogDto.setResponseCode(StringConstants.ResponseCodes.SUCCESS_CODE);
	    	  }
	    	  else{
	    		  if(StringConstants.ResponseCodes.INVALID_HASH_CODE.equalsIgnoreCase(objETranxLogDto.getResponseCode())){
	    		  objETranxLogDto.setOrderInfo(StringConstants.ErrorRemarks.INVALID_HASH_CODE);
	    		  }
	    		  else{
		    		  objETranxLogDto.setOrderInfo(StringConstants.ErrorRemarks.INVALID_ACCESS_CODE);
		    	  }
	    	  }
	    	  
	      }
	      else{
	    	  
	    	  objETranxLogDto.setResponseCode(StringConstants.ResponseCodes.INVALID_TRANSACTION_CODE);
	    	  objETranxLogDto.setTranxcode(StringConstants.Validation.TRANXCODE_ERROR);
	    	  objETranxLogDto.setOrderInfo(StringConstants.ErrorRemarks.INVALID_TRANX_CODE);
	    	  
	      }
	    	  try{
	    		  System.out.println("**************RESPONSE CODE*******After***********"+objETranxLogDto.getResponseCode());
	    				//ETranxCodeDto objETranxCodeDto=objETranxCodeDtoMapper.getTRANXCODEByDesc(objETranxLogDto.getTranxcode());
	    				//objETranxLogDto.setTranxcode(objETranxCodeDto.getTranxcode());
	    				
	    				returnValue=objETranxLogDtoMapper.insert(objETranxLogDto);
	    				System.out.println("&&&&&&&&&&&&&&&&&&&&Transaction log insertion RETURN VALUE : "+returnValue);
	    				
	    				if(returnValue != 1){
	    					//if Transaction Log insertion failed
	    					try{
	    				    	  objEErrorLogDto=createETranxLogDto(objETranxLogDto);
	    				    	  objEErrorLogDto.setResponseCode(StringConstants.ResponseCodes.DB_INSERTION_FAILED);
	    				    	  objEErrorLogDto.setOrderInfo(StringConstants.ErrorRemarks.DB_INSERTION_FAILED);
	    				    	  objETranxLogDto.setResponseCode(StringConstants.ResponseCodes.DB_INSERTION_FAILED);
	    				    	  returnValue=objEErrorLogDtoMapper.insert(objEErrorLogDto);
	    				    	  return objETranxLogDto;
	    				    	  }catch(Exception e){
	    					    	  System.out.println("++INSERTION ERROR  +++");
	    					    	  e.printStackTrace();
	    					    	  objETranxLogDto.setResponseCode(StringConstants.ResponseCodes.DB_INSERTION_FAILED);
	    					    	  return objETranxLogDto;  
	    					      }
	    				}
	    				
	    				System.out.println(objETranxLogDto.getMerchantNo());
	    				System.out.println(objETranxLogDto.getMerchantRefno());
	    				System.out.println(objETranxLogDto.getOrderNo());
	    				System.out.println(objETranxLogDto.getTranxStatus());
	    				try{
	    				objETranxLogDto=objETranxLogDtoMapper.selectTransaction(objETranxLogDto.getMerchantNo(),objETranxLogDto.getMerchantRefno(), objETranxLogDto.getOrderNo(),objETranxLogDto.getAmount(), objETranxLogDto.getTranxStatus());
	    				
	    				if(null==objETranxLogDto){
	    					System.out.println("++NO SELECTION   +++");
	    					objETranxLogDto.setResponseCode(StringConstants.ResponseCodes.DB_SELECTION_FAILED);
	    				}
	    				}catch(Exception e){
	    					System.out.println("++SELECTION ERROR  +++");
					    	  e.printStackTrace();
					    	  objETranxLogDto.setResponseCode(StringConstants.ResponseCodes.DB_SELECTION_FAILED);
					    	  return objETranxLogDto; 
	    				}
	    				objETranxDetailsDto=createETranxDetailsDto(objETranxLogDto);
	    				returnValue=objETranxDetailsDtoMapper.insert(objETranxDetailsDto);
	    				
	    				System.out.println("++Transaction Detail insertion  RETURN VALUE+++"+returnValue);
	    				if(returnValue != 1){
	    					//if Transaction Log insertion failed
	    					try{
	    				    	  objEErrorLogDto=createETranxLogDto(objETranxLogDto);
	    				    	  objEErrorLogDto.setResponseCode(StringConstants.ResponseCodes.DETAIL_INSERTION_FAILED);
	    				    	  objEErrorLogDto.setOrderInfo(StringConstants.ErrorRemarks.DETAIL_INSERTION_FAILED);
	    				    	  objETranxLogDto.setResponseCode(StringConstants.ResponseCodes.DETAIL_INSERTION_FAILED);
	    				    	  returnValue=objEErrorLogDtoMapper.insert(objEErrorLogDto);
	    				    	  return objETranxLogDto;
	    				    	  }catch(Exception e){
	    					    	  System.out.println("++INSERTION ERROR  +++");
	    					    	  e.printStackTrace();
	    					    	  objETranxLogDto.setResponseCode(StringConstants.ResponseCodes.DB_INSERTION_FAILED);
	    					    	  return objETranxLogDto;  
	    					      }
	    				}
	    	  }
	    	  catch(Exception e){
	    		  objETranxLogDto.setResponseCode(StringConstants.ResponseCodes.ERROR_CODE);
	    		  e.printStackTrace();
	    		  return objETranxLogDto;
	    	  }
	    	  
	      return objETranxLogDto;
	}
		
	private ETranxDetailsDto createETranxDetailsDto(ETranxLogDto objETranxLogDto){
		
		ETranxDetailsDto objETranxDetailsDto=new ETranxDetailsDto();
		objETranxDetailsDto.setTranxlogId(objETranxLogDto.getTranxlogid());
		objETranxDetailsDto.setTranxtype(objETranxLogDto.getTranxcode());
		objETranxDetailsDto.setDatetime(objETranxLogDto.getDatetime());
		
		return objETranxDetailsDto;
		
	}	
	private EErrorLogDto createETranxLogDto(ETranxLogDto objETranxLogDto) {
		objEErrorLogDto.setTranxcode(objETranxLogDto.getTranxcode());
		objEErrorLogDto.setDatetime(new Date());
		objEErrorLogDto.setOrderInfo("INSERT FAILED : "+objETranxLogDto.getOrderInfo());
		objEErrorLogDto.setMerchantNo(objETranxLogDto.getMerchantNo());
		objEErrorLogDto.setMerchantRefno(objETranxLogDto.getMerchantRefno());
		objEErrorLogDto.setOrderNo(objETranxLogDto.getOrderNo());
		objEErrorLogDto.setAmount(objETranxLogDto.getAmount());
		objEErrorLogDto.setSecurehashType(objETranxLogDto.getSecurehashType());
		objEErrorLogDto.setVersion(objETranxLogDto.getVersion());
		objEErrorLogDto.setChannel(objETranxLogDto.getChannel());
		objEErrorLogDto.setCurrency((short) 702);
		objEErrorLogDto.setRrn(objETranxLogDto.getRrn());
		objEErrorLogDto.setTranxStatus("Q");
		objEErrorLogDto.setApprovalCode("A01234");
		
		objEErrorLogDto.setResponseCode(objETranxLogDto.getResponseCode());
		

		return objEErrorLogDto;
	}
	
	

}
