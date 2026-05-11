package ui;

import data.ScenarioRepository;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Profile;
import model.Scenario;
import service.MeasurementService;

public class WizardFrame extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private final StepIndicatorPanel stepIndicatorPanel;
    private final Profile profile;
    private final ScenarioRepository scenarioRepository;
    private final MeasurementService measurementService;

    private Scenario selectedScenario;
    private int currentStep;

    private final ProfilePanel profilePanel;
    private final DefinePanel definePanel;
    private final PlanPanel planPanel;
    private final CollectPanel collectPanel;
    private final AnalysePanel analysePanel;

    public WizardFrame() {
        super("ISO 15939 Measurement Process Simulator");

        profile = new Profile();
        scenarioRepository = new ScenarioRepository();
        measurementService = new MeasurementService();

        setSize(1050, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        stepIndicatorPanel = new StepIndicatorPanel();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        profilePanel = new ProfilePanel(this, profile);
        definePanel = new DefinePanel(this, scenarioRepository);
        planPanel = new PlanPanel(this);
        collectPanel = new CollectPanel(this, measurementService);
        analysePanel = new AnalysePanel(this, measurementService);

        cardPanel.add(profilePanel, "Profile");
        cardPanel.add(definePanel, "Define");
        cardPanel.add(planPanel, "Plan");
        cardPanel.add(collectPanel, "Collect");
        cardPanel.add(analysePanel, "Analyse");

        add(stepIndicatorPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);

        goToStep(0);
    }

    public void goToStep(int step) {
        currentStep = step;
        stepIndicatorPanel.updateStep(step);

        if (step == 0) {
            cardLayout.show(cardPanel, "Profile");
        } else if (step == 1) {
            cardLayout.show(cardPanel, "Define");
        } else if (step == 2) {
            planPanel.refresh(selectedScenario);
            cardLayout.show(cardPanel, "Plan");
        } else if (step == 3) {
            collectPanel.refresh(selectedScenario);
            cardLayout.show(cardPanel, "Collect");
        } else if (step == 4) {
            analysePanel.refresh(selectedScenario);
            cardLayout.show(cardPanel, "Analyse");
        }
    }

    public void nextStep() {
        if (currentStep < 4) {
            goToStep(currentStep + 1);
        }
    }

    public void previousStep() {
        if (currentStep > 0) {
            goToStep(currentStep - 1);
        }
    }

    public Scenario getSelectedScenario() {
        return selectedScenario;
    }

    public void setSelectedScenario(Scenario selectedScenario) {
        this.selectedScenario = selectedScenario;
    }
}
