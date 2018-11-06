package com.image.viever.controller;

import com.image.viever.ImageWrapper;
import com.image.viever.controller.actionpanel.ActionPanelController;
import com.image.viever.controller.actionpanel.ZoomController;
import com.image.viever.controller.eventlisteners.ComponentResizedListener;
import com.image.viever.events.Event;
import com.image.viever.events.EventListener;
import com.image.viever.events.EventManager;
import com.image.viever.events.EventTypes;
import com.image.viever.model.ViewedImagesModel;
import com.image.viever.view.ImageViewerFrame;

import java.awt.event.ComponentEvent;

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
                return event.getId() == EventTypes.IMAGE_LOADED;
            }

            @Override
            public void handle(final Event event) {
                ImageWrapper imageWrapper = (ImageWrapper) event.getData();
                mainFrame.displayNewImage(imageWrapper);
            }
        });

        mainFrame.addComponentListener(new ComponentResizedListener() {
            @Override
            public void componentResized(final ComponentEvent e) {
                mainFrame.getImageScrollPanel().adjustImageToCurrentWindowSize();
            }
        });
    }
    private void initControllers() {
        ImagePanelController imagePanelController = new ImagePanelController(mainFrame.getImageScrollPanel());
        imagePanelController.init();

        ActionPanelController actionPanelController = new ActionPanelController(mainFrame.getActionPanel(), viewedImages);
        actionPanelController.init();

        MenuController menuController = new MenuController(mainFrame.getMenu(), viewedImages);
        menuController.init();
    }
}
