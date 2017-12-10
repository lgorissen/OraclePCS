## Description of REST services ##

This page describes the DroneBuzzers REST services.

Get all parts
-------------
Returns a list of all drone parts that can be ordered

**Method:** GET

**Path:** /parts

**Example**

**Send Request** 
> http://host:port/parts

**Request body** 
> n/a

**Response  body**

Media type: application/json
```
 {  
   "parts":[  
      {  
         "id":"DB-FK-A250-V4",
         "category":"Drone",
         "type":"Frame",
         "name":"DroneBuzzer Frame Kit regular V4 (2016 edition)",
         "unitPrice":14.65,
         "currency":"EUR"
      },
      {  
         "id":"DB-FK-A250-V5",
         "category":"Drone",
         "type":"Frame",
         "name":"DroneBuzzer Frame Kit regular V5 (2017 edition)",
         "unitPrice":19.55,
         "currency":"EUR"
      },
      ...
      {  
         "id":"DB-PROP-6x5-CW/CCW-FIB-RE",
         "category":"Drone",
         "type":"Props",
         "name":"DroneBuzzer racing prop 6 inch - 2 pairs/pack, CW & CCW",
         "unitPrice":1.95,
         "currency":"EUR"
      }
   ]
} 
```


Get detailed part information
-------------
Returns the detailed parts information for a specific drone part

**Method:** GET

**Path:** /parts/{partId}

**Send Request** 
> http://host:port/parts/DB-FK-A250-V4

**Request body** 
> n/a

**Response  body**

Media type: application/json
```
{  
   "id":"DB-FK-A250-V4",
   "category":"Drone",
   "type":"Frame",
   "name":"DroneBuzzer Frame Kit regular V4 (2016 edition)",
   "unitPrice":14.65,
   "currency":"EUR"
}
```


Get parts by type
-------------
Returns a list of all drone parts of a specific type. Type can be e.g. “Motor”, “Frame”, etc.

**Method:** GET

**Path:** /parts/type/{typeId}

**Send Request** 
> http://host:port/parts/type/Frame

**Request body** 
> n/a

**Response body**

Media type: application/json
```
{  
   "parts":[  
      {  
         "id":"DB-FK-A250-V4",
         "category":"Drone",
         "type":"Frame",
         "name":"DroneBuzzer Frame Kit regular V4 (2016 edition)",
         "unitPrice":14.65,
         "currency":"EUR"
      },
      {  
         "id":"DB-FK-A250-V5",
         "category":"Drone",
         "type":"Frame",
         "name":"DroneBuzzer Frame Kit regular V5 (2017 edition)",
         "unitPrice":19.55,
         "currency":"EUR"
      },
      {  
         "id":"DB-FK-R250-SuperR",
         "category":"Drone",
         "type":"Frame",
         "name":"DroneBuzzer Race Frame Kit version SuperR",
         "unitPrice":23.15,
         "currency":"EUR"
      }
   ]
}
```


Get parts by category
-------------
Returns a list of all parts of a specific category. The only category that is supported is “Drone”.

**Method:** GET

**Path:** /parts/category/{categoryId}

**Send Request** 
> http://host:port/parts/category/Drone

**Request body** 
> n/a

**Response body**

Media type: application/json
```
{
   "parts":[
      {
         "id":"DB-FK-A250-V4",
         "category":"Drone",
         "type":"Frame",
         "name":"DroneBuzzer Frame Kit regular V4 (2016 edition)",
         "unitPrice":14.65,
         "currency":"EUR"
      },
      {
         "id":"DB-FK-A250-V5",
         "category":"Drone",
         "type":"Frame",
         "name":"DroneBuzzer Frame Kit regular V5 (2017 edition)",
         "unitPrice":19.55,
         "currency":"EUR"
      },
      ...
      {
         "id":"DB-PROP-6x5-CW/CCW-FIB-RE",
         "category":"Drone",
         "type":"Props",
         "name":"DroneBuzzer racing prop 6 inch - 2 pairs/pack, CW & CCW",
         "unitPrice":1.95,
         "currency":"EUR"
      }
   ]
}
```

Submit a parts order
-------------
Submits a new order and returns a summary of the order.

**Method:** POST

**Path:** /parts/order

**Send Request** 
> http://host:port/parts/order

**Request body** 

Media type: application/json
```
{
   "clientId":"TDF",
   "clientReference":"TDF-0067",
   "orderLines":[
      {
         "id":"DB-FK-A250-V4",
         "count":22
      },
      {
         "id":"DB-38406-2350KV",
         "count":4
      }
   ]
}
```

**Response body**

Media type: application/json
```
{
    "dbOrderNumber": "DB-00423773",
    "clientId": "TDF",
    "clientReference": "TDF-0067",
    "totalAmount": 410.1,
    "orderLineSummary": [
        {
            "part": {
                "id": "DB-FK-A250-V4",
                "category": "Drone",
                "type": "Frame",
                "name": "DroneBuzzer Frame Kit regular V4 (2016 edition)",
                "unitPrice": 14.65,
                "currency": "EUR"
            },
            "count": 22
        },
        {
            "part": {
                "id": "DB-38406-2350KV",
                "category": "Drone",
                "type": "Motor",
                "name": "DroneBuzzer regular 2017",
                "unitPrice": 21.95,
                "currency": "EUR"
            },
            "count": 4
        }
    ]
}
```
