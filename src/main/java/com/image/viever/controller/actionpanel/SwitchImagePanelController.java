package com.image.viever.controller.actionpanel;

import com.image.viever.utils.ImageFileManager;
import com.image.viever.events.EventManager;
import com.image.viever.events.EventTypes;
import com.image.viever.events.impl.ImageLoadedEvent;
import com.image.viever.model.ViewedImagesModel;
import com.image.viever.view.actionpanel.SwitchImagePanel;

import java.io.File;
import java.util.Optional;

public class SwitchImagePanelController {

    private static final int LEFT_ARROW_KEY_CODE = 37;

    private static final int RIGHT_ARROW_KEY_CODE = 39;

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

        EventManager eventManager = EventManager.getInstance();
        eventManager.add(EventTypes.KEY_PRESSED.eventListenerForType( e -> {
            int code = (int) e.getData();
            if (code == LEFT_ARROW_KEY_CODE) {
                switchImagePanel.getPreviousImageButton().doClick();
            } else if (code == RIGHT_ARROW_KEY_CODE){
                switchImagePanel.getNextImageButton().doClick();
            }
        }));
    }

    private void loadImage(Optional<String> imagePath) {
        imagePath
            .map(File::new)
            .map(file -> ImageFileManager.loadImage(file))
            .ifPresent(imageWrapper -> {
                viewedImages.setBaseImageVersion(imageWrapper);
                EventManager.getInstance().fireEvent(new ImageLoadedEvent(imageWrapper));
            });
    }
}
