package robot.ANN;

public class InputNeuron extends AbstractNeuron {
    private IInput input;

    public InputNeuron(IInput input) {
        this.input = input;
    }

    public void uncharge() {
        charge = -1;
    }

    public boolean hasAsPredecessor(AbstractNeuron neuron) {
        return false;
    }

    @Override
    public double getCharge() {
        return input.processInput();
    }

}
