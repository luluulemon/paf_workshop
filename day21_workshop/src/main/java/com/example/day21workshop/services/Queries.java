package com.example.day21workshop.services;

public class Queries {
    
    public static final String SQL_GET_CUSTOMERS =
        "select concat(last_name, \" \",  first_name) as customer from customers limit ? offset ?";


    public static final String SQL_GET_CUSTOMER_DETAILS =
        "select * from customers where id= ? ;";


    public static final String SQL_GET_CUSTOMER_ORDERS =
        "select * from orders where customer_id= ?; ";
}
