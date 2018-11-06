package com.image.viever.controller.actionpanel;

import com.image.viever.ImageFileManager;
import com.image.viever.events.EventManager;
import com.image.viever.events.impl.ImageLoadedEvent;
import com.image.viever.model.ViewedImagesModel;
import com.image.viever.view.actionpanel.SwitchImagePanel;

import java.io.File;
import java.util.Optional;

public class SwitchImagePanelController {


    private SwitchImagePanel switchImagePanel;

    private ViewedImagesModel viewedImages;

    public SwitchImagePanelController(final SwitchImagePanel switchImagePanel, final ViewedImagesModel viewedImages) {
        this.switchImagePanel = switchImagePanel;
        this.viewedImages = viewedImages;
    }

    public void init() {
        switchImagePanel.getPreviousImageButton().addActionListener(e -> {
            Optional<String> previousImagePath = viewedImages.getPreviousImagePath();
            loadImage(previousImagePath);
        });

        switchImagePanel.getNextImageButton().addActionListener(e -> {
            Optional<String> nextImagePath = viewedImages.getNextImagePath();
            loadImage(nextImagePath);
        });
    }

    private void loadImage(Optional<String> imagePath) {
        imagePath
            .map(File::new)
            .map(file -> ImageFileManager.loadImage(file))
            .ifPresent(imageWrapper -> {
                viewedImages.setCurrentImage(imageWrapper);
                EventManager.getInstance().fireEvent(new ImageLoadedEvent(imageWrapper));
            });
    }
}
