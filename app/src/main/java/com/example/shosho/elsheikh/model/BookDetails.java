package com.example.shosho.elsheikh.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookDetails {
    private String Title;
    private String desc;
    private String Img;
    private String Date;
    private String PDFurl;

    public String getPDFurl() {
        return PDFurl;
    }

    public void setPDFurl(String PDFurl) {
        this.PDFurl = PDFurl;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImg(String img) {
        Img = img;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTitle() {
        return Title;
    }

    public String getDesc() {
        return desc;
    }

    public String getImg() {
        return Img;
    }

    public String getDate() {
        return Date;
    }
}
