package com.shipping.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/shipping")
public class ShippingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShippingController.class);


    // UHL - Request quote for shipment
    @RequestMapping(method = RequestMethod.POST, value = "/uhl/quote")
    public ResponseEntity getUhlQuote(@RequestBody UhlQuoteRequest uhlQuoteRequest) {

        LOGGER.info("/uhl/quote received UhlQuoteRequest : " + uhlQuoteRequest);

        Random rand = new Random(); 
        String uhlQuoteReference = "UHL-00"+ rand.nextInt(100000000); 

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date validUntilDate = calendar.getTime();
        String validUntil = dateFormat.format(validUntilDate);

        double totalAmount = uhlQuoteRequest.getShipment().getWeight() * 2.2;
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        totalAmount = Double.valueOf(twoDForm.format(totalAmount));

        UhlQuoteResponse uhlQuoteResponse = 
                 new UhlQuoteResponse(uhlQuoteReference,
                                      uhlQuoteRequest.getClientId(),
                                      uhlQuoteRequest.getClientReference(),
                                      totalAmount,
                                      uhlQuoteRequest.getShipment(),
                                      validUntil);
        
        if (uhlQuoteResponse != null) {
            return new ResponseEntity<UhlQuoteResponse>(uhlQuoteResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //UHL - Order shipment  
    @RequestMapping(method = RequestMethod.POST, value = "/uhl/ship")
    public ResponseEntity orderUhlShipment(@RequestBody UhlShipmentRequest uhlShipmentRequest) {

        LOGGER.info("/uhl/ship received UhlShipmentRequest : " + uhlShipmentRequest);

        Random rand = new Random(); 
        String uhlShipmentReference = uhlShipmentRequest.getQuoteReference() + "-"+ rand.nextInt(10000); 

        double totalAmount = uhlShipmentRequest.getShipment().getWeight() * 2.2;
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        totalAmount = Double.valueOf(twoDForm.format(totalAmount));

        UhlShipmentResponse uhlShipmentResponse = 
                 new UhlShipmentResponse(uhlShipmentRequest.getQuoteReference(),
                                         uhlShipmentReference,
                                         uhlShipmentRequest.getClientId(),
                                         uhlShipmentRequest.getClientReference(),
                                         totalAmount,
                                         uhlShipmentRequest.getShipment(),
                                         uhlShipmentRequest.getShipmentDate());

        if (uhlShipmentResponse != null) {
            return new ResponseEntity<UhlShipmentResponse>(uhlShipmentResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // DPS - Request quote for shipment
    @RequestMapping(method = RequestMethod.POST, value = "/dps/quote")
    public ResponseEntity getDpsQuote(@RequestBody DpsQuoteRequest dpsQuoteRequest) {

        LOGGER.info("/dps/quote received DpsQuoteRequest : " + dpsQuoteRequest);

        Random rand = new Random(); 
        String dpsQuoteReferenceId = "DPS-00"+ rand.nextInt(100000000); 

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 10);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date validUntilDate = calendar.getTime();
        String validUntil = dateFormat.format(validUntilDate);

        double totalAmount = dpsQuoteRequest.getDpsShipment().getWeight() * 2.0 + 10;
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        totalAmount = Double.valueOf(twoDForm.format(totalAmount));

        DpsQuoteResponse dpsQuoteResponse = 
                 new DpsQuoteResponse(dpsQuoteReferenceId,
                                      dpsQuoteRequest.getClientDpsAccountId(),
                                      dpsQuoteRequest.getClientQuoteReferenceId(),
                                      totalAmount,
                                      dpsQuoteRequest.getDpsShipment(),
                                      validUntil);

        if (dpsQuoteResponse != null) {
            return new ResponseEntity<DpsQuoteResponse>(dpsQuoteResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //DPS - Order shipment  
    @RequestMapping(method = RequestMethod.POST, value = "/dps/ship")
    public ResponseEntity orderDpsShipment(@RequestBody DpsShipmentRequest dpsShipmentRequest) {

        LOGGER.info("/dps/ship received DpsShipmentRequest : " + dpsShipmentRequest);

        Random rand = new Random(); 
        String dpsShipmentReferenceId = dpsShipmentRequest.getDpsQuoteReferenceId() + "-"+ rand.nextInt(100000); 

        double totalAmount = dpsShipmentRequest.getDpsShipment().getWeight() * 2.0 + 10;
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        totalAmount = Double.valueOf(twoDForm.format(totalAmount));

        DpsShipmentResponse dpsShipmentResponse = 
                 new DpsShipmentResponse(dpsShipmentRequest.getDpsQuoteReferenceId(),
                                         dpsShipmentReferenceId,
                                         dpsShipmentRequest.getClientAccountId(),
                                         dpsShipmentRequest.getClientReferenceId(),
                                         totalAmount,
                                         dpsShipmentRequest.getDpsShipment(),
                                         dpsShipmentRequest.getPickupDateRequested(),
                                         dpsShipmentRequest.getPickupDateRequested());

        if (dpsShipmentResponse != null) {
            return new ResponseEntity<DpsShipmentResponse>(dpsShipmentResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
