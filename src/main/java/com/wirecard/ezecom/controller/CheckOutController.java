package com.wirecard.ezecom.controller;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import com.wirecard.ezecom.constants.StringConstants;
import com.wirecard.ezecom.dto.EErrorLogDto;
import com.wirecard.ezecom.dto.ETranxLogDto;
import com.wirecard.ezecom.form.ItemBean;
import com.wirecard.ezecom.service.UnSuccessfullValidationService;
import com.wirecard.ezecom.service.ValidationService;
import com.wirecard.ezecom.service.SuccessfullValidationService;
import com.wirecard.ezecom.validator.EMerchantDetailsValidator;
import com.wirecard.ezecom.validator.ETransactionValidator;
import com.wirecard.ezecom.validator.HashCodeValidator;
import com.wirecard.ezecom.validator.ItemValidator;

/**
 @author WCCTTI-JANAHAN
 */

/**
 * Handles requests for the application home page.
 */
@Controller
//@RequestMapping("/index.jsp")
public class CheckOutController {

	private static final Logger logger = LoggerFactory
			.getLogger(CheckOutController.class);
	@Autowired
	ItemValidator objItemValidator;
	@Autowired
	private ValidationService objValidationService;
	@Autowired
	private EMerchantDetailsValidator objEMerchantDetailsValidator;
	@Autowired
	private ETransactionValidator objETransactionValidator;
	@Autowired
	private HashCodeValidator objHashCodeValidator;
	@Autowired
	private ETranxLogDto objETranxLogDto;
	@Autowired
	private EErrorLogDto objEErrorLogDto;
	@Autowired
	private SuccessfullValidationService objSuccessfullValidationService;
	@Autowired
	private UnSuccessfullValidationService objUnSuccessfullValidationService;

	@RequestMapping(value="/index.htm", method = RequestMethod.POST)
	public String checkOut(@ModelAttribute("item") ItemBean itemBean,
			BindingResult result, HttpServletRequest request) {

		String uniqueTransactionFlag;
		String validateAccessCodeFlag, validateMerchantFlag, responseCode;

		
		//Creating Bean from User Request/Form -local method
		createItemBean(itemBean,request);
		
		// Form Validation
		objItemValidator.validate(itemBean, result);
		if (result.hasErrors()) {
			itemBean.setResponseCode(StringConstants.ResponseCodes.REQUIRED_FIELD_MISSING);
			itemBean.setErrorRemark(StringConstants.ErrorRemarks.FIELD_MISSING);
			return StringConstants.JspPages.ERROR_PAGE;
		}

		// Merchant validation
		validateMerchantFlag = objValidationService
				.valdateMerchantNumber(itemBean.getMerchantNo());

		if (validateMerchantFlag
				.equals(StringConstants.Validation.INVALID_MERCHANT)) {
			objEMerchantDetailsValidator.validateEMerchant(
					validateMerchantFlag, result);

			if (result.hasErrors()) {

				 objETranxLogDto=createETranxLogDto(itemBean);
				
				 objETranxLogDto.setOrderInfo(StringConstants.ErrorRemarks.INVALID_MERCHANT);
				 objETranxLogDto.
				 setResponseCode(StringConstants.ResponseCodes.
				 INVALID_MERCHANT);
				 objUnSuccessfullValidationService.insertETranxLog
				 (objETranxLogDto);
				 
				itemBean.setResponseCode(StringConstants.ResponseCodes.INVALID_MERCHANT);
				itemBean.setErrorRemark(StringConstants.ErrorRemarks.INVALID_MERCHANT);
				return StringConstants.JspPages.ERROR_PAGE;
			}

		} else if (StringConstants.ResponseCodes.ERROR_CODE
				.equals(validateMerchantFlag)) {
			objEMerchantDetailsValidator.validateException(
					validateMerchantFlag, result);
			itemBean.setResponseCode(StringConstants.ResponseCodes.ERROR_CODE);
			return StringConstants.JspPages.ERROR_PAGE;

		}

		// Date validation
		itemBean.setValidatedMerchantTranxDate(objItemValidator.validateDate(
				itemBean.getDateTime(), result));
		if (result.hasErrors()) {
			itemBean.setValidatedMerchantTranxDate(new Date());
			objETranxLogDto = createETranxLogDto(itemBean);
			objETranxLogDto.setAmount(0.0);
			objETranxLogDto
					.setResponseCode(StringConstants.ResponseCodes.INVALID_TRANSACTION_DATE);
			objETranxLogDto.setOrderInfo(StringConstants.ErrorRemarks.INVALID_TRANSACTION_DATE +" : "+itemBean.getDateTime());
			responseCode = objUnSuccessfullValidationService
					.insertETranxLog(objETranxLogDto);
			itemBean.setResponseCode(responseCode);
			itemBean.setErrorRemark(StringConstants.ErrorRemarks.INVALID_TRANSACTION_DATE);
			return StringConstants.JspPages.ERROR_PAGE;
		}

		// Amount Validation
		String amt = (String) request
				.getParameter(StringConstants.MerchantForm.AMOUNT);
		objItemValidator.validateAmount(amt, result);
		if (result.hasErrors()) {

			objETranxLogDto = createETranxLogDto(itemBean);
			objETranxLogDto.setAmount(0.0);
			objETranxLogDto.setOrderInfo(StringConstants.ErrorRemarks.INVALID_AMOUNT+" : "+amt);
			objETranxLogDto
					.setResponseCode(StringConstants.ResponseCodes.INVALID_AMOUNT);
			responseCode = objUnSuccessfullValidationService
					.insertETranxLog(objETranxLogDto);
			itemBean.setResponseCode(responseCode);
			itemBean.setErrorRemark(StringConstants.ErrorRemarks.INVALID_AMOUNT);
			return StringConstants.JspPages.ERROR_PAGE;
		}
		itemBean.setAmount(Double.valueOf(request.getParameter("e_Amount")));

		// Validating Merchant tranx Ref number
		uniqueTransactionFlag = objValidationService.isUniqueTransaction(
				itemBean.getMerchantNo(), itemBean.getMerchantTranxRefNo(),
				itemBean.getOrderNo());

		if (StringConstants.Validation.NON_UNIQUE_TRANSACTION
				.equals(uniqueTransactionFlag)) {

			objETransactionValidator.isUniqueTranxvalidation(
					uniqueTransactionFlag, result);
			if (result.hasErrors()) {
				objETranxLogDto = createETranxLogDto(itemBean);
				objETranxLogDto
						.setResponseCode(StringConstants.ResponseCodes.INVALID_TRANSACTION_REFERENCE_NUMBER);
				responseCode = objUnSuccessfullValidationService
						.insertETranxLog(objETranxLogDto);
				itemBean.setResponseCode(responseCode);
				itemBean.setErrorRemark(StringConstants.ErrorRemarks.INVALID_TRANSACTION_REFERENCE_NUMBER);
				return StringConstants.JspPages.ERROR_PAGE;
			}
		} else if (StringConstants.ResponseCodes.ERROR_CODE
				.equals(uniqueTransactionFlag)) {
			objEMerchantDetailsValidator.validateException(
					uniqueTransactionFlag, result);
			if (result.hasErrors()) {
				objETranxLogDto = createETranxLogDto(itemBean);
				itemBean.setResponseCode(StringConstants.ResponseCodes.ERROR_CODE);
				responseCode = objUnSuccessfullValidationService
						.insertETranxLog(objETranxLogDto);
				itemBean.setResponseCode(responseCode);
				return StringConstants.JspPages.ERROR_PAGE;
			}
		}
		// ACCESS CODE VALIDATION
		validateAccessCodeFlag = objValidationService.valdateAccessCode(
				itemBean.getMerchantNo(), itemBean.getAccessCode());
		if (validateAccessCodeFlag
				.equals(StringConstants.Validation.INVALID_ACCESS_CODE)) {
			objEMerchantDetailsValidator.validateEMerchant(
					validateAccessCodeFlag, result);
			if (result.hasErrors()) {
				objETranxLogDto = createETranxLogDto(itemBean);
				objETranxLogDto
						.setResponseCode(StringConstants.ResponseCodes.INVALID_ACCESS_CODE);
				//responseCode = objUnSuccessfullValidationService
				//		.insertETranxLog(objETranxLogDto);
				ETranxLogDto objSuccessfulETranxLogDto = objSuccessfullValidationService
						.insertETranxLog(objETranxLogDto);
				itemBean.setResponseCode(objSuccessfulETranxLogDto.getResponseCode());
				itemBean.setErrorRemark(StringConstants.ErrorRemarks.INVALID_ACCESS_CODE);

				return StringConstants.JspPages.ERROR_PAGE;
			}
		} else if (StringConstants.ResponseCodes.ERROR_CODE
				.equals(validateMerchantFlag)) {
			objEMerchantDetailsValidator.validateException(
					validateMerchantFlag, result);
			if (result.hasErrors()) {
				objETranxLogDto = createETranxLogDto(itemBean);
				itemBean.setResponseCode(StringConstants.ResponseCodes.ERROR_CODE);
				responseCode = objUnSuccessfullValidationService
						.insertETranxLog(objETranxLogDto);
				itemBean.setResponseCode(responseCode);
				return StringConstants.JspPages.ERROR_PAGE;
			}
		}
/*
		//Validate tranx code
		System.out.println("********************TRANX CODE***********"+itemBean.getTransactionCode());
				if(!(itemBean.getTransactionCode().equalsIgnoreCase(StringConstants.Validation.TRANX_CODE_PAY)||
						itemBean.getTransactionCode().equalsIgnoreCase(StringConstants.Validation.TRANX_CODE_EQUERY))){
					objETranxLogDto = createETranxLogDto(itemBean);
					objETranxLogDto
							.setResponseCode(StringConstants.ResponseCodes.INVALID_TRANSACTION_CODE);
					responseCode = objUnSuccessfullValidationService
							.insertETranxLog(objETranxLogDto);
					objETransactionValidator.isValidTransactionCodevalidation(StringConstants.Validation.INVALID_TRANSACTION_CODE, result);
					if (result.hasErrors()) {
					itemBean.setResponseCode(responseCode);
					itemBean.setErrorRemark(StringConstants.ErrorRemarks.INVALID_TRANX_CODE);
					return StringConstants.JspPages.ERROR_PAGE;
				}
				}
				*/
			/*
		// Validate Hash Code
		objHashCodeValidator.validate(itemBean, result);
		if (result.hasErrors()) {
			System.out.println("+++++++++++++++INVALID HASH CODE+++++++++++++++++++++++");
			objETranxLogDto = createETranxLogDto(itemBean);
			objETranxLogDto
					.setResponseCode(StringConstants.ResponseCodes.INVALID_HASH_CODE);
			ETranxLogDto objSuccessfulETranxLogDto = objSuccessfullValidationService
					.insertETranxLog(objETranxLogDto);
			itemBean.setResponseCode(objSuccessfulETranxLogDto.getResponseCode());
			if(StringConstants.ResponseCodes.INVALID_TRANSACTION_CODE.equalsIgnoreCase(objSuccessfulETranxLogDto.getResponseCode())){
			itemBean.setErrorRemark(StringConstants.ErrorRemarks.INVALID_TRANX_CODE);
			}
			else{
				itemBean.setErrorRemark(StringConstants.ErrorRemarks.INVALID_HASH_CODE);
			}
			return StringConstants.JspPages.ERROR_PAGE;
		}
		System.out.println("+++++++++++++++VALID HASH CODE+++++++++++++++++++++++");
		*/
		//Tranx code validation inside successfull
		// SUCCESSFUL
		System.out.println("Item Bean Response  code : "+itemBean.getResponseCode());
		objETranxLogDto = createETranxLogDto(itemBean);
		System.out.println("Item Bean Response  code : "+objETranxLogDto.getResponseCode());
		objETranxLogDto.setResponseCode(StringConstants.ResponseCodes.SUCCESS_CODE);
		ETranxLogDto objSuccessfulETranxLogDto = objSuccessfullValidationService
				.insertETranxLog(objETranxLogDto);
		if(!(objSuccessfulETranxLogDto.getResponseCode().equalsIgnoreCase(StringConstants.ResponseCodes.SUCCESS_CODE))){
			if(objSuccessfulETranxLogDto.getResponseCode().equalsIgnoreCase(StringConstants.ResponseCodes.INVALID_TRANSACTION_CODE)){
				objETransactionValidator.isValidTransactionCodevalidation(StringConstants.Validation.INVALID_TRANSACTION_CODE, result);
				if (result.hasErrors()) {
				itemBean.setResponseCode(StringConstants.ResponseCodes.INVALID_TRANSACTION_CODE);
				itemBean.setErrorRemark(StringConstants.ErrorRemarks.INVALID_TRANX_CODE);
				return StringConstants.JspPages.ERROR_PAGE;
			}
			}
			if(objSuccessfulETranxLogDto.getResponseCode().equalsIgnoreCase(StringConstants.ResponseCodes.INVALID_HASH_CODE)){
				objETransactionValidator.isValidHAshCodevalidation(StringConstants.Validation.INVALID_HASH_CODE, result);
				if (result.hasErrors()) {
				itemBean.setResponseCode(StringConstants.ResponseCodes.INVALID_HASH_CODE);
				itemBean.setErrorRemark(StringConstants.ErrorRemarks.INVALID_HASH_CODE);
				return StringConstants.JspPages.ERROR_PAGE;
			}
			}
			if(objSuccessfulETranxLogDto.getResponseCode().equalsIgnoreCase(StringConstants.ResponseCodes.INVALID_ACCESS_CODE)){
				objETransactionValidator.isValidAccessCodevalidation(StringConstants.Validation.INVALID_ACCESS_CODE, result);
				if (result.hasErrors()) {
				itemBean.setResponseCode(StringConstants.ResponseCodes.INVALID_ACCESS_CODE);
				itemBean.setErrorRemark(StringConstants.ErrorRemarks.INVALID_ACCESS_CODE);
				return StringConstants.JspPages.ERROR_PAGE;
			}
			}
				if(objSuccessfulETranxLogDto.getResponseCode().equalsIgnoreCase(StringConstants.ResponseCodes.DB_INSERTION_FAILED)){
					objETransactionValidator.isDBInsertionFailed(StringConstants.ResponseCodes.DB_INSERTION_FAILED, result);
					if (result.hasErrors()) {
					itemBean.setResponseCode(StringConstants.ResponseCodes.DB_INSERTION_FAILED);
					itemBean.setErrorRemark(StringConstants.ErrorRemarks.DB_INSERTION_FAILED);
					return StringConstants.JspPages.ERROR_PAGE;
				}
				}
				if(objSuccessfulETranxLogDto.getResponseCode().equalsIgnoreCase(StringConstants.ResponseCodes.DETAIL_INSERTION_FAILED)){
					objETransactionValidator.isDetailInsertionFailed(StringConstants.ResponseCodes.DETAIL_INSERTION_FAILED, result);
					if (result.hasErrors()) {
					itemBean.setResponseCode(StringConstants.ResponseCodes.DETAIL_INSERTION_FAILED);
					itemBean.setErrorRemark(StringConstants.ErrorRemarks.DETAIL_INSERTION_FAILED);
					return StringConstants.JspPages.ERROR_PAGE;
				}
				}
				if(objSuccessfulETranxLogDto.getResponseCode().equalsIgnoreCase(StringConstants.ResponseCodes.DB_SELECTION_FAILED)){
					objETransactionValidator.isDBSelectionFailed(StringConstants.ResponseCodes.DB_SELECTION_FAILED, result);
					if (result.hasErrors()) {
					itemBean.setResponseCode(StringConstants.ResponseCodes.DB_SELECTION_FAILED);
					itemBean.setErrorRemark(StringConstants.ErrorRemarks.DB_SELECTTION_FAILED);
					return StringConstants.JspPages.ERROR_PAGE;
				}
				}
				objETransactionValidator.isCommonFailure(StringConstants.ResponseCodes.ERROR_CODE, result);
				if (result.hasErrors()) {
				itemBean.setResponseCode(StringConstants.ResponseCodes.ERROR_CODE);
				itemBean.setErrorRemark(StringConstants.ErrorRemarks.FATAL_ERROR);
				return StringConstants.JspPages.ERROR_PAGE;
			}	
		
		}
		
		itemBean.setRemarks("SUCCESS");
		itemBean.setResponseCode(objSuccessfulETranxLogDto.getResponseCode());
		
		// QR CODE GENERATION
		//local method call for QR String
		String strQRtext=createQRString(itemBean,objSuccessfulETranxLogDto);
		System.out.println("QR string : " +strQRtext);
		try {
			ByteArrayOutputStream out = QRCode
					.from(strQRtext)
					.withSize(StringConstants.QRImage.QR_IMG_WIDTH,
							StringConstants.QRImage.QR_IMG_HEIGHT)
					.to(ImageType.JPG).stream();

			itemBean.setImgQRData(new org.apache.commons.codec.binary.Base64()
					.encode(out.toByteArray()));
		} catch (Exception e) {
			itemBean.setResponseCode(StringConstants.ResponseCodes.QRCODE_GENERATION_FAILED);
			
			objETransactionValidator.isCommonFailure(StringConstants.ResponseCodes.QRCODE_GENERATION_FAILED, result);
			if (result.hasErrors()) {
			itemBean.setResponseCode(StringConstants.ResponseCodes.QRCODE_GENERATION_FAILED);
			itemBean.setErrorRemark(StringConstants.ResponseCodes.QRCODE_GENERATION_FAILED);
			return StringConstants.JspPages.ERROR_PAGE;
		}	
			
		}

		return StringConstants.JspPages.SUCCESS_PAGE;

	
	}

	private ETranxLogDto createETranxLogDto(ItemBean itemBean) {
		objETranxLogDto.setTranxcode(itemBean.getTransactionCode());

		objETranxLogDto.setMerchantNo(itemBean.getMerchantNo());
		objETranxLogDto.setMerchantRefno(itemBean.getMerchantTranxRefNo());
		objETranxLogDto.setOrderNo(itemBean.getOrderNo());
		objETranxLogDto.setAmount(itemBean.getAmount());
		objETranxLogDto.setSecurehashType(itemBean.getHashAlgo());
		objETranxLogDto.setVersion(itemBean.getVersion());
		if (itemBean.getValidatedMerchantTranxDate() != null) {
			objETranxLogDto.setMerchantTranxDate(itemBean
					.getValidatedMerchantTranxDate());
		}
		objETranxLogDto.setTranxStatus(StringConstants.Validation.TRANX_STATUS_QR);

		return objETranxLogDto;
	}
	
	
	private void createItemBean(ItemBean itemBean,HttpServletRequest request){
		
	  	DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	  	Date date = new Date();
	  	System.out.println("DATE TIME : "+dateFormat.format(date));
	  	
		
		
		itemBean.setMerchantName((String) request
				.getParameter(StringConstants.MerchantForm.MERCHANT_NAME));
		itemBean.setMerchantNo((String) request
				.getParameter(StringConstants.MerchantForm.MERCHANT_NO));
		itemBean.setAccessCode((String) request
				.getParameter(StringConstants.MerchantForm.ACCESS_CODE));
	/*	
		itemBean.setOrderNo((String) request
				.getParameter(StringConstants.MerchantForm.ORDER_NO));
		itemBean.setMerchantTranxRefNo((String) request
				.getParameter(StringConstants.MerchantForm.MERCHANT_TRANX_REF_NO));
	*/			
		
		itemBean.setOrderNo(dateFormat.format(date));
		itemBean.setMerchantTranxRefNo(dateFormat.format(date));
		
		
		itemBean.setTransactionCode((String) request
				.getParameter(StringConstants.MerchantForm.TRANSACTION_CODE));
		itemBean.setVersion((String) request
				.getParameter(StringConstants.MerchantForm.VERSION));
		itemBean.setDateTime((String) request
				.getParameter(StringConstants.MerchantForm.DATE_TIME));
		itemBean.setPayBy((String) request
				.getParameter(StringConstants.MerchantForm.PAY_BY));
		itemBean.setHashValue((String) request
				.getParameter(StringConstants.MerchantForm.HASH_VALUE));
		itemBean.setHashAlgo((String) request
				.getParameter(StringConstants.MerchantForm.HASH_ALGO));
	}
	
	private String createQRString(ItemBean itemBean,ETranxLogDto objSuccessfulETranxLogDto){
		String qrResEncrypt = itemBean.getMerchantNo() + "&" + itemBean.getMerchantTranxRefNo()
				+ "&" + itemBean.getOrderNo() + "&" + itemBean.getAmount();
		String strQRtext = null;
		try {
			strQRtext = StringConstants.QRFields.QR_MER_NAME+":"+itemBean.getMerchantName() +"&"
			+StringConstants.QRFields.QR_MER_ID+":"+itemBean.getMerchantNo() +"&"
			+StringConstants.QRFields.QR_MER_TRAX_REF_NO+":"+ itemBean.getMerchantTranxRefNo() +"&"
			+StringConstants.QRFields.QR_ORDER_NO+":"+ itemBean.getOrderNo() +"&"
			+StringConstants.QRFields.QR_AMOUNT+":"+ itemBean.getAmount() + "&"
			+StringConstants.QRFields.QR_CURRENCY+":"+objSuccessfulETranxLogDto.getCurrency()+"&"
			+StringConstants.QRFields.QR_RRN+":"+objSuccessfulETranxLogDto.getRrn()+"&"
			+StringConstants.QRFields.QR_RES_CODE+":"+ objSuccessfulETranxLogDto.getResponseCode()+"&"
			+StringConstants.QRFields.QR_RES_ENCRYPT+":"+ Util.encrypt3DES("120000001200000012000000", qrResEncrypt);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return strQRtext;
	}
	
	
	@RequestMapping(value="/check.htm", method = RequestMethod.POST)
	public @ResponseBody String checkStatus(@ModelAttribute("item") ItemBean itemBean) {
		boolean status;
		Date dateNow = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String date = dateFormat.format(dateNow);
		System.out.println("+++++++++AJAX++++++++++++++++++++");
		System.out.println("+++++++++values++++++++++++++++++++"+itemBean.getMerchantTranxRefNo()+" : "+itemBean.getOrderNo());
		status=objValidationService.checkPaymentStatus(itemBean.getMerchantNo(), itemBean.getMerchantTranxRefNo(), itemBean.getOrderNo(), itemBean.getAmount(), dateNow);
		System.out.println("+++++++++AJAX+++++STATUS+++++++++++++++"+status);
		//itemBean.setImgQRData(request.getAttribute("e_imgQRData"));
		//itemBean.setResponseCode(StringConstants.ResponseCodes.QRCODE_GENERATION_FAILED);
		//itemBean.setErrorRemark(StringConstants.ResponseCodes.QRCODE_GENERATION_FAILED);
		if(status == true){
			System.out.println("+++++++++++++RETURN 1+++++++++++++++"+status);
			return date;
		}
		else{
			System.out.println("+++++++++++++RETURN 0+++++++++++++++"+status);
			return "0";
		}
		
	}

	
}
