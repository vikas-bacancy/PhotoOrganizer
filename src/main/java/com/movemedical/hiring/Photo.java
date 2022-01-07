package com.movemedical.hiring;

import java.util.Date;

public class Photo {

    private Date date;
    private String name;
    private String city;
    private String newName;
    private String extension;

    public Photo(Date date, String name, String city, String extension) {
        this.date = date;
        this.name = name;
        this.city = city;
        this.extension = extension;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
