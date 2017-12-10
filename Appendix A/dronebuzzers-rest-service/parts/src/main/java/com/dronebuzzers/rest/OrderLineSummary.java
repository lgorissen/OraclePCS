package com.dronebuzzers.rest;

public class OrderLineSummary {
  private Part part;
  private int count;
  
  public OrderLineSummary(){
      super();
      part = null;
      count = 0;
  }
   
  public OrderLineSummary(Part part, int count){
      this.part = part;
      this.count = count;
  }
  
  public Part getPart(){
    return this.part;
  }

  public int getCount() {
    return this.count;
  }

  @Override
  public String toString(){
    return "Part: " + part 
        + " Count: " + count;
  }  
  
}
