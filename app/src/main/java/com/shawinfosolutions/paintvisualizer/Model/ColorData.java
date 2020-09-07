package com.shawinfosolutions.paintvisualizer.Model;

public class ColorData {


    public ColorData() {

    }

    private Integer color;

    private String colorName;
    private String hexcodeVal;

    public String getHexcodeVal() {
        return hexcodeVal;
    }

    public void setHexcodeVal(String hexcodeVal) {
        this.hexcodeVal = hexcodeVal;
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
