package robot;

import java.util.Observable;
import java.util.TreeSet;

import tetris.Action;
import tetris.Grid;
import tetris.shapes.decorators.MovableShape;

import logging.Logger;
import robot.ANN.Neuron.IOutput;
import robot.ANN.Neuron.Inputter;
import robot.ANN.functions.EvaluationFunction;
import robot.GeneticAlgorithm.GeneticAlgorithm;
import robot.GeneticAlgorithm.IChromosome;

public class ANNRobot implements IRobot {
    private GeneticAlgorithm geneticAlgorithm;
    private IChromosome bot;
    private Inputter[] input;
    private IOutput[] output;
    private volatile int score;
    private TreeSet<ActionOutput> outputActions = new TreeSet<>();

    /**
     * class used to sort outputs on their charge, so we can easily select the
     * output with the highest charge.
     */
    private class ActionOutput implements Comparable<ActionOutput> {
        private Action action;
        private double charge;

        ActionOutput(Action action, double charge) {
            this.action = action;
            this.charge = charge;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof ActionOutput)) {
                return false;
            }
            return ((ActionOutput) other).action.equals(this.action);
        }

        @Override
        public int compareTo(ActionOutput o) {
            if (equals(o)) {
                return 0;
            }
            return (int) Math.signum(this.charge - o.charge);
        }
    }

    public ANNRobot(int boardSize, int populationSize, EvaluationFunction evaluationFunction) {
        input = new Inputter[4 + boardSize];
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
            charge -> outputActions.add(new ActionOutput(Action.ROTATE_LEFT, charge)),
            charge -> outputActions.add(new ActionOutput(Action.ROTATE_RIGHT, charge)),
            charge -> outputActions.add(new ActionOutput(Action.MOVE_LEFT, charge)),
            charge -> outputActions.add(new ActionOutput(Action.MOVE_LEFT, charge)),
            charge -> outputActions.add(new ActionOutput(Action.HARD_DROP, charge)) };
        new ActionOutput(Action.INVALID_ACTION, .5);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        score = (int) arg1;
    }

    @Override
    public void setGameState(Grid grid, MovableShape tetromino) {
        input[0].setInput(tetromino.rotation());
        input[1].setInput(tetromino.left().getX());
        input[2].setInput(tetromino.left().getY());
        input[3].setInput(tetromino.getColor());
        for (int x = 0; x < grid.width(); x++) {
            input[4 + x].setInput(grid.getHighestOccupied(x));
        }
        bot.evaluate();
    }

    @Override
    public Action getNextAction() {
        return outputActions.last().action;
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
        for (int i = 0; i < input.length; i++) {
            input[i] = new Inputter();
        }
    }
}
