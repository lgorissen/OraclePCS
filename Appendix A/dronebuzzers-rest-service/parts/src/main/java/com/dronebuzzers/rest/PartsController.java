package com.dronebuzzers.rest;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/parts")
public class PartsController {

    PartDAO pdao = new PartListDAO();

    // Get all parts
    @RequestMapping(method = RequestMethod.GET)
    public Part[] getAll() {
        return pdao.getAllParts().toArray(new Part[0]);
    }

    // Get a part by Id
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ResponseEntity get(@PathVariable String id) {

        Part match = null;
        match = pdao.getPart(id);
        
        if (match != null) {
            return new ResponseEntity<>(match, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get parts by type
    @RequestMapping(method = RequestMethod.GET, value = "/type/{type}")
    public ResponseEntity getByType(@PathVariable String type) {

        List<Part> matchList = pdao.getByType(type);

        if (matchList.size() > 0) {
            return new ResponseEntity<>(matchList.toArray(new Part[0]), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
