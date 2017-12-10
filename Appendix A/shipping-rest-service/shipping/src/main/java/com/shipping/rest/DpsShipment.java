package com.shipping.rest;

public class DpsShipment {
  private String origin;
  private String destination;
  private double weight;
  
  public DpsShipment(){
      super();
      origin = "";
      destination = "";
      weight = 0;
  }
   
  public DpsShipment(String origin, String destination, double weight){
      this.origin = origin;
      this.destination = destination;
      this.weight = weight;
  }
  
  public String getOrigin(){
    return this.origin;
  }

  public String getDestination(){
    return this.destination;
  }

  public double getWeight() {
    return this.weight;
  }

  @Override
  public String toString(){
    return "Origin: " + origin 
        + " Destination: " + destination
        + " Weight: " + weight;
  }  
  
}
