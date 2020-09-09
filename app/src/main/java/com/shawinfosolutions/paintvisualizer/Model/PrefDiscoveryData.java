package com.shawinfosolutions.paintvisualizer.Model;

import java.util.List;

public class PrefDiscoveryData {

    public  PrefDiscoveryData(){

    }

    private String title;
    private String imageLink;
    private String description;

    private List<ColorData> colors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<ColorData> getColors() {
        return colors;
    }

    public void setColors(List<ColorData> colors) {
        this.colors = colors;
    }
}
