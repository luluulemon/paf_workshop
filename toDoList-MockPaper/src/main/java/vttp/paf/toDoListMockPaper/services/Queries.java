package vttp.paf.toDoListMockPaper.services;

public class Queries {
    
    public static final String SQL_FIND_USER_BY_ID =
        "select * from user where user_id = ?";

    public static final String SQL_FIND_ID_FROM_USERNAME =
        "select user_id from user where username = ?";

    public static final String SQL_INSERT_USER = 
        "insert into user(user_id, username, name) values(?, ?, ?)";

    public static final String SQL_INSERT_TASK =
        "insert into task(description, priority, due_date, user_id) values (?,?,?,?) ";
}
