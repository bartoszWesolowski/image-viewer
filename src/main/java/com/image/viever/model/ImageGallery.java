package com.image.viever.model;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class ImageGallery {

    private String id;

    private String label;

    private List<String> imagesPaths = new ArrayList<>();

    public String getId() {
        return id;
    }

    void setId(final String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    void setLabel(final String label) {
        this.label = label;
    }

    public List<String> getImagesPaths() {
        return ImmutableList.copyOf(imagesPaths);
    }

    void addPath(String path) {
        imagesPaths.add(path);
    }
}
