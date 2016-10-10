package tetris.scenes;

import tetris.Controller;

public interface IScreen {

    /**
     * hookEvents ensures that all events of this screen fire the desired
     * methods from the controller object.
     * 
     * @param controller
     *            The controller object that contains all the methods that must
     *            be handled by the events.
     */
    public void hookEvents(Controller controller);
}