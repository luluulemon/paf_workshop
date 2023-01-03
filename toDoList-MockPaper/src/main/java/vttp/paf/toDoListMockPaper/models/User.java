package vttp.paf.toDoListMockPaper.models;

import java.util.UUID;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class User {
    
    private String id;
    private String username;
    private String name;


    public User() {
        // generate ID for user once created
        this.id = UUID.randomUUID().toString().substring(0, 8);
    }

    public User(String username) {
        // generate ID for user once created
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.username = username;
    }

    public void setId(String userId) {    this.id = userId;    }
    public String getId() {     return id;    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static User create(SqlRowSet rs){
        User user = new User();
        user.setId(rs.getString("user_id"));
        user.setName(rs.getString("name"));
        user.setUsername(rs.getString("username"));

        return user;
    }

}
