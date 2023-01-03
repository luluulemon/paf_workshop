package vttp.paf.day28workshop.controllers;

import java.io.StringReader;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import vttp.paf.day28workshop.repositories.GameReviewRepository;

@Controller
public class ReviewRestController {
    
    @Autowired
    GameReviewRepository gameReviewRepo;

    @GetMapping("/game/{game_id}/reviews")
    public ResponseEntity<String> getGameReview(@PathVariable int game_id){

        Optional<Document> game = gameReviewRepo.getGameByID(game_id);
        if(game.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(game_id + " is not found");
        Document gameDoc = game.get();

        Document review = gameReviewRepo.getReviewsByID(game_id);
        // create reviews Array
        List<ObjectId> oids = review.getList("reviews", ObjectId.class);
        JsonArrayBuilder reviewsArray3 = Json.createArrayBuilder();
        oids.stream().forEach(o -> {    String r = "/reviews/" + o.toString();
                                        reviewsArray3.add(r);
                                    });            

        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("game_id", gameDoc.getInteger("gid", 0))
                .add("name", gameDoc.getString("name"))
                .add("year", gameDoc.getInteger("year", 0))
                .add("rank", gameDoc.getInteger("ranking", 0))
                .add("uses_rated", gameDoc.getInteger("users_rated", 0))
                .add("url", gameDoc.getString("url"))
                .add("thumbnail", gameDoc.getString("image"))
                .add("reviews", reviewsArray3.build())
                .add("timestamp", System.currentTimeMillis());

        return ResponseEntity.ok().body(builder.build().toString());
    }


    @GetMapping("/games/highest")
    public ResponseEntity<String> getBestReviews(){

        List<Document> bestReviewsList = gameReviewRepo.findBestRating().getMappedResults();
        
        JsonArrayBuilder gamesBuilder = Json.createArrayBuilder();
        bestReviewsList.stream()
                    .map(v -> v.toJson())
                    .forEach(v -> {
                        JsonReader reader = Json.createReader(new StringReader(v));
                        JsonObject game = reader.readObject();
                        gamesBuilder.add(game);
                    });

        // build return object
        JsonObject bestReviews = Json.createObjectBuilder()
                                    .add("rating", "highest")
                                    .add("games", gamesBuilder.build())
                                    .add("timestamp", System.currentTimeMillis())
                                    .build();
        
        return ResponseEntity.ok().body(bestReviews.toString());
    }


    @GetMapping("/games/lowest")
    public ResponseEntity<String> getWorstReviews(){

        List<Document> bestReviewsList = gameReviewRepo.findWorstRating().getMappedResults();
        
        JsonArrayBuilder gamesBuilder = Json.createArrayBuilder();
        bestReviewsList.stream()
                    .map(v -> v.toJson())
                    .forEach(v -> {
                        JsonReader reader = Json.createReader(new StringReader(v));
                        JsonObject game = reader.readObject();
                        gamesBuilder.add(game);
                    });

        JsonObject bestReviews = Json.createObjectBuilder()
                                    .add("rating", "lowest")
                                    .add("games", gamesBuilder.build())
                                    .add("timestamp", System.currentTimeMillis())
                                    .build();
        
        return ResponseEntity.ok().body(bestReviews.toString());
    }

}
