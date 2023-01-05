package vttp.paf.tvShowsDBExcercise.repositories;


import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

import vttp.paf.tvShowsDBExcercise.models.TvShow;

@Repository
public class TvShowRepository {
    
    @Autowired
    MongoTemplate mongoTemplate;

    public List<TvShow> findShowByRating(Float rating){

        Criteria criteria = Criteria.where("rating.average").gte(rating);

        Query query = Query.query(criteria);
        List<Document> docs = mongoTemplate.find(query, Document.class, "tvshows");

        List<TvShow> shows = docs.stream()
                                .map(show -> TvShow.create(show))
                                .toList();
        return shows;
    }


    public List<TvShow> findbySummary(List<String> matching, List<String> notMatching){

        //**default -> not case sensitive

        TextCriteria textCriteria = TextCriteria.forDefaultLanguage()
                                    .matchingAny(matching.toArray(new String[0]))
                                    .notMatchingAny(notMatching.toArray(new String[0]));
        TextQuery query = TextQuery.queryText(textCriteria).sortByScore();
        query.setScoreFieldName("score");

        List<Document> results = mongoTemplate.find(query, Document.class, "tvshows2");

        List<TvShow> shows = results.stream()
                                    .map(s -> TvShow.create(s))
                                    .toList();

        return shows;
    }
}
