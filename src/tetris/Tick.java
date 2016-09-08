package tetris;

public class Tick extends Thread {

    private long time = 0;

    private boolean ticking = true;

    public Tick(long time) {
        this.time = time;
    }

    public void run() {
        while (this.ticking) {
            try {
                sleep(this.time);
                System.out.println("tick");
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
