package robot;

import java.util.Observable;

import tetris.Action;
import tetris.Grid;
import tetris.tetromino.AbstractTetromino;

import robot.ANN.RandomNeuralNetwork;
import robot.ANN.Neuron.IOutput;
import robot.ANN.Neuron.Inputter;
import robot.ANN.functions.LinearEvaluationFunction;
import robot.GeneticAlgorithm.GeneticAlgorithm;
import robot.GeneticAlgorithm.IChromosome;

public class ANNRobot implements IRobot {
    private GeneticAlgorithm geneticAlgorithm;
    private IChromosome bot;
    private Inputter[] input;
    private IOutput[] output;
    private Action nextAction;

    public ANNRobot(int boardSize) {
        output = new IOutput[] {
            (charge) -> nextAction = Action.values()[(int) (charge * Action.values().length)] };
        setInput();
        geneticAlgorithm = new GeneticAlgorithm(10, LinearEvaluationFunction.class,
            RandomNeuralNetwork.class, input, output);
        input = new Inputter[4 + boardSize];
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        bot.setFitness((int) arg1);
    }

    @Override
    public void setGameState(Grid grid, AbstractTetromino tetromino) {
        input[0].setInput(tetromino.getRotation());
        input[1].setInput(tetromino.getleft().getX());
        input[2].setInput(tetromino.getleft().getY());
        input[3].setInput(tetromino.getColor());
        for (int x = 0; x < grid.width(); x++) {
            input[4 + x].setInput(grid.getHighestOccupied(x));
        }
        bot.evaluate();
    }

    @Override
    public Action getNextAction() {
        return nextAction;
    }

    @Override
    public void resetSession() {
        bot = geneticAlgorithm.getNextRobot();
    }

    private void setInput() {
        for (int i = 0; i < input.length; i++) {
            input[i] = new Inputter();
        }
    }

}
