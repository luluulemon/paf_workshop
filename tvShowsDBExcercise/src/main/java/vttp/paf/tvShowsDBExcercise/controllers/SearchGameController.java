package vttp.paf.tvShowsDBExcercise.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.paf.tvShowsDBExcercise.models.Game;
import vttp.paf.tvShowsDBExcercise.repositories.GameCommentRepository;

@Controller
public class SearchGameController {
    
    @Autowired
    private GameCommentRepository commentRepo;

    @GetMapping("/game")
    public String searchGame(){
        return "searchGame";
    }

    @GetMapping("/getGameComments")
    public String getGameComments(@RequestParam String game, Model model){

        System.out.println("Check game name " + game);
        Optional<Game> gameWithComments = commentRepo.getGameComments(game);
        if(gameWithComments.isEmpty())  return "errorPage";

        model.addAttribute("Game", gameWithComments.get());
        return "results";
    }

}
