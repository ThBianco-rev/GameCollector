package org.example;

import org.example.Exception.GameException;
import org.example.Exception.InputException;
import org.example.Model.CompletionStatus;
import org.example.Service.GameCollectionService;
import org.example.View.InputHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static Logger log = LoggerFactory.getLogger(Main.class);
    GameCollectionService gameCollectionService = new GameCollectionService();
    static String startScreenln0 = "==============================\n";
    static String startScreenln1 = "||           Game           ||\n";
    static String startScreenln2 = "||        Collection        ||\n";
    static String startScreenln3 = "||          Manager     v0.1||\n";
    static String startScreenln4 = "==============================\n";


    public static void main(String[] args) throws InputException, GameException {

        Scanner sc = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler();
        System.out.println(startScreenln0 + startScreenln1 + startScreenln2 +  startScreenln3 + startScreenln4);
        System.out.println("Welcome to Game Collection Manager!");
        while (true){
            System.out.println(
                    "Please enter a command: \"add\" / \"view\" / \"delete\" / \"update\"");
                try{
                    String input = sc.nextLine();
                    inputHandler.handleInput(input);
                }catch (GameException e){
                    System.out.println(e);
                }catch (InputException e){
                    System.out.println(e);
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("You caused an ArrayIndexOutOfBoundsException! Please enter all necessary fields.");
                }catch (NumberFormatException e){
                    //System.out.println("You caused a NumberFormatException! Please enter a number for Play Time");
            }
        }
    }
}