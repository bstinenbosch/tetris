//package test.java;
//
//import org.junit.Test;
//
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import main.java.Controller;
//import main.java.View;
//
//import static org.junit.Assert.assertTrue;
//
//public class ControllerTest {
//	@Test
//	public void tests(){
//		assertTrue(runGame());
//	}
//	
//	public boolean runGame(){
//		View view = new View();
//		view.launch();
//		Controller c = new Controller(new View(), new Canvas().getGraphicsContext2D(), 10, 20);
//		c.startGame();
//		return true;
//	}
//
//}
