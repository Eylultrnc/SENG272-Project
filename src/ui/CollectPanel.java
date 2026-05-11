package ui;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Dimension;
import model.Metric;
import model.Scenario;
import service.MeasurementService;

public class CollectPanel extends JPanel {
    private final WizardFrame frame;
    private final MeasurementService measurementService;
    private final JLabel title;
    private final DefaultTableModel tableModel;

    public CollectPanel(WizardFrame frame, MeasurementService measurementService) {
        this.frame = frame;
        this.measurementService = measurementService;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        title = new JLabel("Step 4: Collect Data");
        title.setFont(title.getFont().deriveFont(22f));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Metric", "Direction", "Range", "Value", "Score (1-5)", "Coeff / Unit"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        table.setRowHeight(26);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        JButton nextButton = new JButton("Analyse");
        backButton.addActionListener(e -> frame.previousStep());
        nextButton.addActionListener(e -> frame.nextStep());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void refresh(Scenario scenario) {
        tableModel.setRowCount(0);
        if (scenario == null) {
            return;
        }

        title.setText("Step 4: Collect Data — " + scenario.getName());

        for (Dimension dimension : scenario.getDimensions()) {
            for (Metric metric : dimension.getMetrics()) {
                double score = measurementService.calculateMetricScore(metric);
                tableModel.addRow(new Object[]{
                    metric.getName(),
                    metric.getDirection().getLabel(),
                    metric.getRangeText(),
                    metric.getValue(),
                    score,
                    metric.getCoefficient() + " / " + metric.getUnit()
                });
            }
        }
    }
}
