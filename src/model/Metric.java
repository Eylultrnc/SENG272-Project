package model;

public class Metric {
    private final String name;
    private final int coefficient;
    private final Direction direction;
    private final double min;
    private final double max;
    private final String unit;
    private final double value;

    public Metric(String name, int coefficient, Direction direction, double min, double max, String unit, double value) {
        this.name = name;
        this.coefficient = coefficient;
        this.direction = direction;
        this.min = min;
        this.max = max;
        this.unit = unit;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public Direction getDirection() {
        return direction;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public String getRangeText() {
        return formatNumber(min) + "-" + formatNumber(max);
    }

    public String getUnit() {
        return unit;
    }

    public double getValue() {
        return value;
    }

    private String formatNumber(double number) {
        if (number == (long) number) {
            return String.valueOf((long) number);
        }
        return String.valueOf(number);
    }
}
