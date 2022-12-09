package vttp.paf.day26workshop.models;

import org.bson.Document;


import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Game {
    
    private int id;
    private String name;
    private int year;
    private int ranking;
    private int users_rated;
    private String url;
    private String image;

    public static Game create(Document doc){
        Game game = new Game();
        game.setId(doc.getInteger("gid", 0));
        game.setName(doc.getString("name"));
        return game;
    }

    public static JsonObject toJSON(Document doc){
        JsonObjectBuilder builder = Json.createObjectBuilder();

        return builder.add("name", doc.getString("name"))
                .add("id", doc.getInteger("gid", 0)).build();
    }

    public static JsonObject rankingToJSON(Document doc){
        JsonObjectBuilder builder = Json.createObjectBuilder();

        return builder.add("name", doc.getString("name"))
                .add("id", doc.getInteger("gid", 0))
                .add("ranking", doc.getInteger("ranking", 0))
                .build();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getRanking() {
        return ranking;
    }
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    public int getUsers_rated() {
        return users_rated;
    }
    public void setUsers_rated(int users_rated) {
        this.users_rated = users_rated;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }


}
