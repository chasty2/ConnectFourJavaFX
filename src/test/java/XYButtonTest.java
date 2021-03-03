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

	@BeforeEach
	public void initJFX() throws Exception {
	  Thread t = new Thread("JavaFX Application Thread") {
	     public void run() {
	        Application.launch(NonApp.class);
	     }
	  };
	  t.setDaemon(true);
	  t.start();
	}
	
	@BeforeEach
	void setup()
	{
		// Init javafx toolkit
		com.sun.javafx.application.PlatformImpl.startup(() -> {});
		// Init two XYButtons
		button1 = new XYButton(0,0);
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
		assertEquals(0, button1.getX(),"button1 getX() failed");
		assertEquals(5, button2.getX(),"button2 getX() failed");
	}
	
	@Test
	void testGetY()
	{
		assertEquals(0, button1.getY(),"button1 getY() failed");
		assertEquals(5, button2.getY(),"button2 getY() failed");
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
	 */
	@Test
	void testSetX()
	{
		button1.setX(1);
		assertEquals(1, button1.getX(), "button1.setX() failed");
		button2.setX(1);
		assertEquals(1, button2.getX(), "button2.setX() failed");
	}
	
	@Test
	void testSetY()
	{
		button1.setY(1);
		assertEquals(1, button1.getY(), "button1.setY() failed");
		button2.setY(1);
		assertEquals(1, button2.getY(), "button2.setY() failed");
	}
	
	@Test
	void testSetValidWhenTrue()
	{
		button2.setValid();
		assertTrue(button2.getValid(),"button2.setValid() failed");
	}
	
	@Test
	void testSetValidWhenFalse()
	{
		button1.setValid();
		assertTrue(button1.getValid(),"button1.setValid() failed");
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

}
