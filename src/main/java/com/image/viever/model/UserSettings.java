package com.image.viever.model;

import java.util.ArrayList;
import java.util.List;

public class UserSettings {

    private String defaultImagesPath;

    private List<ImageGallery> imageGalleries = new ArrayList<>();

    public String getDefaultImagesPath() {
        return defaultImagesPath;
    }

    public List<ImageGallery> getImageGalleries() {
        return imageGalleries;
    }
}
