package com.example.day21workshop.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.day21workshop.models.Customer;
import com.example.day21workshop.models.Order;
import com.example.day21workshop.services.CustomerListService;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;



@RestController
@RequestMapping(path="/api/customer/", produces="application/json")
public class CustomerController {
    
    @Autowired
    CustomerListService customerListSvc;

    public int offset=0;
    public int limit=5;

    @GetMapping
    public ResponseEntity<String> getCustomer(@RequestParam(name="offset", required=false) Integer offsetInput, 
                                @RequestParam(name="limit", required=false) Integer limitInput){
        
        if(limitInput != null){     limit = limitInput;     }
        if(offsetInput != null){    offset = offsetInput;   }
          
        List<String>   customers = customerListSvc.getCustomers(offset, limit);   

        return ResponseEntity.status(HttpStatus.OK)
                    .body(customers.toString());
    }


    @GetMapping("{customer_id}")
    public ResponseEntity<String> getCustomerDetails(@PathVariable(name="customer_id") int id){

        Customer customer = customerListSvc.getCustomerDetails(id);
        if(customer.getId() == null)    // return 404 if id does not exist
        {   return ResponseEntity.status(404).body("No such ID");   }

        JsonObject body = customer.toJSON();

        return ResponseEntity.ok(body.toString());
    }

    @GetMapping("{customer_id}/orders")
    public ResponseEntity<String> getCustomerOrders(@PathVariable int customer_id){

        List<Order> orderList = customerListSvc.getCustomerOrders(customer_id);
        
        // Convert Order to Json, throw into JsonArray
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for(Order o: orderList){
            builder.add(o.toJSON());
        }

        return ResponseEntity.status(HttpStatus.OK)
                            .body(builder.build().toString());
    }

}
