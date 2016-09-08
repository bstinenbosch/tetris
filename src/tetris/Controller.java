package tetris;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.*;
import java.lang.*;

import javax.swing.JFrame;

public class Controller extends Canvas implements Runnable{
	
	public static final int WIDTH = 400, HEIGHT = 565;
	
	public static void main(String args[]){
		final JFrame frame = new JFrame("Tetris");
		frame.setSize(WIDTH,HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		Controller tm = new Controller();
		frame.add(tm);
		frame.setVisible(true);
		tm.start();
		
		tm.addKeyListener(new TetrisKeyListener()); //Hiermee laad je de KeyListener in!!
	}
	
	public void start(){
		Thread t = new Thread(this);
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();
	}
	
	public void run(){
		boolean running = true;
		while(running){
			update();
			BufferStrategy buf = getBufferStrategy();
			if(buf == null){
				createBufferStrategy(3);
				continue;
			}
			Graphics2D g = (Graphics2D) buf.getDrawGraphics();
			render(g);
			buf.show();
		}
	}
	
	public void update(){
		
	}
	
	public void render(Graphics2D g){
		g.setColor(Color.BLACK);
		g.fillRect(0,0,WIDTH,HEIGHT);
		g.setColor(Color.WHITE);
		g.drawString("TETRIS", 170, 50);
	}
}
