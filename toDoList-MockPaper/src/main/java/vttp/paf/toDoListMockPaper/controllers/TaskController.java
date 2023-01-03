package vttp.paf.toDoListMockPaper.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import vttp.paf.toDoListMockPaper.models.Tasks;
import vttp.paf.toDoListMockPaper.services.InsertException;
import vttp.paf.toDoListMockPaper.services.UserService;

@Controller
public class TaskController {

    @Autowired
    private UserService userSvc;

    private String currUser;
    private List<Tasks> currTaskList;
    
    @GetMapping("/addTask")
    public String addTask(Model model, @ModelAttribute Tasks tasks){


        if(currTaskList == null)
        {   currTaskList = new LinkedList<>();  }
        currTaskList.add(tasks);
        tasks.setTaskList(currTaskList);
        currUser = tasks.getUsername();

        model.addAttribute("Tasks", tasks);

        return "todolist";
    }

    @PostMapping("/saveTasks")
    public String saveTasks(Model model){
        
        Tasks task = new Tasks();
        task.setUsername(currUser);
        task.setTaskList(currTaskList);
        try{   userSvc.upsertTask(task);   }
        catch(InsertException e){   e.printStackTrace();    }

        // reset curr
        currUser = "";
        currTaskList = new LinkedList<>();

        return "confirmation";
    }
}
