package com.shipping.rest;

public class UhlQuoteResponse {

  private String quoteReference;
  private String clientId;
  private String clientReference;
  private double totalAmount;
  private UhlShipment shipment;
  private String validUntil;
  
  public UhlQuoteResponse(){
      super();
      quoteReference = "";
      clientId = "";
      clientReference = "";
      totalAmount = 0;
      shipment = new UhlShipment();
      validUntil = "";
  }
   
  public UhlQuoteResponse(String quoteReference, 
                          String clientId, 
                          String clientReference, 
                          double totalAmount,
                          UhlShipment shipment,
                          String validUntil){
      this.quoteReference = quoteReference;
      this.clientId = clientId;
      this.clientReference = clientReference;
      this.totalAmount = totalAmount;
      this.shipment = shipment;
      this.validUntil = validUntil;
  }
  
  public String getQuoteReference() {
    return this.quoteReference;
  }

  public void setQuoteReference(String quoteReference) {
    this.quoteReference = quoteReference;
    return;
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

  public double getTotalAmount() {
    return this.totalAmount;
  }

  public void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
    return;
  }

  public UhlShipment getShipment() {
    return this.shipment;
  }

  public void setShipment(UhlShipment Shipment) {
    this.shipment = shipment;
    return;
  }

  public String getValidUntil() {
    return this.validUntil;
  }

  public void setValidUntil(String validUntil) {
    this.validUntil = validUntil;
    return;
  }


  
  @Override
  public String toString(){
    return "Quote Reference: " + quoteReference
         + " Client Id: " + clientId
         + " Client Reference: " + clientReference
         + " Total Amount: " + totalAmount
         + " Shipment: " + shipment
         + " Valid Until: " + validUntil;
  }  
  
}
