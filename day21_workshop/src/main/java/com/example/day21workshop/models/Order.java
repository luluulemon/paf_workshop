package com.example.day21workshop.models;


import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Order {
    
    private int id;
    private int employee_id;
    private int customer_id;
    private String order_date;
    private String shipped_date;
    private int shipper_id;
    private String ship_name;
    private String ship_address;
    private String ship_city;
    private String ship_state_province;
    private String ship_zip_postal_code;
    private String ship_country_region;
    private String shipping_fee;
    private String taxes;
    private String payment_type;
    private String paid_date;
    private String notes;
    private String tax_rate;
    private Integer tax_status_id;
    private int status_id;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getEmployee_id() {
        return employee_id;
    }
    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }
    public int getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    public String getOrder_date() {
        return order_date;
    }
    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }
    public String getShipped_date() {
        return shipped_date;
    }
    public void setShipped_date(String shipped_date) {
        this.shipped_date = shipped_date;
    }
    public int getShipper_id() {
        return shipper_id;
    }
    public void setShipper_id(int shipper_id) {
        this.shipper_id = shipper_id;
    }
    public String getShip_name() {
        return ship_name;
    }
    public void setShip_name(String ship_name) {
        this.ship_name = ship_name;
    }
    public String getShip_address() {
        return ship_address;
    }
    public void setShip_address(String ship_address) {
        this.ship_address = ship_address;
    }
    public String getShip_city() {
        return ship_city;
    }
    public void setShip_city(String ship_city) {
        this.ship_city = ship_city;
    }
    public String getShip_state_province() {
        return ship_state_province;
    }
    public void setShip_state_province(String ship_state_province) {
        this.ship_state_province = ship_state_province;
    }
    public String getShip_zip_postal_code() {
        return ship_zip_postal_code;
    }
    public void setShip_zip_postal_code(String ship_zip_postal_code) {
        this.ship_zip_postal_code = ship_zip_postal_code;
    }
    public String getShip_country_region() {
        return ship_country_region;
    }
    public void setShip_country_region(String ship_country_region) {
        this.ship_country_region = ship_country_region;
    }
    public String getShipping_fee() {
        return shipping_fee;
    }
    public void setShipping_fee(String shipping_fee) {
        this.shipping_fee = shipping_fee;
    }
    public String getTaxes() {
        return taxes;
    }
    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }
    public String getPayment_type() {
        return payment_type;
    }
    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }
    public String getPaid_date() {
        return paid_date;
    }
    public void setPaid_date(String paid_date) {
        this.paid_date = paid_date;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getTax_rate() {
        return tax_rate;
    }
    public void setTax_rate(String tax_rate) {
        this.tax_rate = tax_rate;
    }
    public Integer getTax_status_id() {
        return tax_status_id;
    }
    public void setTax_status_id(Integer tax_status_id) {
        this.tax_status_id = tax_status_id;
    }
    public int getStatus_id() {
        return status_id;
    }
    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }


    public static Order create(SqlRowSet rs){
        Order order = new Order();
        order.setId(rs.getInt("Id"));
        order.setEmployee_id(rs.getInt("employee_id"));
        order.setCustomer_id(rs.getInt("customer_id"));
        order.setOrder_date(rs.getString("order_date"));
        if(rs.getString("shipped_date") == null){ order.setShipped_date("NA");  }
            else     order.setShipped_date(rs.getString("shipped_date"));
        if(rs.getString("shipper_id") == null){ order.setShipper_id(0); }    
            else    order.setShipper_id(rs.getInt("shipper_id"));
        order.setShip_name(rs.getString("ship_name"));
        order.setShip_address(rs.getString("ship_address"));
        order.setShip_city(rs.getString("ship_city"));
        order.setShip_state_province(rs.getString("ship_state_province"));
        order.setShip_zip_postal_code(rs.getString("ship_zip_postal_code"));
        order.setShipping_fee(rs.getString("shipping_fee"));
        order.setTaxes(rs.getString("taxes"));
        if(rs.getString("payment_type") == null){   order.setPayment_type("NA");    }
            else    order.setPayment_type(rs.getString("payment_type"));
        if(rs.getString("paid_date") == null){  order.setPaid_date("NA");}
            else    order.setPaid_date(rs.getString("paid_date"));
        order.setTax_rate(rs.getString("tax_rate"));
        if(rs.getString("tax_status_id") == null){  order.setTax_status_id(0);  }
            else    order.setTax_status_id(rs.getInt("tax_status_id"));
        order.setStatus_id(rs.getInt("status_id"));


        return order;
    }


    public JsonObject toJSON(){

        return Json.createObjectBuilder()
                .add("Id", getId())
                .add("Employee_id", getEmployee_id())
                .add("Customer_id", getCustomer_id())
                .add("Order date", getOrder_date())
                .add("Shipped date", getShipped_date())
                .add("shipper id", getShipper_id())
                .add("Ship name", getShip_name())
                .add("Address", getShip_address())
                .add("City", getShip_city())
                .add("Province", getShip_state_province())
                .add("Postal code", getShip_zip_postal_code())
                .add("Shipping fee", getShipping_fee())
                .add("taxes", getTaxes())
                .add("Paid date", getPaid_date())
                .add("tax status id", getTax_status_id())
                .add("Status id", getStatus_id())
                .build();
    }
}
