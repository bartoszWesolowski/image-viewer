package com.image.viever.controller.menu;

import com.image.viever.model.ImageGallery;
import com.image.viever.utils.FileUtil;
import com.image.viever.utils.MessagesPresenter;
import com.image.viever.utils.PathPicker;
import com.image.viever.view.ProgressBarFrame;
import com.image.viever.view.menu.CopyGalleryFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class CopyGalleryFrameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CopyGalleryFrameController.class);


    private CopyGalleryFrame copyGalleryFrame = new CopyGalleryFrame();

    private PathPicker pathPicker = new PathPicker();

    private MessagesPresenter messagesPresenter = new MessagesPresenter(copyGalleryFrame);

    private File destinationFile;

    public CopyGalleryFrameController() {
        bindEvents();
    }

    public void open() {
        copyGalleryFrame.setVisible(true);
    }

    private void bindEvents() {
        copyGalleryFrame.getSelectDestinationPathButton()
                .addActionListener(e -> selectDestinationDirectory());

        copyGalleryFrame.getSaveButton().addActionListener(e -> copyGallery());

        copyGalleryFrame.getCancelButton().addActionListener(e -> close());
    }

    private void selectDestinationDirectory() {
        pathPicker.getDirectory(copyGalleryFrame)
                .ifPresent(path -> {
                    destinationFile = path;
                    copyGalleryFrame.setDestinationPath(destinationFile.getAbsolutePath());
                });
    }

    private void copyGallery() {
        ImageGallery selected = copyGalleryFrame.getSelectedItem();
        if (selected != null && destinationFile != null) {
            if (selected.getImagesPaths().isEmpty()) {
                messagesPresenter.showWarningDialog("Empty gallery", "No images to copy");
                return;
            }
            ProgressBarFrame progressBarFrame = new ProgressBarFrame("Copying image gallery", selected.getImagesPaths().size());
            progressBarFrame.open();
            selected.getImagesPaths()
                    .forEach(image -> {
                        progressBarFrame
                                .addMessage("Copying file : " + image)
                                .operationCalculated();
                        copyGalleryItem(image);
                    });
            messagesPresenter.showInfoDialog("Operation finished successfully", "");
        } else {
            messagesPresenter.showWarningDialog("Unable to copy gallery", "Please select destination path and Image gallery to copy.");
        }
    }

    private void copyGalleryItem(final String imagePath) {
        try {
            LOGGER.debug("Copying image {} to {}", imagePath, destinationFile.getAbsolutePath());
            FileUtil.copyFile(imagePath, destinationFile);
        } catch (IOException ex) {
            LOGGER.error("Failed to copy image {} to {}", imagePath, destinationFile.getAbsolutePath(), ex);
            messagesPresenter.showErrorDialog("Copying gallery images to selected directory failed", "");
        }
    }

    private void close() {
        copyGalleryFrame.dispose();
    }
}
