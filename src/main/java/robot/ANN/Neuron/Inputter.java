package robot.ANN.Neuron;

public class Inputter implements IInput {
    private double value;

    @Override
    public double processInput() {
        return value;
    }

    public void setInput(double value) {
        this.value = value;
    }

}
