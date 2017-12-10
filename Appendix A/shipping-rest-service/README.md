# Shipping REST services 

A description of the REST services for the shipment providers.

Two shipment providers are support: UHL and DPS. 


## UHL - Request quote for shipment
Returns a quote for shipping a package of 'weight' from 'origin' to 'destination'  


**Method:** POST

**Path:** /shipping/uhl/quote

**Send Request**
> `http://host:port/shipping/uhl/quote`

**Request body**

Media type: application/json
```
{
   "clientId" : "TDF",
   "clientReference" : "TDF-SHIPMENT-2438",
   "shipment":
      {
         "from" : "Amsterdam",
         "to" : "Paris",
         "weight" : 5.5
      }
}
```

**Response body**

Media type: application/json
```
{
    "quoteReference": "UHL-0075651559",
    "clientId": "TDF",
    "clientReference": "TDF-SHIPMENT-2438",
    "totalAmount": 12.1,
    "shipment": {
        "from": "Amsterdam",
        "to": "Paris",
        "weight": 5.5
    },
    "validUntil": "26-11-2017"
}
```


UHL - Order shipment
-------------
Order a shipment based on a quote number

**Method:** POST

**Path:** /shipping/uhl/ship

**Send Request**
> `http://host:port/shipping/uhl/ship`

**Request body**

Media type: application/json
```
{
   "clientId" : "TDF",
   "clientReference" : "TDF-SHIPMENT-2438",
   "shipment" :
      {
         "from" : "Amsterdam",
         "to" : "Paris",
         "weight" : 5.5
      },
   "quoteReference" : "UHL-0056267895",
   "shipmentDate" : "17-12-2017"
}
```

**Response body**

Media type: application/json
```
{
    "quoteReference": "UHL-0056267895",
    "shipmentReference": "UHL-0056267895-3647",
    "clientId": "TDF",
    "clientReference": "TDF-SHIPMENT-2438",
    "totalAmount": 12.1,
    "shipment": {
        "from": "Amsterdam",
        "to": "Paris",
        "weight": 5.5
    },
    "shipmentDate": "17-12-2017"
}
```



DPS - Request quote for shipment
-------------
Returns a quote for shipping a package of 'weight' from 'origin' to 'destination'  


**Method:** POST

**Path:** /shipping/dps/quote

**Send Request**
> `http://host:port/shipping/dps/quote`

**Request body**

Media type: application/json
```
{
   "clientDpsAccountId" : "TDF",
   "clientQuoteReferenceId" : "TDF-SHIPMENT-2438",
   "dpsShipment" :
      {
         "origin" : "Amsterdam",
         "destination" : "Paris",
         "weight" : 5.5
      }
}
```

**Response body**

Media type: application/json
```
{
    "dpsQuoteReferenceId": "DPS-0013921208",
    "clientAccountId": "TDF",
    "clientQuoteReferenceId": "TDF-SHIPMENT-2438",
    "totalAmount": 21,
    "dpsShipment": {
        "origin": "Amsterdam",
        "destination": "Paris",
        "weight": 5.5
    },
    "validUntil": "29-11-2017"
}
```


DPS - Order shipment
-------------
Order a shipment based on a quote number

**Method:** POST

**Path:** /shipping/dps/ship

**Send Request**
> `http://host:port/shipping/dps/ship`

**Request body**

Media type: application/json
```
{
   "clientAccountId" : "TDF",
   "clientReferenceId" : "TDF-SHIPMENT-2438",
   "dpsShipment" :
      {
         "origin" : "Amsterdam",
         "destination" : "Paris",
         "weight" : 5.5
      },
   "dpsQuoteReferenceId" : "DPS-0056267895",
   "pickupDateRequested" : "17-12-2017"
}
```

**Response body**

Media type: application/json
```
{
    "dpsQuoteReferenceId": "DPS-0056267895",
    "dpsShipmentReferenceId": "DPS-0056267895-39729",
    "clientAccountId": "TDF",
    "clientReferenceId": "TDF-SHIPMENT-2438",
    "totalAmount": 21,
    "dpsShipment": {
        "origin": "Amsterdam",
        "destination": "Paris",
        "weight": 5.5
    },
    "pickupDateRequested": "17-12-2017",
    "pickupDatePlanned": "17-12-2017"
}
```
