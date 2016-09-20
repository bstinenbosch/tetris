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
import tetris.tetromino.AbstractTetromino;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * MainScreen is the class containing all the GUI-related stuff.
 * Here we draw up the different screens and hook the key events.
 */
public class View extends Application {

    private static final int BLOCK_SIZE = 20;
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 20;
    private static final int CORNER = 3;
    private Controller controller;
    private Stage primaryStage;

    /**
     * start inits the application and displays a loading screen
     *
     * @param primaryStage is the root object containing the application.
     */
    @Override
    public void start(Stage primaryStage) {
        controller = new Controller(this);
        this.primaryStage = primaryStage;
        gotoLauncher();
        primaryStage.setTitle("Tetris");
        primaryStage.show();
    }

    /**
     * gotoLauncher navigates to the launcher and contains its definition.
     */
    private void gotoLauncher() {
        Label titleLabel = new Label("TETRIS");
        titleLabel.setStyle("-fx-font-size:250%; -fx-text-fill:white");
        Button startNewGameButton = new Button("Start new game");
        startNewGameButton.setOnAction(event -> {
            //TODO board_width en board_height uitlezen uit size sliders oid
            controller.startGame(BOARD_WIDTH, BOARD_HEIGHT);
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

        primaryStage.setScene(startScreen);
    }

    /**
     * gotoGameScreen inits and shows the game screen,
     * hooks the key events and fires up the game controller.
     */
	 public GraphicsContext gotoGameScreen(){     
	     Canvas canvas = new Canvas(BLOCK_SIZE*BOARD_WIDTH, BLOCK_SIZE*BOARD_HEIGHT);
	     GraphicsContext board = canvas.getGraphicsContext2D();
	     
	     Pane leftPane = new Pane();    
	     leftPane.getChildren().add(canvas);    
	     leftPane.setStyle("-fx-background-color: grey");
	
	     Button exitButton = new Button("exit");
	     exitButton.setOnAction(new EventHandler<ActionEvent>(){
	    	 @Override
	    	 public void handle(ActionEvent event){
	    		 System.out.println("aan het afsluiten"); // debugging purposes
	    		 controller.stop();
	    	 }
	     });
	     Button restartButton = new Button("restart");
	     restartButton.setOnAction(new EventHandler<ActionEvent>(){
	    	 @Override
	    	 public void handle(ActionEvent event){
	    		 	controller.restartGame();   
	    	 }
	     });
	
	     GridPane rightPane = new GridPane();  
	     rightPane.setVgap(10);
	     GridPane.setConstraints(exitButton, 0, 0);
	     GridPane.setConstraints(restartButton, 0, 1);     
	     rightPane.getChildren().addAll(exitButton, restartButton);    
	     rightPane.setStyle("-fx-background-color: grey");
	          
	     GridPane rootGameScreen = new GridPane();
	     rootGameScreen.setHgap(10);
	     GridPane.setConstraints(leftPane, 0, 0);
	     GridPane.setConstraints(rightPane, 1, 0);     
	     rootGameScreen.getChildren().addAll(leftPane, rightPane);
	     rootGameScreen.setOnKeyPressed(controller.getOnKeyPressed());
	
	     Scene gameScreen = new Scene(rootGameScreen);
	     primaryStage.setScene(gameScreen);  
	     
	     gameScreen.getRoot().requestFocus();
	     
	     return board;
	 }
 
 
 /**
  * setColor sets the draw color pairs of the board according to predefined pairs. 
  * @param board the gameboard on which to set the colors
  * @param color the color pair ID
  */
 private void setColor(GraphicsContext board, int color){
	 switch(color){
	 case 1:
	     board.setFill(Color.LIGHTBLUE);
	     board.setStroke(Color.BLUE);
	     break;
	 case 2:
	     board.setFill(Color.LIGHTCORAL);
	     board.setStroke(Color.CORAL);
	     break;
	 case 3:
	     board.setFill(Color.LIGHTYELLOW);
	     board.setStroke(Color.YELLOW);
	     break;
	 case 4:
	     board.setFill(Color.LIGHTGREEN);
	     board.setStroke(Color.GREEN);
	     break;
	 case 5:
	     board.setFill(Color.LIGHTPINK);
	     board.setStroke(Color.PINK);
	     break;
	 case 6:
	     board.setFill(Color.LIGHTSALMON);
	     board.setStroke(Color.SALMON);
	     break;
	 default:
		 throw new IllegalArgumentException(String.format("Color %d is not a valid color number.", color));
	 } 
 }
 
	 /**
	  * drawGrid draws the entire gameboard. As tetrominos reach their final place, 
	  * they are registered on the grid to be drawn by this function.
	  * @param board the canvas to draw the gameboard on
	  * @param grid the gameboard to draw on the canvas
	  */
	 public void drawGrid(GraphicsContext board, Grid grid){
		 for(int x = 0; x<BOARD_WIDTH; x++){
			 for(int y = 0; y<BOARD_HEIGHT; y++){
				 drawRectangle(board,grid.get(x, y), new Coordinate(x,y));
			 }
		 }
	 }
	 
	 /**
	  * drawTetromino employs the structure of a tetromino to draw it on a gameboard.
	  * @param board the gameboard to draw the tetromino on
	  * @param tetromino the tetromino to draw
	  */
	 public void drawTetromino(GraphicsContext board, AbstractTetromino tetromino){	 
		 for(int i = 0; i<4; i++){
			 drawRectangle(board, tetromino.getColor(), tetromino.get(i));
		 }	 
	 }
	 
	 /**
	  * drawRectangle draws one cube on the game grid.
	  * @param board specifies the gameboard(canvas) to draw on
	  * @param color specifies the color pair to draw in (color pairs provided by setColor)
	  * @param coordinate the cube in the grid that is to be drawn.
	  */
	 private void drawRectangle(GraphicsContext board, int color, Coordinate coordinate){
		 if(color>0){
			 setColor(board, color);
		     board.setLineWidth(BLOCK_SIZE/10.);
			 board.fillRoundRect(coordinate.getX()*BLOCK_SIZE, (BOARD_HEIGHT-1-coordinate.getY())*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, CORNER, CORNER);
			 board.strokeRoundRect(coordinate.getX()*BLOCK_SIZE, (BOARD_HEIGHT-1-coordinate.getY())*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, CORNER, CORNER);
		 }
	 }
	 
	 /**
	  * clearBoard erases the current board so it can be redrawn.
	  * @param board
	  */
	 public void clearBoard(GraphicsContext board){
		 board.setFill(Color.BLACK);
		 board.fillRect(0, 0, BOARD_WIDTH*BLOCK_SIZE, BOARD_HEIGHT*BLOCK_SIZE);
	 }
	 
	 /**
	  * gameOver displays the "game over" text
	  * @param board the canvas to draw on
	  */
	 public void gameOver(GraphicsContext board){
		 board.setTextAlign(TextAlignment.CENTER);
		 board.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 20));
		 board.setFill(Color.RED);
		 board.fillText("GAME OVER", BOARD_WIDTH*BLOCK_SIZE/2, BOARD_HEIGHT*BLOCK_SIZE/2);
		 
	 }
	 
	 /**
	  * stop is called when the application terminates. Here any asynchronous threads should be terminated.
	  */
	 public void stop(){
		 primaryStage.close();
	 }
    
 	public static void main(String[] args) {
        launch(args);
    }
}
