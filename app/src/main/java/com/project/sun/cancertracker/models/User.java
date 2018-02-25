package com.project.sun.cancertracker.models;

/**
 * Created by sun on 1/31/2018.
 */

public class User {
    private String userName;
    private String passWord;

    public User(){
    }

    public User(String userName, String passWord){
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName(){
        return this.userName;
    }

    public String getPassWord(){
        return this.passWord;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setPassWord(String passWord){
        this.passWord = passWord;
    }
}
