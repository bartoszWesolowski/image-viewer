package com.image.viever.controller.menu;

import com.image.viever.events.Event;
import com.image.viever.events.EventManager;
import com.image.viever.events.impl.ImageGalleryAddedEvent;
import com.image.viever.model.UserSettingsManager;
import com.image.viever.model.UserSettingsModificationException;
import com.image.viever.model.ViewedImagesModel;
import com.image.viever.view.menu.GalleriesMenu;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class GalleriesMenuController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GalleriesMenuController.class);

    private final UserSettingsManager userSettingsManager = UserSettingsManager.getInstance();

    private GalleriesMenu galleriesMenu;

    public GalleriesMenuController(final GalleriesMenu galleriesMenu) {
        this.galleriesMenu = galleriesMenu;
    }

    public void init() {
        galleriesMenu.getCopyGallery().addActionListener(e -> {
            CopyGalleryFrameController copyGalleryFrameController = new CopyGalleryFrameController();
            copyGalleryFrameController.open();
        });

        galleriesMenu.getCreateGallery().addActionListener(e -> {
            String galleryName = JOptionPane.showInputDialog("Enter gallery name");
            if (userSettingsManager.getSettings().hasImageGallery(galleryName)) {
                JOptionPane.showMessageDialog(galleriesMenu, "Gallery with that name already exists.");
            } else if (StringUtils.isBlank(galleryName)) {
                JOptionPane.showMessageDialog(galleriesMenu, "Name can not be empty");
            } else {
                this.createImageGallery(galleryName);
            }
        });
    }

    private void createImageGallery(String label) {
        try {
            userSettingsManager.createGallery(label);
            EventManager.getInstance().fireEvent(new ImageGalleryAddedEvent(label));
        } catch (UserSettingsModificationException e) {
            LOGGER.error("Failed to create gallery", e);
            JOptionPane.showMessageDialog(galleriesMenu, "Failed to create gallery.");
        }
    }

}
