package com.ase.aseapp;

import java.util.List;

/**
 * Created by alex on 31.01.17.
 */

public class ServerEvent {
    private Person person;
    private List<Session> groupSessions;
    private QRBasisCode qrBasisCode;

    public ServerEvent(QRBasisCode qrBasisCode) {
        this.qrBasisCode = qrBasisCode;
    }

    public ServerEvent(Person person) {
        this.person = person;
    }

    public ServerEvent(List<Session> groupSessions) {
        this.groupSessions = groupSessions;
    }

    public QRBasisCode getQrBasisCode() {
        return qrBasisCode;
    }

    public void setQrBasisCode(QRBasisCode qrBasisCode) {
        this.qrBasisCode = qrBasisCode;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Session> getGroupSessions() {
        return groupSessions;
    }

    public void setGroupSessions(List<Session> groupSessions) {
        this.groupSessions = groupSessions;
    }
}