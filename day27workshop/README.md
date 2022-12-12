Updating comments collection

Endpoints:
/review (POST)
- adding a new comment

/review/{reviewID}  (PUT)
- updating a comment, only update either rating or comment (c_text)

/review/{reviewID}  (GET)
- get the latest edit of comment

/review/{reviewID}/history  (GET)
- get the comment, together with the list of updates