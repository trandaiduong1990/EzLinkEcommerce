package com.wirecard.ezecom.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.wirecard.ezecom.constants.StringConstants;
import com.wirecard.ezecom.dto.EMerchantDetailsDto;
import com.wirecard.ezecom.form.ItemBean;

/**
@author WCCTTI-JANAHAN
 */
@Component
public class EMerchantDetailsValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		//just validate the EMerchantDetailsDto instances
		return EMerchantDetailsDto.class.isAssignableFrom(clazz);
		
	}

	public void validate(Object target, Errors errors) {
		if(target==null){
			errors.rejectValue("merchantNo", "notmatch.merchantNo");
			//return;
		}

	}
	
	public void validateEMerchant(String validationFlag, Errors errors) {
		
		
		if(validationFlag.equals(StringConstants.Validation.INVALID_ACCESS_CODE)){
			System.out.println("++++++++++++Invalid AccessCode+++++++++++++++++++");
			errors.rejectValue("accessCode", "notmatch.accessCode");
		}
		if(validationFlag.equals(StringConstants.Validation.INVALID_MERCHANT)){
			System.out.println("++++++++++++Invalid Merchant+++++++++++++++++++");
			errors.rejectValue("merchantNo", "notmatch.merchantNo");
		}
		
		}
		
		public String validateException(String validationFlag, Errors errors) {

			if(StringConstants.ResponseCodes.ERROR_CODE.equals(validationFlag)){
				errors.rejectValue("responseCode", "notmatch.responseCode");
			}
			return validationFlag;

	}

}
