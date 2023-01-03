package vttp.paf.toDoListMockPaper.models;

import java.util.Date;
import java.util.List;

public class Tasks {
    
    private String username;
    private String description;
    private String priority;
    private String dueDate;
    private List<Tasks> taskList;


    public String getUsername() {        return username;    }
    public void setUsername(String username) {        this.username = username;   }

    public String getDescription() {        return description;    }
    public void setDescription(String description) {        this.description = description;    }

    public String getPriority() {        return priority;    }
    public void setPriority(String priority) {        this.priority = priority;    }

    public String getDueDate() {        return dueDate;    }
    public void setDueDate(String dueDate) {        this.dueDate = dueDate;    }

    public List<Tasks> getTaskList() {        return taskList;    }
    public void setTaskList(List<Tasks> taskList) {        this.taskList = taskList;    }

}
