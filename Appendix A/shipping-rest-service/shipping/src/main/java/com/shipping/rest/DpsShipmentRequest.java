package com.shipping.rest;

public class DpsShipmentRequest {

  private String clientAccountId;
  private String clientReferenceId;
  private DpsShipment dpsShipment;
  private String dpsQuoteReferenceId;
  private String pickupDateRequested;
  
  public DpsShipmentRequest(){
      super();
      clientAccountId = "";
      clientReferenceId = "";
      dpsShipment = new DpsShipment();
      dpsQuoteReferenceId = "";
      pickupDateRequested = "";
  }
   
  public DpsShipmentRequest(String clientAccountId, 
                            String clientReferenceId, 
                            DpsShipment dpsShipment,
                            String dpsQuoteReferenceId,
                            String pickupDateRequested){
      this.clientAccountId = clientAccountId;
      this.clientReferenceId = clientReferenceId;
      this.dpsShipment = dpsShipment;
      this.dpsQuoteReferenceId = dpsQuoteReferenceId;
      this.pickupDateRequested = pickupDateRequested;
  }
  
  public String getClientAccountId() {
    return this.clientAccountId;
  }

  public void setClientAccountId(String clientAccountId) {
    this.clientAccountId = clientAccountId;
    return;
  }

  public String getClientReferenceId() {
    return this.clientReferenceId;
  }

  public void setClientReferenceId(String clientReferenceId) {
    this.clientReferenceId = clientReferenceId;
    return;
  }

  public DpsShipment getDpsShipment() {
    return this.dpsShipment;
  }

  public void setDpsShipment(DpsShipment dpsShipment) {
    this.dpsShipment = dpsShipment;
    return;
  }

  public String getDpsQuoteReferenceId() {
    return this.dpsQuoteReferenceId;
  }

  public void setDpsQuoteReferenceId(String dpsQuoteReferenceId) {
    this.dpsQuoteReferenceId = dpsQuoteReferenceId;
    return;
  }

  public String getPickupDateRequested() {
    return this.pickupDateRequested;
  }

  public void setPickupDateRequested(String pickupDateRequested) {
    this.pickupDateRequested = pickupDateRequested;
    return;
  }


  
  @Override
  public String toString(){
    return " Client Account Id: " + clientAccountId
         + " Client Reference Id: " + clientReferenceId
         + " DPS Shipment: " + dpsShipment
         + " DPS Quote Reference Id: " + dpsQuoteReferenceId
         + " Pickup Date Requested: " + pickupDateRequested;
  }  
  
}
