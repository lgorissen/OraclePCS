package com.elidodo.parts;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import org.springframework.ws.soap.SoapHeaderElement;

import org.w3._2005._08.addressing.*;



public final class SoapHeaderUtils {


    public static String getText(SoapHeaderElement headerElement){

        String response = "";

        if (headerElement != null ) {
            response = headerElement.getText();
        }

        return response;
    }

    public static String getAddress(SoapHeaderElement headerElement){

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

    public static EndpointReferenceType getEndpoint(SoapHeaderElement headerElement){

        EndpointReferenceType response = new EndpointReferenceType();

        if ( headerElement != null) {
            try {

                JAXBContext context = JAXBContext.newInstance(org.w3._2005._08.addressing.ObjectFactory.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                JAXBElement jaxbHeaderElement =  (JAXBElement)unmarshaller.unmarshal(headerElement.getSource());
								
                EndpointReferenceType endpointAttributedURI = (EndpointReferenceType)jaxbHeaderElement.getValue();
                EndpointReferenceType endpoint = (EndpointReferenceType)jaxbHeaderElement.getValue();

                response =  endpoint;

            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }

        return response;
    }

}
