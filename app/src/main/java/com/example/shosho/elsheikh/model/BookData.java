package com.example.shosho.elsheikh.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookData {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("c_img")
    @Expose
    private String cImg;
    @SerializedName("c_date")
    @Expose
    private String cDate;
    @SerializedName("c_book")
    @Expose
    private String cBook;

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

    public String getCImg() {
        return cImg;
    }

    public void setCImg(String cImg) {
        this.cImg = cImg;
    }

    public String getCDate() {
        return cDate;
    }

    public void setCDate(String cDate) {
        this.cDate = cDate;
    }

    public String getCBook() {
        return cBook;
    }

    public void setCBook(String cBook) {
        this.cBook = cBook;
    }
}