package ui;

import data.ScenarioRepository;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import model.Scenario;

public class DefinePanel extends JPanel {
    private final WizardFrame frame;
    private final ScenarioRepository repository;
    private final JPanel scenarioPanel;
    private final ButtonGroup typeGroup;
    private final ButtonGroup modeGroup;
    private ButtonGroup scenarioGroup;

    private String selectedType = "Product Quality";
    private String selectedMode = "Education";
    private Scenario selectedScenario;

    public DefinePanel(WizardFrame frame, ScenarioRepository repository) {
        this.frame = frame;
        this.repository = repository;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        JLabel title = new JLabel("Step 2: Define Quality Dimensions");
        title.setFont(title.getFont().deriveFont(22f));
        add(title, BorderLayout.NORTH);

        JPanel content = new JPanel(new GridLayout(1, 3, 20, 0));

        JPanel typePanel = createSectionPanel("2a. Quality Type");
        typeGroup = new ButtonGroup();
        JRadioButton productButton = createRadio("Product Quality", typeGroup, true);
        JRadioButton processButton = createRadio("Process Quality", typeGroup, false);
        productButton.addActionListener(e -> selectedType = "Product Quality");
        processButton.addActionListener(e -> selectedType = "Process Quality");
        typePanel.add(productButton);
        typePanel.add(processButton);

        JPanel modePanel = createSectionPanel("2b. Mode");
        modeGroup = new ButtonGroup();
        JRadioButton educationButton = createRadio("Education", modeGroup, true);
        JRadioButton healthButton = createRadio("Health", modeGroup, false);
        educationButton.addActionListener(e -> {
            selectedMode = "Education";
            updateScenarios();
        });
        healthButton.addActionListener(e -> {
            selectedMode = "Health";
            updateScenarios();
        });
        modePanel.add(educationButton);
        modePanel.add(healthButton);

        scenarioPanel = createSectionPanel("2c. Scenario");
        updateScenarios();

        content.add(typePanel);
        content.add(modePanel);
        content.add(scenarioPanel);
        add(content, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        JButton nextButton = new JButton("Next");
        backButton.addActionListener(e -> frame.previousStep());
        nextButton.addActionListener(e -> validateAndContinue());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createSectionPanel(String title) {
        JPanel panel = new JPanel(new GridLayout(8, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder(title));
        return panel;
    }

    private JRadioButton createRadio(String text, ButtonGroup group, boolean selected) {
        JRadioButton button = new JRadioButton(text, selected);
        group.add(button);
        return button;
    }

    private void updateScenarios() {
        scenarioPanel.removeAll();
        scenarioPanel.setBorder(BorderFactory.createTitledBorder("2c. Scenario"));
        scenarioGroup = new ButtonGroup();
        List<Scenario> scenarios = repository.getScenariosByMode(selectedMode);

        selectedScenario = null;
        for (int i = 0; i < scenarios.size(); i++) {
            Scenario scenario = scenarios.get(i);
            JRadioButton button = new JRadioButton(scenario.getName(), i == 0);
            if (i == 0) {
                selectedScenario = scenario;
            }
            button.addActionListener(e -> selectedScenario = scenario);
            scenarioGroup.add(button);
            scenarioPanel.add(button);
            scenarioPanel.add(new JLabel("<html><small>" + scenario.getDescription() + "</small></html>"));
        }

        scenarioPanel.revalidate();
        scenarioPanel.repaint();
    }

    private void validateAndContinue() {
        if (selectedType == null) {
            JOptionPane.showMessageDialog(this, "Please select one quality type to continue.");
            return;
        }
        if (selectedMode == null) {
            JOptionPane.showMessageDialog(this, "Please select one mode to continue.");
            return;
        }
        if (selectedScenario == null) {
            JOptionPane.showMessageDialog(this, "Please select one scenario to continue.");
            return;
        }

        frame.setSelectedScenario(selectedScenario);
        frame.nextStep();
    }
}
