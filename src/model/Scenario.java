package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scenario {
    private final String mode;
    private final String name;
    private final String description;
    private final List<Dimension> dimensions;

    public Scenario(String mode, String name, String description) {
        this.mode = mode;
        this.name = name;
        this.description = description;
        this.dimensions = new ArrayList<>();
    }

    public void addDimension(Dimension dimension) {
        dimensions.add(dimension);
    }

    public String getMode() {
        return mode;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Dimension> getDimensions() {
        return Collections.unmodifiableList(dimensions);
    }

    @Override
    public String toString() {
        return name;
    }
}
