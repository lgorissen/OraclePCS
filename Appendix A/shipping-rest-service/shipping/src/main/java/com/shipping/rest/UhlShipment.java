package com.shipping.rest;

public class UhlShipment {
  private String from;
  private String to;
  private double weight;
  
  public UhlShipment(){
      super();
      from = "to-be-set";
      to = "to-be-set";
      weight = 0.0;
  }
   
  public UhlShipment(String from, String to, double weight){
      this.from = from;
      this.to = to;
      this.weight = weight;
  }
  
  public String getFrom(){
    return this.from;
  }

  public void setFrom(String from){
    this.from = from;
    return;
  }

  public String getTo(){
    return this.to;
  }

  public void setTo(String to){
    this.to = to;
    return;
  }

  public double getWeight() {
    return this.weight;
  }

  public void setWeight(double weight){
    this.weight = weight;
    return;
  }

  @Override
  public String toString(){
    return "From: " + from 
        + " To: " + to
        + " Weight: " + weight;
  }  
  
}
