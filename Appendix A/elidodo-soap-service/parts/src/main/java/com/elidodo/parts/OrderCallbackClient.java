package com.elidodo.parts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.SoapFaultClientException;

import org.w3._2005._08.addressing.*;

import com.elidodo.parts.ws.OrderPartsResponse;

public class OrderCallbackClient extends WebServiceGatewaySupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderCallbackClient.class);


	public void orderCallback(String callbackAction,
                                  OrderPartsResponse callbackOrderPartsResponse,
                                  String callbackToURI,
				  String callbackMessageID,
				  String callbackRelatesTo,
				  EndpointReferenceType toEndpointReference) {


		LOGGER.info("About to execute callback ... callbackToURI " + callbackToURI);

		try { 
			getWebServiceTemplate().marshalSendAndReceive(callbackToURI,
								callbackOrderPartsResponse,
								new SoapHeaders(callbackAction,
										callbackMessageID,
										callbackRelatesTo,
										toEndpointReference));
		} catch (SoapFaultClientException sfce) {
			LOGGER.info("Found exception sfce.getFaultCode(): "+ sfce.getFaultCode());
		} catch (Exception e) {
			LOGGER.info("Found exception : "+ e);
		}

		return;
	}

}
