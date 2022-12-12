package vttp.paf.day27workshop.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

import jakarta.json.JsonObject;

@Repository
public class GameReviewRepository {
    
    @Autowired
    MongoTemplate mongoTemplate;

    public ObjectId insertComment(JsonObject comment){

        Document commentDoc = Document.parse(comment.toString());
        Document insertedDoc = mongoTemplate.insert(commentDoc, "comments");
        ObjectId newID = insertedDoc.getObjectId("_id");
        
        return newID;
    }

    public Optional<String> getGameName(int gid){
        List<Document> results = mongoTemplate.find(
                    Query.query(Criteria.where("gid").is(gid)), Document.class, "games");
        if(results.size() == 0)
        {   return Optional.empty();    }
        
        String gameName = results.get(0).getString("name");
        return Optional.of(gameName);
    }


    public Optional<Document> getReviewByID(String reviewID) throws IllegalArgumentException{
    // handle this exception:
    // "java.lang.IllegalArgumentException: invalid hexadecimal representation of an ObjectId:
    // [6395ee975a3b906e9bea6f]\r\n\tat org.bson.types.ObjectId.parseHexString(ObjectId.java:419)\r\n\tat org.bson.types.ObjectId.<init>(ObjectId.java:205)\r\n\tat 
    // vttp.paf.day27workshop.repositories.GameReviewRepository.getReviewByID(GameReviewRepository.java:47)

        Document result = 
        mongoTemplate.findById(new ObjectId(reviewID), Document.class, "comments"); 
        
        if(result == null)
            return Optional.empty();

        return Optional.of(result);
    }


    public long updateReview(String reviewID, Update updateReview){

        Query query = Query.query(Criteria.where("_id").is(new ObjectId(reviewID)) );
        UpdateResult result = mongoTemplate.updateFirst(query, updateReview,
                    Document.class, "comments");

        return result.getModifiedCount();
    }
}
