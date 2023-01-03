package vttp.paf.toDoListMockPaper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import vttp.paf.toDoListMockPaper.models.Tasks;
import vttp.paf.toDoListMockPaper.models.User;
import static vttp.paf.toDoListMockPaper.services.Queries.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Optional<User> findUserByUserID(String userId){

        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_FIND_USER_BY_ID, userId);

        if(rs.next()){  
            User user = User.create(rs);
            return Optional.of(user);
        }
        else{
            return Optional.empty();
        }
    }


    public String insertUser(User user){
        
        int insert = jdbcTemplate.update(SQL_INSERT_USER, user.getId(), user.getUsername(), user.getName());
        if(insert < 1) return "";
        return user.getId();
    }

    @Transactional (rollbackFor = InsertException.class)
    public void upsertTask (Tasks task) throws InsertException{
        // check if user is available
        String id = "";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_FIND_ID_FROM_USERNAME, task.getUsername());
        
        if(rs.next())
        {   id = rs.getString("user_id");   }
        else        // else create user
        {   User user = new User(task.getUsername());   
            id = insertUser(user);
            System.out.printf("New user %s created with ID %s\n", user, id);
        }
        final String user_id = id;

        System.out.println(">>>>>>>>>>" + task.getTaskList().get(0).getDueDate());

        List<Object[]> taskBatch = task.getTaskList().stream()
             .map (TASK -> new Object[]
             {TASK.getDescription(), TASK.getPriority(), TASK.getDueDate(), user_id })
             .collect(Collectors.toList());

        try{
        int[] updateResults = jdbcTemplate.batchUpdate(SQL_INSERT_TASK, taskBatch);
        }
        catch(DataIntegrityViolationException e){   System.out.println("Date wrong format");
            return;
        }
        
        // test for rollback
        if(true){
            throw new InsertException("Error inserting");
        }
    }
}
