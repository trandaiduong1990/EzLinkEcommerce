package com.wirecard.ezecom.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.wirecard.ezecom.constants.StringConstants;
import com.wirecard.ezecom.form.ItemBean;
import com.wirecard.ezecom.util.ApplicationUtil;

/**
@author WCCTTI-JANAHAN
 */
@Component
public class HashCodeValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		//just validate the Item instances
		return ItemBean.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ItemBean objItem=(ItemBean)target;
		
		if(objItem.getHashAlgo().equalsIgnoreCase(StringConstants.Validation.HASH_CODE_ALGO)){
			String generatedHashCode= createHashCode(objItem);
			//--------------------------------------------------------------------------------------------------------
			//ToDO:
			String oldMerRefNo=objItem.getMerchantTranxRefNo();
			int newMerRefNo=Integer.parseInt(objItem.getMerchantTranxRefNo())+1;
			objItem.setMerchantTranxRefNo(String.valueOf(newMerRefNo));
			String nextGeneratedHashCode= createHashCode(objItem);
			objItem.setMerchantTranxRefNo(oldMerRefNo);
			String nextEncryptedHashCodeToCompare=ApplicationUtil.get_SHA_256_SecurePassword(nextGeneratedHashCode,"" );
			System.out.println("++NEXT Encrypted HashCode : "+nextEncryptedHashCodeToCompare);
			//--------------------------------------------------------------------------------------------------------
			
			String EncryptedHashCodeToCompare=ApplicationUtil.get_SHA_256_SecurePassword(generatedHashCode,"" );
			System.out.println("++Encrypted HashCode : "+EncryptedHashCodeToCompare);
			System.out.println("++HashCode From Form : "+objItem.getHashValue());
			if(!(EncryptedHashCodeToCompare.equals(objItem.getHashValue()))){
				if(!(objItem.getTransactionCode().equalsIgnoreCase(StringConstants.Validation.TRANX_CODE_PAY)||
						objItem.getTransactionCode().equalsIgnoreCase(StringConstants.Validation.TRANX_CODE_EQUERY))){
					errors.rejectValue("transactionCode", "notmatch.transactionCode");
				}
				else{
				errors.rejectValue("hashValue", "notmatch.hashValue");
				}
			}
			System.out.println("++NO HASH CODE VALIDATION ERROR++");
		}
		else{
			
			
			errors.rejectValue("hashAlgo", "notmatch.hashAlgo");
			
		}

	}
	private String createHashCode(ItemBean objItemBean){
		String generateHashCode=
				StringConstants.MerchantForm.ACCESS_CODE+"="+objItemBean.getAccessCode()
				+"&"+StringConstants.MerchantForm.AMOUNT+"="+objItemBean.getAmount()
				+"&"+StringConstants.MerchantForm.DATE_TIME+"="+objItemBean.getDateTime()
				+"&"+StringConstants.MerchantForm.HASH_ALGO+"="+objItemBean.getHashAlgo()
				//+"&e_HashValue="+objItemBean.getHashValue()
				+"&"+StringConstants.MerchantForm.MERCHANT_NAME+"="+objItemBean.getMerchantName()
				+"&"+StringConstants.MerchantForm.MERCHANT_NO+"="+objItemBean.getMerchantNo()
				+"&"+StringConstants.MerchantForm.MERCHANT_TRANX_REF_NO+"="+objItemBean.getMerchantTranxRefNo()
				+"&"+StringConstants.MerchantForm.ORDER_NO+"="+objItemBean.getOrderNo()
				+"&"+StringConstants.MerchantForm.PAY_BY+"="+objItemBean.getPayBy()
				+"&"+StringConstants.MerchantForm.TRANSACTION_CODE+"="+objItemBean.getTransactionCode()
				+"&"+StringConstants.MerchantForm.VERSION+"="+objItemBean.getVersion();
			
		//System.out.println("++Generated HashCode : "+generateHashCode);
		return generateHashCode;
	}

}
