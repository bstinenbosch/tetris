package robot.ANN;

public class NeuralPathNode {
    private double weight;
    private AbstractNeuron neuron;

    public NeuralPathNode(double weight, AbstractNeuron neuron) {
        this.neuron = neuron;
        this.weight = weight;
    }

    public double getWeight() {
        return this.weight;
    }

    // public void setWeight(double weight) {
    // this.weight = weight;
    // }

    public AbstractNeuron getNeuron() {
        return this.neuron;
    }
    //
    // public void setNeuron(AbstractNeuron neuron) {
    // this.neuron = neuron;
    // }
}
