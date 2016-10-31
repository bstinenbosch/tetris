package robot.ANN.Neuron;

import robot.ANN.functions.EvaluationFunction;

public class OutputNeuron extends Neuron {
    private IOutput output;

    public OutputNeuron(EvaluationFunction function, IOutput output) {
        super(function);
        this.output = output;
    }

    @Override
    public double getCharge() {
        double charge = super.getCharge();
        if (charge > .5) {
            output.interpret();
        }
        return charge;
    }

    public IOutput getOutput() {
        return output;
    }
}
