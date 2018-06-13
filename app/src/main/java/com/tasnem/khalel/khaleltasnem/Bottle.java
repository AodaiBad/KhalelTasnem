package com.tasnem.khalel.khaleltasnem;

/**
 * Created by user on 13/06/2018.
 */

public class Bottle {

    /**
     * the nmessege
     */

    private String messege;

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessege() {

        return messege;
    }

    public String getKeyId() {
        return keyId;
    }

    public String getEmail() {
        return email;
    }

    private String keyId;

    public Bottle() {
        this.messege = messege;
        this.keyId = keyId;
        this.email = email;
    }

    private String email;

    @Override
    public String toString() {
        return "Bottle{" +
                "messege='" + messege + '\'' +
                ", keyId='" + keyId + '\'' +
                ", email=" + email + '\'' +
                '}';

    }
}

