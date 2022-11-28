package com.example.day21workshop.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.example.day21workshop.models.Customer;
import com.example.day21workshop.models.Order;

import static com.example.day21workshop.services.Queries.*;


@Service
public class CustomerListService {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<String> getCustomers(int offset, int limit){
        
        List<String> customers = new LinkedList<>();
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_CUSTOMERS, limit, offset);

        while (rs.next())
        {   customers.add(rs.getString("customer")); }

        return customers;
    }


    public Customer getCustomerDetails(int id){

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_CUSTOMER_DETAILS, id);
        
        Customer customer = new Customer();
        
        if(rs.next())
        {   customer = customer.create(rs); }

        return customer;
    }


    public List<Order> getCustomerOrders(int id){
        
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_CUSTOMER_ORDERS, id);
        List<Order> orders = new LinkedList<>();
        while(rs.next())
        {   orders.add(Order.create(rs));   }

        System.out.println(">>>> Check list size: " + orders.size());

        return orders;
    }


}
