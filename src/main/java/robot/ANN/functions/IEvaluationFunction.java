package robot.ANN.functions;

import java.util.HashMap;

import robot.ANN.Neuron.AbstractNeuron;

public interface IEvaluationFunction {

    /**
     * evaluate determines what charge is passed to the Neuron on the other
     * side.
     * 
     * @param predecessors
     *            the neurons whose charge must be evaluated.
     * @return the output charge. Should at all times be in [0,1].
     */
    public double evaluate(HashMap<AbstractNeuron, Double> predecessors);

}
