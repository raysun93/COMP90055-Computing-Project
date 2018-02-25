package com.project.sun.cancertracker.models;

/**
 * Created by sun on 2/21/2018.
 */

public class Bristol {
    private String Amount;
    private int Score;
    private String DateTime;
    public Bristol(){
    }

    public Bristol(String Amount, int Score, String DateTime){
        this.Amount = Amount;
        this.Score = Score;
        this.DateTime = DateTime;
    }

    public void setScore(int score) {
        this.Score = score;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public int getScore() {
        return Score;
    }

    public String getAmount() {
        return Amount;
    }

    public String getDateTime() {
        return DateTime;
    }
}
