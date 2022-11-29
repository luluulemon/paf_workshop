package paf.workshop.day22.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import paf.workshop.day22.models.Rsvp;

import static paf.workshop.day22.services.Queries.*;

import java.util.LinkedList;
import java.util.List;

@Service
public class RsvpSvc {
    
    @Autowired
    JdbcTemplate template;

    public List<Rsvp> getRSVPs(){

        SqlRowSet rs = template.queryForRowSet(SQL_GET_RSVPS);

        List<Rsvp> rsvpList = new LinkedList<>();
        while(rs.next())
        {   rsvpList.add(Rsvp.create(rs));   }

        return rsvpList;
    }


    public List<Rsvp> getRSVPByName(String name){

        SqlRowSet rs = template.queryForRowSet(SQL_GET_RSVP_BY_NAME, "%%%s%%".formatted(name));

        List<Rsvp> rsvpList = new LinkedList<>();
        while(rs.next())
        {   rsvpList.add(Rsvp.create(rs));   }

        return rsvpList;
    } 
    
    
    public int updateRSVP(Rsvp rsvp){
        return template.update(SQL_UPDATE_BY_NAME, 
            rsvp.getEmail(), rsvp.getPhone(), rsvp.getDate(), rsvp.getComments(), rsvp.getName());
    }


    public int insertRSVP(Rsvp rsvp){

        return template.update(SQL_INSEERT_RSVP, 
            rsvp.getName(), rsvp.getEmail(), rsvp.getPhone(), rsvp.getDate(), rsvp.getComments());
    }


    public boolean getRSVPByEmail(String email){

        SqlRowSet rs = template.queryForRowSet(SQL_GET_RSVP_BY_EMAIL, email);
        return rs.next();
    }

    public String getRSVPCount(){
        SqlRowSet rs = template.queryForRowSet(SQL_GET_RSVP_COUNT);
        rs.next();
        
        return rs.getString("count");
    }
}
