import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import javafx.application.Application;
import javafx.stage.Stage;

class XYButtonTest 
{
	private static XYButton button1;
	private static XYButton button2;
	
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
		
		// Inits as invalid
		button1 = new XYButton(0,0);
		// Inits as valid
		button2 = new XYButton(5,5);
		// Link button1 to button2
		button1.setNext(button2);
	}
	
	/*
	 *  Test Constructor
	 */
	@Test
	void testConstructor() 
	{
		assertEquals("XYButton", button1.getClass().getName(),
				"button1 constructor failed");
		assertEquals("XYButton", button2.getClass().getName(),
				"button2 constructor failed");
	}
	
	/*
	 * Test XYButton Getters
	 */
	@Test
	void testGetX()
	{
		assertEquals(0, button1.getRow(),"button1 getRow() failed");
		assertEquals(5, button2.getRow(),"button2 getRow() failed");
	}
	
	@Test
	void testGetY()
	{
		assertEquals(0, button1.getColumn(),"button1 getY() failed");
		assertEquals(5, button2.getColumn(),"button2 getY() failed");
	}
	
	@Test
	void testGetValidWhenTrue()
	{
		assertTrue(button2.getValid(),"button2.getValid() failed");
	}
	
	@Test
	void testGetValidWhenFalse()
	{
		assertFalse(button1.getValid(), "button1.getValid() failed");
	}
	
	@Test
	void testGetPlayer()
	{
		assertEquals(0, button1.getPlayer(), "button1.getPlayer() failed");
		assertEquals(0, button2.getPlayer(), "button2.getPlayer() failed");
	}
	
	@Test
	void testGetNextWhenNotNull()
	{
		assertEquals(button2,button1.getNext(), "button1.getNext() failed");
	}
	
	@Test
	void testGetNextWhenNull()
	{
		assertNull(button2.getNext(),"button2.getNext() failed");
	}
	
	/*
	 * Test XYButton Setters
	 * 
	 * NOTE: SetColor is not tested here, as it is outside of the scope
	 *       of this assignment
	 */
	@Test
	void testSetX()
	{
		button1.setRow(1);
		assertEquals(1, button1.getRow(), "button1.setRow() failed");
		button2.setRow(1);
		assertEquals(1, button2.getRow(), "button2.setRow() failed");
	}
	
	@Test
	void testSetY()
	{
		button1.setY(1);
		assertEquals(1, button1.getColumn(), "button1.setY() failed");
		button2.setY(1);
		assertEquals(1, button2.getColumn(), "button2.setY() failed");
	}
	
	@Test
	void testSetValidWhenTrue()
	{
		button2.setValid(true);
		assertTrue(button2.getValid(),"button2.setValid(true) failed");
	}
	
	@Test
	void testSetValidWhenFalse()
	{
		button1.setValid(true);
		assertTrue(button1.getValid(),"button1.setValid(true) failed");
	}
	
	@Test
	void testSetPlayerToOne()
	{
		button1.setPlayer(1);
		assertEquals(1, button1.getPlayer(), "button1.setPlayer() failed");
		button2.setPlayer(1);
		assertEquals(1, button2.getPlayer(), "button2.setPlayer() failed");
	}
	
	@Test
	void testSetPlayerToTwo()
	{
		button1.setPlayer(2);
		assertEquals(2, button1.getPlayer(), "button1.setPlayer() failed");
		button2.setPlayer(2);
		assertEquals(2, button2.getPlayer(), "button2.setPlayer() failed");
	}
	
	@Test
	void testSetNextToNull()
	{
		button1.setNext(null);
		assertNull(button1.getNext(),"button1.setNext() failed");
		button2.setNext(null);
		assertNull(button2.getNext(),"button2.setNext() failed");
	}
	
	@Test
	void testSetNextToValid()
	{
		button1.setNext(button2);
		assertEquals(button2, button1.getNext(),"button1.setNext() failed");
		button2.setNext(button1);
		assertEquals(button1, button2.getNext(),"button2.setNext() failed");
	}
	
	/*
	 * Test button Press/unPress logic
	 * 
	 * Note that button validity is checked in button event handler, and
	 * is not tested here
	 */
	@Test
	void testPressButtonPlayerOne()
	{
		button1.press(1);
		assertFalse(button1.getValid(), 
				"button1 not set to invalid on press");
		assertTrue(button1.getNext().getValid(),
				"button1 did not set button2 to valid on press");
		assertEquals("-fx-background-color: red;", button1.getStyle(),
				"button1.press() set invalid color");
		assertEquals(1, button1.getPlayer(),
				"button1.press() set invalid player");
	}
	
	@Test
	void testPressButtonPlayerTwo()
	{
		button1.press(2);
		assertFalse(button1.getValid(), 
				"button1 not set to invalid on press");
		assertTrue(button1.getNext().getValid(),
				"button1 did not set button2 to valid on press");
		assertEquals("-fx-background-color: blue;", button1.getStyle(),
				"button1.press() set invalid color");
		assertEquals(2, button1.getPlayer(),
				"button1.press() set invalid player");
	}
	
	@Test
	void testUnPressButton()
	{
		button1.press(1);
		button1.unPress();
		assertTrue(button1.getValid(), 
				"button1 not set to valid on unPress()");
		assertFalse(button1.getNext().getValid(),
				"button1 did not set button2 to false on unPress()");
		assertEquals("-fx-background-color: grey;", button1.getStyle(),
				"button1.unPress() set invalid color");
		assertEquals(0, button1.getPlayer(),
				"button1.unPress() did not unset player");
	}
}
