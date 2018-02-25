package com.project.sun.cancertracker.models;

/**
 * Created by sun on 2/5/2018.
 */

public class Category {
    private String Name;
    private String Image;

    public Category(){
    }

    public Category(String Name, String Image){
        this.Image = Image;
        this.Name = Name;
    }

    public String getName(){
        return this.Name;
    }

    public String getImage(){
        return this.Image;
    }

    public void setName(String Name){
        this.Name = Name;
    }

    public void setImage(String Image){
        this.Image = Image;
    }
}
