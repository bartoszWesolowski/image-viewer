package com.image.viever.controller;

import com.image.viever.ImageWrapper;
import com.image.viever.controller.eventlisteners.ComponentResizedListener;
import com.image.viever.events.EventManager;
import com.image.viever.model.ViewedImagesModel;
import com.image.viever.utils.ImageModifier;
import com.image.viever.view.ImageScrollPanel;

import java.awt.event.ComponentEvent;

import static com.image.viever.events.EventTypes.IMAGE_CLOSED;
import static com.image.viever.events.EventTypes.IMAGE_LOADED;
import static com.image.viever.events.EventTypes.IMAGE_MODIFIED;
import static com.image.viever.events.EventTypes.ZOOM_CHANGED;

public class ImagePanelController {

    private final EventManager eventManager = EventManager.getInstance();

    private final ImageModifier imageModifier = new ImageModifier();

    private ImageScrollPanel imageScrollPanel;

    private ViewedImagesModel viewedImages;

    public ImagePanelController(final ImageScrollPanel imageScrollPanel, final ViewedImagesModel viewedImages) {
        this.imageScrollPanel = imageScrollPanel;
        this.viewedImages = viewedImages;
    }

    public void init() {


        imageScrollPanel.addComponentListener(new ComponentResizedListener() {
            @Override
            public void componentResized(final ComponentEvent e) {
                adjustImageToCurrentWindowSize(viewedImages.getCurrentBaseImageVersion());
            }
        });

        eventManager.add(
                IMAGE_CLOSED.eventListenerForType(e -> {
                    imageScrollPanel.clearImage();
                    viewedImages.closeCurrentImage();
                })
        );

        eventManager.add(IMAGE_LOADED.eventListenerForType(e -> {
            ImageWrapper loadedImage = (ImageWrapper) e.getData();
            adjustImageToCurrentWindowSize(loadedImage);
        }));

        eventManager.add(ZOOM_CHANGED.eventListenerForType(e -> {
            if(viewedImages.hasCurrentImage()) {
                int zoom = (int) e.getData();
                double scale = zoom / 100.0;
                ImageWrapper scaled = imageModifier.resize(viewedImages.getCurrentBaseImageVersion(), scale);
                viewedImages.setCurrentlyDisplayedImageVersion(scaled);
                imageScrollPanel.setDisplayedImage(scaled);
            }
        }));


        eventManager.add(IMAGE_MODIFIED.eventListenerForType(e -> {
            ImageWrapper modified = (ImageWrapper) e.getData();
            viewedImages.setBaseImageVersion(modified);
            imageScrollPanel.setDisplayedImage(modified);
        }));
    }

    private void adjustImageToCurrentWindowSize(ImageWrapper originalImage) {
        if (originalImage != null) {
            ImageWrapper adjustedToImageContainer = imageModifier.adjustToSizeOfContainer(originalImage, imageScrollPanel.getSize());
            viewedImages.setCurrentlyDisplayedImageVersion(adjustedToImageContainer);
            imageScrollPanel.setDisplayedImage(adjustedToImageContainer);
        }
    }
}
