package com.image.viever.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.*;

public class ImageGallery {

    private String id;

    private String label;

    private Set<String> imagesPaths = new HashSet<>();

    public ImageGallery(String label) {
        this.label = label;
        this.id = UUID.randomUUID().toString() + ":" + Calendar.getInstance().getTimeInMillis();
    }

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

    public Collection<String> getImagesPaths() {
        return ImmutableSet.copyOf(imagesPaths);
    }

    void addPath(String path) {
        imagesPaths.add(path);
    }
}
