package robot;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Observable;
import java.util.stream.Collectors;

import tetris.Action;
import tetris.Controller;
import tetris.Grid;
import tetris.shapes.decorators.MovableShape;

import javafx.application.Platform;

import logging.Logger;
import robot.ANN.Neuron.IInput;
import robot.ANN.Neuron.IOutput;
import robot.ANN.functions.EvaluationFunction;
import robot.GeneticAlgorithm.GeneticAlgorithm;
import robot.GeneticAlgorithm.IChromosome;

public class ANNRobot implements IRobot {
    private GeneticAlgorithm geneticAlgorithm;
    private IChromosome bot;
    private IInput[] input;
    private IOutput[] output;
    private volatile int score;
    private Grid grid;
    private MovableShape tetromino;
    private Controller controller;
    DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public ANNRobot(int boardSize, int populationSize, EvaluationFunction evaluationFunction) {
        setInput();
        setOutput();
        geneticAlgorithm = new GeneticAlgorithm(populationSize, evaluationFunction, input, output);
        bot = geneticAlgorithm.getNextRobot();
    }

    /**
     * hook output events and charges in the outputactions sorted list.
     */
    private void setOutput() {
        output = new IOutput[] {
            () -> Platform.runLater(() -> Action.HARD_DROP.attempt(tetromino, grid, controller)),
            () -> Platform.runLater(() -> Action.ROTATE_LEFT.attempt(tetromino, grid, controller)),
            () -> Platform.runLater(() -> Action.ROTATE_RIGHT.attempt(tetromino, grid, controller)),
            () -> Platform.runLater(() -> Action.MOVE_LEFT.attempt(tetromino, grid, controller)),
            () -> Platform.runLater(() -> Action.MOVE_LEFT.attempt(tetromino, grid, controller)), };
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        score = (int) arg1;
    }

    @Override
    public String setGameState(Grid grid, MovableShape tetromino, Controller controller) {
        this.grid = grid;
        this.tetromino = tetromino;
        this.controller = controller;
        bot.evaluate();
        return Arrays.stream(input).map((iinput) -> decimalFormat.format(iinput.processInput()))
            .collect(Collectors.joining(", "));
    }

    @Override
    public void resetSession() {
        bot.setFitness(score);
        bot = geneticAlgorithm.getNextRobot();
        Logger.info(bot, "I got returned on resetSession");
    }

    /**
     * instantiate the inputter array, which is used to easily set the game
     * state.
     */
    private void setInput() {
        input = new IInput[] { () -> 1. / (1 + tetromino.rotation()),
            () -> 1. / (1 + tetromino.left().getX()), () -> 1. / (1 + tetromino.left().getY()),
            () -> 1. / (1 + tetromino.getColor()), () -> 1. / (1 + grid.getHighestOccupied(0)),
            () -> 1. / (1 + grid.getHighestOccupied(1)),
            () -> 1. / (1 + grid.getHighestOccupied(2)),
            () -> 1. / (1 + grid.getHighestOccupied(3)),
            () -> 1. / (1 + grid.getHighestOccupied(4)),
            () -> 1. / (1 + grid.getHighestOccupied(5)),
            () -> 1. / (1 + grid.getHighestOccupied(6)),
            () -> 1. / (1 + grid.getHighestOccupied(7)),
            () -> 1. / (1 + grid.getHighestOccupied(8)),
            () -> 1. / (1 + grid.getHighestOccupied(9)), };
    }
}
