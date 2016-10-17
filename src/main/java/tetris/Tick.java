package tetris;

import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

class Tick extends Thread implements Observer {

    private long time;
    private EventHandler<ActionEvent> onTick;
    public volatile boolean running;
    public volatile boolean waiting;
    private long basetime = 200;

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
        waiting = true;
        this.time = time;
    }

    public Tick(EventHandler<ActionEvent> event) {
        this.onTick = event;
        waiting = true;
        this.time = basetime;
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

    /**
     * reset the time to the starting value.
     */
    public void resetTime() {
        time = basetime;
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof Score) {
            this.time = (long) Math.max(1, basetime * Math.exp(-.0002 * (int) arg));
        }
    }
}
