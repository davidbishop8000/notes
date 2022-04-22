package com.skynet.notes;

import java.io.File;

public class Note {
    private String title;
    private String date;
    private File imgFile;

    public Note(String title, String date, File imgFile){
        this.title = title;
        this.date = date;
        this.imgFile = imgFile;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setImgFile(File imgFile){
        this.imgFile = imgFile;
    }

    public String getTitle(){
        return this.title;
    }
    public String getDate(){
        return this.date;
    }
    public File getImgFile(){
        return this.imgFile;
    }
}
