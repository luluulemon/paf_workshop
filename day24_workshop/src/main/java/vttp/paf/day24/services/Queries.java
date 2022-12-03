package vttp.paf.day24.services;

public class Queries {
    
    public static final String SQL_SAVE_ORDER =
        "insert into orders(order_date, customer_name, ship_address, notes) values(?, ?, ?, ?)";

    public static final String SQL_SAVE_ORDER_2 =
        "insert into orders values(?, ?, ?, ?, ?)";

    public static final String SQL_GET_ORDER_ID =
        "select max(order_id) max_id from orders";


    public static final String SQL_SAVE_ORDER_PRODUCTS =
        "insert into order_details(product, unit_price, discount, quantity, order_id) values(?, ?, ?, ?, ?)";
}
