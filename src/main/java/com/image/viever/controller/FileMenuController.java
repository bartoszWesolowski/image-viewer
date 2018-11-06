package com.image.viever.controller;

import com.image.viever.ImageFileManager;
import com.image.viever.ImageWrapper;
import com.image.viever.events.Event;
import com.image.viever.events.EventManager;
import com.image.viever.events.EventTypes;
import com.image.viever.events.impl.ImageClosedEvent;
import com.image.viever.events.impl.ImageLoadedEvent;
import com.image.viever.model.ViewedImagesModel;
import com.image.viever.utils.MessagesPresenter;
import com.image.viever.utils.PathPicker;
import com.image.viever.view.menu.FileMenu;

import java.io.File;

/**
 * Created by Bartosz Wesolowski on 04.11.2018.
 */
public class FileMenuController {

    final static int OK_EXIT_STATUS = 0;

    private final PathPicker pathPicker = new PathPicker();

    private final EventManager eventManager = EventManager.getInstance();

    private FileMenu fileMenu;

    private ViewedImagesModel viewedImagesModel;

    private MessagesPresenter messagesPresenter;

    public FileMenuController(final FileMenu editMenu, final ViewedImagesModel viewedImagesModel) {
        this.fileMenu = editMenu;
        this.viewedImagesModel = viewedImagesModel;
        this.messagesPresenter = new MessagesPresenter(editMenu);
    }

    public void init() {
        bindActionListeners();
    }

    private void bindActionListeners() {
        fileMenu.getOpenMenuItem()
                .addActionListener( e -> {
                    pathPicker.getPath(fileMenu)
                            .ifPresent(imageFile -> setNewImage(imageFile));

                });

        fileMenu.getOpenExampleMenuItem()
                .addActionListener(e -> {
                    File example = new File("monkey.jpg");
                    setNewImage(example);
                });

        fileMenu.getCloseMenuItem()
                .addActionListener(e -> {
                    eventManager.fireEvent(new ImageClosedEvent(viewedImagesModel.getCurrentImage()));
                    viewedImagesModel.closeCurrentImage();
                });

        fileMenu.getQuitMenuItem()
                .addActionListener(e -> System.exit(OK_EXIT_STATUS));

        fileMenu.getSaveAsMenuItem()
                .addActionListener(e -> {
                    if (viewedImagesModel.hasCurrentImage()) {
                        pathPicker.getPath(fileMenu)
                                .ifPresent(destinationFile -> {
                                    ImageFileManager.saveImage(viewedImagesModel.getCurrentImage(), destinationFile);
                                    messagesPresenter.showInfoDialog("File saved", "Saved image to: " + destinationFile.getAbsolutePath());
                                });
                    } else {
                        messagesPresenter.showWarningDialog("File not saved", "There is no image currently available for saving.");
                    }
                });
    }

    private void setNewImage(File file) {
        ImageWrapper imageWrapper = ImageFileManager.loadImage(file);
        if (imageWrapper == null) {
            EventManager.getInstance().fireEvent(new Event(EventTypes.INVALID_FILE_LOADED, file));
        } else {
            viewedImagesModel.setCurrentImage(imageWrapper);
            EventManager.getInstance().fireEvent(new ImageLoadedEvent(imageWrapper));
        }
    }
}
