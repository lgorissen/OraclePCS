package com.dronebuzzers.rest;

public class Part {
  private final String id;
  private final String type;
  private final String name;
  private final int count;
  private final double price;
  private final String currency;
  
  public Part(){
      super();
      id = "";
      type = "";
      name = "";
      count = 0;
      price = 0.0;
      currency = "";
  }
   
  public Part(String id, String type, String name, int count, double price, String currency){
      this.id = id;
      this.type = type;
      this.name = name;
      this.count = count;
      this.price = price;
      this.currency = currency;
  }
  
  public String getId(){
    return this.id;
  }

  public String getType() {
    return this.type;
  }

  public String getName(){
    return this.name;
  }
  
  public int getCount(){
    return this.count;
  }

  public double getPrice() {
    return this.price;
  }

  public String getCurrency() {
    return this.currency;
  } 
  
  @Override
  public String toString(){
    return "ID: " + id 
        + " Type: " + type
        + " Name: " + name
        + " Count: " + count
        + " Price: " + price
        + " Currency: " + currency;
  }  
  
}
