package main.java.tetris;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class Controller{
	
	private View ui;
	private Grid grid;
	private GraphicsContext board;
	private Tetromino tetromino;
	private Tick timer = new Tick(new EventHandler<ActionEvent>(){
		@Override
		public  void handle(ActionEvent e){
			lowerTetromino();
		}
	});
	private EventHandler<KeyEvent> onKeyPressed = new EventHandler<KeyEvent>(){
		@Override
		public void handle(KeyEvent event) {
			switch(event.getCode()){
			case DOWN:
				tetromino.rotateRight();
				lowerTetromino();
				break;
			case LEFT:
				if(tetromino.left()>0){
					tetromino.moveLeft();
				}
				break;
			case RIGHT:
				if(tetromino.right()<grid.width()-1){
					tetromino.moveRight();
				}
				break;
			case UP:
				tetromino.rotateLeft();
				break;
			default:
				break;				
			}
			redraw();
		}    	 
     };
	
    /**
     * the Controller class determines the game flow and does the actual event handling.
     * @param ui the application in which the game is running
     * @param board the canvas on which we paint the gameboard
     * @param width the width of the gameboard
     * @param height the height of the gameboard
     */
	public Controller(View ui){
		this.ui = ui;
	}
	
	/**
	 * tries to lower a tetromino. If this is not possible, a new tetromino is launched or the game is over.
	 */
	private void lowerTetromino(){
		// check if current tetromino can be lowered
		if(tryToLower()){
		// if so, lower it	
			redraw();		
		} else if(tetromino.top() >= grid.height()-1 ){
			// tetromino is in top position and cannot be lowered, so game over
			grid.registerTetromino(tetromino);
			gameOver();
		} else {
		// else, register tetromino on grid and create new tetromino
			grid.registerTetromino(tetromino);
			dropNewTetromino();
		}
	}
	
	/**
	 * drops a new tetromino and makes sure that it is drawn on the canvas.
	 */
	private void dropNewTetromino(){
		grid.clearLines();
		tetromino = new Tetromino(grid.width()/2,grid.height());
		redraw();
		// pick random new tetromino and drop tetromino
	}
	
	/**
	 * tryToLower checks the lowerability of the tetromino and if possible lowers it.
	 * @return true on succes, false on failure to load.
	 */
	private boolean tryToLower(){
		tetromino.moveDown();
		//check lowerability
		for(int i=0; i<4; i++){
			if(!grid.isFree(tetromino.get(i))){
				tetromino.moveUp();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * redraw empties the canvas and redraws the gameboard and the current active tetromino
	 */
	private void redraw(){
		ui.clearBoard(board);
		ui.drawGrid(board, grid);
		ui.drawTetromino(board, tetromino);
	}
	
	/**
	 * gameOver handles the end of the game
	 */
	private void gameOver(){
		timer.requestStop();
		System.out.println("game over");
		ui.gameOver(board);
	}
	
	/**
	 * stop handles asynchronous threads when the application is closed.
	 */
	public void stop(){
		timer.requestStop();
		try {
			timer.join();
		} catch (InterruptedException e) {
			System.out.println("The controller thread got interrupted while waiting for the tick thread.");
			e.printStackTrace();
		}
		ui.stop();
	}
	
	/**
	 * starts the game
	 * @param width the width of the game board
	 * @param height the height of the game board
	 */
	public void startGame(int width, int height){
		grid = new Grid(width, height);	
		board = ui.gotoGameScreen();
		dropNewTetromino();
		timer.start();
		System.out.println("started running");
	}
	
	/**
	 * restarts the game
	 */
	public void restartGame(){
		grid.clearBoard();	
		dropNewTetromino();
		timer.start();
		System.out.println("restarted.");
		
	}
	
	/**
	 * public accessor for the key handle event object.
	 * @return
	 */
	public EventHandler<KeyEvent> getOnKeyPressed(){
		return onKeyPressed;
	}
	
	/**
	 * returns true if the game is over
	 * @return true if the game is over, else false
	 */
	public boolean isGameOver(){
		for(int x=0; x<grid.width(); x++){
			if(grid.get(x, grid.height())!=0){
				return true;
			}
		}
		return false;
	}
}
