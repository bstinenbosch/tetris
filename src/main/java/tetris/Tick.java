package tetris;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

class Tick extends Thread {

    private long time;
    private EventHandler<ActionEvent> onTick;
    public volatile boolean running;
    public volatile boolean waiting;

    /**
     * Tick is a timer class. No clue why this isn't a default Java library
     * dingetje.
     *
     * @param event
     *            the event that is fired at the moment of tick
     * @param time
     *            the time in milliseconds between each tick
     */
    public Tick(EventHandler<ActionEvent> event, long time) {
	this.onTick = event;
	running = true;
	waiting = false;
	this.time = time;
    }

    public Tick(EventHandler<ActionEvent> event) {
	this(event, 200);
    }

    /**
     * run is the code that is executed once the thread is started.
     */
    public void run() {
	running = true;
	while (running) {
	    try {
		if (!waiting) {
		    onTick.handle(new ActionEvent());
		}
		sleep(this.time);
	    } catch (InterruptedException ie) {
		ie.printStackTrace();
	    }
	}
    }

    /**
     * requestStop halts the thread in a clean fashion.
     */
    void requestStop() {
	running = false;
    }

    /**
     * pause pauses the handling of events.
     */
    public void pause() {
	waiting = true;
    }

    /**
     * unpause unpauses the handling of events.
     */
    public void unpause() {
	waiting = false;
    }

    /**
     * accessor method for the set time of the tick.
     *
     * @return the set time
     */
    public long getTime() {
	return time;
    }

    /**
     * setter method for the tick time.
     *
     * @param time
     *            time
     */
    public void setTime(long time) {
	this.time = time;
    }
}
