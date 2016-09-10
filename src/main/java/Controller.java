package main.java;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import tetris.View;

public class Controller {
	
	private View ui;
	
	public Controller(View ui){
		this.ui = ui;
	}
	
	public void startGame(int speed){
		Tick timer = new Tick(2/speed, new EventHandler<ActionEvent>(){
			@Override
			public  void handle(ActionEvent e){
				lowerTetromino();
			}
		});
		boolean alive = true;
		while(alive){
			// TODO game logic
		}
	}
	
	private void lowerTetromino(){
		// check if current tetromino can be lowered
		// if so, lower it
		// else, create new tetromino
	}
	
	private void dropNewTetromino(){
		// check if there is room
		// if not, end game
		// else, pick random new tetromino and drop tetromino
	}
}
