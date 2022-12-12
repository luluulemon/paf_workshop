package vttp.paf.day27workshop.controllers;

import java.io.StringReader;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import vttp.paf.day27workshop.models.Comment;
import vttp.paf.day27workshop.repositories.GameReviewRepository;

@RestController
public class GameReviewRestController {
    //63944d537d1743487a5cd3d5
    @Autowired
    private GameReviewRepository gameReviewRepo;

    @PutMapping("review/{reviewID}")
    public ResponseEntity<String> updateReview(@PathVariable String reviewID, 
                                                @RequestBody Comment comment){
            
        // check if reviewID exists
        Optional<Document> review = gameReviewRepo.getReviewByID(reviewID);
        if(review.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
                                           
        // build document to throw in Update object
        JsonObjectBuilder newReviewBuilder = Json.createObjectBuilder(); 
        newReviewBuilder.add("c_text", comment.getComment())
                .add("rating", comment.getRating())
                .add("posted", System.currentTimeMillis());
        
        Document reviewToInsert = Document.parse(newReviewBuilder.build().toString());
        Update reviewUpdate = new Update().push("edited", reviewToInsert);
                                        
        return ResponseEntity.ok().body(reviewUpdate.getUpdateObject().toJson());
    }


    @GetMapping("/review/{reviewID}")
    public ResponseEntity<String> getLatestComment(@PathVariable String reviewID){

        Optional<Document> Op;
        
        // handle exception for invalid hex String for ObjectID
        try{    Op = gameReviewRepo.getReviewByID(reviewID);    }
        catch(IllegalArgumentException e)
        {    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());   }
        
        if(Op.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
        
        Document reviewDoc = Op.get();

        boolean edited = true;
        Document lastComment = reviewDoc;
        if(reviewDoc.get("edited") == null)
        {   edited = false; }

        // get latest comment, if edited
        if(edited == true){
            List<Document> commentsArray = reviewDoc.getList("edited", Document.class);
            lastComment = commentsArray.get(commentsArray.size()-1);
        }

        JsonObjectBuilder reviewBuilder = Json.createObjectBuilder();
        reviewBuilder.add("user", reviewDoc.getString("user"))
                        .add("rating", lastComment.getDouble("rating"))
                        .add("comment", lastComment.getString("c_text"))
                        .add("id", reviewDoc.getInteger("gid", 0))
                        .add("posted", reviewDoc.getLong("posted"))
                        .add("name", reviewDoc.getString("name"))
                        .add("edited", edited)
                        .add("timestamp", System.currentTimeMillis());
                        
        return ResponseEntity.ok().body(reviewBuilder.build().toString());
    }


    @GetMapping("/review/{reviewID}/history")
    public ResponseEntity<String> getReviewHistory(@PathVariable String reviewID){

        Optional<Document> Op;
        
        // handle exception for invalid hex String for ObjectID
        try{    Op = gameReviewRepo.getReviewByID(reviewID);    }
        catch(IllegalArgumentException e)
        {    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());   }

        if(Op.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
        
        Document reviewDoc = Op.get();

        // convert the Document back to a JsonObject
        JsonReader reader = Json.createReader(new StringReader(reviewDoc.toJson()));
        JsonObject fullReview = reader.readObject();

        // How to add new attribute to JsonObject
        //fullReview.put("timestamp", Json.createValue(System.currentTimeMillis()));

        
        return ResponseEntity.ok().body(fullReview.toString());
    }

}
