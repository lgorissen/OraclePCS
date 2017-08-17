package com.dronebuzzers.rest;

import java.util.List;

public interface PartDAO {
    public List<Part> getAllParts();
    public Part getPart(String id);
    public List<Part> getByType(String title);
}
