package com.image.viever.controller.menu;

import com.image.viever.utils.ImageFileManager;
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
import org.apache.commons.lang3.StringUtils;


import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

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
                .addActionListener( e ->
                    pathPicker.getPath(fileMenu)
                    .ifPresent(file -> {
                        this.setNewImage(file);
                        viewedImagesModel.clearViewedFiles();
                        File[] images = file.getParentFile().listFiles(new ImageFileFilter());
                        for (File image : images) {
                            viewedImagesModel.addImagePath(image.getPath());
                        }
                    })
                );

        fileMenu.getOpenExampleMenuItem()
                .addActionListener(e -> {
                    File example = new File("monkey.jpg");
                    setNewImage(example);
                });

        fileMenu.getCloseMenuItem()
                .addActionListener(e -> {
                    eventManager.fireEvent(new ImageClosedEvent(viewedImagesModel.getCurrentBaseImageVersion()));
                    viewedImagesModel.closeCurrentImage();
                });

        fileMenu.getQuitMenuItem()
                .addActionListener(e -> System.exit(OK_EXIT_STATUS));

        fileMenu.getSaveAsMenuItem()
                .addActionListener(e -> {
                    if (viewedImagesModel.hasCurrentImage()) {
                        pathPicker.getPath(fileMenu)
                                .ifPresent(destinationFile -> {
                                    ImageFileManager.saveImage(viewedImagesModel.getCurrentBaseImageVersion(), destinationFile);
                                    messagesPresenter.showInfoDialog("File saved", "Saved image to: " + destinationFile.getAbsolutePath());
                                });
                    } else {
                        messagesPresenter.showWarningDialog("File not saved", "There is no image currently available for saving.");
                    }
                });
    }

    private void setNewImage(File file) {
        ImageWrapper loadedImage = ImageFileManager.loadImage(file);
        if (loadedImage == null) {
            EventManager.getInstance().fireEvent(new Event(EventTypes.INVALID_FILE_LOADED, file));
        } else {
            viewedImagesModel.setOriginalImage(loadedImage);
            EventManager.getInstance().fireEvent(new ImageLoadedEvent(loadedImage));
        }
    }

    static class ImageFileFilter implements FilenameFilter {

        private static final List<String> EXTENSIONS = Arrays.asList(
                "gif", "png", "jpg", "jpeg"
        );

        @Override
        public boolean accept(final File dir, final String name) {
            final String lowerCasedName = StringUtils.lowerCase(name);
            return EXTENSIONS.stream()
                    .anyMatch(extension -> StringUtils.endsWith(lowerCasedName, "." + extension));
        }
    }
}
