package com.shawinfosolutions.paintvisualizer.Model;

import java.io.Serializable;

public class ProductList implements Serializable {

    String name;
    String imageLink;
    String description;
    String uses;
    String type;
    String color;
    String finish;
    String recommended;
    String mixingRatio;
    String colorName;
    String hexColorCode;


    String intentVal;

    public String getIntentVal() {
        return intentVal;
    }

    public void setIntentVal(String intentVal) {
        this.intentVal = intentVal;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getHexColorCode() {
        return hexColorCode;
    }

    public void setHexColorCode(String hexColorCode) {
        this.hexColorCode = hexColorCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUses() {
        return uses;
    }

    public void setUses(String uses) {
        this.uses = uses;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public String getRecommended() {
        return recommended;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }

    public String getMixingRatio() {
        return mixingRatio;
    }

    public void setMixingRatio(String mixingRatio) {
        this.mixingRatio = mixingRatio;
    }
}
