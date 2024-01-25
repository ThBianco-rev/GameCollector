package org.example.View;

import org.example.Exception.GameException;
import org.example.Exception.InputException;
import org.example.Main;
import org.example.Model.Game;
import org.example.Service.GameCollectionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class InputHandler {
    GameCollectionService gameCollectionService;

    public InputHandler() {
        gameCollectionService = new GameCollectionService();
    }

    public void handleInput(String input) throws GameException, InputException {
        switch (input.toLowerCase()) {
            case "add": {
                handleAdd();
                break;
            }
            case "view": {
                System.out.println(handleView());
                break;
            }
            case "update": {
                handleUpdate();
                break;
            }
            case "delete": {
                handleDelete();
                break;
            }
            default:
                throw new InputException("Unexpected command. Please enter a valid command.");
        }
    }

    private void handleAdd() throws GameException, ArrayIndexOutOfBoundsException {
        Scanner sc = new Scanner(System.in);
        System.out.println("To add a game to your collection, please enter the following information, delimited by commas (,)" + "\n" +
                "Title, Platform, Genre, Play Time (hours)" + "\n" +
                "Example: Mario Kart 8 Deluxe, Nintendo Switch, Racing, 35.5");
        while (true) {
            String nextInput = sc.nextLine();
            if (nextInput.equalsIgnoreCase("done")) {
                break;
            }
            String[] stringArr = nextInput.split(",");
            gameCollectionService.addGame(stringArr[0].trim(), stringArr[1].trim(), stringArr[2].trim(), stringArr[3].trim());
        }
    }

    private String handleView() {
        List<Game> games = gameCollectionService.getGameCollection();
        return "Your Game Collection: \n" + games.toString();
    }

    private void handleDelete() throws GameException {
        Scanner sc = new Scanner(System.in);
        System.out.println("To remove a game from your collection, please enter the Title. Or use the \"done\" command to return to main menu.");
        while (true) {
            //TODO: Exception handling
            String nextInput = sc.nextLine();
            if (nextInput.equalsIgnoreCase("done")) {
                break;
            }
            gameCollectionService.removeGameByTitle(nextInput);
        }
    }

    private void handleUpdate() throws GameException {
        Scanner sc = new Scanner(System.in);
        System.out.println("To update a game in your collection, please enter the Title. Or use the \"done\" command to return to main menu.");
        String input = sc.nextLine();
        Game gameToUpdate;
        if (input.equalsIgnoreCase("done")) {
            return;
        }
        // Check to make sure game is in collection
        gameToUpdate = gameCollectionService.prepUpdateGame(input);

        System.out.println("Please enter the name of the field you would like to update: \"Title\" / \"Platform\" / \"Genre\" / \"Play Time\"" +
                "\nOr use the \"done\" command to return to main menu.");
        String updateMenuInput = sc.nextLine();

        switch (updateMenuInput.toLowerCase()) {
            case "title": {

                System.out.println("Please enter the new Title");
                String newInput = sc.nextLine();
                Main.log.info("Attempting to update Title for: " + gameToUpdate);
                gameCollectionService.updateTitle(gameToUpdate, newInput);
                break;

            }
            case "platform": {
                System.out.println("Please enter the new Platform");
                String newInput = sc.nextLine();
                Main.log.info("Attempting to update Platform for: " + gameToUpdate);
                gameCollectionService.updatePlatform(gameToUpdate, newInput);
                break;
            }
            case "genre": {
                System.out.println("Please enter the new Genre");
                String newInput = sc.nextLine();
                Main.log.info("Attempting to update Genre for: " + gameToUpdate);
                gameCollectionService.updateGenre(gameToUpdate, newInput);
                break;
            }
            case "play time": {
                System.out.println("Please enter the new Play Time");
                String newInput = sc.nextLine();
                Main.log.info("Attempting to update Play Time for: " + gameToUpdate);
                gameCollectionService.updatePlayTime(gameToUpdate, newInput);
                break;
            }
            default:
                System.out.println("Unexpected command. Please enter a valid command.");
                break;
        }
    }
}




