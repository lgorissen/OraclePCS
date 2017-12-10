package com.shipping.rest;

public class DpsQuoteResponse {

  private String dpsQuoteReferenceId;
  private String clientAccountId;
  private String clientQuoteReferenceId;
  private double totalAmount;
  private DpsShipment dpsShipment;
  private String validUntil;
  
  public DpsQuoteResponse(){
      super();
      dpsQuoteReferenceId = "";
      clientAccountId = "";
      clientQuoteReferenceId = "";
      totalAmount = 0;
      dpsShipment = new DpsShipment();
      validUntil = "";
  }
   
  public DpsQuoteResponse(String dpsQuoteReferenceId, 
                          String clientAccountId, 
                          String clientQuoteReferenceId, 
                          double totalAmount,
                          DpsShipment dpsShipment,
                          String validUntil){
      this.dpsQuoteReferenceId = dpsQuoteReferenceId;
      this.clientAccountId = clientAccountId;
      this.clientQuoteReferenceId = clientQuoteReferenceId;
      this.totalAmount = totalAmount;
      this.dpsShipment = dpsShipment;
      this.validUntil = validUntil;
  }
  
  public String getDpsQuoteReferenceId() {
    return this.dpsQuoteReferenceId;
  }

  public void setDpsQuoteReferenceId(String dpsQuoteReferenceId) {
    this.dpsQuoteReferenceId = dpsQuoteReferenceId;
    return;
  }

  public String getClientAccountId() {
    return this.clientAccountId;
  }

  public void setClientAccountId(String clientAccountId) {
    this.clientAccountId = clientAccountId;
    return;
  }

  public String getClientQuoteReferenceId() {
    return this.clientQuoteReferenceId;
  }

  public void setClientQuoteReferenceId(String clientQuoteReferenceId) {
    this.clientQuoteReferenceId = clientQuoteReferenceId;
    return;
  }

  public double getTotalAmount() {
    return this.totalAmount;
  }

  public void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
    return;
  }

  public DpsShipment getDpsShipment() {
    return this.dpsShipment;
  }

  public void setDpsShipment(DpsShipment dpsShipment) {
    this.dpsShipment = dpsShipment;
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
    return "DPS Quote Reference: " + dpsQuoteReferenceId
         + " Client Account Id: " + clientAccountId
         + " Client Quote Reference Id: " + clientQuoteReferenceId
         + " Total Amount: " + totalAmount
         + " DPS Shipment: " + dpsShipment
         + " Valid Until: " + validUntil;
  }  
  
}
