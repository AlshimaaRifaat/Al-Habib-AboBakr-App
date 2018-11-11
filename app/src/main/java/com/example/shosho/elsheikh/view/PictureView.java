package com.example.shosho.elsheikh.view;

import com.example.shosho.elsheikh.model.PictureData;

import java.util.List;

public interface PictureView {
    void showPicturesData(List<PictureData>pictureData);
    void error();
}
