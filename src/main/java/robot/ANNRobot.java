package robot;

import java.util.Observable;

import tetris.Action;
import tetris.Grid;
import tetris.tetromino.AbstractTetromino;

import robot.ANN.AbstractNeuralNetwork;
import robot.ANN.RandomLinearNeuralNetwork;

public class ANNRobot implements IRobot {
    private AbstractNeuralNetwork network = new RandomLinearNeuralNetwork();

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setGameState(Grid grid, AbstractTetromino tetromino) {
        // TODO Auto-generated method stub

    }

    @Override
    public Action getNextAction() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void resetSession() {
        // TODO Auto-generated method stub

    }

}
