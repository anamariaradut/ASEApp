package com.ase.aseapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by alex on 05.02.17.
 */

public class QRBasisCode implements Serializable {

    @SerializedName("code")
    @Expose
    private String attendanceCode;

    public QRBasisCode(String attendanceCode) {
        this.attendanceCode = attendanceCode;
    }

    public String getAttendanceCode() {
        return attendanceCode;
    }

    public void setAttendanceCode(String attendanceCode) {
        this.attendanceCode = attendanceCode;
    }
}
