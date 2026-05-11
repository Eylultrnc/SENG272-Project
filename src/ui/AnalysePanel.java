package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import model.Scenario;
import service.MeasurementService;

public class AnalysePanel extends JPanel {
    private final WizardFrame frame;
    private final MeasurementService measurementService;
    private final JLabel title;
    private final JPanel scorePanel;
    private final RadarChartPanel radarChartPanel;
    private final JLabel gapLabel;

    public AnalysePanel(WizardFrame frame, MeasurementService measurementService) {
        this.frame = frame;
        this.measurementService = measurementService;

        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        title = new JLabel("Step 5: Analyse");
        title.setFont(title.getFont().deriveFont(22f));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));

        scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(8, 1, 5, 5));
        scorePanel.setBorder(BorderFactory.createTitledBorder("Dimension-Based Weighted Averages"));

        radarChartPanel = new RadarChartPanel();
        radarChartPanel.setBorder(BorderFactory.createTitledBorder("Radar Chart"));

        centerPanel.add(scorePanel);
        centerPanel.add(radarChartPanel);
        add(centerPanel, BorderLayout.CENTER);

        gapLabel = new JLabel();
        gapLabel.setOpaque(true);
        gapLabel.setBackground(new Color(255, 245, 204));
        gapLabel.setBorder(BorderFactory.createTitledBorder("Gap Analysis"));
        add(gapLabel, BorderLayout.SOUTH);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.previousStep());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.PAGE_END);
    }

    public void refresh(Scenario scenario) {
        scorePanel.removeAll();
        if (scenario == null) {
            return;
        }

        title.setText("Step 5: Analyse — " + scenario.getName());
        Map<String, Double> dimensionScores = measurementService.calculateDimensionScores(scenario);

        String weakestDimension = "";
        double weakestScore = 6;

        for (Map.Entry<String, Double> entry : dimensionScores.entrySet()) {
            String dimensionName = entry.getKey();
            double score = entry.getValue();

            if (score < weakestScore) {
                weakestScore = score;
                weakestDimension = dimensionName;
            }

            JLabel label = new JLabel(dimensionName + " — " + String.format("%.2f", score));
            JProgressBar bar = new JProgressBar(0, 50);
            bar.setValue((int) Math.round(score * 10));
            bar.setStringPainted(true);
            bar.setString(String.format("%.2f / 5.00", score));
            scorePanel.add(label);
            scorePanel.add(bar);
        }

        double gap = 5.0 - weakestScore;
        String level = measurementService.getQualityLevel(weakestScore);

        gapLabel.setText("<html><b>Lowest dimension:</b> " + weakestDimension
                + " | <b>Score:</b> " + String.format("%.2f", weakestScore)
                + " | <b>Gap:</b> " + String.format("%.2f", gap)
                + " | <b>Quality level:</b> " + level
                + "<br>This dimension has the lowest score and requires the most improvement.</html>");

        radarChartPanel.setScores(dimensionScores);
        scorePanel.revalidate();
        scorePanel.repaint();
    }
}
