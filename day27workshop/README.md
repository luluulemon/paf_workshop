Updating comments collection

Endpoints:
/review (POST) 
- adding a new comment, using html form (path = "/")
- required fields: username, gid, rating

/review/{reviewID}  (PUT)
- updating a comment, only update either rating or comment (c_text)
- pushing a document(rating, comment) into 'edited' array

/review/{reviewID}  (GET)
- get the latest edit of comment

/review/{reviewID}/history  (GET)
- get the comment, together with the list of updates