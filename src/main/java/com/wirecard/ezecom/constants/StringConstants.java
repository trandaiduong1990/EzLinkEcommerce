package com.wirecard.ezecom.constants;
/**
@author WCCTTI-JANAHAN
 */

public interface StringConstants {
	
	public interface Validation{
	public final String TRANX_CODE_PAY ="COT";
	public final String TRANX_CODE_EQUERY ="EQR";
	
	public final String INVALID_MERCHANT="INVALID_MERCHANT";
	public final String VALID_MERCHANT="VALID_MERCHANT";
	public final String INVALID_ACCESS_CODE="INVALID_ACCESS_CODE";
	public final String VALID_ACCESS_CODE="VALID_ACCESS_CODE";
	public final String INVALID_TRANSACTION_CODE="INVALID TRANSACTION CODE";
	public final String INVALID_HASH_CODE="INVALID HASH CODE";
	
	public final String UNIQUE_TRANSCTION="UNIQUE_TRANSCTION";
	public final String NON_UNIQUE_TRANSACTION="NON_UNIQUE_TRANSACTION";
	
	public final String PAYMENT_NOT_COMPLETED="PAYMENT NOT COMPLETED";
	public final String PAYMENT_COMPLETED="PAYMENT COMPLETED";
	
	public final String ACCESS_CODE_SECURED_KEY_VALUE_YES="Y";
	
	public final String TRANXCODE_ERROR="ERR";
	
	public final String TRANX_STATUS_QR="Q";
	public final String TRANX_STATUS_RECIEPT="R";
	
	
	
	public final String HASH_CODE_ALGO="SHA256";
	
	public final int MAX_ORDER_INFO_LENGTH=19;
	}
	
	public interface MerchantForm{
		public final String MERCHANT_NAME="e_MerchantName";
		public final String MERCHANT_NO="e_MerchantNo";
		public final String ACCESS_CODE="e_AccessCode";
		public final String ORDER_NO="e_OrderNo";
		public final String MERCHANT_TRANX_REF_NO="e_MerchantTranxRefNo";
		public final String TRANSACTION_CODE="e_TransactionCode";
		public final String VERSION="e_Version";
		public final String DATE_TIME="e_DateTime";
		public final String PAY_BY="e_PayBy";
		public final String HASH_VALUE="e_HashValue";
		public final String HASH_ALGO="e_HashAlgo";
		public final String AMOUNT="e_Amount";
		
		
	}
	public interface ResponseCodes{
		public final String SUCCESS_CODE ="00";
		public final String ERROR_CODE ="20";
		public final String INVALID_AMOUNT ="13";
		public final String INVALID_ACCESS_CODE ="57";
		public final String INVALID_HASH_CODE ="55";
		public final String INVALID_TRANSACTION_CODE ="40";
		public final String INVALID_MERCHANT ="03";
		public final String INVALID_TRANSACTION_DATE ="33";
		public final String INVALID_TRANSACTION_REFERENCE_NUMBER ="12";
		public final String REQUIRED_FIELD_MISSING ="05";
		public final String QRCODE_GENERATION_FAILED ="63";
		public final String DB_INSERTION_FAILED ="0A";
		public final String DETAIL_INSERTION_FAILED ="0C";
		public final String DB_SELECTION_FAILED ="0B";
		
	}
	
	public interface JspPages{
		//public final String ADD_ITEM_PAGE ="addItems";
		public final String ERROR_PAGE ="error";
		public final String SUCCESS_PAGE ="success";
	}
	
	public interface QRImage{
		public final int QR_IMG_WIDTH =200;
		public final int QR_IMG_HEIGHT =200;
	}
	
	public interface QRFields{
		public final String QR_MER_NAME ="QR_MER_NAME";
		public final String QR_MER_TRAX_REF_NO ="QR_MER_TRAX_REF_NO";
		public final String QR_MER_ID ="QR_MER_ID";
		public final String QR_ORDER_NO ="QR_ORDER_NO";
		public final String QR_AMOUNT ="QR_AMOUNT";
		public final String QR_CURRENCY ="QR_CURRENCY";
		public final String QR_RRN ="QR_RRN";
		public final String QR_RES_CODE ="QR_RES_CODE";
		public final String QR_RES_ENCRYPT ="QR_RES_ENCRYPT";
		
	}
	
	public interface ErrorRemarks{
		public final String FIELD_MISSING ="REQUIRED FIELDS MISSING";
		public final String INVALID_MERCHANT ="INVALID MERCHANT";
		public final String INVALID_TRANSACTION_DATE ="INVALID TRANSACTION DATE";
		public final String INVALID_AMOUNT ="INVALID AMOUNT";
		public final String INVALID_TRANSACTION_REFERENCE_NUMBER ="NON UNIQUE TRANSACTION REFERENCE NUMBER";
		public final String INVALID_ACCESS_CODE ="INVALID ACCESS CODE";
		public final String INVALID_TRANSACTION_CODE ="INVALID TRANSACTION CODE";
		public final String INVALID_HASH_CODE ="INVALID HASH CODE";
		public final String INVALID_TRANX_CODE ="INVALID TRANSACTION CODE";
		public final String DB_INSERTION_FAILED ="TRANXLOG INSERTION FAILED";
		public final String DB_SELECTTION_FAILED ="TRANXLOG SELECTION FAILED";
		public final String DETAIL_INSERTION_FAILED ="TRANXDETAIL SELECTION FAILED";
		public final String FATAL_ERROR ="FATAL_ERROR";
		
		
	}
}
