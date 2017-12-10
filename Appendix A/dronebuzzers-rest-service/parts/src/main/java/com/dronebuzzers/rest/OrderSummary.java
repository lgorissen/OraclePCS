package com.dronebuzzers.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderSummary {
  private String dbOrderNumber;
  private String clientId;
  private String clientReference;
  private double totalAmount;
  private List<OrderLineSummary> orderLines;
  
  public OrderSummary(){
      super();
      dbOrderNumber = "";
      clientId = "";
      clientReference = "";
      totalAmount = 0.0;
      orderLines = new ArrayList<OrderLineSummary>();
  }
   
  public OrderSummary(String dbOrderNumber, String clientId, String clientReference, double totalAmount, List<OrderLineSummary> orderLines){
      this.dbOrderNumber = dbOrderNumber;
      this.clientId = clientId;
      this.clientReference = clientReference;
      this.totalAmount = totalAmount;
      this.orderLines = orderLines;
  }
  
  public String getDbOrderNumber(){
    return this.dbOrderNumber;
  }

  public String getClientId() {
    return this.clientId;
  }

  public String getClientReference() {
    return this.clientReference;
  }

  public double getTotalAmount() {
    return this.totalAmount;
  }

  public List<OrderLineSummary> getOrderLineSummary() {
    return this.orderLines;
  }

  public void setOrderLines(List<OrderLineSummary> orderLines) {
    this.orderLines = orderLines;
    return;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
    return;
  }

  public void setClientReference(String clientReference) {
    this.clientReference = clientReference;
    return;
  }

  public void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
    return;
  }

  public void setDbOrderNumber() {

    Random rand = new Random(); 
    String randomNumber = "00"+ rand.nextInt(1000000);
    dbOrderNumber = "DB-" + randomNumber.toString();
    return;
  }

  
  @Override
  public String toString(){
    return "Db Order Number: " + dbOrderNumber 
        + " Client Id: " + clientId
        + " Client Reference: " + clientReference
        + " Total Amount: " + totalAmount
        + " Parts count: " + orderLines.size();
  }  
  
}
