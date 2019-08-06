package com.dalililastproject;

public class DataModel {

    private String name;
    private String disc;
    private String image;
    private int id;
    private String icon;
    private String title;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    private int viewType;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private String info;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getDisc() {
        return disc;
    }

    public String getImage() {
        return image;
    }
    public int getId() {
        return id;
    }
}
