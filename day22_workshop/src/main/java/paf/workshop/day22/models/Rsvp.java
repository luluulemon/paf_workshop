package paf.workshop.day22.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Rsvp {
    
    private String name;
    private String email;
    private String phone;
    private String date;
    private String comments;

    public String getName() {    return name;    }
    public void setName(String name) {  this.name = name;    }

    public String getEmail() {    return email;    }
    public void setEmail(String email) {    this.email = email;    }

    public String getPhone() {    return phone;    }
    public void setPhone(String phone) {    this.phone = phone;    }

    public String getDate() {    return date;    }
    public void setDate(String date) {    this.date = date;    }

    public String getComments() {    return comments;    }
    public void setComments(String comments) {    this.comments = comments;    }

    public static Rsvp create(SqlRowSet rs){
        Rsvp rsvp = new Rsvp();
        rsvp.setName(rs.getString("Name"));
        rsvp.setEmail(rs.getString("Email"));
        rsvp.setPhone(rs.getString("Phone"));
        rsvp.setDate(rs.getString("Confirmation_date"));
        rsvp.setComments(rs.getString("Comments"));

        return rsvp;
    }

    public JsonObject toJSON(){
        JsonObjectBuilder builder = Json.createObjectBuilder();
        return  
        builder.add("name", getName())
            .add("email", getEmail())
            .add("phone", getPhone())
            .add("date", getDate())
            .add("comments", getComments())
            .build();
    }
}
