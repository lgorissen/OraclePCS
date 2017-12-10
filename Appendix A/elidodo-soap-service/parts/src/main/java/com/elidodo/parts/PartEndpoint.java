package com.elidodo.parts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Async;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;
import org.springframework.ws.soap.SoapHeaderElement;

import com.elidodo.parts.ws.*;

import org.w3._2005._08.addressing.*;

import java.lang.InterruptedException;

import java.util.Random;


@Endpoint
public class PartEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(PartEndpoint.class);

	private static final String NAMESPACE_URI = "http://elidodo.com/parts/ws";
	private static final String WS_ADDRESSING_NS = "http://www.w3.org/2005/08/addressing";

	private PartRepository partRepository;

	@Autowired
	public PartEndpoint(PartRepository partRepository) {
		this.partRepository = partRepository;
	}

	@Autowired
	private OrderCallbackClient orderCallbackClient;


	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPartRequest")
	@ResponsePayload
	public GetPartResponse getPart(@RequestPayload GetPartRequest request) {

		LOGGER.info("getPart received request " + request.toString());

		GetPartResponse response = new GetPartResponse();
		response.setPart(partRepository.findPart(request.getId()));

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPartsRequest")
	@ResponsePayload
	public GetPartsResponse getParts(@RequestPayload GetPartsRequest request) {

		LOGGER.info("getParts received request " + request.toString());

		GetPartsResponse response = new GetPartsResponse();
		response.setPartList(partRepository.getParts());

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPartsByTypeRequest")
	@ResponsePayload
	public GetPartsByTypeResponse getPartsByType(@RequestPayload GetPartsByTypeRequest request) {

		LOGGER.info("getPartsByType received request: "+ request.toString());

		GetPartsByTypeResponse response = new GetPartsByTypeResponse();
                PartList partList = partRepository.findPartsByType(request.getType());
		response.setPartList(partList);

		return response;
	}

	@Async
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "orderPartsRequest")
	@ResponsePayload
	public void orderParts(@RequestPayload OrderPartsRequest request,
                               @SoapHeader("{" + WS_ADDRESSING_NS  + "}Action") SoapHeaderElement actionHeaderElement,
                               @SoapHeader("{" + WS_ADDRESSING_NS  + "}MessageID") SoapHeaderElement messageIDHeaderElement,
                               @SoapHeader("{" + WS_ADDRESSING_NS  + "}RelatesTo") SoapHeaderElement relatesToHeaderElement,
                               @SoapHeader("{" + WS_ADDRESSING_NS  + "}ReplyTo") SoapHeaderElement replyToHeaderElement,
                               @SoapHeader("{" + WS_ADDRESSING_NS  + "}From") SoapHeaderElement fromHeaderElement,
                               @SoapHeader("{" + WS_ADDRESSING_NS  + "}To") SoapHeaderElement toHeaderElement) {

		LOGGER.info("orderParts received request: "+ request.toString());

		String callbackMessageID = SoapHeaderUtils.getText(messageIDHeaderElement);
		LOGGER.info("orderParts received MessageID: "+ callbackMessageID);
		String callbackRelatesTo = SoapHeaderUtils.getText(relatesToHeaderElement);
		LOGGER.info("orderParts received RelatesTo: "+ callbackRelatesTo);
		EndpointReferenceType callbackToEndpointReference = SoapHeaderUtils.getEndpoint(replyToHeaderElement);

		try {
			Thread.sleep(8000L);
		} catch (InterruptedException ie) {
			LOGGER.info("Found exception during sleep : "+ ie.toString());
		}

		LOGGER.info("Done sleeping: about to do callback");

		String callbackAction = "http://elidodo.com/parts/parts/ws/OrderPartsCallbackPort/orderPartsResponse";
		String callbackToURI = "http://www.w3.org/2005/08/addressing/anonymous";

                // determine what callback endpoint is (callbackToURI)

                String soapReplyToAddress = SoapHeaderUtils.getAddress(replyToHeaderElement);
                String soapFromAddress = SoapHeaderUtils.getAddress(fromHeaderElement);

                if ( (soapReplyToAddress != null) && (soapReplyToAddress.length() > 0) ) {
			callbackToURI = soapReplyToAddress;
		} else if ( (soapFromAddress != null) && (soapFromAddress.length() > 0) ) {
			callbackToURI = soapFromAddress;
		}
                
                LOGGER.info("callback endpoint is now set to : " + callbackToURI);

                // compose the response
                OrderPartsResponse callbackOrderPartsResponse = new OrderPartsResponse();

                callbackOrderPartsResponse.setYourOrderNumber(request.getYourOrderNumber());
                callbackOrderPartsResponse.setEliDodoOrderNumber("ELI-0000" + new Random().nextInt(1000000));
                callbackOrderPartsResponse.setOrderAccepted(true);
                //LOGGER.info("DEBUG orderlinelist size "+ request.getOrderLineList().getOrderLine().size());
                callbackOrderPartsResponse.setOrderLineList(request.getOrderLineList());
                callbackOrderPartsResponse.setTotalOrderAmount(partRepository.getOrderAmount(request.getOrderLineList()));
                callbackOrderPartsResponse.setCurrency(Currency.fromValue("EUR"));


                // send the callback
                orderCallbackClient.orderCallback(callbackAction,
                                                  callbackOrderPartsResponse,
                                                  callbackToURI,
						  callbackMessageID,
						  callbackRelatesTo,
						  callbackToEndpointReference);

		LOGGER.info("orderCallbackClient return from callBack - done!");

		return;

	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "orderPartsResponse")
	@ResponsePayload
	public void orderPartsCallback(@RequestPayload OrderPartsResponse request) {
		
		LOGGER.info("orderPartsCallback received request (should not be here!): "+ request.toString());

		return;
	}


}
