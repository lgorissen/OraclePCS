package com.dronebuzzers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class MockPartList {
  private static final CopyOnWriteArrayList<Part> pList = new CopyOnWriteArrayList<>();
  
  static {
      
      
        String jsonString = "[{\"id\":\"DB-38406\",\"type\":\"Motor\",\"name\":\"DroneBuzzer regular\",\"count\":1,\"price\":18.95,\"currency\":\"EUR\"},{\"id\":\"DB-38606\",\"type\":\"Motor\",\"name\":\"DroneBuzzer racer\",\"count\":1,\"price\":21.95,\"currency\":\"EUR\"},{\"id\":\"DB-SC-622-25A\",\"type\":\"Speedcontroller\",\"name\":\"DroneBuzzer speedcontoller regular - 25A\",\"count\":1,\"price\":8.95,\"currency\":\"EUR\"},{\"id\":\"DB-SC-629-40A\",\"type\":\"Speedcontroller\",\"name\":\"DroneBuzzer speedcontoller racer - 40A\",\"count\":1,\"price\":8.95,\"currency\":\"EUR\"},{\"id\":\"DB-FC-9773-A\",\"type\":\"Flightcontroller\",\"name\":\"DroneBuzzer flightcontoller regular - 4ch\",\"count\":1,\"price\":24.15,\"currency\":\"EUR\"},{\"id\":\"DB-FC-9773-R\",\"type\":\"Speedcontroller\",\"name\":\"DroneBuzzer speedcontoller racer - 4ch\",\"count\":1,\"price\":43.95,\"currency\":\"EUR\"}]";


        try {

            ObjectMapper mapper = new ObjectMapper();

            Part[] myParts = mapper.readValue(jsonString, Part[].class);
            
            pList.addAll(Arrays.asList(myParts));
            
        } catch (IOException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
      
  }
  
  private MockPartList(){}
  
  public static CopyOnWriteArrayList<Part> getInstance(){
    return pList;
  }
  
}
