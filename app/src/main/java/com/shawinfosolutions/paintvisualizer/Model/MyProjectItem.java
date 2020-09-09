package com.shawinfosolutions.paintvisualizer.Model;

import java.io.Serializable;

public class MyProjectItem implements Serializable {

    String title;
    String colors;
    String pictures;
    String products;
    String projectImgLink;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getProjectImgLink() {
        return projectImgLink;
    }

    public void setProjectImgLink(String projectImgLink) {
        this.projectImgLink = projectImgLink;
    }
}
