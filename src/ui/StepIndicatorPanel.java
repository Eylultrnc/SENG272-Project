package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StepIndicatorPanel extends JPanel {
    private final String[] stepNames = {"Profile", "Define", "Plan", "Collect", "Analyse"};
    private final JLabel[] labels;

    public StepIndicatorPanel() {
        setLayout(new GridLayout(1, stepNames.length, 8, 0));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        labels = new JLabel[stepNames.length];

        for (int i = 0; i < stepNames.length; i++) {
            labels[i] = new JLabel((i + 1) + ". " + stepNames[i], SwingConstants.CENTER);
            labels[i].setOpaque(true);
            labels[i].setBorder(BorderFactory.createEmptyBorder(8, 6, 8, 6));
            add(labels[i]);
        }

        updateStep(0);
    }

    public void updateStep(int currentStep) {
        for (int i = 0; i < labels.length; i++) {
            if (i < currentStep) {
                labels[i].setText("✓ " + stepNames[i]);
                labels[i].setBackground(new Color(204, 235, 204));
                labels[i].setForeground(new Color(20, 90, 20));
                labels[i].setFont(labels[i].getFont().deriveFont(Font.PLAIN));
            } else if (i == currentStep) {
                labels[i].setText((i + 1) + ". " + stepNames[i]);
                labels[i].setBackground(new Color(0, 102, 204));
                labels[i].setForeground(Color.WHITE);
                labels[i].setFont(labels[i].getFont().deriveFont(Font.BOLD));
            } else {
                labels[i].setText((i + 1) + ". " + stepNames[i]);
                labels[i].setBackground(new Color(230, 230, 230));
                labels[i].setForeground(Color.DARK_GRAY);
                labels[i].setFont(labels[i].getFont().deriveFont(Font.PLAIN));
            }
        }
    }
}
