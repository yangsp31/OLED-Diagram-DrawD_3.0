package DTO;

public class searchMaterialDTO {
    private String name;
    private String purpose;
    private double highValue;
    private double lowValue;

    public searchMaterialDTO(String purpose, String name, double highValue, double lowValue) {
        this.name = name;
        this.purpose = purpose;
        this.highValue = highValue;
        this.lowValue = lowValue;
    }

    public String getName() {
        return name;
    }

    public String getPurpose() {
        return purpose;
    }

    public double getHighValue() {
        return highValue;
    }

    public double getLowValue() {
        return lowValue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setHighValue(double highValue) {
        this.highValue = highValue;
    }

    public void setLowValue(double lowValue) {
        this.lowValue = lowValue;
    }
}
