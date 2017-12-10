package com.shipping.rest;

public class UhlQuoteRequest {

  private String clientId;
  private String clientReference;
  private UhlShipment shipment;
  
  public UhlQuoteRequest(){
      super();
      clientId = "";
      clientReference = "";
      shipment = new UhlShipment();
  }
   
  public UhlQuoteRequest(String clientId, String clientReference, UhlShipment shipment){
      this.clientId = clientId;
      this.clientReference = clientReference;
      this.shipment = shipment;
  }
  
  public String getClientId() {
    return this.clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
    return;
  }

  public String getClientReference() {
    return this.clientReference;
  }

  public void setClientReference(String clientReference) {
    this.clientReference = clientReference;
    return;
  }

  public UhlShipment getShipment() {
    return this.shipment;
  }

  public void setShipment(UhlShipment shipment) {
    this.shipment = shipment;
    return;
  }


  
  @Override
  public String toString(){
    return " Client Id: " + clientId
         + " Client Reference: " + clientReference
         + " Shipment: " + shipment;
  }  
  
}
