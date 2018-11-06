package com.image.viever.model;


import com.image.viever.ImageWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ViewedImagesModel {

    private List<String> viewedFilesPaths = new ArrayList<>();

    private ImageWrapper currentOriginalImage;

    private ImageWrapper currentlyDisplayedImageVersion;

    public void closeCurrentImage() {
        this.currentOriginalImage = null;
        this.currentlyDisplayedImageVersion = null;
    }

    public boolean hasCurrentImage() {
        return currentOriginalImage != null;
    }

    public void clearViewedFiles() {
        viewedFilesPaths.clear();
    }

    public void addImagePath(String path) {
        if (!viewedFilesPaths.contains(path)) {
            viewedFilesPaths.add(path);
        }
    }

    public Optional<String> getNextImagePath() {
        int lastElementIndex = viewedFilesPaths.size() - 1;
        return getCurrentImagePathIndex()
                .filter(index -> index < lastElementIndex)
                .map(index -> index + 1)
                .map(nextElementIndex -> viewedFilesPaths.get(nextElementIndex));
    }

    public Optional<String> getPreviousImagePath() {
        int firstElementIndex = 0;
        return getCurrentImagePathIndex()
                .filter(currentElementIndex -> currentElementIndex > firstElementIndex)
                .map(currentElementIndex -> currentElementIndex - 1)
                .map(previousElementIndex -> viewedFilesPaths.get(previousElementIndex));
    }

    private Optional<Integer> getCurrentImagePathIndex() {
        return Optional.ofNullable(currentOriginalImage)
                .map(imageWrapper -> imageWrapper.getOriginalFile())
                .map(file -> file.getAbsolutePath())
                .filter(currentImagePath -> viewedFilesPaths.contains(currentImagePath))
                .map(currentImagePath -> viewedFilesPaths.indexOf(currentImagePath));
    }

    public void setCurrentOriginalImage(ImageWrapper imageWrapper) {
        this.currentOriginalImage = imageWrapper;
        this.currentlyDisplayedImageVersion = imageWrapper;
    }

    public void setCurrentlyDisplayedImageVersion(final ImageWrapper currentlyDisplayedImageVersion) {
        this.currentlyDisplayedImageVersion = currentlyDisplayedImageVersion;
    }

    public ImageWrapper getCurrentOriginalImage() {
        return currentOriginalImage;
    }

    public ImageWrapper getCurrentlyDisplayedImageVersion() {
        return currentlyDisplayedImageVersion;
    }
}
