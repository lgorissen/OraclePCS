package com.dronebuzzers.rest;

public class OrderLineIn {
  private String id;
  private int count;
  
  public OrderLineIn(){
      super();
      id = "";
      count = 0;
  }
   
  public OrderLineIn(String id, int count){
      this.id = id;
      this.count = count;
  }
  
  public String getId(){
    return this.id;
  }

  public int getCount() {
    return this.count;
  }

  @Override
  public String toString(){
    return "ID: " + id 
        + " Count: " + count;
  }  
  
}
