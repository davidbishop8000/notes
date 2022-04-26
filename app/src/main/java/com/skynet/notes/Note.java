package com.skynet.notes;

import java.io.File;

public class Note {
    private long id;
    private String title;
    private String date;
    private File imgFile;
    private String text;
    private int textColor;
    private int textSize;

    public Note(long id, String title, String date, File imgFile, String text, int textColor, int textSize){
        this.id = id;
        this.title = title;
        this.date = date;
        this.imgFile = imgFile;
        this.text = text;
        this.textColor = textColor;
        this.textSize = textSize;
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
    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
    public void setTextSize(int textSize) {
        this.textSize = textSize;
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
    public int getTextColor() {
        return this.textColor;
    }
    public int getTextSize() {
        return this.textSize;
    }
}
