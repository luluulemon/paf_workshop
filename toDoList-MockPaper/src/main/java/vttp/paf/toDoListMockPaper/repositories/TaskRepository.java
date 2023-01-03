package vttp.paf.toDoListMockPaper.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.paf.toDoListMockPaper.models.Tasks;

import static vttp.paf.toDoListMockPaper.services.Queries.*;

@Repository
public class TaskRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insertTask(Tasks task){

        String id = "";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_FIND_ID_FROM_USERNAME, task.getUsername());
        if(rs.next())
        { id = rs.getString("user_id"); }
        jdbcTemplate.update(SQL_INSERT_TASK, task.getDescription(), task.getPriority(), task.getDueDate(), id);
    }


    
}
