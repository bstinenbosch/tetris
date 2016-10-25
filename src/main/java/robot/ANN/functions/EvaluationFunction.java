package robot.ANN.functions;

public enum EvaluationFunction implements IEvaluationFunctionFactory {
    LINEAR {
        public AbstractEvaluationFunction newInstance() {
            return new LinearEvaluationFunction();
        }
    },
    SIGMOID {
        public AbstractEvaluationFunction newInstance() {
            return new SigmoidEvaluationFunction();
        }
    };

}
