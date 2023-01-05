package vttp.paf.tvShowsDBExcercise.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import vttp.paf.tvShowsDBExcercise.models.TvShow;
import vttp.paf.tvShowsDBExcercise.repositories.TvShowRepository;

@RestController
public class TvShowController {
    
    @Autowired
    private TvShowRepository tvShowRepo;

    @GetMapping(path = "rating/{rating}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getShowByRating(@PathVariable Float rating){

        List<TvShow> shows = tvShowRepo.findShowByRating(rating);

        // create body
        JsonArrayBuilder showsArrayBuilder = Json.createArrayBuilder();
        shows.stream().forEach(show -> showsArrayBuilder.add(show.toJson()));

        return ResponseEntity.status(HttpStatus.FOUND).body(showsArrayBuilder.build().toString());
    }
}
