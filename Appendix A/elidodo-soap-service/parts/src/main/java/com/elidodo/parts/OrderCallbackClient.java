package com.elidodo.parts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.elidodo.parts.ws.OrderPartsResponse;

public class OrderCallbackClient extends WebServiceGatewaySupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderCallbackClient.class);

	public void orderCallback(String callbackAction,
				  String callbackMessageID,
				  String callbackRelatesTo,
				  String callbackFromAddress,
				  String callbackToAddress) {

		OrderPartsResponse callbackRequest = new OrderPartsResponse();
		callbackRequest.setOrderAccepted(true);

		LOGGER.info("About to execute callback ...  ");

		try {
			getWebServiceTemplate().marshalSendAndReceive(callbackToAddress,
								callbackRequest,
								new SoapHeaders(callbackAction,
										callbackMessageID,
										callbackRelatesTo,
										callbackFromAddress,
										callbackToAddress));
		} catch (Exception e) {
			LOGGER.info("Found exception : "+ e);
		}

		return;
	}

}
