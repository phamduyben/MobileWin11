package com.example.baitap2_29_3;

public class User {
    private String name;
    private int resourceIDAnh;

    public User(String name, int resourceIDAnh) {
        this.name = name;
        this.resourceIDAnh = resourceIDAnh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResourceIDAnh() {
        return resourceIDAnh;
    }

    public void setResourceIDAnh(int resourceIDAnh) {
        this.resourceIDAnh = resourceIDAnh;
    }
}

