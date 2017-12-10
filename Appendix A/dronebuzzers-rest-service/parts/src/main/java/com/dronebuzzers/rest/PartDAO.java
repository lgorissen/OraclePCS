package com.dronebuzzers.rest;

import java.util.List;

public interface PartDAO {
    public List<Part> getAllParts();
    public Part getPart(String id);
    public List<Part> getPartsByType(String type);
    public List<Part> getPartsByCategory(String category);
    public double getAmount(String id, int count);
}
