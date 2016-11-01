package robot.ANN;

import java.util.Iterator;

import robot.ANN.Neuron.OutputNeuron;

public class PathIterator implements Iterator<Path> {
    private Iterator<OutputNeuron> outputIterator;
    private Iterator<Path> currentNeuronIterator;
    private Path path;

    public PathIterator(Iterator<OutputNeuron> outputIterator) {
        this.outputIterator = outputIterator;
        if (outputIterator.hasNext()) {
            OutputNeuron ouputNeuron = outputIterator.next();
            path = new Path();
            path.push(new PathNode(0, ouputNeuron));
            currentNeuronIterator = ouputNeuron.iterator(path);
        }
    }

    @Override
    public boolean hasNext() {
        if (currentNeuronIterator == null) {
            return false;
        }
        while (!currentNeuronIterator.hasNext()) {
            if (outputIterator.hasNext()) {
                OutputNeuron oNeuron = outputIterator.next();
                path = new Path();
                path.push(new PathNode(0, oNeuron));
                currentNeuronIterator = oNeuron.iterator(path);
            } else {
                return false;
            }
        }
        return currentNeuronIterator.hasNext();
    }

    @Override
    public Path next() {
        return currentNeuronIterator.next();
    }
}
