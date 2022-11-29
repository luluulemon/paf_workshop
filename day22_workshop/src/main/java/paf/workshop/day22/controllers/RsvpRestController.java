package paf.workshop.day22.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import paf.workshop.day22.models.Rsvp;
import paf.workshop.day22.services.RsvpSvc;

@RestController
@RequestMapping(path="/api", produces = "application/json")
public class RsvpRestController {
    
    @Autowired
    RsvpSvc rsvpsvc;

    @GetMapping(path="/rsvps")
    public ResponseEntity<String> getRSVPs(){

        List<Rsvp> rsvplist = rsvpsvc.getRSVPs();

        JsonArrayBuilder builder = Json.createArrayBuilder();
        for(Rsvp rsvp:rsvplist){
            builder.add(rsvp.toJSON());
        }

        return ResponseEntity.ok(builder.build().toString());
    }


    @GetMapping(path="/rsvp")
    public ResponseEntity<String> getRSVPbyName(@RequestParam String name){

        List<Rsvp> rsvplist = rsvpsvc.getRSVPByName(name);
        if(rsvplist.size() == 0){   return ResponseEntity
                                        .status(HttpStatus.NOT_FOUND)
                                        .body("No " + name + " rsvp");  }

        JsonArrayBuilder builder = Json.createArrayBuilder();
        for(Rsvp rsvp:rsvplist){
            builder.add(rsvp.toJSON());
        }

        return ResponseEntity.ok(builder.build().toString());
    }


    @PostMapping(path="/rsvp", consumes="application/x-www-form-urlencoded")
    public ResponseEntity<String> insertRSVP(@RequestBody MultiValueMap<String, String> form){
        
        Rsvp rsvp = new Rsvp();
        rsvp.setName(form.getFirst("name"));
        rsvp.setEmail(form.getFirst("email"));
        rsvp.setPhone(form.getFirst("phone"));
        rsvp.setDate(form.getFirst("date"));
        rsvp.setComments(form.getFirst("comments"));
        
        if( rsvpsvc.updateRSVP(rsvp) > 0)
        {   return ResponseEntity.status(201).body("Updated " + rsvp.getName());    }

        if( rsvpsvc.insertRSVP(rsvp) > 0)
        {    return ResponseEntity.status(201).body("Created rsvp for " + rsvp.getName());  }    

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Not Created");
    }
 
    
    @PutMapping(path="/rsvp/{email}" ,consumes="application/x-www-form-urlencoded")
    public ResponseEntity<String> updateRSVP(@PathVariable String email, 
            @RequestBody(required=false) MultiValueMap<String,String> form){
        
        if(!rsvpsvc.getRSVPByEmail(email)){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body("Email not found");
        }
        
        Rsvp rsvp = new Rsvp();
        rsvp.setName(form.getFirst("name"));
        rsvp.setEmail(form.getFirst("email"));
        rsvp.setPhone(form.getFirst("phone"));
        rsvp.setDate(form.getFirst("date"));
        rsvp.setComments(form.getFirst("comments"));

        if(rsvpsvc.updateRSVP(rsvp) > 0)
        {   return ResponseEntity.status(201).body("Updated rsvp for " + email);    }
       

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Not Updated");

    }


    @GetMapping(path="/rsvp/count", produces="application/json")
    public ResponseEntity<String> getRSVPCount(){
        String count = rsvpsvc.getRSVPCount();
        return ResponseEntity.ok().body("No. of RSVP is " + count);
    }
}
