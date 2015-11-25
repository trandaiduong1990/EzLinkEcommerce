package com.wirecard.ezecom.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.wirecard.ezecom.constants.StringConstants;
import com.wirecard.ezecom.dto.EMerchantDetailsDto;
import com.wirecard.ezecom.dto.ETranxLogDto;

/**
@author WCCTTI-JANAHAN
 */
@Component
public class ETransactionValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		//just validate the EMerchantDetailsDto instances
				return ETranxLogDto.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub

	}
	
	public void isUniqueTranxvalidation(String Validationlag, Errors errors) {
		if(StringConstants.Validation.NON_UNIQUE_TRANSACTION.equals(Validationlag)){
			errors.rejectValue("orderNo", "notmatch.orderNo");
		}

	}
	
	public void isValidTransactionCodevalidation(String Validationlag, Errors errors) {
		if(StringConstants.Validation.INVALID_TRANSACTION_CODE.equals(Validationlag)){
			errors.rejectValue("transactionCode", "notmatch.transactionCode");
		}

	}
	
	public void isValidCurrency(String Validationlag, Errors errors) {
		if(StringConstants.Validation.INVALID_CURRENCY.equals(Validationlag)){
			errors.rejectValue("currency", "notmatch.currency");
		}

	}
	
	public void isValidHAshCodevalidation(String Validationlag, Errors errors) {
		if(StringConstants.Validation.INVALID_HASH_CODE.equals(Validationlag)){
			errors.rejectValue("transactionCode", "notmatch.hashValue");
		}

	}
	
	public void isValidAccessCodevalidation(String Validationlag, Errors errors) {
		if(StringConstants.Validation.INVALID_ACCESS_CODE.equals(Validationlag)){
			errors.rejectValue("transactionCode", "notmatch.accessCode");
		}

	}
	
	public void isDBInsertionFailed(String Validationlag, Errors errors) {
		if(StringConstants.ResponseCodes.DB_INSERTION_FAILED.equals(Validationlag)){
			errors.rejectValue("transactionCode", "notmatch.DbInsertionFailed");
		}

	}
	public void isDBSelectionFailed(String Validationlag, Errors errors) {
		if(StringConstants.ResponseCodes.DB_SELECTION_FAILED.equals(Validationlag)){
			errors.rejectValue("transactionCode", "notmatch.DbSelectionFailed");
		}

	}
	public void isDetailInsertionFailed(String Validationlag, Errors errors) {
		if(StringConstants.ResponseCodes.DETAIL_INSERTION_FAILED.equals(Validationlag)){
			errors.rejectValue("transactionCode", "notmatch.DbDetailInsertionFailed");
		}

	}
	public void isCommonFailure(String Validationlag, Errors errors) {
		if(StringConstants.ResponseCodes.ERROR_CODE.equals(Validationlag)){
			errors.rejectValue("transactionCode", "notmatch.responseCode");
		}

	}
	
	public void isQRGenerationFailure(String Validationlag, Errors errors) {
		if(StringConstants.ResponseCodes.QRCODE_GENERATION_FAILED.equals(Validationlag)){
			errors.rejectValue("transactionCode", "notmatch.QRGenerationFailed");
		}

	}

}
