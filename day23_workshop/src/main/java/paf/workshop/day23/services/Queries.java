package paf.workshop.day23.services;


public class Queries {
    
    public static final String SQL_GET_ORDER_DETAILS = 
        "select * from get_order_details";

    /* 
    create view get_order_details as 
        select orders.id, orders.order_date, orders.customer_id, 
        (1-order_details.discount)*order_details.quantity*order_details.unit_price price, 
        order_details.quantity*products.standard_cost cost
        from orders join order_details on orders.id = order_details.order_id
        join products on order_details.product_id = products.id ;
    */

    public static final String SQL_GET_ORDER_BY_ID =
        "select * from get_order_details where id = ?";
}
