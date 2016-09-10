package main.java;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import tetris.Tetromino;
import tetris.View;

public class Controller extends Thread{
	
	private View ui;
	private Grid grid;
	private Tetromino tetromino;
	private Tick timer = new Tick(new EventHandler<ActionEvent>(){
		@Override
		public  void handle(ActionEvent e){
			lowerTetromino();
		}
	});
	
	public Controller(View ui, int width, int height){
		this.ui = ui;
		this.grid = new Grid(width, height);		
	}
	
	public void startGame(){
		dropNewTetromino();
		timer.start();
	}
	
	private void lowerTetromino(){
		// check if current tetromino can be lowered
		if (tetromino.bottom() == 0){
			dropNewTetromino();
		}else if(tryToLower()){
		// if so, lower it			
		} else if(tetromino.top() >= grid.height()-1 ){
			// tetromino is in top position and cannot be lowered, so game over
			gameOver();
		} else {
		// else, register tetromino on grid and create new tetromino
			grid.registerTetromino(tetromino);
			dropNewTetromino();
		}
		// TODO draw grid
		// TODO draw tetromino
	}
	
	private void dropNewTetromino(){
		tetromino = new Tetromino(grid.width()/2,grid.height());
		// pick random new tetromino and drop tetromino
	}
	
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
	
	private void gameOver(){
		timer.requestStop();
	}
}
