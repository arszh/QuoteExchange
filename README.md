
Server have two entity: Quote and Item. Quote include items.

For access by token need to put to header ```key:token value:token```

# API


## Item , URI = "/Item"
### Get getAll URI =  "/"
#### response:
```son    
[
     {
        "id": 1,
        "name": "USD"
     },
     {
        "id": 2,
        "name": "NIS"
     },
     {
        "id": 3,
        "name": "EUR"
     }
]
```

### Get findById URI= "/{id}"
#### response:
```son    
{
        "id": 1,
        "name": "USD"
}
```

### Post create URI= "/"
#### request:
```son
 {
       "name": "UAH"
 }
```

#### response:
```son    
{
        "id": 4,
        "name": "UAH"
}
```

### PUT update URI= "/{id}"
#### request:
```son
{
       "name": "MLD"
}
```

#### response:
```son    
{
   "id":4,
   "name":"MLD"
}
```
## Quote , URI = "/Quote"
### Get getAll URI =  "/"
#### response:
```son    
[
   {
      "id":1,
      "name":"UAH/USD",
      "price":3,
      "items":[
         {
            "id":1,
            "name":"UAH"
         },
         {
            "id":2,
            "name":"USD"
         }
      ]
   },
   {
      "id":2,
      "name":"NIS/USD",
      "price":3.4,
      "items":[
         {
            "id":3,
            "name":"NIS"
         },
         {
            "id":2,
            "name":"USD"
         }
      ]
   }
]
```

### Get findById URI= "/{id}"
#### response:
```son    
{
   "id":2,
   "name":"NIS/USD",
   "price":3.4,
   "items":[
      {
         "id":3,
         "name":"NIS"
      },
      {
         "id":2,
         "name":"USD"
      }
   ]
}
```

### Post create URI= "/"
#### request:
items should be exist
```son
{
   "name":"NIS/USD",
   "price":3.4,
   "items":[
      {
         "id":3
      },
      {
         "id":2
      }
   ]
}
```

#### response:
```son    
{
   "id":4,
   "name":"NIS/USD",
   "price":3.4,
   "items":[
      {
         "id":3,
         "name":"USD"
      },
      {
         "id":2,
         "name":"NIS"
      }
   ]
}
```

### PUT update URI= "/{id}"
#### request:
```son
{
   "name":"NIS/USD",
   "price":3.6,
   "items":[
      {
         "id":3,
         "name":"USD"
      },
      {
         "id":2,
         "name":"NIS"
      }
   ]
}
```

#### response:
```son    
{
   "id":4,
   "name":"NIS/USD",
   "price":3.6,
   "items":[
      {
         "id":3,
         "name":"USD"
      },
      {
         "id":2,
         "name":"NIS"
      }
   ]
}
```
      

