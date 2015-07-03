package com.wirecard.ezecom.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;









import com.wirecard.ezecom.constants.StringConstants;
import com.wirecard.ezecom.form.ItemBean;

/**
@author WCCTTI-JANAHAN
 */
@Component
public class ItemValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		//just validate the Item instances
				return ItemBean.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount",
				"required.amount", "Field name is required.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderNo",
				"required.orderNo", "Field name is required.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "merchantTranxRefNo",
				"required.merchantTranxRefNo", "Field name is required.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "merchantNo",
				"required.merchantNo", "Field name is required.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accessCode",
				"required.accessCode", "Field name is required.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "transactionCode",
				"required.transactionCode", "Field name is required.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hashAlgo",
				"required.hashAlgo", "Field name is required.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hashValue",
				"required.hashValue", "Field name is required.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateTime",
				"required.dateTime", "Field name is required.");
		
		/*
		ItemBean objItem=(ItemBean)target;
		
		
		
		if(!(StringConstants.Validation.TRANX_CODE_PAY.equalsIgnoreCase(objItem.getTransactionCode())
				||(StringConstants.Validation.TRANX_CODE_EQUERY.equalsIgnoreCase(objItem.getTransactionCode())))){
			errors.rejectValue("transactionCode", "notmatch.transactionCode");
		}
	*/
	}
	
	public void validateAmount(String amount, Errors errors) {
		try{
		Double amt=Double.valueOf(amount).doubleValue();
		if((amt< 0.0)){
			errors.rejectValue("amount", "notmatch.amount");
		}
		}
		catch(Exception e){
			System.out.println("+++++AMOUNT FORMAT ERROR++++++++++++++");
			errors.rejectValue("amount", "notmatch.amountFormat");
		}
	}
	
	public Date validateDate(String dateToValdate, Errors errors) {
		//String dateInString = new java.text.SimpleDateFormat("DDMMYYYYHHMISS").format(dateToValdate);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HHmmss");
		formatter.setLenient(false);
		 
		Date parsedDate = null;
		try {
			Date maxDate = formatter.parse("31-12-2099 000000");
			parsedDate = formatter.parse(dateToValdate);
			if(parsedDate.after(maxDate)){
				throw new Exception();
			}
			System.out.println("++validated DATE TIME ++"+formatter.format(parsedDate));
			
		} catch (Exception e) {
			errors.rejectValue("dateTime", "notmatch.dateTime");
		}
		return parsedDate;
	}

}
