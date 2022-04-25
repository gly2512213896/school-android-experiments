package com.guo.Ex7;

public class IconRelation {
    private int imageId;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public IconRelation(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }
}
