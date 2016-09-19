package tetris;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import tetris.tetromino.AbstractTetromino;
import tetris.tetromino.TetrominoFactory;

public class Controller{
	
	private View ui;
	private Grid grid;
	private GraphicsContext board;
	private AbstractTetromino tetromino;
	private boolean gameOver = false;
	private EventHandler<ActionEvent> onTick = new EventHandler<ActionEvent>(){
		@Override
		public  void handle(ActionEvent e){
			lowerTetromino();
		}
	};
	private Tick timer = new Tick(onTick);
	private EventHandler<KeyEvent> onKeyPressed = new EventHandler<KeyEvent>(){
		@Override
		public void handle(KeyEvent event) {
			if (!gameOver){
				switch(event.getCode()){
				case DOWN:
	                checkRotateRight();
					break;
				case LEFT:
                    if(tetromino.left()>0){
                        tetromino.moveLeft();
                  }
                  for(int i=0; i<4; i++){
                         if(!grid.isFree(tetromino.get(i))){
                             tetromino.moveRight(); break;
                         }
                    }
                  break;

				case RIGHT:
                    if(tetromino.right()<grid.width()-1){
                        tetromino.moveRight();
                   }
                   for(int i=0; i<4; i++){
                          if(!grid.isFree(tetromino.get(i))){
                              tetromino.moveLeft(); break;
                          }
                     }
                   break;

				case UP:
	                checkRotateLeft();
					break;
				default:
					break;
				}
				redraw();
			}
		}    	 
     };
	
    /**
     * the Controller class determines the game flow and does the actual event handling.
     * @param ui the application in which the game is running
     * @param board the canvas on which we paint the gameboard
     * @param width the width of the gameboard
     * @param height the height of the gameboard
     */
	public Controller(View ui){//, GraphicsContext board, int width, int height){
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
		Coordinate position = new Coordinate(grid.width()/2, grid.height());
		tetromino = TetrominoFactory.createRandom(position);
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
		gameOver = true;
		System.out.println("game over");
		ui.gameOver(board);
	}
	
	/**
	 * stop handles asynchronous threads when the application is closed.
	 */
	public void stop(){
		timer.requestStop();
		ui.stop();
	}

	/**
	 * starts the game
	 */
	public void startGame(int width, int height){
		gameOver = false;
		board = ui.gotoGameScreen();
		grid = new Grid(width, height);
		dropNewTetromino();
		timer.start();
		System.out.println("started running");
	}
	
	public void restartGame(){
		grid.clearBoard();
		dropNewTetromino();
		System.out.println("started running again");		
	}
	
	/**
	 * public accessor for the key handle event object.
	 * @return
	 */
	public EventHandler<KeyEvent> getOnKeyPressed(){
		return onKeyPressed;
	}
	
    
    public boolean checkRotateRight(){
        tetromino.rotateRight();
        for(int i=0; i<4; i++){
           if(!grid.isFree(tetromino.get(i))){
               tetromino.rotateLeft();
               return false;
           }
        }
       return true;
   }
    public boolean checkRotateLeft(){
        tetromino.rotateLeft();
        for(int i=0; i<4; i++){
           if(!grid.isFree(tetromino.get(i))){
               tetromino.rotateRight();
               return false;
           }
        }
       return true;
    }
}
