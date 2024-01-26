import org.example.Exception.GameException;
import org.example.Model.Game;
import org.example.Service.GameCollectionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
public class GameCollectionServiceTest {

    GameCollectionService gameCollectionService;

    // Instantiate new service before each test
    @Before
    public void setUp(){
        gameCollectionService = new GameCollectionService();
    }

    // Before adding any games, list is empty
    @Test
    public void emptyAtStart(){
        List<Game> gameList = gameCollectionService.getGameCollection();
        Assert.assertTrue(gameList.isEmpty());
    }

    // Before adding any games, list is empty
    @Test
    public void addGame(){
    // Arrange
        String testTitle = "Golden Sun";
        String testPlatform = "Nintendo Switch";
        String testGenre = "RPG";
        String testPlayTime = "23.7";
        Double testPlayTimeAsDouble = Double.parseDouble(testPlayTime);
        LocalDate testDateAdded = LocalDate.now();
    // Act
        try{
            gameCollectionService.addGame(testTitle, testPlatform, testGenre, testPlayTime);
        } catch (GameException e) {
            e.printStackTrace();
            Assert.fail("Exception should not be thrown");
        }
    // Assert

        List<Game> gameList = gameCollectionService.getGameCollection();
        Game actual = gameList.get(0);
        Assert.assertEquals(testTitle, actual.getTitle());
        Assert.assertEquals(testPlatform, actual.getPlatform());
        Assert.assertEquals(testGenre, actual.getGenre());
        Assert.assertEquals(testPlayTimeAsDouble, actual.getPlayTime());
        Assert.assertEquals(testDateAdded, actual.getDateAdded());
    }

    // Add game where play time is not numerical
    // Exception expected
    @Test
    public void addGameNFE() throws NumberFormatException{
        // Arrange
        String testTitle = "Golden Sun";
        String testPlatform = "Nintendo Switch";
        String testGenre = "RPG";
        String testPlayTime = "abcd";
        LocalDate testDateAdded = LocalDate.now();
        // Act
        try{
            //Double testPlayTimeAsDouble = Double.parseDouble(testPlayTime);
            gameCollectionService.addGame(testTitle, testPlatform, testGenre, testPlayTime);
            Assert.fail("Exception should be thrown");
        } catch (NumberFormatException | GameException e) {
            e.printStackTrace();
        }
    }

    // Add game where title is empty
    // Exception expected
    @Test
    public void addGameEmptyTitle(){
        // Arrange
        String testTitle = "";
        String testPlatform = "Nintendo Switch";
        String testGenre = "RPG";
        String testPlayTime = "23.7";
        Double testPlayTimeAsDouble = Double.parseDouble(testPlayTime);
        LocalDate testDateAdded = LocalDate.now();
        // Act
        try{
            gameCollectionService.addGame(testTitle, testPlatform, testGenre, testPlayTime);
            Assert.fail("Exception should be thrown");
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    // Add game where play time is negative
    // Exception expected
    @Test
    public void addGameNegativePlayTime(){
        // Arrange
        String testTitle = "Golden Sun";
        String testPlatform = "Nintendo Switch";
        String testGenre = "RPG";
        String testPlayTime = "-5";
        Double testPlayTimeAsDouble = Double.parseDouble(testPlayTime);
        LocalDate testDateAdded = LocalDate.now();
        // Act
        try{
            gameCollectionService.addGame(testTitle, testPlatform, testGenre, testPlayTime);
            Assert.fail("Exception should be thrown");
        } catch (GameException e) {
            e.printStackTrace();
        }
    }
    // Add game where title is already in gameList
    // Exception expected
    @Test
    public void addGameDuplicateTitle(){
        // Arrange
        String testTitle = "Golden Sun";
        String testPlatform = "Nintendo Switch";
        String testGenre = "RPG";
        String testPlayTime = "23.7";
        Double testPlayTimeAsDouble = Double.parseDouble(testPlayTime);
        LocalDate testDateAdded = LocalDate.now();
        // Act
        try{
            gameCollectionService.addGame(testTitle, testPlatform, testGenre, testPlayTime);
            // second add should throw an exception
            gameCollectionService.addGame(testTitle, testPlatform, testGenre, testPlayTime);
            // should not get here
            Assert.fail("Exception should be thrown");
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    // Remove game from gameList
    @Test
    public void removeGame(){
        // Arrange
        String testTitle = "Golden Sun";
        String testPlatform = "Nintendo Switch";
        String testGenre = "RPG";
        String testPlayTime = "23.7";
        Double testPlayTimeAsDouble = Double.parseDouble(testPlayTime);
        LocalDate testDateAdded = LocalDate.now();
        // Act
        try{
            gameCollectionService.addGame(testTitle, testPlatform, testGenre, testPlayTime);
            gameCollectionService.removeGameByTitle(testTitle);

        } catch (GameException e) {
            e.printStackTrace();
            Assert.fail("Exception should not be thrown");
        }
        // Assert
        // List should be empty because the game was deleted
        Assert.assertTrue(gameCollectionService.getGameCollection().isEmpty());
    }

    // Remove game from gameList
    // Exception expected
    @Test
    public void tryRemoveGameWrongTitle(){
        // Arrange
        String testTitle = "Golden Sun";
        String testPlatform = "Nintendo Switch";
        String testGenre = "RPG";
        String testPlayTime = "23.7";
        Double testPlayTimeAsDouble = Double.parseDouble(testPlayTime);
        LocalDate testDateAdded = LocalDate.now();
        // Act
        try{
            gameCollectionService.addGame(testTitle, testPlatform, testGenre, testPlayTime);
            gameCollectionService.removeGameByTitle("Super Mario Party");
            Assert.fail("Exception should be thrown");
        } catch (GameException e) {
            e.printStackTrace();

        }
        // Assert
        // List should NOT be empty because the game was not deleted
        Assert.assertFalse(gameCollectionService.getGameCollection().isEmpty());
    }

    // check before update
    @Test
    public void prepUpdateGame(){
        // Arrange
        String testTitle = "Golden Sun";
        String testPlatform = "Nintendo Switch";
        String testGenre = "RPG";
        String testPlayTime = "23.7";
        Double testPlayTimeAsDouble = Double.parseDouble(testPlayTime);
        LocalDate testDateAdded = LocalDate.now();
        // Act
        try{
            gameCollectionService.addGame(testTitle, testPlatform, testGenre, testPlayTime);
            // Assert
            // Game should be returned
            Assert.assertTrue(gameCollectionService.prepUpdateGame(testTitle) != null);

        } catch (GameException e) {
            e.printStackTrace();
            Assert.fail("Exception should not be thrown");
        }
    }

    // check before update
    // Exception expected
    @Test
    public void prepUpdateGameNotFound(){
        // Arrange
        String testTitle = "Golden Sun";
        String testPlatform = "Nintendo Switch";
        String testGenre = "RPG";
        String testPlayTime = "23.7";
        Double testPlayTimeAsDouble = Double.parseDouble(testPlayTime);
        LocalDate testDateAdded = LocalDate.now();
        // Act
        try{
            gameCollectionService.addGame(testTitle, testPlatform, testGenre, testPlayTime);
            // Assert
            // Exception should be thrown since we try to update a game that is not in our list
            gameCollectionService.prepUpdateGame("Other Title");
            Assert.fail("Should not get to this line");

        } catch (GameException e) {
            e.printStackTrace();

        }
    }

    // update Title of game in list
    @Test
    public void updateTitle(){
        // Arrange
        String testTitle = "Golden Sun";
        String testPlatform = "Nintendo Switch";
        String testGenre = "RPG";
        String testPlayTime = "23.7";
        Double testPlayTimeAsDouble = Double.parseDouble(testPlayTime);
        LocalDate testDateAdded = LocalDate.now();
        
        Game testGame = new Game();
        String newTitle = "Super Mario RPG";
        // Act
        try{
            gameCollectionService.addGame(testTitle, testPlatform, testGenre, testPlayTime);
            testGame = gameCollectionService.getGameByTitle(testTitle);
            gameCollectionService.updateTitle(testGame, newTitle);

        } catch (GameException e) {
            e.printStackTrace();
            Assert.fail("Exception should not be thrown");
        }
        // Assert
        // Game title should match the new title
        Assert.assertTrue(testGame.getTitle().equals(newTitle));
    }

    // update Platform of game in list
    @Test
    public void updatePlatform(){
        // Arrange
        String testTitle = "Golden Sun";
        String testPlatform = "Nintendo Switch";
        String testGenre = "RPG";
        String testPlayTime = "23.7";
        Double testPlayTimeAsDouble = Double.parseDouble(testPlayTime);
        LocalDate testDateAdded = LocalDate.now();

        Game testGame = new Game();
        String newPlatform = "PC";
        // Act
        try{
            gameCollectionService.addGame(testTitle, testPlatform, testGenre, testPlayTime);
            testGame = gameCollectionService.getGameByTitle(testTitle);
            gameCollectionService.updatePlatform(testGame, newPlatform);

        } catch (GameException e) {
            e.printStackTrace();
            Assert.fail("Exception should not be thrown");
        }
        // Assert
        // Game Platform should match the new platform
        Assert.assertTrue(testGame.getPlatform().equals(newPlatform));
    }

    // update Genre of game in list
    @Test
    public void updateGenre(){
        // Arrange
        String testTitle = "Golden Sun";
        String testPlatform = "Nintendo Switch";
        String testGenre = "RPG";
        String testPlayTime = "23.7";
        Double testPlayTimeAsDouble = Double.parseDouble(testPlayTime);
        LocalDate testDateAdded = LocalDate.now();

        Game testGame = new Game();
        String newGenre = "Racing";
        // Act
        try{
            gameCollectionService.addGame(testTitle, testPlatform, testGenre, testPlayTime);
            testGame = gameCollectionService.getGameByTitle(testTitle);
            gameCollectionService.updateGenre(testGame, newGenre);

        } catch (GameException e) {
            e.printStackTrace();
            Assert.fail("Exception should not be thrown");
        }
        // Assert
        // Game genre should match the new genre
        Assert.assertTrue(testGame.getGenre().equals(newGenre));
    }

    // update play time of game in list
    @Test
    public void updatePlayTime(){
        // Arrange
        String testTitle = "Golden Sun";
        String testPlatform = "Nintendo Switch";
        String testGenre = "RPG";
        String testPlayTime = "23.7";
        Double testPlayTimeAsDouble = Double.parseDouble(testPlayTime);
        LocalDate testDateAdded = LocalDate.now();

        Game testGame = new Game();
        String newPlayTime = "82.04";
        Double newPlayTimeAsDouble = Double.parseDouble(newPlayTime);
        // Act
        try{
            gameCollectionService.addGame(testTitle, testPlatform, testGenre, newPlayTime);
            testGame = gameCollectionService.getGameByTitle(testTitle);
            gameCollectionService.updatePlayTime(testGame, newPlayTime);

        } catch (GameException e) {
            e.printStackTrace();
            Assert.fail("Exception should not be thrown");
        }
        // Assert
        // Game play time should match the new play time
        Assert.assertTrue(testGame.getPlayTime().equals(newPlayTimeAsDouble));
    }

}
