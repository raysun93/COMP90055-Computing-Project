package com.project.sun.cancertracker.models;

/**
 * Created by sun on 2/21/2018.
 */

public class LifeVital {
    private int Weight;
    private int Height;
    private int Fat;
    private int BloodHigh;
    private int BloodLow;
    private String Date;

    public LifeVital(){
    }

    public LifeVital(int Weight,int Height, int Fat, int BloodHigh, int BloodLow, String Date){
        this.Weight = Weight;
        this.Height = Height;
        this.Fat = Fat;
        this.BloodHigh = BloodHigh;
        this.BloodLow = BloodLow;
        this.Date = Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setBloodHigh(int bloodHigh) {
        BloodHigh = bloodHigh;
    }

    public void setBloodLow(int bloodLow) {
        BloodLow = bloodLow;
    }

    public void setFat(int fat) {
        Fat = fat;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public String getDate() {
        return Date;
    }

    public int getBloodHigh() {
        return BloodHigh;
    }

    public int getBloodLow() {
        return BloodLow;
    }

    public int getFat() {
        return Fat;
    }

    public int getHeight() {
        return Height;
    }

    public int getWeight() {
        return Weight;
    }
}
