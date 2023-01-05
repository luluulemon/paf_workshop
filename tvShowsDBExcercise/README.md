Various endpoints for tvshows (mongoDB localhost)

endpoints:
    1. / : getting matching Summaries by score
        - enter matching keywords, non matching keywords (prefix with "-"), and minimum score
        - returns html
    2. /rating/{rating}
        - search for tvshows with minimum ratings as path variable
        - returns Json
    3. /game
        - search for top 20 comments of the game
        - comments ranked by ratings
    