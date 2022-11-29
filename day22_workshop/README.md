Creating table for RSVP with CRUD functions

endpoints:
/api/rsvps
get method
Get List of RSVPs

/api/rsvp?name=
get method
Get RSVP by Name

/api/rsvp       
post method
urlencoded form with keys: name, email, phone, date, comments

/api/rsvp/{email}
put method
urlencoded form with keys: name, email, phone, date, comments

/api/rsvp/count
GET
return total no. of RSVPs