package com.ase.aseapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by alex on 04.02.17.
 */

public class Session implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("startDate")
    @Expose
    private String startDate;

    @SerializedName("endDate")
    @Expose
    private String endDate;

    @SerializedName("room")
    @Expose
    private String room;

    @SerializedName("groupId")
    @Expose
    private String groupId;

    public Session(String id, String startDate, String endDate, String room, String groupId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
        this.groupId = groupId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
