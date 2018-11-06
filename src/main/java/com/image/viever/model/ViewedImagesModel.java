package com.image.viever.model;


import com.image.viever.ImageWrapper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

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
        return Optional.ofNullable(currentImage)
                .map(imageWrapper -> imageWrapper.getOriginalFile())
                .map(file -> file.getAbsolutePath())
                .filter(currentImagePath -> viewedFilesPaths.contains(currentImagePath))
                .map(currentImagePath -> viewedFilesPaths.indexOf(currentImagePath));
    }

    public void setCurrentImage(ImageWrapper imageWrapper) {
        this.currentImage = imageWrapper;
    }

    public ImageWrapper getCurrentImage() {
        return currentImage;
    }

}
