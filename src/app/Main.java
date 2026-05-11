package app;

import javax.swing.SwingUtilities;
import ui.WizardFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WizardFrame frame = new WizardFrame();
            frame.setVisible(true);
        });
    }
}
