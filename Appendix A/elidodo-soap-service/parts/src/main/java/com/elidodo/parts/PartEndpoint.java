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

import com.elidodo.parts.ws.PartList;
import com.elidodo.parts.ws.GetPartRequest;
import com.elidodo.parts.ws.GetPartResponse;
import com.elidodo.parts.ws.GetPartsByTypeRequest;
import com.elidodo.parts.ws.GetPartsByTypeResponse;
import com.elidodo.parts.ws.OrderPartsRequest;
import com.elidodo.parts.ws.OrderPartsResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.JAXBElement;

import org.w3._2005._08.addressing.*;

import java.lang.InterruptedException;

import java.util.UUID;



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


	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPartsByTypeRequest")
	@ResponsePayload
	public GetPartsByTypeResponse getPartsByType(@RequestPayload GetPartsByTypeRequest request) {

		LOGGER.info("getPartsByType received request: ", request.toString());

		GetPartsByTypeResponse response = new GetPartsByTypeResponse();
                PartList partList = new PartList();
                partList.getPart().add(partRepository.findPart(request.getType()));
		response.setPartList(partList);

		return response;
	}

	@Async
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "orderPartsRequest")
	@ResponsePayload
	public void orderParts(@RequestPayload OrderPartsRequest request,
                               @SoapHeader("{" + WS_ADDRESSING_NS  + "}Action") SoapHeaderElement actionHeaderElement,
                               @SoapHeader("{" + WS_ADDRESSING_NS  + "}MessageID") SoapHeaderElement messageIDHeaderElement,
                               @SoapHeader("{" + WS_ADDRESSING_NS  + "}ReplyTo") SoapHeaderElement replyToHeaderElement,
                               @SoapHeader("{" + WS_ADDRESSING_NS  + "}From") SoapHeaderElement fromHeaderElement,
                               @SoapHeader("{" + WS_ADDRESSING_NS  + "}To") SoapHeaderElement toHeaderElement) {

		LOGGER.info("orderParts received request: ", request.toString());

		String soapAction = getTextFromElement(actionHeaderElement);
		String soapMessageID = getTextFromElement(messageIDHeaderElement);
		String soapTo = getTextFromElement(toHeaderElement);
		String soapFromAddress = getAddressFromElement(fromHeaderElement);
		String soapReplyToAddress = getAddressFromElement(replyToHeaderElement);

		try {
			Thread.sleep(8000L);
		} catch (InterruptedException ie) {
			LOGGER.info("Found exception during sleep : "+ ie.toString());
		}

		LOGGER.info("Done sleeping: about to do callback");

		String callbackAction = "http://elidodo.com/parts/parts-ws/OrderPartsCallbackPort/orderPartsResponse";
		String callbackMessageID = UUID.randomUUID().toString();
                String callbackRelatesTo = soapMessageID;
		String callbackFromAddress =  soapTo;
		String callbackToAddress = "http://www.w3.org/2005/08/addressing/anonymous";
                if ( (soapReplyToAddress != null) && (soapReplyToAddress.length() > 0) ) {
			callbackToAddress = soapReplyToAddress;
		} else if ( (soapFromAddress != null) && (soapFromAddress.length() > 0) ) {
			callbackToAddress = soapFromAddress;
		}
                


                orderCallbackClient.orderCallback(callbackAction,
						  callbackMessageID,
						  callbackRelatesTo,
						  callbackFromAddress,
						  callbackToAddress);

		LOGGER.info("orderParts header Action: " +  soapAction);
		LOGGER.info("orderParts header MessageID: " +  soapMessageID);
		LOGGER.info("orderParts header To: " +  soapTo);
		LOGGER.info("orderParts header From Address: " +  soapFromAddress);
		LOGGER.info("orderParts header ReplyTo Address: " +  soapReplyToAddress);

		return;

	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "orderPartsResponse")
	@ResponsePayload
	public void orderPartsCallback(@RequestPayload OrderPartsResponse request) {
		
		LOGGER.info("orderPartsCallback received request: ", request.toString());

		return;
	}

        private String getTextFromElement(SoapHeaderElement headerElement){

                String response = "";

                if (headerElement != null ) {
                        response = headerElement.getText();
                }

                return response;
        }

        private String getAddressFromElement(SoapHeaderElement headerElement){

                String response = "";

                if ( headerElement != null) {
                        try {

                                JAXBContext context = JAXBContext.newInstance(org.w3._2005._08.addressing.ObjectFactory.class);
                                Unmarshaller unmarshaller = context.createUnmarshaller();
                                JAXBElement jaxbHeaderElement =  (JAXBElement)unmarshaller.unmarshal(headerElement.getSource());
                                EndpointReferenceType endpoint = (EndpointReferenceType)jaxbHeaderElement.getValue();
                                AttributedURIType endpointAddress = endpoint.getAddress();

                                response =  endpointAddress.getValue();

                        } catch (JAXBException e) {
                                e.printStackTrace();
                        }
                }

                return response;
        }


}
