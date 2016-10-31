package robot.ANN.Neuron;

import java.util.Iterator;

import robot.ANN.Path;

public class InputIterator implements Iterator<Path> {

    private Path path;
    private boolean hasIterated = false;

    public InputIterator(Path path) {
        this.path = path;
    }

    @Override
    public boolean hasNext() {
        return (!hasIterated);
    }

    @Override
    public Path next() {
        hasIterated = true;
        return (Path) path.clone();
    }
}
