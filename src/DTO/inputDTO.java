package DTO;

import java.awt.*;

public class inputDTO {
    private String materialType;
    private String name;
    private Color color;
    private Double highValue;
    private Double lowValue;

    public inputDTO(String materialType, String name, Double highValue, Double lowValue) {
        this.materialType = materialType;
        this.name = name;
        this.highValue = highValue;
        this.lowValue = lowValue;
    }

    public inputDTO(String materialType, String name, Color color, Double highValue, Double lowValue) {
        this.materialType = materialType;
        this.name = name;
        this.color = color;
        this.highValue = highValue;
        this.lowValue = lowValue;
    }

    public String getMaterialType() {
        return materialType;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public Double getHighValue() {
        return highValue;
    }

    public Double getLowValue() {
        return lowValue;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
