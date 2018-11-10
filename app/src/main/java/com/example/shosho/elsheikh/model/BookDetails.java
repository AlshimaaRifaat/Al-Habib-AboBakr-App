package com.example.shosho.elsheikh.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookDetails {
    private String title;
    private String description;
    private String cImg;
    private String cDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getcImg() {
        return cImg;
    }

    public void setcImg(String cImg) {
        this.cImg = cImg;
    }

    public String getcDate() {
        return cDate;
    }

    public void setcDate(String cDate) {
        this.cDate = cDate;
    }
}
