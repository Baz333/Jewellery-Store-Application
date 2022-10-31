package com.example.demo;

import java.io.Serializable;

public class MaterialComponent implements Serializable {

    //fields
    private MaterialComponent nextComponent;
    private String name;
    private String desc;
    private int weight = 0;
    private String quality;



    //constructor
    public MaterialComponent(MaterialComponent nextComponent, String name, String desc, int weight, String quality) {
        setNextComponent(nextComponent);
        setName(name);
        setDesc(desc);
        setWeight(weight);
        setQuality(quality);
    }



    //getters
    public MaterialComponent getNextComponent() {
        return nextComponent;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getWeight() {
        return weight;
    }

    public String getQuality() {
        return quality;
    }



    //setters
    public void setNextComponent(MaterialComponent nextComponent) {
        this.nextComponent = nextComponent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

}
