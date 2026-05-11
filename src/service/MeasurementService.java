package service;

import java.util.LinkedHashMap;
import java.util.Map;
import model.Dimension;
import model.Direction;
import model.Metric;
import model.Scenario;

public class MeasurementService {
    public double calculateMetricScore(Metric metric) {
        double min = metric.getMin();
        double max = metric.getMax();
        double value = metric.getValue();
        double rawScore;

        if (metric.getDirection() == Direction.HIGHER_BETTER) {
            rawScore = 1 + ((value - min) / (max - min)) * 4;
        } else {
            rawScore = 5 - ((value - min) / (max - min)) * 4;
        }

        double clamped = Math.max(1.0, Math.min(5.0, rawScore));
        return Math.round(clamped * 2.0) / 2.0;
    }

    public Map<String, Double> calculateDimensionScores(Scenario scenario) {
        Map<String, Double> scores = new LinkedHashMap<>();

        for (Dimension dimension : scenario.getDimensions()) {
            double weightedSum = 0;
            double coefficientSum = 0;

            for (Metric metric : dimension.getMetrics()) {
                double metricScore = calculateMetricScore(metric);
                weightedSum += metricScore * metric.getCoefficient();
                coefficientSum += metric.getCoefficient();
            }

            scores.put(dimension.getName(), weightedSum / coefficientSum);
        }

        return scores;
    }

    public String getQualityLevel(double score) {
        if (score >= 4.5) {
            return "Excellent";
        } else if (score >= 3.5) {
            return "Good";
        } else if (score >= 2.5) {
            return "Needs Improvement";
        }
        return "Poor";
    }
}
