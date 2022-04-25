package com.skynet.notes;

import java.io.File;

public class Note {
    private long id;
    private String title;
    private String date;
    private File imgFile;
    private String text;

    public Note(long id, String title, String date, File imgFile, String text){
        this.id = id;
        this.title = title;
        this.date = date;
        this.imgFile = imgFile;
        this.text = text;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setImgFile(File imgFile) {
        this.imgFile = imgFile;
    }
    public void setText(String text) {
        this.text = text;
    }

    public long getId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }
    public String getDate() {
        return this.date;
    }
    public File getImgFile() {
        return this.imgFile;
    }
    public String getText() {
        return this.text;
    }
}
