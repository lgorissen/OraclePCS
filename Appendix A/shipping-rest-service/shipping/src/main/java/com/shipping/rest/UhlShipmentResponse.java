package com.shipping.rest;

public class UhlShipmentResponse {

  private String quoteReference;
  private String shipmentReference;
  private String clientId;
  private String clientReference;
  private double totalAmount;
  private UhlShipment shipment;
  private String shipmentDate;
  
  public UhlShipmentResponse(){
      super();
      quoteReference = "";
      shipmentReference = "";
      clientId = "";
      clientReference = "";
      totalAmount = 0;
      shipment = new UhlShipment();
      shipmentDate = "";
  }
   
  public UhlShipmentResponse(String quoteReference,
                             String shipmentReference,
                             String clientId, 
                             String clientReference, 
                             double totalAmount,
                             UhlShipment shipment,
                             String shipmentDate){
      this.quoteReference = quoteReference;
      this.shipmentReference = shipmentReference;
      this.clientId = clientId;
      this.clientReference = clientReference;
      this.totalAmount = totalAmount;
      this.shipment = shipment;
      this.shipmentDate = shipmentDate;
  }
  
  public String getQuoteReference() {
    return this.quoteReference;
  }

  public void setQuoteReference(String quoteReference) {
    this.quoteReference = quoteReference;
    return;
  }

  public String getShipmentReference() {
    return this.shipmentReference;
  }

  public void setShipmentReference(String shipmentReference) {
    this.shipmentReference = shipmentReference;
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

  public void setShipment(UhlShipment shipment) {
    this.shipment = shipment;
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
    return " Quote Reference: " + quoteReference
         + " Shipment Reference: " + shipmentReference
         + " Client Id: " + clientId
         + " Client Reference: " + clientReference
         + " Total Amount: " + totalAmount
         + " Shipment: " + shipment
         + " Shipment Date: " + shipmentDate;
  }  
  
}
