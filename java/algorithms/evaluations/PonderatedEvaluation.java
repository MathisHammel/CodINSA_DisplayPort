package algorithms.evaluations;

import models.Game;

import java.util.Map;

public class PonderatedEvaluation implements EvaluationInterface{
    private Map<EvaluationInterface, Double> evaluations;
    PonderatedEvaluation(Map<EvaluationInterface, Double> evaluations) {
        this.evaluations = evaluations;
    }

    @Override
    public double evaluate(Game g) {
        double sum = 0;
        for(EvaluationInterface ei: evaluations.keySet()) {
            sum += evaluations.get(ei) * ei.evaluate(g);
        }
        return sum;
    }
}
