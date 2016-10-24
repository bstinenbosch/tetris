package robot.ANN.functions;

public interface IEvaluationFunctionFactory {
    /**
     * create a new instance of the supplied type of evaluation function.
     * 
     * @return the new instance.
     */
    public AbstractEvaluationFunction newInstance();
}
