package vttp.paf.day27workshop.controllers;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import vttp.paf.day27workshop.repositories.GameReviewRepository;

@Controller
public class GameReviewController {
    
    @Autowired
    private GameReviewRepository gameReviewRepo;

    @PostMapping("/review")
    public String saveReview(@RequestBody MultiValueMap<String, String> form, Model model){

        int gameID = Integer.parseInt( form.getFirst("gid") );
        Optional<String> gameName = gameReviewRepo.getGameName(gameID);
        if(gameName.isEmpty())
        {   return "notFound";   }  

        // convert to JsonObject to pass to Repo
        JsonObjectBuilder commentBuilder = Json.createObjectBuilder();
        commentBuilder.add("user", form.getFirst("username"))
                .add("rating", Double.parseDouble(form.getFirst("rating")))
                .add("c_text", form.getFirst("comment"))
                .add("gid", gameID)
                .add("posted", System.currentTimeMillis())
                .add("name", gameName.get());

        ObjectId id = gameReviewRepo.insertComment(commentBuilder.build());
        System.out.println(" >>>>>> Check ID" + id);
        model.addAttribute("Id", id.toString());

        return "confirmation";
    }
}



// {
//     user: <name form field>,
//     rating: <rating form field>,
//     comment: <comment form field>,
//     ID: <game id form field>,
//     posted: <date>,
//     name: <The board game’s name as per ID>
// }
    