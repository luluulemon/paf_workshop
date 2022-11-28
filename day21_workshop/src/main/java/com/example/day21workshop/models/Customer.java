package com.example.day21workshop.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Customer {

    private String id;
    private String company;
    private String last_name;
    private String first_name;
    private String email_address;
    private String job_title;
    private String business_phone;
    private String fax_number;
    private String address;
    private String city;
    private String state_province;
    private String zip_postal_code;
    private String country_region;
    private String web_page;
    private String notes;
    private String attachments;

    public String getId() {    return id;    }
    public void setId(String id) {  this.id = id;    }

    public String getCompany() {    return company;    }
    public void setCompany(String company) {    this.company = company;    }

    public String getLast_name() {  return last_name;    }
    public void setLast_name(String last_name) {    this.last_name = last_name;    }

    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getEmail_address() {
        return email_address;
    }
    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }
    public String getJob_title() {
        return job_title;
    }
    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }
    public String getBusiness_phone() {
        return business_phone;
    }
    public void setBusiness_phone(String business_phone) {
        this.business_phone = business_phone;
    }
    public String getFax_number() {
        return fax_number;
    }
    public void setFax_number(String fax_number) {
        this.fax_number = fax_number;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState_province() {
        return state_province;
    }
    public void setState_province(String state_province) {
        this.state_province = state_province;
    }
    public String getZip_postal_code() {
        return zip_postal_code;
    }
    public void setZip_postal_code(String zip_postal_code) {
        this.zip_postal_code = zip_postal_code;
    }
    public String getCountry_region() {
        return country_region;
    }
    public void setCountry_region(String country_region) {
        this.country_region = country_region;
    }
    public String getWeb_page() {
        return web_page;
    }
    public void setWeb_page(String web_page) {
        this.web_page = web_page;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getAttachments() {
        return attachments;
    }
    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public Customer create(SqlRowSet rs){
        Customer customer = new Customer();
        customer.setFirst_name(rs.getString("first_name"));
        customer.setLast_name(rs.getString("last_name"));
        customer.setId(rs.getString("id"));
        customer.setJob_title(rs.getString("job_title"));
        customer.setBusiness_phone(rs.getString("business_phone"));
        customer.setFax_number(rs.getString("fax_number"));
        customer.setAddress(rs.getString("address"));
        customer.setCity(rs.getString("city"));
        customer.setState_province(rs.getString("state_province"));
        customer.setZip_postal_code(rs.getString("zip_postal_code"));
        customer.setCountry_region(rs.getString("country_region"));


        return customer;
    }


    public JsonObject toJSON(){

        return Json.createObjectBuilder()
            .add("id", getId())
            .add("First_name", getFirst_name())
            .add("Last_name", getLast_name())
            .add("Job_title", getJob_title())
            .add("Business_phone", getBusiness_phone())
            .add("Address", getAddress())
            .add("City", getCity())
            .add("province", getState_province())
            .add("Postal_code", getZip_postal_code())
            .add("Region", getCountry_region())
            .build();
    }
}
