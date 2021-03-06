import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

class GameLogicTest 
{
	private static GameLogic game;
	private static GridPane board;
	/*
	 *  Copied from Piazza, used to bypass ExceptionInInitializerError
	 *  by running a mock JavaFX application
	 */
	public static class NonApp extends Application 
	{
		@Override
		public void start(Stage primaryStage) throws Exception {
	    // do nothing here since we just need to start the JavaFX toolkit
	  }
	}

	@BeforeAll
	public static void initJFX() throws Exception {
	  Thread t = new Thread("JavaFX Application Thread") {
	     public void run() {
	        Application.launch(NonApp.class);
	     }
	  };
	  t.setDaemon(true);
	  t.start();
	  // Init javafx toolkit
	  com.sun.javafx.application.PlatformImpl.startup(() -> {});
	}
	
	@BeforeEach
	void setup()
	{
		game = new GameLogic();
		board = new GridPane();
	}
	
	/*
	 *  Test Constructor
	 */
	@Test
	void testConstructor()
	{
		assertEquals("GameLogic", game.getClass().getName(),
				"GameLogic onstructor failed");
	}
	
	/*
	 * Test functionality of turn management
	 */
	@Test
	void testGetCurrentPlayer()
	{
		// GameLogic initiates with currentPlayer = playerOne = 1
		assertEquals(1, game.getCurrentPlayer(), "game.getCurrentPlayer() failed");
	}
	
	@Test
	void testChangeTurn()
	{
		// Change to playerTwo and test
		game.changeTurn();
		assertEquals(2, game.getCurrentPlayer(), "game.changeTurn() failed");
		// Change back and test again
		game.changeTurn();
		assertEquals(1, game.getCurrentPlayer(), "game.changeTurn() failed");
	}
	
	@Test
	void testGetMove()
	{
		// Build  simplified gameBoard
		for(int column = 0; column<7; column++) 
		{
			for(int row = 0; row<6; row++) 
			{
				XYButton b = new XYButton(row, column);
				board.add(b, column, row);
			}
		}
		// Test XYButtons returned by getMove()
		XYButton button = game.getMove(board,1,1);
		assertEquals(1, button.getRow(), "button.getRow() failed");
		assertEquals(1, button.getColumn(), "button.getY() failed");
		
		button = game.getMove(board, 3, 6);
		assertEquals(3, button.getRow(), "button.getX() failed");
		assertEquals(6, button.getColumn(), "button.getY() failed");
		
		button = game.getMove(board, 5, 2);
		assertEquals(5, button.getRow(), "button.getX() failed");
		assertEquals(2, button.getColumn(), "button.getY() failed");
		
		// Test values at edge of the board
		button = game.getMove(board, 5, 6);
		assertEquals(5, button.getRow(), "button.getX() failed");
		assertEquals(6, button.getColumn(), "button.getY() failed");
	}
	
	@Test
	void testGetMoveWhenNull()
	{
		// Build  simplified gameBoard
		for(int column = 0; column<7; column++) 
		{
			for(int row = 0; row<6; row++) 
			{
				XYButton b = new XYButton(row, column);
				board.add(b, column, row);
			}
		}
		// Test getMove() with invalid addresses
		XYButton button = game.getMove(board,-1,4);
		assertNull(button, "button.getMove() failed on invalid input");
		
		button = game.getMove(board, 3, -1);
		assertNull(button, "button.getMove() failed on invalid input");
		
		button = game.getMove(board, -2, -2);
		assertNull(button, "button.getMove() failed on invalid input");
	}
}