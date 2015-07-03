package com.wirecard.ezecom.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wirecard.ezecom.constants.StringConstants;
import com.wirecard.ezecom.form.ItemBean;

/**
@author WCCTTI-JANAHAN
 */

@Controller
public class StatusCheckController {
	
	@RequestMapping(value="/check1.jsp", method = RequestMethod.GET)
	public @ResponseBody String checkStatus(@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname	) {
		System.out.println("+++++++++AJAX++++++++++++++++++++");
		//itemBean.setImgQRData(request.getAttribute("e_imgQRData"));
		//itemBean.setResponseCode(StringConstants.ResponseCodes.QRCODE_GENERATION_FAILED);
		//itemBean.setErrorRemark(StringConstants.ResponseCodes.QRCODE_GENERATION_FAILED);
		
		return "test";//StringConstants.JspPages.SUCCESS_PAGE;
	}

}
