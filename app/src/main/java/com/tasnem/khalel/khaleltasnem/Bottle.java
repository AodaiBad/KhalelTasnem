package com.tasnem.khalel.khaleltasnem;

/**
 * Created by user on 13/06/2018.
 */

public class Bottle {

    private String message;

    private String keyId;

    private String email;

    public Bottle(String message, String keyId) {
        this.message = message;
        this.keyId = keyId;
    }

    public Bottle() {
    }

    public Bottle(String message) {
        this.message = message;
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}



