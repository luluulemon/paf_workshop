package paf.workshop.day23.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import paf.workshop.day23.models.Order;
import paf.workshop.day23.services.OrderService;

@Controller
public class OrderController {
    
    @Autowired
    OrderService orderSvc;

    @GetMapping("/getorder")
    public String getOrder(@RequestParam(name="order_id") int orderId, Model model){

        List<Order> orderList = orderSvc.getOrderDetails(orderId);
        model.addAttribute("OrderId", orderId);
        model.addAttribute("Orders", orderList);
        return "orderDisplay";
    }
}
