package com.apex.aslearn.bean;

public class Fruit {
    private String name;
    private int imageID;

    public int getImageID() {
        return imageID;
    }

    public String getName() {
        return name;
    }

    public Fruit(String name, int imageID){
        this.name = name;
        this.imageID = imageID;
    }


}
