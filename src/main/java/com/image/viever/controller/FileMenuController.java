package com.image.viever.controller;

import com.image.viever.ImageFileManager;
import com.image.viever.ImageWrapper;
import com.image.viever.events.Event;
import com.image.viever.events.EventManager;
import com.image.viever.events.EventTypes;
import com.image.viever.model.ViewedImagesModel;
import com.image.viever.utils.PathPicker;
import com.image.viever.view.menu.FileMenu;

import java.io.File;

/**
 * Created by Bartosz Wesolowski on 04.11.2018.
 */
public class FileMenuController {

    private final PathPicker pathPicker = new PathPicker();

    private FileMenu fileMenu;

    private ViewedImagesModel viewedImagesModel;

    public FileMenuController(final FileMenu editMenu, final ViewedImagesModel viewedImagesModel) {
        this.fileMenu = editMenu;
        this.viewedImagesModel = viewedImagesModel;
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
    }

    private void setNewImage(File file) {
        ImageWrapper imageWrapper = ImageFileManager.loadImage(file);
        if (imageWrapper == null) {
            EventManager.getInstance().fireEvent(new Event(EventTypes.INVALID_FILE_LOADED, file));
        } else {
            viewedImagesModel.setCurrentImage(imageWrapper);
            EventManager.getInstance().fireEvent(new Event(EventTypes.IMAGE_CHANGED, imageWrapper));
        }
    }
}
