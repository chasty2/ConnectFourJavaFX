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
import javafx.scene.Node;

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
		XYButton prev = null;
		// Build  simplified gameBoard
		for(int column = 0; column<7; column++) 
		{
			for(int row = 0; row<6; row++) 
			{
				// Init XYButton
				XYButton b = new XYButton(row, column);		
				// Link b to XYButton 'above' it, or null
				b.setNext(prev);
				// Add button to board
				board.add(b, column, row);
				// Set prev to b for LL construction
				prev = b;
			}
		}
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
	
	/*
	 * Test button access
	 */
	@Test
	void testGetMove()
	{
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
		// Test getMove() with invalid addresses
		XYButton button = game.getMove(board,-1,4);
		assertNull(button, "button.getMove() failed on invalid input");
		
		button = game.getMove(board, 3, -1);
		assertNull(button, "button.getMove() failed on invalid input");
		
		button = game.getMove(board, -2, -2);
		assertNull(button, "button.getMove() failed on invalid input");
	}
	
	/*
	 * Test Event Log Behavior
	 */
	@Test
	void testGetMoveList()
	{
		assertEquals("com.sun.javafx.collections.ObservableListWrapper", 
				game.getMoveList().getClass().getName(),
				"game.getMoveList() failed");
	}
	
	@Test
	void testAddValidMoveToLogs()
	{
		game.addMoveToLogs(game.getMove(board,5,2));
		assertTrue(game.getMoveList().contains("Player 1 pressed 5, 2"),
				"game.addMoveToLogs() failed on valid entry");
		assertTrue(game.getMoveList().contains("Player 1 is up"),
				"game.addMoveToLogs() failed on invalid entry");
	}
	
	@Test
	void testAddInvalidMoveToLogs()
	{
		game.addMoveToLogs(game.getMove(board,3,2));
		assertTrue(game.getMoveList().contains(
				"3, 2 is an invalid move. Try again"),
				"game.addMoveToLogs() failed on invalid entry");
		assertTrue(game.getMoveList().contains("Player 1 is up"),
				"game.addMoveToLogs() failed on invalid entry");
	}
	
	@Test
	void testPruneLogsWithOneValidEntry()
	{
		// Adding changeTurn as they both happen on button press
		game.addMoveToLogs(game.getMove(board,5,2));
		game.changeTurn();
		// Prune move
		Integer[] move = game.pruneLogs();
		assertEquals(5, move[0], "game.pruneLogs() gave invalid row");
		assertEquals(2, move[1], "game.pruneLogs() gave invalid column");
		assertFalse(game.getMoveList().contains("Player 1 pressed 5, 2"),
				"game.pruneLogs() didn't remove log entry");
	}
	
	@Test
	void testPruneLogsWithTwoInvalidEntries()
	{
		// Adding changeTurn as they both happen on button press
		game.addMoveToLogs(game.getMove(board,5,2));
		game.changeTurn();
		game.addMoveToLogs(game.getMove(board,5,3));
		// Prune first move
		Integer[] move = game.pruneLogs();
		assertEquals(5, move[0], "game.pruneLogs() gave invalid row");
		assertEquals(3, move[1], "game.pruneLogs() gave invalid column");
		assertFalse(game.getMoveList().contains("Player 2 pressed 5, 3"),
				"game.pruneLogs() didn't remove log entry");
		assertTrue(game.getMoveList().contains("Player 1 pressed 5, 2"),
				"game.pruneLogs() removed too many log entries");
		// Prune second move
		move = game.pruneLogs();
		assertEquals(5, move[0], "game.pruneLogs() gave invalid row on 2nd prune");
		assertEquals(2, move[1], "game.pruneLogs() gave invalid column on 2nd prune");
		assertFalse(game.getMoveList().contains("Player 1 pressed 5, 2"),
				"game.pruneLogs() didn't remove log entry on second prune");
	}
	
	@Test
	void testPruneLogsWithOneValidOneInvalidEntry()
	{
		// Adding changeTurn as they both happen on button press
		game.addMoveToLogs(game.getMove(board,5,1));
		game.changeTurn();
		//Invalid entry
		game.addMoveToLogs(game.getMove(board,4,2));
		// Prune move
		Integer[] move = game.pruneLogs();
		assertEquals(5, move[0], "game.pruneLogs() gave invalid row");
		assertEquals(1, move[1], "game.pruneLogs() gave invalid column");
		assertFalse(game.getMoveList().contains("Player 1 pressed 5, 1"),
				"game.pruneLogs() didn't remove log entry");
	}
	
	/*
	 * Test Move Reversals
	 */
	@Test
	void testReverseMove()
	{
		// Simulate button press
		XYButton button = game.getMove(board, 5, 1);
		game.addMoveToLogs(button);
		button.press(game.getCurrentPlayer());
		game.changeTurn();
		// Reverse move and test
		game.reverseMove(board);
		assertTrue(button.getValid(), 
				"button not set to valid on reverseMove()");
		assertFalse(button.getNext().getValid(),
				"Did not set next button to invalid on reverseMove()");
		assertEquals("-fx-background-color: grey;", button.getStyle(),
				"game.reverseMove() set invalid button color");
		assertEquals(0, button.getPlayer(),
				"game.reverseMove() did not unset player in button");
	}
	
	@Test
	void testClearBoard()
	{
		// Simulate 3 button presses
		XYButton button1 = game.getMove(board, 5, 1);
		game.addMoveToLogs(button1);
		button1.press(game.getCurrentPlayer());
		game.changeTurn();
		XYButton button2 = game.getMove(board, 4, 1);
		game.addMoveToLogs(button2);
		button2.press(game.getCurrentPlayer());
		game.changeTurn();
		XYButton button3 = game.getMove(board, 3, 1);
		game.addMoveToLogs(button3);
		button3.press(game.getCurrentPlayer());
		game.changeTurn();
		// Clear board and test each button
		game.clearBoard(board);
		
		assertTrue(button1.getValid(), 
				"button not set to valid on clearBoard()");
		assertFalse(button1.getNext().getValid(),
				"Did not set next button to invalid on clearBoard()");
		assertEquals("-fx-background-color: grey;", button1.getStyle(),
				"game.clearBoard() set invalid button color");
		assertEquals(0, button1.getPlayer(),
				"game.clearBoard() did not unset player in button");
		
		assertFalse(button2.getValid(), 
				"button not set to valid on clearBoard()");
		assertEquals("-fx-background-color: grey;", button2.getStyle(),
				"game.reverseMove() set invalid button color");
		assertEquals(0, button2.getPlayer(),
				"game.reverseMove() did not unset player in button");
		
		assertFalse(button3.getValid(), 
				"button not set to valid on clearBoard()");
		assertEquals("-fx-background-color: grey;", button3.getStyle(),
				"game.reverseMove() set invalid button color");
		assertEquals(0, button3.getPlayer(),
				"game.reverseMove() did not unset player in button");	
	}
	
	/*
	 * Test enable/disable buttons
	 */
	@Test
	void testDisableButtons()
	{
		XYButton button = null;
		game.disableButtons(board);
		for (Node node : board.getChildren())
		{
			button = (XYButton) node;
			assertTrue(button.isDisable(),"game.disableButtons(board) failed");
		}
	}
	
	@Test
	void testEnableButtons()
	{
		XYButton button = null;
		game.enableButtons(board);
		for (Node node : board.getChildren())
		{
			button = (XYButton) node;
			assertFalse(button.isDisable(),"game.enableButtons(board) failed");
		}
	}
	
	/*
	 * Test end-game checks that return false due to
	 * 'else if (button.getPlayer() != getCurrentPlayer())'
	 */
	@Test
	void testCheckWinLeftNoWin()
	{
		assertFalse(game._checkWinLeft(board, 1, game.getMove(board, 5, 3)), 
				"game._checkWinLeft failed with no win");
	}
	
	@Test
	void testCheckWinRightNoWin()
	{
		assertFalse(game._checkWinRight(board, 1, game.getMove(board, 5, 3)), 
				"game._checkWinRight failed wit no win");
	}
	
	@Test
	void testCheckWinUpNoWin()
	{
		assertFalse(game._checkWinUp(board, 1, game.getMove(board, 5, 3)), 
				"game._checkWinLeft failed with no win");
	}
	
	@Test
	void testCheckWinDownNoWin()
	{
		assertFalse(game._checkWinDown(board, 1, game.getMove(board, 2, 3)), 
				"game._checkWinRight failed wit no win");
	}
	
	
} // End GameLogicTest