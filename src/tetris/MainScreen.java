package tetris;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

/**
 * MainScreen is the class containing all the GUI-related stuff.
 * @author ms
 *
 */

public class MainScreen extends Application{
	private static final int BLOCK_SIZE = 20;
	private static final int BOARD_WIDTH = 10;
	private static final int BOARD_HEIGHT = 20;
	private static final int CORNER = 3;
		    
	/**
	 * start inits the application and displays a loading screens
	 * @param primaryStage is the root object containing the application.
	 */
    @Override
    public void start(Stage primaryStage) {
        Button startNewGameButton = new Button("Start new game");
        startNewGameButton.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Nu zou ik een spel moeten starten"); // debugging purposes
                gotoGameScreen(primaryStage);
            }
        });
        
        Slider speedSlider = new Slider();
        Label annotationLabel = new Label("de level slider is er als voorbeeld/filler.");
        
        TilePane rootStartScreen = new TilePane(Orientation.VERTICAL);    
        rootStartScreen.setTileAlignment(Pos.CENTER);
        rootStartScreen.getChildren().addAll(startNewGameButton, speedSlider, annotationLabel);

        Scene startScreen = new Scene(rootStartScreen);

        primaryStage.setTitle("Tetris");
        primaryStage.setScene(startScreen);
        primaryStage.show();
    }
    
    /**
     * gotoGameScreen inits the game screen
     * @param primaryStage
     */
 public static void gotoGameScreen(Stage primaryStage){     
     Canvas canvas = new Canvas(BLOCK_SIZE*BOARD_WIDTH, BLOCK_SIZE*(BOARD_HEIGHT+4));
     
     Pane pane = new Pane();    
     pane.getChildren().add(canvas);    
     pane.setStyle("-fx-background-color: grey");
     
     Button exitButton = new Button("exit");
     exitButton.setOnAction(new EventHandler<ActionEvent>(){
    	 @Override
    	 public void handle(ActionEvent event){
    		 System.out.println("aan het afsluiten"); // debugging purposes
    		 primaryStage.close();
    	 }
     });
          
     GridPane rootGameScreen = new GridPane();
     GridPane.setConstraints(pane, 0, 0);
     GridPane.setConstraints(exitButton, 1, 0);
     rootGameScreen.getChildren().addAll(pane, exitButton);

     GraphicsContext board = canvas.getGraphicsContext2D();
     drawRectangle(board);

     Scene gameScreen = new Scene(rootGameScreen);
     primaryStage.setScene(gameScreen);
 }
 
 public static void drawRectangle(GraphicsContext board){
	 drawRectangle(board, new Coordinate(0,0));
 }
 
 public static void drawRectangle(GraphicsContext board, Coordinate coordinate){
     board.setFill(Color.AQUA);
     board.setStroke(Color.BLUE);
     board.setLineWidth(BLOCK_SIZE/10.);
	 board.fillRoundRect(coordinate.getX(), coordinate.getY(), BLOCK_SIZE, BLOCK_SIZE, CORNER, CORNER);
	 board.strokeRoundRect(coordinate.getX(), coordinate.getY(), BLOCK_SIZE, BLOCK_SIZE, CORNER, CORNER);	 
 }
 
 public static void drawShape(Tetromino tetromino){
	 //TODO aan board komen
	 for(Coordinate block: tetromino){
		 drawRectangle(board, block);
	 }	 
 }
    
 public static void main(String[] args) {
        launch(args);
    }
}
