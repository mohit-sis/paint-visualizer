package com.shawinfosolutions.paintvisualizer.Model;

public class ColorData {


    public ColorData() {

    }

    private Integer color;

    private String colorName;
    private String hex;

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public void setColor(String id) {
    }
}
