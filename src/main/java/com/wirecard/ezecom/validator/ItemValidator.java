package com.wirecard.ezecom.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "merchantNo",
				"required.merchantNo", "Field name is required.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accessCode",
				"required.accessCode", "Field name is required.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "transactionCode",
				"required.transactionCode", "Field name is required.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "payBy",
				"required.payBy", "Field name is required.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currency",
				"required.currency", "Field name is required.");
		
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
		Double amt=Double.parseDouble(amount);
		if((amt<= 0.0) && (amt > 500.0)){
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
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
//		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HHmmss");
		formatter.setLenient(false);
		 
		Date parsedDate = null;
		try {
			Date dateNow = new Date();
			parsedDate = formatter.parse(dateToValdate);
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(dateNow);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(parsedDate);
			if(!isSameDay(cal1, cal2)){
				throw new Exception();
			}
			System.out.println("++validated DATE TIME ++"+formatter.format(parsedDate));
			
		} catch (Exception e) {
			errors.rejectValue("dateTime", "notmatch.dateTime");
		}
		return parsedDate;
	}
	
	 public boolean isSameDay(Calendar cal1, Calendar cal2) {
	      if (cal1 == null || cal2 == null) {
	          return false;
	      }
	      return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
	              cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
	              cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	  }

}
