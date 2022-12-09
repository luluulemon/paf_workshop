package vttp.paf.day26workshop.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.json.JsonObject;
import vttp.paf.day26workshop.repositories.GameRepository;

@Controller
public class GameRestController {
    
    @Autowired
    private GameRepository gameRepo;

    @GetMapping("/games")
    public ResponseEntity<String> getGames
        (@RequestParam(required = false) Integer limit, @RequestParam(required=false) Integer offset)
    {
        if(limit == null)
        {   limit = 25;   }
        if(offset == null)
        {   offset = 0; }

        JsonObject resp = gameRepo.getGame(limit, offset);

        return ResponseEntity.ok().body(resp.toString());
    }


    @GetMapping("/games/ranking")
    public ResponseEntity<String> getGamesRanking
        (@RequestParam(required = false) Integer limit, @RequestParam(required=false) Integer offset)
    {
        if(limit == null)
        {   limit = 25;   }
        if(offset == null)
        {   offset = 0; }

        JsonObject resp = gameRepo.getRank(limit, offset);

        return ResponseEntity.ok().body(resp.toString());
    }


    @GetMapping("/game/{gid}")
    public ResponseEntity<String> getGameDetails(@PathVariable int gid){

        Optional<JsonObject> game = gameRepo.getGameDetails(gid);
        if(game.isEmpty() )
        {   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);       }

        return ResponseEntity.ok().body(game.get().toString());
    }
}
