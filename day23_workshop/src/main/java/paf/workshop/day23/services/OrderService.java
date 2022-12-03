package paf.workshop.day23.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import paf.workshop.day23.models.Order;

import static paf.workshop.day23.services.Queries.*;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderService {
    
    @Autowired
    JdbcTemplate template;

    public List<Order> getOrderDetails(int orderId){

        List<Order> orderList = new LinkedList<>();
        
        SqlRowSet rs = template.queryForRowSet(SQL_GET_ORDER_BY_ID, orderId);
        while(rs.next()){
            orderList.add(Order.create(rs));
        }

        return orderList;
    }
}
