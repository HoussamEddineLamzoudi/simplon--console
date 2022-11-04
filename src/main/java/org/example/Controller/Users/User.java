package org.example.Controller.Users;

import java.io.IOException;

public abstract class User {

    String fullName;
    protected String email;
    protected  String psw;

    public User(String fullName, String email, String psw) {
        this.fullName = fullName;
        this.email = email;
        this.psw = psw;
    }

    public User() {

    }

    public abstract User login(String email, String psw) throws IOException;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", psw='" + psw + '\'' +
                '}';
    }
}
