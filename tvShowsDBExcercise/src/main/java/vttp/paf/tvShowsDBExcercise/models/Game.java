package vttp.paf.tvShowsDBExcercise.models;

import java.util.List;

import org.bson.Document;

public class Game {
    
    private String name;
    private String image;
    private List<Comment> comments;
    private int gameId;

    public String getName() {        return name;    }
    public void setName(String name) {        this.name = name;    }
    
    public String getImage() {        return image;    }
    public void setImage(String image) {        this.image = image;    }

    public List<Comment> getComments() {        return comments;    }
    public void setComments(List<Comment> comments) {        this.comments = comments;    }

    public int getGameId() {        return gameId;    }
    public void setGameId(int gameId) {        this.gameId = gameId;    }

    public static Game create(Document doc){
        Game game = new Game();
        game.setName(doc.get("_id", Document.class).getString("name"));
        System.out.println("Check name in create Game: " + game.getName());
        game.setImage(doc.get("_id", Document.class).getString("image"));
        // game.setGameId(doc.getInteger("gid"));
        
        List<Comment> comments = doc.getList("comments", Document.class)
                                    .stream()
                                    .map(c -> Comment.createComment(c))
                                    .toList();

        game.setComments(comments);

        return game;
    }

}
