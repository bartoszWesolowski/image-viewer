package com.image.viever.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserSettings {

    private String defaultImagesPath;

    private List<ImageGallery> imageGalleries = new ArrayList<>();

    public boolean hasImageGallery(String label) {
       return imageGalleries.stream()
                .anyMatch(gallery -> StringUtils.equals(gallery.getLabel(), label));
    }

    public String getDefaultImagesPath() {
        return defaultImagesPath;
    }

    public List<ImageGallery> getImageGalleries() {
        return imageGalleries;
    }

}
