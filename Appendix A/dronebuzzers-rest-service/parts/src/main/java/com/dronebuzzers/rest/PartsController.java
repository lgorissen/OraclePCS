package com.dronebuzzers.rest;

import java.util.List;
import java.util.ArrayList;

import java.text.DecimalFormat;
import java.lang.Double;

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
    public ResponseEntity getAll() {

        Part[] partsArray = pdao.getAllParts().toArray(new Part[0]);

        Parts parts = new Parts();

        parts.setParts(partsArray);
        
        if (parts != null) {
            return new ResponseEntity<>(parts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
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

    // Get parts by category
    @RequestMapping(method = RequestMethod.GET, value = "/category/{category}")
    public ResponseEntity getPartsByCategory(@PathVariable String category) {

        Part[] partsArray = pdao.getPartsByCategory(category).toArray(new Part[0]);

        Parts parts = new Parts();
        parts.setParts(partsArray);
        
        if (parts != null) {
            return new ResponseEntity<>(parts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    // Get parts by type
    @RequestMapping(method = RequestMethod.GET, value = "/type/{type}")
    public ResponseEntity getPartsByType(@PathVariable String type) {

        Part[] partsArray = pdao.getPartsByType(type).toArray(new Part[0]);

        Parts parts = new Parts();
        parts.setParts(partsArray);
        
        if (parts != null) {
            return new ResponseEntity<>(parts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    //Submit a parts order
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity update(@RequestBody OrderIn orderIn) {

        OrderSummary orderSummary = new OrderSummary();

        OrderLineIn[] orderLinesIn = orderIn.getOrderLines();

        double totalOrderAmount = 0.0;
        //OrderLineSummary[] orderLines = new OrderLineSummary[orderLinesIn.length];
        List<OrderLineSummary> orderLines = new ArrayList<OrderLineSummary>();
        int orderLineCount = 0;
        
        for(int i=0 ; i < orderLinesIn.length ; i++ ) {
            if ( orderLinesIn[i].getCount() > 0 ) {
                totalOrderAmount = totalOrderAmount + pdao.getAmount(orderLinesIn[i].getId(), orderLinesIn[i].getCount());
                orderLines.add(new OrderLineSummary(pdao.getPart(orderLinesIn[i].getId()), orderLinesIn[i].getCount()));
                //orderLineCount = orderLineCount + 1;
            }
        }

        DecimalFormat twoDForm = new DecimalFormat("#.##");
        totalOrderAmount = Double.valueOf(twoDForm.format(totalOrderAmount));

        orderSummary.setTotalAmount(totalOrderAmount);
        orderSummary.setClientId(orderIn.getClientId());
        orderSummary.setClientReference(orderIn.getClientReference());
        orderSummary.setDbOrderNumber();
        orderSummary.setOrderLines(orderLines);

        return new ResponseEntity<OrderSummary>(orderSummary, HttpStatus.OK);
    }

}
