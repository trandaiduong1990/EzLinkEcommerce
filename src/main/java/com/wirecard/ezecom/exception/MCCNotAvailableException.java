package com.wirecard.ezecom.exception;
/**
@author WCCTTI-JANAHAN
 */

public class MCCNotAvailableException extends Exception {
	
	String FaultMessage;
	public MCCNotAvailableException(){}

	public MCCNotAvailableException(String faultMessage) {
		super();
		FaultMessage = faultMessage;
	}
	
	

}
