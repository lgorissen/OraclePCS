package com.elidodo.parts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.SoapMessage;

import org.w3._2005._08.addressing.*;

import java.net.URLDecoder;

import javax.xml.namespace.QName;

import java.util.UUID;


public class SoapHeaders implements WebServiceMessageCallback{

	private static final Logger LOGGER = LoggerFactory.getLogger(SoapHeaders.class);

	private String callbackAction;
	private String callbackMessageID;
	private String callbackRelatesTo;
	//private String callbackFromAddress;
	private EndpointReferenceType toEndpointReference;
	//private AttributedURIType toEndpointReference;

	public SoapHeaders(String callbackAction,
			   String callbackMessageID,
			   String callbackRelatesTo,
			   EndpointReferenceType toEndpointReference) {

		this.callbackAction = callbackAction;
		this.callbackMessageID = callbackMessageID;
		this.callbackRelatesTo = callbackRelatesTo;
		this.toEndpointReference = toEndpointReference;
	}
	
	@Override
	public void doWithMessage(WebServiceMessage message) {


		SoapHeader header = ((SoapMessage)message).getSoapHeader();

		// set Action Soap Header Element
                SoapHeaderElement actionHeaderElementOut =  
			header.addHeaderElement(new QName("http://www.w3.org/2005/08/addressing", "Action", "wsa"));
		actionHeaderElementOut.setText(callbackAction);
                LOGGER.info(".doWithMessage : set Action to " + callbackAction);
		
		// set MessageID Soap Header Element
                SoapHeaderElement messageIDHeaderElementOut =  
			header.addHeaderElement(new QName("http://www.w3.org/2005/08/addressing", "MessageID", "wsa"));
                String randomMessageID = UUID.randomUUID().toString();
		messageIDHeaderElementOut.setText(randomMessageID);
                LOGGER.info(".doWithMessage : set MessageID to " + randomMessageID);


		// set RelatesTo Soap Header Element
                SoapHeaderElement relatesToHeaderElementOut =  
			header.addHeaderElement(new QName("http://www.w3.org/2005/08/addressing", "RelatesTo", "wsa"));
                if ( ( callbackRelatesTo != null ) && ( callbackRelatesTo.length() > 0 ) ) {
		    relatesToHeaderElementOut.setText(callbackRelatesTo);
                    LOGGER.info(".doWithMessage : set RelatesTo to " + callbackRelatesTo);
                } else {
		    relatesToHeaderElementOut.setText(callbackMessageID);
                    LOGGER.info(".doWithMessage : set RelatesTo to " + callbackMessageID);
                }

                // set To Soap Header Element
                try {
                    String toAddress = toEndpointReference.getAddress().getValue();
                    toAddress = URLDecoder.decode(toAddress, "UTF-8");

                    SoapHeaderElement toHeaderElementOut =  
		        header.addHeaderElement(new QName("http://www.w3.org/2005/08/addressing", "To", "wsa"));
        	    toHeaderElementOut.setText(toAddress);
                    LOGGER.info(".doWithMessage : set To to address : " + toAddress );
                } catch (Exception e) {
                     LOGGER.info(".doWithMessage: Exception when setting To Soap Header Element : " + e);
                }

	}
	
}
