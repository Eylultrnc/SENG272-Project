package ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Profile;

public class ProfilePanel extends JPanel {
    private final WizardFrame frame;
    private final Profile profile;
    private final JTextField usernameField;
    private final JTextField schoolField;
    private final JTextField sessionNameField;

    public ProfilePanel(WizardFrame frame, Profile profile) {
        this.frame = frame;
        this.profile = profile;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        JLabel title = new JLabel("Step 1: Profile");
        title.setFont(title.getFont().deriveFont(22f));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        usernameField = new JTextField(25);
        schoolField = new JTextField(25);
        sessionNameField = new JTextField(25);

        addRow(formPanel, gbc, 0, "Username:", usernameField);
        addRow(formPanel, gbc, 1, "School:", schoolField);
        addRow(formPanel, gbc, 2, "Session Name:", sessionNameField);

        add(formPanel, BorderLayout.CENTER);

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> validateAndContinue());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int row, String label, JTextField field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(field, gbc);
    }

    private void validateAndContinue() {
        if (usernameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your username to continue.");
            return;
        }
        if (schoolField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your school to continue.");
            return;
        }
        if (sessionNameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a session name to continue.");
            return;
        }

        profile.setUsername(usernameField.getText().trim());
        profile.setSchool(schoolField.getText().trim());
        profile.setSessionName(sessionNameField.getText().trim());
        frame.nextStep();
    }
}
