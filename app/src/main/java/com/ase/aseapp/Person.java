package com.ase.aseapp;

/**
 * Created by alex on 31.01.17.
 *
 * This are the parameters which define personal data of user
 */

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import android.app.Activity;


public class Person implements Serializable{

    @SerializedName("groupId")
    @Expose
    private String groupID;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("userId")
    @Expose
    private String userID;
    @SerializedName("type")
    @Expose
    private String type;

    /*private int errorCode;
    private int status = 1;
    private String error;*/

    public Person(String groupID, String email, String token, String userID, String type){
        this.groupID = groupID;
        this.email = email;
        this.token = token;
        this.userID = userID;
        this.type = type;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
