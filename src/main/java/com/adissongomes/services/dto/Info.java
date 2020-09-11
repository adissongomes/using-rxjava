package com.adissongomes.services.dto;

public class Info {
    private int id;
    private String info;

    public Info(int id, String info) {
        this.id = id;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return String.format("id %07d - info '%s'", id, info);
    }
}
