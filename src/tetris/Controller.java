package tetris;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.lang.*;

import javax.swing.JFrame;

public class Controller extends Canvas implements Runnable {

    public static final int WIDTH = 400, HEIGHT = 565;

    public static void main(String args[]) {
        SetFrameAndKey();

    }
    
    public static void SetFrameAndKey() {
        final JFrame frame = new JFrame("Tetris");					//makes frame
        frame.setSize(WIDTH, HEIGHT);                             	//sets
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    	//makes sure program will close when you press the X
        frame.setLocationRelativeTo(null);							//puts the program in the middle of the screen
        frame.setResizable(false);									//makes sure the user cannot resize the program
        Controller tm = new Controller();							//makes the object controller
        frame.add(tm);												//adds the controller to the frame
        frame.setVisible(true);										//makes the frame visible
        tm.start();											 		//starts the controller	
        tm.addKeyListener(new TetrisKeyListener()); 				//Adds the KeyListener

    }

    public void start() {											//method to make the game start
        Thread t = new Thread(this);								//implements runnable								
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();													//calls the method whatever you pass into the thread
    }
    

    public void run() {
        boolean running = true;
        while (running) {
            update();
            BufferStrategy buf = getBufferStrategy();
            if (buf == null) {
                createBufferStrategy(3);
                continue;
            }
            Graphics2D g = (Graphics2D) buf.getDrawGraphics();
            render(g);
            buf.show();
        }
    }


    public void update() {

    }

    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);
        g.drawString("TETRIS", 170, 50);
    }
}
