package vttp.paf.day28workshop.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ReplaceWithOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GameReviewRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public Optional<Document> getGameByID(int game_id){

        List<Document> results = mongoTemplate.find(
            Query.query(Criteria.where("gid").is(game_id)), 
            Document.class, "games");

        if(results.size() == 0)
            return Optional.empty();

        return Optional.of(results.get(0));
    }

    //     db.comments.aggregate( [
    //     { $match: { gid: 1 }},
    //     { $group: 
    //         { _id: "$gid",
    //           reviews: { $push: "$_id" }
    //         }
    //     },
    //     { $project: { _id:0 } }
    // ])
    public Document getReviewsByID(int game_id){

        MatchOperation matchReviewByID = Aggregation.match(Criteria.where("gid").is(game_id));
        GroupOperation groupReviews = Aggregation.group("gid")
                                            .push("_id")
                                            .as("reviews");

        Aggregation pipeline = Aggregation.newAggregation(matchReviewByID, groupReviews);

        AggregationResults<Document> reviews = mongoTemplate.aggregate
                        (pipeline, "comments", Document.class);

        // need to handle unavailable game_id
        
        return reviews.getMappedResults().get(0);
        // return reviews.iterator().next();
        
    }                   

    // db.comments.aggregate([
    //     {    $sort: { gid:1, rating: -1} },
    //     {    $group: { _id: "$gid", best_rating: { $first: "$$ROOT" } } },
    //     {    $replaceWith: "$best_rating" },
    //     {    $sort: { gid:1 } }
    // ])
    public AggregationResults<Document> findBestRating(){

        SortOperation sortByRating = Aggregation.sort(Sort.by(Direction.ASC, "gid"))
                                                .and(Sort.by(Direction.DESC, "rating"));

        GroupOperation groupByGame = Aggregation.group("gid")
                                    .first("$$ROOT")
                                    .as("best_rating");

        // ReplaceWithOperation getBestRating = Aggregation.
        SortOperation sortByGame = Aggregation.sort(Sort.by(Direction.ASC, "_id"));

        Aggregation pipeline = Aggregation.newAggregation(sortByRating, groupByGame, sortByGame);

        AggregationResults<Document> bestRatings = 
            mongoTemplate.aggregate(pipeline, "comments", Document.class);

        return bestRatings;
    }


    public AggregationResults<Document> findWorstRating(){

        SortOperation sortByRating = Aggregation.sort(Sort.by(Direction.ASC, "gid"))
                                                .and(Sort.by(Direction.ASC, "rating"));

        GroupOperation groupByGame = Aggregation.group("gid")
                                    .first("$$ROOT")
                                    .as("worst_rating");

        // ReplaceWithOperation getBestRating = Aggregation.
        SortOperation sortByGame = Aggregation.sort(Sort.by(Direction.ASC, "_id"));

        Aggregation pipeline = Aggregation.newAggregation(sortByRating, groupByGame, sortByGame);

        AggregationResults<Document> bestRatings = 
            mongoTemplate.aggregate(pipeline, "comments", Document.class);

        return bestRatings;
    }

}
