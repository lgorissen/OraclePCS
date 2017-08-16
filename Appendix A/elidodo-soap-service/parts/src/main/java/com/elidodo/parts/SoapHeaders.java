package com.elidodo.parts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.SoapMessage;

import org.w3._2005._08.addressing.*;

import javax.xml.namespace.QName;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.springframework.xml.transform.StringSource;


public class SoapHeaders implements WebServiceMessageCallback{

	private static final Logger LOGGER = LoggerFactory.getLogger(SoapHeaders.class);

	private String callbackAction;
	private String callbackMessageID;
	private String callbackRelatesTo;
	private String callbackFromAddress;
	private String callbackToAddress;

	public SoapHeaders(String callbackAction,
			   String callbackMessageID,
			   String callbackRelatesTo,
			   String callbackFromAddress,
			   String callbackToAddress) {
		this.callbackAction = callbackAction;
		this.callbackMessageID = callbackMessageID;
		this.callbackRelatesTo = callbackRelatesTo;
		this.callbackFromAddress = callbackFromAddress;
		this.callbackToAddress = callbackToAddress;
	}
	
	@Override
	public void doWithMessage(WebServiceMessage message) {

		SoapHeader header = ((SoapMessage)message).getSoapHeader();

		SoapHeaderElement actionHeaderElement =  
			header.addHeaderElement(new QName("http://www.w3.org/2005/08/addressing", "Action", "wsa"));
		actionHeaderElement.setText(callbackAction);
		
		SoapHeaderElement messageIDHeaderElement =  
			header.addHeaderElement(new QName("http://www.w3.org/2005/08/addressing", "MessageID", "wsa"));
		messageIDHeaderElement.setText(callbackMessageID);
		
		SoapHeaderElement relatesToHeaderElement =  
			header.addHeaderElement(new QName("http://www.w3.org/2005/08/addressing", "RelatesTo", "wsa"));
		relatesToHeaderElement.setText(callbackRelatesTo);
		
		
		StringSource fromSource = new StringSource("<wsa:From xmlns:wsa=\"http://www.w3.org/2005/08/addressing\"><Address>" + callbackFromAddress + "</Address></wsa:From>");

		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(fromSource, header.getResult());

		} catch (Exception e) {
			LOGGER.info("Exception during transformation : "+ e);
		}
	}
	
}
