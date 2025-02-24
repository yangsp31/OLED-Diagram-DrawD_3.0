package DTO;

import java.awt.*;

public class material {
    private String name;
    private Color color;
    private String type;
    private double x;
    private double width;
    private double lowValue;
    private double highValue;
    private int zIndex;
    private boolean isInvertValue;
    private boolean isInvertName;
    private boolean isShowValue;
    private boolean isShowName;
    private boolean isCut;
    private String borerType;
    private Color borerColor;
    private float transparency;

    public material(String name, Color color, String type, double x, double width, double lowValue, double highValue, int zIndex) {
        this.name = name;
        this.color = color;
        this.type = type;
        this.x = x;
        this.width = width;
        this.lowValue = lowValue;
        this.highValue = highValue;
        this.zIndex = zIndex;
        this.isInvertValue = false;
        this.isInvertName = false;
        this.isShowValue = true;
        this.isShowName = true;
        this.isCut = false;
        this.borerType = "None";
        this.borerColor = new Color(150, 150, 150);
        this.transparency = 1.0f;
    }

    public material(material otherMaterial) {
        this.name = otherMaterial.name;
        this.color = otherMaterial.color;
        this.type = otherMaterial.type;
        this.x = otherMaterial.x;
        this.width = otherMaterial.width;
        this.lowValue = otherMaterial.lowValue;
        this.highValue = otherMaterial.highValue;
        this.zIndex = otherMaterial.zIndex;
        this.isInvertValue = otherMaterial.isInvertValue;
        this.isInvertName = otherMaterial.isInvertName;
        this.isShowValue = otherMaterial.isShowValue;
        this.isShowName = otherMaterial.isShowName;
        this.isCut = otherMaterial.isCut;
        this.borerType = otherMaterial.borerType;
        this.borerColor = otherMaterial.borerColor;
        this.transparency = otherMaterial.transparency;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public double getX() {
        return x;
    }

    public double getWidth() {
        return width;
    }

    public double getLowValue() {
        return lowValue;
    }

    public double getHighValue() {
        return highValue;
    }

    public int getzIndex() {
        return zIndex;
    }

    public boolean isInvertValue() {
        return isInvertValue;
    }

    public boolean isInvertName() {
        return isInvertName;
    }

    public boolean isShowValue() {
        return isShowValue;
    }

    public boolean isShowName() {
        return isShowName;
    }

    public boolean isCut() {
        return isCut;
    }

    public String getBorerType() {
        return borerType;
    }

    public Color getBorerColor() {
        return borerColor;
    }

    public float getTransparency() {
        return transparency;
    }

    public void setZIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    public void setInvertValue(boolean isInvertValue) {
        this.isInvertValue = isInvertValue;
    }

    public void setInvertName(boolean isInvertName) {
        this.isInvertName = isInvertName;
    }

    public void setShowValue(boolean isShowValue) {
        this.isShowValue = isShowValue;
    }

    public void setShowName(boolean isShowName) {
        this.isShowName = isShowName;
    }

    public void setCut(boolean isCut) {
        this.isCut = isCut;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setLowValue(double lowValue) {
        this.lowValue = lowValue;
    }

    public void setHighValue(double highValue) {
        this.highValue = highValue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBorerType(String borerType) {
        this.borerType = borerType;
    }

    public void setBorerColor(Color borerColor) {
        this.borerColor = borerColor;
    }

    public void setTransparency(float transparency) {
        this.transparency = transparency;
    }
}
