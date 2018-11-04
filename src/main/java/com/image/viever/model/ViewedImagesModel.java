package com.image.viever.model;


import com.image.viever.ImageFileManager;
import com.image.viever.ImageWrapper;
import com.image.viever.events.Event;
import com.image.viever.events.EventManager;
import com.image.viever.events.EventTypes;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class ViewedImagesModel {

    private LinkedHashSet<String> vievedFilesPaths = new LinkedHashSet<>();

    private ImageWrapper currentImage;

    public void setCurrentImage(ImageWrapper imageWrapper) {
        this.currentImage = imageWrapper;
    }

    public LinkedHashSet<String> getVievedFilesPaths() {
        return vievedFilesPaths;
    }

    public ImageWrapper getCurrentImage() {
        return currentImage;
    }
}
