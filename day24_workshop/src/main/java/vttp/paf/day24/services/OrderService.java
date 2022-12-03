package vttp.paf.day24.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp.paf.day24.models.Order;
import vttp.paf.day24.models.Product;

import static vttp.paf.day24.services.Queries.*;

@Service
public class OrderService {
    
    @Autowired
    JdbcTemplate template;

    @Transactional
    public void saveOrder(Order order, List<Product> products){

        // Get the max id + 1
        SqlRowSet rs = template.queryForRowSet(SQL_GET_ORDER_ID);
        rs.next();
        int orderId = rs.getInt("max_id") + 1;

        // update order 
        int orderUpdate = template.update(SQL_SAVE_ORDER_2, orderId, 
                            order.getDate(), order.getName(), order.getAddress(), order.getNotes());

        // update products of the order
        List<Object[]> productBatch = products.stream()
                                        .map (product -> new Object[]{product.getProductname(),
                                            product.getPrice(), product.getDiscount(), product.getQuantity(),
                                            orderId }).collect(Collectors.toList());

        int[] productsUpdate = template.batchUpdate(SQL_SAVE_ORDER_PRODUCTS, productBatch);

        System.out.println("check updates >>> " + orderUpdate);
        
    }
}
