package robot.ANN.functions;

public enum EvaluationFunction implements IEvaluationFunctionFactory {
    LINEAR {
        public IEvaluationFunction newInstance() {
            return new LinearEvaluationFunction();
        }
    },
    SIGMOID {
        public IEvaluationFunction newInstance() {
            return new SigmoidEvaluationFunction();
        }
    };

}
