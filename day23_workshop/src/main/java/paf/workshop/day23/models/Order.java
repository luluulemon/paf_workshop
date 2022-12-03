package paf.workshop.day23.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Order {
    
    private int id;
    private String order_date;
    private int customer_id;
    private float price;
    private float cost;

    public int getId() {    return id;    }
    public void setId(int id) {    this.id = id;    }

    public String getOrder_date() {    return order_date;    }
    public void setOrder_date(String order_date) {    this.order_date = order_date;    }

    public int getCustomer_id() {    return customer_id;    }
    public void setCustomer_id(int customer_id) {    this.customer_id = customer_id;    }

    public float getPrice() {    return price;    }
    public void setPrice(float price) {    this.price = price;    }

    public float getCost() {    return cost;    }
    public void setCost(float cost) {    this.cost = cost;    }

    // static method: generate order object from SqlRowSet
    public static Order create(SqlRowSet rs){
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setOrder_date(rs.getString("order_date"));
        order.setCustomer_id(rs.getInt("customer_id"));
        order.setCost(rs.getFloat("cost"));
        order.setPrice(rs.getFloat("price"));

        return order;
    }

}
