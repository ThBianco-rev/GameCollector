package org.example.Service;

import org.example.Exception.GameException;
import org.example.Main;
import org.example.Model.Game;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GameCollectionService {
/**
 * Keep track of my collection of games
 * Add a game to my collection
 * Update values
 */
    List<Game> gameCollection = new ArrayList<>();

    /*
    * Add a new game to the list*/
    public void addGame(String title, String platform, String genre, String playTimeString) throws GameException {
        LocalDate dateAdded = LocalDate.now();
        Main.log.info("Attempting to add a game:" + title +", " +platform +", " + genre +", " + playTimeString + ", " + dateAdded);
        double playTime;
        try{
            double d = Double.parseDouble(playTimeString);
        } catch (NumberFormatException e) {
            Main.log.warn("Throwing NumberFormatException because Play Time is not numerical:" + playTimeString);
            System.out.println("Play Time must be numerical.");
        }
        playTime = Double.parseDouble(playTimeString);

        if(title.isEmpty()){
            Main.log.warn("Throwing GameException due to empty title:" + title);
            throw new GameException("Title cannot be empty");
        }

        if(playTime < 0){
            Main.log.warn("Throwing GameException due to negative Play Time:" + playTime);
            throw new GameException("Play Time cannot be negative.");
        }

        if(getGameByTitle(title) != null){
            Main.log.warn("Throwing GameException due to duplicate game Title in collection:" + title);
            throw new GameException("That game is already in your collection.");
        }
        else
        {
            Game newGame = new Game(title, platform, genre, playTime, dateAdded);
            gameCollection.add(newGame);
            Main.log.info("Game added successfully:" + newGame);
            System.out.println("Game added successfully! \n" +
                    "Add another game or use the \"done\" command to return to main menu.");
        }
    }

    /*Find a game by its title and remove that game from the list*/
    public void removeGameByTitle(String title) throws GameException {
        Main.log.info("Attempting to remove game with Title:" + title);
        if(getGameByTitle(title) == null)
        {
            Main.log.warn("Throwing GameException because matching Title not found in collection:" + title);
            throw new GameException("Game not found.");
        }
        else
        {

            gameCollection.remove(getGameByTitle(title));
            Main.log.info("Game removed successfully:" + title);
            System.out.println("Game removed successfully! \n" +
                    "Remove another game or use the \"done\" command to return to main menu.");
        }
    }

    /*Return the entire collection*/
    public List<Game> getGameCollection(){
        return gameCollection;
    }

    /*Return a game by searching with its title*/
    public Game getGameByTitle(String title){
        for (Game game : gameCollection) {
            if (game.getTitle().equalsIgnoreCase(title))
            {
                Main.log.info("Game found in collection:" + game);
                System.out.println("Game found in collection:" + game);
                return game;
            }
        }
        Main.log.warn("Game not found in collection");
        return null;
    }

    /*Check prior to updating game for returning data to user*/
    public Game prepUpdateGame(String title) throws GameException{
        Game gameToUpdate = getGameByTitle(title);
        if (gameToUpdate == null){
            throw new GameException("Game not found");
        }
         else{
            return gameToUpdate;
        }
    }

    /*Update title field of selected game*/
    public void updateTitle(Game gameToUpdate, String newTitle){
        gameToUpdate.setTitle(newTitle);
        Main.log.info("Title update successfully. Updated Game info:" + gameToUpdate);
        System.out.println("Title updated successfully");
    }

    /*Update platform field of selected game*/
    public void updatePlatform(Game gameToUpdate, String newPlatform){
        gameToUpdate.setPlatform(newPlatform);
        Main.log.info("Platform update successfully. Updated Game info:" + gameToUpdate);
        System.out.println("Platform updated successfully");
    }

    /*Update genre field of selected game*/
    public void updateGenre(Game gameToUpdate, String newGenre){
        gameToUpdate.setGenre(newGenre);
        Main.log.info("Genre update successfully. Updated Game info:" + gameToUpdate);
        System.out.println("Genre updated successfully");
    }

    /*Update play time field of selected game*/
    public void updatePlayTime(Game gameToUpdate, String newPlayTime) throws NumberFormatException{
        gameToUpdate.setPlayTime(Double.parseDouble(newPlayTime));
        Main.log.info("Play Time update successfully. Updated Game info:" + gameToUpdate);
        System.out.println("Play Time updated successfully");
    }
}
