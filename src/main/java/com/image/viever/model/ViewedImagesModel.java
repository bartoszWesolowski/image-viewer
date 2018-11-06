package com.image.viever.model;


import com.image.viever.ImageWrapper;

import java.util.LinkedHashSet;

public class ViewedImagesModel {

    private LinkedHashSet<String> viewedFilesPaths = new LinkedHashSet<>();

    private ImageWrapper currentImage;

    public void closeCurrentImage() {
        //TODO: implement close image operation
    }

    public boolean hasCurrentImage() {
        return currentImage != null;
    }

    public void setCurrentImage(ImageWrapper imageWrapper) {
        this.currentImage = imageWrapper;
    }

    public ImageWrapper getCurrentImage() {
        return currentImage;
    }

}
