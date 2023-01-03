Day 21 - Retrieving customers info from Northwind SQL

Day 22 - Creating RSVP database with CRUD functions

Day 23 - Get order by order_id from northwind SQL, combines 3 tables

Day 24 
- Create Order form with order details
- Save to SQL 2 tables: orders, order_details (various products in order)

Day 26
- Boardgames database on localhost, with games and comments collections
    Endpoints:
    1. /games, with optional query params limit & offset
    2. /games/rank, with optional query params limit & offset
    3. /game/{gid}

Day 27
- Boardgames database on localhost, with games and comments collections
    Endpoints:
    1. /review  (POST), add a new comment, from html form
    2. /review/{reviewID}  (PUT), update comment: only rating or comment fields
    3. /review/{reviewID}   (GET), get latest edit of comment
    4. /review/{reviewID}/history   (GET), get full comment, together with list of updates


ToDoList MockPaper
- add tasks to to-do List

endPoints:
1. /user 
-> to add user, generate userID
2. / 
-> Mainpage: to add task, and save to database (on localhost)
- adds list of tasks as a transaction
- has an InsertException, just to try the rollback
