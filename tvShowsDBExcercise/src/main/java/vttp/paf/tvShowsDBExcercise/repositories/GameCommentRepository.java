package vttp.paf.tvShowsDBExcercise.repositories;


import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import vttp.paf.tvShowsDBExcercise.models.Game;

@Repository
public class GameCommentRepository {
    
    @Autowired
    MongoTemplate mongoTemplate;

    // db.games.aggregate([
    //     { $match: { name: "Elfenland"} },
    //     { $lookup:  { from: "reviews", localField: "gid", foreignField: "gid", as: "comments"} },
    //     {  $unwind: "$comments"},
    //     { $sort: { rating: -1 }},
    //     {  $limit: 20 },
    //     { $group: { _id: { name: "$name", image: "$image"},
    //                 comments: { $push: "$comments"}
    //                 }
    //     }
    // ])

    public Optional<Game> getGameComments(String name){

        MatchOperation matchGameName = Aggregation.match(Criteria.where("name").regex(name));
        LookupOperation lookupComments = Aggregation.lookup("reviews", "gid", "gid", "comments");
        UnwindOperation unwindComments = Aggregation.unwind("comments");
        SortOperation sortByRating = Aggregation.sort(Direction.ASC, "rating");
        LimitOperation limitComments = Aggregation.limit(20);
        GroupOperation groupComments = Aggregation.group("name", "image")
                                    .push("comments").as("comments");

        Aggregation pipeline = Aggregation.newAggregation(matchGameName, 
                                lookupComments, unwindComments, sortByRating, limitComments, groupComments);
                                
        AggregationResults<Document> comments = mongoTemplate
                                .aggregate(pipeline, "games", Document.class);

        if(!comments.iterator().hasNext())  return Optional.empty();
        
        Document gameWithComments =  comments.iterator().next();

        return Optional.of(Game.create(gameWithComments));
    }

}
