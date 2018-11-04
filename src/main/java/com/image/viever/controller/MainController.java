package com.image.viever.controller;

import com.image.viever.ImageWrapper;
import com.image.viever.events.Event;
import com.image.viever.events.EventListener;
import com.image.viever.events.EventManager;
import com.image.viever.events.EventTypes;
import com.image.viever.model.ViewedImagesModel;
import com.image.viever.view.ImageViewerFrame;

public class MainController {

    private ImageViewerFrame mainFrame;

    private ViewedImagesModel viewedImages;

    public MainController(final ImageViewerFrame mainFrame, final ViewedImagesModel viewedImages) {
        this.mainFrame = mainFrame;
        this.viewedImages = viewedImages;
    }

    public void openApp(){
        this.bindEventListeners();
        this.initControllers();
        this.mainFrame.setVisible(true);
    }

    private void bindEventListeners() {
        EventManager eventManager = EventManager.getInstance();
        eventManager.add(new EventListener() {
            @Override
            public boolean accepts(final Event event) {
                return event.getId() == EventTypes.IMAGE_CHANGED;
            }

            @Override
            public void handle(final Event event) {
                ImageWrapper imageWrapper = (ImageWrapper) event.getData();
                mainFrame.setImage(imageWrapper);
            }
        });
    }
    private void initControllers() {
        ZoomController zoomController = new ZoomController(mainFrame);
        zoomController.init();

        MenuController menuController = new MenuController(mainFrame.getMenu(), viewedImages);
        menuController.init();
    }
}
