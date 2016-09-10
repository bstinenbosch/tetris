package main.java;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Tick extends Thread {

    private long time = 500;
    private EventHandler<ActionEvent> onTick;
    public volatile boolean running;
    
    public Tick(EventHandler<ActionEvent> event) {
        this.onTick = event;
    }
    
    public void setOnTick(EventHandler<ActionEvent> event){
    	onTick = event;
    }
    
    public void run() {
        while (running) {
            try {
                onTick.handle(new ActionEvent());
                System.out.println("tick");
                sleep(this.time);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
    
    public void requestStop(){
    	running = false;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
