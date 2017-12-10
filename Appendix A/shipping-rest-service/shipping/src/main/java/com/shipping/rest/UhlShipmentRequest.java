package com.shipping.rest;

public class UhlShipmentRequest {

  private String clientId;
  private String clientReference;
  private UhlShipment shipment;
  private String quoteReference;
  private String shipmentDate;
  
  public UhlShipmentRequest(){
      super();
      clientId = "";
      clientReference = "";
      shipment = new UhlShipment();
      quoteReference = "";
      shipmentDate = "";
  }
   
  public UhlShipmentRequest(String clientId, 
                            String clientReference, 
                            UhlShipment shipment,
                            String quoteReference,
                            String shipmentDate){
      this.clientId = clientId;
      this.clientReference = clientReference;
      this.shipment = shipment;
      this.quoteReference = quoteReference;
      this.shipmentDate = shipmentDate;
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

  public String getQuoteReference() {
    return this.quoteReference;
  }

  public void setQuoteReference(String quoteReference) {
    this.quoteReference = quoteReference;
    return;
  }

  public String getShipmentDate() {
    return this.shipmentDate;
  }

  public void setShipmentDate(String shipmentDate) {
    this.shipmentDate = shipmentDate;
    return;
  }


  
  @Override
  public String toString(){
    return " Client Id: " + clientId
         + " Client Reference: " + clientReference
         + " Shipment: " + shipment
         + " Quote Reference: " + quoteReference
         + " Shipment Date: " + shipmentDate;
  }  
  
}
