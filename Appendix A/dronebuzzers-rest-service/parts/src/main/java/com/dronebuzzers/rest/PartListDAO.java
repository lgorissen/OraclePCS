package com.dronebuzzers.rest;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class PartListDAO implements PartDAO{
    
    private final CopyOnWriteArrayList<Part> pList = MockPartList.getInstance();
    
    @Override
    public List<Part> getAllParts(){
        return pList;
    }
   

    @Override
    public Part getPart(String id){
        Part match = null;
                
        match = pList.stream()
                    .filter(e -> e.getId().contains(id))
                    .findFirst().orElse(match);
        
        return match;        
    }
    

    @Override
    public List<Part> getPartsByType(String type){
        
       List<Part> matchList = 
            pList.stream()
                .filter((e) -> (e.getType().contains(type)))
                .collect(Collectors.toList());
         
        return matchList;
    }

    
    @Override
    public List<Part> getPartsByCategory(String category){
        
       List<Part> matchList = 
            pList.stream()
                .filter((e) -> (e.getCategory().contains(category)))
                .collect(Collectors.toList());
         
        return matchList;
    }

    @Override
    public double getAmount(String id, int count) {
        Part match = null;
                
        match = pList.stream()
                    .filter(e -> e.getId().contains(id))
                    .findFirst().orElse(match);

        double amount = match.getUnitPrice() * count;

        return amount;

    }
    
    
}
