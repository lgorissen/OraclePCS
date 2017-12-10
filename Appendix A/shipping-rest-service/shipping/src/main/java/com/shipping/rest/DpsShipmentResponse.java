package com.shipping.rest;

public class DpsShipmentResponse {

  private String dpsQuoteReferenceId;
  private String dpsShipmentReferenceId;
  private String clientAccountId;
  private String clientReferenceId;
  private double totalAmount;
  private DpsShipment dpsShipment;
  private String pickupDateRequested;
  private String pickupDatePlanned;
  
  public DpsShipmentResponse(){
      super();
      dpsQuoteReferenceId = "";
      dpsShipmentReferenceId = "";
      clientAccountId = "";
      clientReferenceId = "";
      totalAmount = 0;
      dpsShipment = new DpsShipment();
      pickupDateRequested = "";
      pickupDatePlanned = "";
  }
   
  public DpsShipmentResponse(String dpsQuoteReferenceId,
                             String dpsShipmentReferenceId,
                             String clientAccountId, 
                             String clientReferenceId, 
                             double totalAmount,
                             DpsShipment dpsShipment,
                             String pickupDateRequested,
                             String pickupDatePlanned){
      this.dpsQuoteReferenceId = dpsQuoteReferenceId;
      this.dpsShipmentReferenceId = dpsShipmentReferenceId;
      this.clientAccountId = clientAccountId;
      this.clientReferenceId = clientReferenceId;
      this.totalAmount = totalAmount;
      this.dpsShipment = dpsShipment;
      this.pickupDateRequested = pickupDateRequested;
      this.pickupDatePlanned = pickupDatePlanned;
  }
  
  public String getDpsQuoteReferenceId() {
    return this.dpsQuoteReferenceId;
  }

  public void setDpsQuoteReferenceId(String dpsQuoteReferenceId) {
    this.dpsQuoteReferenceId = dpsQuoteReferenceId;
    return;
  }

  public String getDpsShipmentReferenceId() {
    return this.dpsShipmentReferenceId;
  }

  public void setDpsShipmentReferenceId(String dpsShipmentReferenceId) {
    this.dpsShipmentReferenceId = dpsShipmentReferenceId;
    return;
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

  public String getPickupDateRequested() {
    return this.pickupDateRequested;
  }

  public void setPickupDateRequested(String pickupDateRequested) {
    this.pickupDateRequested = pickupDateRequested;
    return;
  }

  public String getPickupDatePlanned() {
    return this.pickupDatePlanned;
  }

  public void setPickupDatePlanned(String pickupDatePlanned) {
    this.pickupDatePlanned = pickupDatePlanned;
    return;
  }


  
  @Override
  public String toString(){
    return " DPS Quote Reference Id: " + dpsQuoteReferenceId
         + " DPS Shipment Reference Id: " + dpsShipmentReferenceId
         + " Client Account Id: " + clientAccountId
         + " Client Reference Id: " + clientReferenceId
         + " Total Amount: " + totalAmount
         + " DPS Shipment: " + dpsShipment
         + " Pickup Date Requested: " + pickupDateRequested
         + " Pickup Date Planned: " + pickupDatePlanned;
  }  
  
}
