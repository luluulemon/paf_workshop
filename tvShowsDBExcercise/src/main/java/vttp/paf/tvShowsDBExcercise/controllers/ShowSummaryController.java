package vttp.paf.tvShowsDBExcercise.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.paf.tvShowsDBExcercise.models.TvShow;
import vttp.paf.tvShowsDBExcercise.services.SummaryService;

@Controller
public class ShowSummaryController {
    
    @Autowired
    private SummaryService summarySvc;

    @GetMapping("/getSummaries")
    public String getMatchingSummaries(@RequestParam String keywords, 
        @RequestParam Double score, Model model){

        System.out.println(keywords);
        System.out.println(score);
        List<TvShow> shows = summarySvc.getSummaries(keywords, score);

        model.addAttribute("Shows", shows);

        return "summaryDisplay";
    }
}
