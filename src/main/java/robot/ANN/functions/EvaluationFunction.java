package robot.ANN.functions;

public enum EvaluationFunction implements IEvaluationFunctionFactory {
    Linear {
        public AbstractEvaluationFunction newInstance() {
            return new LinearEvaluationFunction();
        }
    },
    SoftMax {
        public AbstractEvaluationFunction newInstance() {
            return new SoftMax();
        }
    };

}
