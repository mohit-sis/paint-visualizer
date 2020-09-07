package com.shawinfosolutions.paintvisualizer;

public class TouchPoints {

    private double x;
    private  double y;


    private double [] selectedColor;

    public TouchPoints(double x, double y, double [] color) {
        this.x = x;
        this.y = y;
        selectedColor = color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double[] getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(double[] selectedColor) {
        this.selectedColor = selectedColor;
    }


}
