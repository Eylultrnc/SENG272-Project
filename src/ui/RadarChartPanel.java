package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JPanel;

public class RadarChartPanel extends JPanel {
    private Map<String, Double> scores = new LinkedHashMap<>();

    public void setScores(Map<String, Double> scores) {
        this.scores = scores;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (scores == null || scores.isEmpty()) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int radius = Math.min(width, height) / 3;

        int count = scores.size();

        for (int level = 1; level <= 5; level++) {
            int levelRadius = radius * level / 5;
            Polygon grid = createPolygon(centerX, centerY, levelRadius, count, null);
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawPolygon(grid);
        }

        String[] labels = scores.keySet().toArray(new String[0]);
        double[] values = scores.values().stream().mapToDouble(Double::doubleValue).toArray();

        for (int i = 0; i < count; i++) {
            double angle = -Math.PI / 2 + 2 * Math.PI * i / count;
            int x = centerX + (int) (Math.cos(angle) * radius);
            int y = centerY + (int) (Math.sin(angle) * radius);
            g2.setColor(Color.GRAY);
            g2.drawLine(centerX, centerY, x, y);
            g2.setColor(Color.DARK_GRAY);
            g2.drawString(labels[i], centerX + (int) (Math.cos(angle) * (radius + 25)) - 35,
                    centerY + (int) (Math.sin(angle) * (radius + 25)));
        }

        Polygon scorePolygon = createPolygon(centerX, centerY, radius, count, values);
        g2.setColor(new Color(0, 102, 204, 90));
        g2.fillPolygon(scorePolygon);
        g2.setColor(new Color(0, 102, 204));
        g2.drawPolygon(scorePolygon);
    }

    private Polygon createPolygon(int centerX, int centerY, int radius, int count, double[] values) {
        Polygon polygon = new Polygon();

        for (int i = 0; i < count; i++) {
            double ratio = values == null ? 1.0 : values[i] / 5.0;
            int pointRadius = (int) (radius * ratio);
            double angle = -Math.PI / 2 + 2 * Math.PI * i / count;
            int x = centerX + (int) (Math.cos(angle) * pointRadius);
            int y = centerY + (int) (Math.sin(angle) * pointRadius);
            polygon.addPoint(x, y);
        }

        return polygon;
    }
}
