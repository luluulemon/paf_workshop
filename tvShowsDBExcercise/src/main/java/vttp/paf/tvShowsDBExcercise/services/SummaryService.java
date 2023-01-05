package vttp.paf.tvShowsDBExcercise.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.paf.tvShowsDBExcercise.models.TvShow;
import vttp.paf.tvShowsDBExcercise.repositories.TvShowRepository;

@Service
public class SummaryService {
    
    @Autowired
    private TvShowRepository tvShowRepo;

    public List<TvShow> getSummaries(String keywordsString, Double score){

        List<String> matching = new ArrayList<>();
        List<String> notMatching = new ArrayList<>();


        String[] keywords = keywordsString.split(" ");
        for(String s: keywords)
            if(s.startsWith("-")){  notMatching.add(s);    }
            else{   matching.add(s);    }

        List<TvShow> shows = tvShowRepo.findbySummary(matching, notMatching);

        return shows.stream().filter(s -> s.getScore() > score).toList();
    }
}
