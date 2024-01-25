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
    public void addGameNFE(){
        // Arrange
        String testTitle = "Golden Sun";
        String testPlatform = "Nintendo Switch";
        String testGenre = "RPG";
        String testPlayTime = "abcd";
        LocalDate testDateAdded = LocalDate.now();
        // Act
        try{
            Double testPlayTimeAsDouble = Double.parseDouble(testPlayTime);
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
        // List should be empty because the game was deleted
        Assert.assertTrue(gameCollectionService.getGameCollection().isEmpty());
    }

}
