package com.image.viever.model;


import com.image.viever.ImageWrapper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class ViewedImagesModel {

    private List<String> viewedFilesPaths = new ArrayList<>();

    private ImageWrapper currentImage;

    public void closeCurrentImage() {
        //TODO: implement close image operation
    }

    public boolean hasCurrentImage() {
        return currentImage != null;
    }

    public void addImagePath(String path) {
        if (!viewedFilesPaths.contains(path)) {
            viewedFilesPaths.add(path);
        }
    }

    public void setCurrentImage(ImageWrapper imageWrapper) {
        this.currentImage = imageWrapper;
    }

    public ImageWrapper getCurrentImage() {
        return currentImage;
    }

}
