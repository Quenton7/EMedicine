package com.example.emedicine;

public class DBUsers {
    String username,pass,name,contact;

    public DBUsers() {
    }

    public DBUsers(String username, String pass, String name, String contact) {
        this.username = username;
        this.pass = pass;
        this.name = name;
        this.contact = contact;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
