package vttp.paf.day26workshop.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import vttp.paf.day26workshop.models.Game;

@Repository
public class GameRepository {
    
    @Autowired
    MongoTemplate mongoTemplate;

    public JsonObject getGame(int limit, int offset){

        Query query2 = Query.query(Criteria.where("name").regex("/*"))
                            .limit(limit).skip(offset);
        query2.fields().include("name", "gid");
        List<Document> results2 = mongoTemplate.find(query2, Document.class, "games");

        System.out.println( ">>>>>>>>>>>>>>>>>>>>>" + results2.size() ); 

        // build games Array
        JsonArrayBuilder gameArrBuilder = Json.createArrayBuilder();
        results2.stream()
                .forEach(doc -> gameArrBuilder.add( Game.toJSON(doc) ) );
        JsonArray gamesArray = gameArrBuilder.build();

        // // build main object
        JsonObjectBuilder builder = Json.createObjectBuilder();
        return    builder.add("games", gamesArray)
                    .add("offset", offset)
                    .add("limit", limit)
                    .add("total", gamesArray.size())
                    .add("timestamp", System.currentTimeMillis())
                    .build();
    }


    public JsonObject getRank(int limit, int offset){

        Query query = Query.query(Criteria.where("ranking").gte(0))
                        .with(Sort.by(Sort.Direction.ASC, "ranking"))
                        .limit(limit).skip(offset);
        query.fields().include("name", "gid", "ranking");
        List<Document> results = mongoTemplate.find(query, Document.class, "games");

        System.out.println(" print array size >>>>> " + results.size());    
        // build games Array
        JsonArrayBuilder gameArrBuilder = Json.createArrayBuilder();
        results.stream()
                .forEach(doc -> gameArrBuilder.add( Game.rankingToJSON(doc) ) );
        JsonArray gamesArray = gameArrBuilder.build();

        // // build main object
        JsonObjectBuilder builder = Json.createObjectBuilder();
        return    builder.add("games", gamesArray)
                    .add("offset", offset)
                    .add("limit", limit)
                    .add("total", gamesArray.size())
                    .add("timestamp", System.currentTimeMillis())
                    .build();
    }


    public Optional<JsonObject> getGameDetails(int gid){

        Query query = Query.query(Criteria.where("gid").is(gid));
        List<Document> result = mongoTemplate.find(query, Document.class, "games");

        if(result.size() == 0)
        {   return Optional.empty(); }

        JsonObjectBuilder builder = Json.createObjectBuilder();
        Document game = result.get(0);
        JsonObject gameJson =    builder.add("gid", game.getInteger("gid", 0))
                                .add("name", game.getString("name"))
                                .add("year", game.getInteger("year", 0))
                                .add("ranking", game.getInteger("ranking", 0))
                                .add("users rated", game.getInteger("users_rated", 0))
                                .add("url", game.getString("url"))
                                .add("image", game.getString("image"))
                                .add("timestamp", System.currentTimeMillis())
                                .build();

        return Optional.of(gameJson);
    }

}

/* 
{
    "_id" : ObjectId("6392cca75d6aed722ad85079"),
    "gid" : NumberInt(3),
    "name" : "Samurai",
    "year" : NumberInt(1998),
    "ranking" : NumberInt(188),
    "users_rated" : NumberInt(13455),
    "url" : "https://www.boardgamegeek.com/boardgame/3/samurai",
    "image" : "https://cf.geekdo-images.com/micro/img/4XUy5QxQQfMHjx7pm2JpkJrTdDQ=/fit-in/64x64/pic3211873.jpg"
}
*/