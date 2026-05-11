package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Dimension;
import model.Direction;
import model.Metric;
import model.Scenario;

public class ScenarioRepository {
    private final Map<String, List<Scenario>> scenariosByMode;

    public ScenarioRepository() {
        scenariosByMode = new HashMap<>();
        loadScenarios();
    }

    public List<String> getModes() {
        return new ArrayList<>(scenariosByMode.keySet());
    }

    public List<Scenario> getScenariosByMode(String mode) {
        return scenariosByMode.getOrDefault(mode, new ArrayList<>());
    }

    private void loadScenarios() {
        List<Scenario> education = new ArrayList<>();
        education.add(createEducationTeamAlpha());
        education.add(createEducationTeamBeta());

        List<Scenario> health = new ArrayList<>();
        health.add(createHealthHospitalA());
        health.add(createHealthHospitalB());

        scenariosByMode.put("Education", education);
        scenariosByMode.put("Health", health);
    }

    private Scenario createEducationTeamAlpha() {
        Scenario scenario = new Scenario("Education", "Scenario C — Team Alpha", "Learning management system evaluation.");

        Dimension usability = new Dimension("Usability", 25);
        usability.addMetric(new Metric("SUS score", 50, Direction.HIGHER_BETTER, 0, 100, "points", 89));
        usability.addMetric(new Metric("Onboarding time", 50, Direction.LOWER_BETTER, 0, 60, "min", 5));

        Dimension performance = new Dimension("Performance Efficiency", 20);
        performance.addMetric(new Metric("Video start time", 50, Direction.LOWER_BETTER, 0, 15, "sec", 2));
        performance.addMetric(new Metric("Concurrent exams", 50, Direction.HIGHER_BETTER, 0, 600, "users", 520));

        Dimension accessibility = new Dimension("Accessibility", 20);
        accessibility.addMetric(new Metric("WCAG compliance", 50, Direction.HIGHER_BETTER, 0, 100, "%", 92));
        accessibility.addMetric(new Metric("Screen reader score", 50, Direction.HIGHER_BETTER, 0, 100, "%", 80));

        Dimension reliability = new Dimension("Reliability", 20);
        reliability.addMetric(new Metric("Uptime", 50, Direction.HIGHER_BETTER, 95, 100, "%", 98.8));
        reliability.addMetric(new Metric("MTTR", 50, Direction.LOWER_BETTER, 0, 120, "min", 22));

        Dimension suitability = new Dimension("Functional Suitability", 15);
        suitability.addMetric(new Metric("Feature completion", 50, Direction.HIGHER_BETTER, 0, 100, "%", 86));
        suitability.addMetric(new Metric("Assignment submit rate", 50, Direction.HIGHER_BETTER, 0, 100, "%", 91));

        scenario.addDimension(usability);
        scenario.addDimension(performance);
        scenario.addDimension(accessibility);
        scenario.addDimension(reliability);
        scenario.addDimension(suitability);
        return scenario;
    }

    private Scenario createEducationTeamBeta() {
        Scenario scenario = new Scenario("Education", "Scenario D — Team Beta", "Alternative LMS scenario with weaker accessibility.");

        Dimension usability = new Dimension("Usability", 25);
        usability.addMetric(new Metric("SUS score", 50, Direction.HIGHER_BETTER, 0, 100, "points", 72));
        usability.addMetric(new Metric("Onboarding time", 50, Direction.LOWER_BETTER, 0, 60, "min", 18));

        Dimension performance = new Dimension("Performance Efficiency", 20);
        performance.addMetric(new Metric("Video start time", 50, Direction.LOWER_BETTER, 0, 15, "sec", 6));
        performance.addMetric(new Metric("Concurrent exams", 50, Direction.HIGHER_BETTER, 0, 600, "users", 350));

        Dimension accessibility = new Dimension("Accessibility", 20);
        accessibility.addMetric(new Metric("WCAG compliance", 50, Direction.HIGHER_BETTER, 0, 100, "%", 58));
        accessibility.addMetric(new Metric("Screen reader score", 50, Direction.HIGHER_BETTER, 0, 100, "%", 62));

        Dimension reliability = new Dimension("Reliability", 20);
        reliability.addMetric(new Metric("Uptime", 50, Direction.HIGHER_BETTER, 95, 100, "%", 97.1));
        reliability.addMetric(new Metric("MTTR", 50, Direction.LOWER_BETTER, 0, 120, "min", 45));

        Dimension suitability = new Dimension("Functional Suitability", 15);
        suitability.addMetric(new Metric("Feature completion", 50, Direction.HIGHER_BETTER, 0, 100, "%", 77));
        suitability.addMetric(new Metric("Assignment submit rate", 50, Direction.HIGHER_BETTER, 0, 100, "%", 69));

        scenario.addDimension(usability);
        scenario.addDimension(performance);
        scenario.addDimension(accessibility);
        scenario.addDimension(reliability);
        scenario.addDimension(suitability);
        return scenario;
    }

    private Scenario createHealthHospitalA() {
        Scenario scenario = new Scenario("Health", "Scenario A — Hospital Portal", "Health management system scenario.");

        Dimension usability = new Dimension("Usability", 25);
        usability.addMetric(new Metric("Patient satisfaction", 50, Direction.HIGHER_BETTER, 0, 100, "points", 84));
        usability.addMetric(new Metric("Appointment completion time", 50, Direction.LOWER_BETTER, 0, 20, "min", 4));

        Dimension performance = new Dimension("Performance Efficiency", 20);
        performance.addMetric(new Metric("Page response time", 50, Direction.LOWER_BETTER, 0, 10, "sec", 1.8));
        performance.addMetric(new Metric("Daily transactions", 50, Direction.HIGHER_BETTER, 0, 10000, "requests", 7800));

        Dimension security = new Dimension("Security", 20);
        security.addMetric(new Metric("Authentication success", 50, Direction.HIGHER_BETTER, 80, 100, "%", 96));
        security.addMetric(new Metric("Open vulnerabilities", 50, Direction.LOWER_BETTER, 0, 30, "issues", 4));

        Dimension reliability = new Dimension("Reliability", 20);
        reliability.addMetric(new Metric("Uptime", 50, Direction.HIGHER_BETTER, 95, 100, "%", 99.1));
        reliability.addMetric(new Metric("Incident recovery time", 50, Direction.LOWER_BETTER, 0, 180, "min", 30));

        Dimension maintainability = new Dimension("Maintainability", 15);
        maintainability.addMetric(new Metric("Code review coverage", 50, Direction.HIGHER_BETTER, 0, 100, "%", 88));
        maintainability.addMetric(new Metric("Critical code smells", 50, Direction.LOWER_BETTER, 0, 50, "items", 9));

        scenario.addDimension(usability);
        scenario.addDimension(performance);
        scenario.addDimension(security);
        scenario.addDimension(reliability);
        scenario.addDimension(maintainability);
        return scenario;
    }

    private Scenario createHealthHospitalB() {
        Scenario scenario = new Scenario("Health", "Scenario B — Clinic Scheduler", "Clinic scheduling process scenario.");

        Dimension processEfficiency = new Dimension("Process Efficiency", 30);
        processEfficiency.addMetric(new Metric("Sprint completion rate", 50, Direction.HIGHER_BETTER, 0, 100, "%", 74));
        processEfficiency.addMetric(new Metric("Average task delay", 50, Direction.LOWER_BETTER, 0, 15, "days", 5));

        Dimension collaboration = new Dimension("Team Collaboration", 20);
        collaboration.addMetric(new Metric("Review participation", 50, Direction.HIGHER_BETTER, 0, 100, "%", 68));
        collaboration.addMetric(new Metric("Blocked tasks", 50, Direction.LOWER_BETTER, 0, 30, "tasks", 8));

        Dimension codeQuality = new Dimension("Code Quality", 20);
        codeQuality.addMetric(new Metric("Test coverage", 50, Direction.HIGHER_BETTER, 0, 100, "%", 71));
        codeQuality.addMetric(new Metric("Defect density", 50, Direction.LOWER_BETTER, 0, 10, "defects/KLOC", 3));

        Dimension reliability = new Dimension("Reliability", 15);
        reliability.addMetric(new Metric("Deployment success", 50, Direction.HIGHER_BETTER, 0, 100, "%", 90));
        reliability.addMetric(new Metric("Rollback count", 50, Direction.LOWER_BETTER, 0, 10, "count", 1));

        Dimension documentation = new Dimension("Documentation", 15);
        documentation.addMetric(new Metric("API documentation coverage", 50, Direction.HIGHER_BETTER, 0, 100, "%", 64));
        documentation.addMetric(new Metric("Outdated pages", 50, Direction.LOWER_BETTER, 0, 40, "pages", 14));

        scenario.addDimension(processEfficiency);
        scenario.addDimension(collaboration);
        scenario.addDimension(codeQuality);
        scenario.addDimension(reliability);
        scenario.addDimension(documentation);
        return scenario;
    }
}
