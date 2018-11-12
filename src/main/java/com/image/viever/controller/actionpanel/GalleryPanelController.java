package com.image.viever.controller.actionpanel;

import com.image.viever.model.UserSettingsManager;
import com.image.viever.model.UserSettingsModificationException;
import com.image.viever.model.ViewedImagesModel;
import com.image.viever.utils.MessagesPresenter;
import com.image.viever.view.actionpanel.GalleryPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class GalleryPanelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GalleryPanelController.class);

    private UserSettingsManager userSettingsManager = UserSettingsManager.getInstance();

    private GalleryPanel galleryPanel;

    private ViewedImagesModel viewedImagesModel;

    private MessagesPresenter messagesPresenter;

    public GalleryPanelController(final GalleryPanel galleryPanel, final ViewedImagesModel viewedImagesModel) {
        this.galleryPanel = galleryPanel;
        this.viewedImagesModel = viewedImagesModel;
        this.messagesPresenter = new MessagesPresenter(galleryPanel);
    }

    public void init() {
        galleryPanel.getAddToGalleryButton().addActionListener(e -> {
            if (viewedImagesModel.hasCurrentImage()) {
                addToGallery();
            }
        });
    }

    private void addToGallery() {
        galleryPanel.getSelectedGallery()
                .ifPresent(gallery -> {
                    File originalFile = viewedImagesModel.getOriginalImage().getOriginalFile();
                    try {
                        userSettingsManager.addFileToGallery(gallery.getId(), originalFile.getAbsolutePath());
                        messagesPresenter.showInfoDialog("Success", "Image added to gallery");
                    } catch (UserSettingsModificationException e) {
                        messagesPresenter.showErrorDialog("Fail", "Failed to add image to gallery.");
                        LOGGER.error("Failed to add image to gallery", e);
                    }
                });
    }
}
