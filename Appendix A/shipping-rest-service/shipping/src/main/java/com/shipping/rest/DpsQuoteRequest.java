package com.shipping.rest;

public class DpsQuoteRequest {

  private String clientDpsAccountId;
  private String clientQuoteReferenceId;
  private DpsShipment dpsShipment;
  
  public DpsQuoteRequest(){
      super();
      clientDpsAccountId = "";
      clientQuoteReferenceId = "";
      dpsShipment = new DpsShipment();
  }
   
  public DpsQuoteRequest(String clientDpsAccountId, String clientQuoteReferenceId, DpsShipment dpsShipment){
      this.clientDpsAccountId = clientDpsAccountId;
      this.clientQuoteReferenceId = clientQuoteReferenceId;
      this.dpsShipment = dpsShipment;
  }
  
  public String getClientDpsAccountId() {
    return this.clientDpsAccountId;
  }

  public void setClientDpsAccountId(String clientDpsAccountId) {
    this.clientDpsAccountId = clientDpsAccountId;
    return;
  }

  public String getClientQuoteReferenceId() {
    return this.clientQuoteReferenceId;
  }

  public void setClientQuoteReferenceId(String clientQuoteReferenceId) {
    this.clientQuoteReferenceId = clientQuoteReferenceId;
    return;
  }

  public DpsShipment getDpsShipment() {
    return this.dpsShipment;
  }

  public void setDpsShipment(DpsShipment dpsShipment) {
    this.dpsShipment = dpsShipment;
    return;
  }


  
  @Override
  public String toString(){
    return " Client Dps Account Id: " + clientDpsAccountId
         + " Client Quote Reference Id: " + clientQuoteReferenceId
         + " DPS Shipment: " + dpsShipment;
  }  
  
}
