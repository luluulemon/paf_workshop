package vttp.paf.toDoListMockPaper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import vttp.paf.toDoListMockPaper.models.User;
import vttp.paf.toDoListMockPaper.services.UserService;

@Controller
public class UserController {
    
    @Autowired
    private UserService userSvc;

    @GetMapping("/user")
    public String directPage(){
        return "user";
    }

    @GetMapping("/addUser")
    public String insertUser(Model model, @ModelAttribute User user){


        String userId = userSvc.insertUser(user);
        System.out.println(userId);


        return "Confirmation";
    } 
}
