package ru.inno.stc16.caching.entity;

import java.io.Serializable;

public class Course implements Serializable {
    private static int LAST_ID = 0;

    private int id;
    private String title;

    public Course(String title) {
        this.id = LAST_ID++;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
