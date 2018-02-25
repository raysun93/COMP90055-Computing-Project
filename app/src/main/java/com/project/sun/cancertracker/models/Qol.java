package com.project.sun.cancertracker.models;

/**
 * Created by sun on 2/11/2018.
 */

public class Qol {
    private String Title;

    public Qol(){
    }

    public Qol(String Title){
        this.Title = Title;
    }

    public String getTitle(){
        return this.Title;
    }

    public void setTitle(String Title){
        this.Title = Title;
    }
}
