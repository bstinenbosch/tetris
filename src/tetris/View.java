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
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import main.java.Controller;
import javafx.scene.paint.Color;

/**
 * MainScreen is the class containing all the GUI-related stuff.
 * @author ms
 *
 */

public class View extends Application{
	private static final int BLOCK_SIZE = 20;
	private static final int BOARD_WIDTH = 10;
	private static final int BOARD_HEIGHT = 24;
	private static final int CORNER = 3;
		    
	/**
	 * start inits the application and displays a loading screens
	 * @param primaryStage is the root object containing the application.
	 */
    @Override
    public void start(Stage primaryStage) {
    	Label titleLabel = new Label("TETRIS");
    	titleLabel.setStyle("-fx-font-size:250%; -fx-text-fill:white");
        Button startNewGameButton = new Button("Start new game");
        startNewGameButton.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                gotoGameScreen(primaryStage);
            }
        });
        startNewGameButton.setStyle("-fx-background-color: red");
        
        Slider speedSlider = new Slider();
        Label annotationLabel = new Label("de level slider is er als voorbeeld/filler.");
        annotationLabel.setStyle("-fx-text-fill:white");
        
        TilePane rootStartScreen = new TilePane(Orientation.VERTICAL);    
        rootStartScreen.setTileAlignment(Pos.CENTER);
        rootStartScreen.getChildren().addAll(titleLabel, startNewGameButton, speedSlider, annotationLabel);
        rootStartScreen.setStyle("-fx-background-color: black");

        Scene startScreen = new Scene(rootStartScreen);
        //startScreen.getStylesheets().add("tetris/stylesheet.css");
        

        primaryStage.setTitle("Tetris");
        primaryStage.setScene(startScreen);
        primaryStage.show();
    }
    
    /**
     * gotoGameScreen inits the game screen
     * @param primaryStage
     */
 public void gotoGameScreen(Stage primaryStage){     
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
     rootGameScreen.setHgap(10);
     GridPane.setConstraints(pane, 0, 0);
     GridPane.setConstraints(exitButton, 1, 0);
     rootGameScreen.getChildren().addAll(pane, exitButton);

     GraphicsContext board = canvas.getGraphicsContext2D();
     //drawShape(board, new Tetromino());

     Scene gameScreen = new Scene(rootGameScreen);
     primaryStage.setScene(gameScreen);
     
     Controller controller = new Controller(this, BOARD_WIDTH, BOARD_HEIGHT);
     controller.start();
     
 }
 
 public static void drawRectangle(GraphicsContext board, int[] coordinate){
	 // TODO kleur random maken of juist iets anders mee doen
     board.setFill(Color.AQUA);
     board.setStroke(Color.BLUE);
     board.setLineWidth(BLOCK_SIZE/10.);
	 board.fillRoundRect(coordinate[0]*BLOCK_SIZE, coordinate[1]*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, CORNER, CORNER);
	 board.strokeRoundRect(coordinate[0]*BLOCK_SIZE, coordinate[1]*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, CORNER, CORNER);	 
 }
 
 public static void drawTetromino(GraphicsContext board, Tetromino tetromino){
	 for(int i = 0; i<4; i++){
		 drawRectangle(board, tetromino.get(i));
	 }	 
 }
    
 public static void main(String[] args) {
        launch(args);
    }
}
