package robot.ANN;

public interface IInput {

    /**
     * processInput gives the input node its value which will be propagated
     * through the network.
     * 
     * @return the value to propagate.
     */
    public double processInput();

}