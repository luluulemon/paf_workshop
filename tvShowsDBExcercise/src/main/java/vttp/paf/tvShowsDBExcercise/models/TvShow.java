package vttp.paf.tvShowsDBExcercise.models;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class TvShow {
    
    private String name;
    private String type;
    private String language;
    private List<String> genres;
    private Double rating;
    private String summary;
    private String image;
    private Double score;

    public Double getScore() {        return score;    }
    public void setScore(Double score) {        this.score = score;    }

    public String getName() {        return name;    }
    public void setName(String name) {        this.name = name;    }

    public String getType() {        return type;    }
    public void setType(String type) {        this.type = type;    }

    public String getLanguage() {        return language;    }
    public void setLanguage(String language) {        this.language = language;    }

    public List<String> getGenres() {        return genres;    }
    public void setGenres(List<String> genres) {        this.genres = genres;    }
    
    public Double getRating() {        return rating;    }
    public void setRating(Double rating) {        this.rating = rating;    }

    public String getSummary() {        return summary;    }
    public void setSummary(String summary) {        this.summary = summary;    }

    public String getImage() {        return image;    }
    public void setImage(String image) {        this.image = image;    }

    public static TvShow create(Document doc){
        TvShow show = new TvShow();
        show.setName(doc.getString("name"));
        show.setLanguage(doc.getString("language"));
        show.setGenres(doc.getList("genres", String.class, new LinkedList<>()));
        show.setImage(doc.get("image", Document.class).getString("original"));
        show.setSummary(doc.getString("summary"));
            
        Object rating = doc.get("rating", Document.class).get("average");
        if(rating instanceof Integer)
        {   show.setRating( ((Integer)rating).doubleValue() ); }
        else
        {   show.setRating( (Double)rating);}

        if(doc.getDouble("score") != null) show.setScore(doc.getDouble("score"));

        return show;
    }


    public JsonObject toJson(){
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("name", getName())
                .add("language", getLanguage())
                .add("Genres", getGenres().toString())
                .add("image", defaultValue(getImage(), "No Image") )
                .add("summary", defaultValue(getSummary(), "No Summary"))
                .add("rating", rating);

        return builder.build();
    }


    public <T> T defaultValue(T actual, T defVal){
        if(null == actual)
             return defVal;
        return actual;
    }
}
