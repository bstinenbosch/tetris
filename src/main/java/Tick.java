package main.java;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Tick extends Thread {

    private long time = 0;

    private boolean ticking = true;
    private EventHandler<ActionEvent> onTick;
    
    public Tick(long time, EventHandler<ActionEvent> event) {
        this.time = time;
        this.onTick = event;
    }
    
    public void setOnTick(EventHandler<ActionEvent> event){
    	onTick = event;
    }
    
    public void run() {
        while (this.ticking) {
            try {
                sleep(this.time);
                System.out.println("tick");
                onTick.handle(new ActionEvent());
            } catch (InterruptedException ie) {
                ie.printStackTrace();
                this.ticking = false;
            }
        }
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
