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
    public List<Part> getByType(String name){
        
       List<Part> matchList = 
            pList.stream()
                .filter((e) -> (e.getType().contains(name)))
                .collect(Collectors.toList());
         
        return matchList;
    }
    
    
}
