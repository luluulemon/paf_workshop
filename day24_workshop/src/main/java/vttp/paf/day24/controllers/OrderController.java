package vttp.paf.day24.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;
import vttp.paf.day24.models.Order;
import vttp.paf.day24.models.Product;
import vttp.paf.day24.services.OrderService;

@Controller
public class OrderController {

    @Autowired
    OrderService orderSvc;
    
    @GetMapping( {"/","/addproduct"} )
    public String addproduct(Model model, HttpSession sess, @ModelAttribute Product product, 
                            @ModelAttribute Order order)
    {
        List<Product> productList = (List<Product>)sess.getAttribute("Products");
        if(productList == null){
            productList = new LinkedList<>();
            sess.setAttribute("Products", productList);
        }

        System.out.println("Check session" + sess.getId());
        if(product.getProductname() != null)
            productList.add(product);
        for(Product p: productList)
            System.out.println(" >>>>" + p.getProductname());

        model.addAttribute("Products", productList);
        model.addAttribute("Order", order);

        return "orderform";
    }


    @GetMapping("order")
    public String sendOrder(@ModelAttribute Order order, HttpSession sess, Model model){

        System.out.println("Check sess id >>>>> " + sess.getId());

        List<Product> productList = (List<Product>) sess.getAttribute("Products");
        orderSvc.saveOrder(order, productList);

        sess.invalidate();
        model.addAttribute("Order", order);

        return "orderCfm";
    }
}
