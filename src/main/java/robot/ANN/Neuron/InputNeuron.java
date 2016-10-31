package robot.ANN.Neuron;

import java.util.Iterator;

import robot.ANN.Path;

public class InputNeuron extends AbstractNeuron {
    private IInput input;

    @Override
    public Iterator<Path> iterator(Path path) {
        return new InputIterator(path);
    }

    public InputNeuron(IInput input) {
        this.input = input;
    }

    @Override
    public void uncharge() {
        charge = -1;
    }

    @Override
    public boolean hasAsPredecessor(AbstractNeuron neuron) {
        return false;
    }

    @Override
    public double getCharge() {
        return input.processInput();
    }

    public IInput getInput() {
        return input;
    }

}
