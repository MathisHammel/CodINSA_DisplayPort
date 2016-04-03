package algorithms.globalEvaluations;

import models.Game;

import java.util.Map;

public class PonderatedEvaluation implements GlobalEvaluationInterface {
    private Map<GlobalEvaluationInterface, Double> evaluations;
    PonderatedEvaluation(Map<GlobalEvaluationInterface, Double> evaluations) {
        this.evaluations = evaluations;
    }

    @Override
    public double evaluate(Game g) {
        double sum = 0;
        for(GlobalEvaluationInterface ei: evaluations.keySet()) {
            sum += evaluations.get(ei) * ei.evaluate(g);
        }
        return sum;
    }
}
