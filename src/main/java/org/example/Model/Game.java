package org.example.Model;

import javax.annotation.processing.Completion;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

/**
 * Field definitions
 */

    // Title of the game
    private String title;

    // Platform on which we own the game
    private Platform platform;

    // Genre
    private String genre;

    // Completion Status
    private CompletionStatus completionStatus;

    // Amount of time spent playing the game
    private Double playTime;

    // Date the game was added to our collection
    private LocalDate dateAdded;


/**
 * Getters and Setters
 */
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String newTitle){
        this.title = newTitle;
    }
    public String getPlatform() {
        return this.platform.toString();
    }
    public void setPlatform(String newPlatform) {

        Platform[] platforms = Platform.values();
        for (Platform platform : platforms) {
            if(newPlatform.equalsIgnoreCase(platform.toString()))
            {
                this.platform = platform;
                return;
            }
            else this.platform = Platform.UNK;
        }
    }
    public String getGenre(){
        return this.genre;
    }
    public void setGenre(String newGenre){
        this.genre = newGenre;
    }
    public void setCompletionStatus(String newStatus) {

        CompletionStatus[] compStats = CompletionStatus.values();
        for (CompletionStatus compStat : compStats) {
            if(newStatus.equalsIgnoreCase(compStat.toString()))
            {
                this.completionStatus = compStat;
            }
        }
    }
    public String getCompletionStatus() {
        return this.completionStatus.toString();
    }
    public LocalDate getDateAdded() {
        return dateAdded;
    }
    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Double getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Double playTime) {
        this.playTime = playTime;
    }

/**
 * Constructors
 */
    public Game(){}
    public Game(String title, String platform, String genre, Double playTime, LocalDate dateAdded) {
        setTitle(title);
        setPlatform(platform);
        setGenre(genre);
        setPlayTime(playTime);
        setDateAdded(dateAdded);
    }

    public Game(String title, String platform, String genre, String completionStatus, Double playTime, LocalDate dateAdded) {
        setTitle(title);
        setPlatform(platform);
        setGenre(genre);
        setCompletionStatus(completionStatus);
        setPlayTime(playTime);
        setDateAdded(dateAdded);
    }

    @Override
    public String toString() {
        return
                "Title: " + title +
                "\n \t Platform: " + platform +
                "\n \t Genre: " + genre +
                "\n \t Play Time: " + playTime +
                "\n \t Date Added: " + dateAdded +
                "\n";
    }
}
