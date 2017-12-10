package com.dronebuzzers.rest;

import java.util.Random;

public class OrderIn {
  private String clientId;
  private String clientReference;
  private OrderLineIn[] orderLines;
  
  public OrderIn(){
      super();
      clientId = "";
      clientReference = "";
      orderLines = new OrderLineIn[0];
  }
   
  public OrderIn(String clientId, String clientReference, OrderLineIn[] orderLines){
      this.clientId = clientId;
      this.clientReference = clientReference;
      this.orderLines = orderLines;
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

  public OrderLineIn[] getOrderLines() {
    return this.orderLines;
  }

  
  @Override
  public String toString(){
    return " Client Reference: " + clientReference
        + " OrderLines count: " + orderLines.length;
  }  
  
}
