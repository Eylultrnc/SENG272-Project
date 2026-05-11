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

public class PlanPanel extends JPanel {
    private final WizardFrame frame;
    private final JLabel title;
    private final DefaultTableModel tableModel;

    public PlanPanel(WizardFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        title = new JLabel("Step 3: Plan Measurement");
        title.setFont(title.getFont().deriveFont(22f));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Dimension", "Metric", "Coefficient", "Direction", "Range", "Unit"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        table.setRowHeight(26);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        JButton nextButton = new JButton("Next");
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

        title.setText("Step 3: Plan Measurement — " + scenario.getName());

        for (Dimension dimension : scenario.getDimensions()) {
            for (Metric metric : dimension.getMetrics()) {
                tableModel.addRow(new Object[]{
                    dimension.getName() + " (" + dimension.getCoefficient() + ")",
                    metric.getName(),
                    metric.getCoefficient(),
                    metric.getDirection().getLabel(),
                    metric.getRangeText(),
                    metric.getUnit()
                });
            }
        }
    }
}
