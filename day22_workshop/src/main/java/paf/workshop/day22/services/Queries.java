package paf.workshop.day22.services;

public class Queries {
    
    public static final String SQL_GET_RSVPS =
        "select * from rsvp";

    public static final String SQL_GET_RSVP_BY_NAME =
        "select * from rsvp where name like ?"; 

    public static final String SQL_UPDATE_BY_NAME =
        "update rsvp set email=?, phone=?, confirmation_date=?, comments=? where name = ?";

    public static final String SQL_INSEERT_RSVP =
        "insert into rsvp values(?,?,?,?,?)";

    public static final String SQL_GET_RSVP_BY_EMAIL =
        "select * from rsvp where email = ? ";

    public static final String SQL_GET_RSVP_COUNT =
        "select count(*) as count from rsvp";
}
