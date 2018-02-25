package com.project.sun.cancertracker.models;

/**
 * Created by sun on 2/18/2018.
 */

public class qolScore {

    private String score;

    public qolScore(){
    }

    public qolScore(String score){

        this.score = score;
    }

    public void setScore(String score) {
        this.score = score;
    }


    public String getScore() {
        return this.score;
    }
}
