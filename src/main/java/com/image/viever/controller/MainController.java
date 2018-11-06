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

import static com.image.viever.events.EventTypes.IMAGE_LOADED;

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
        eventManager.add(
            IMAGE_LOADED.eventListenerForType(e -> {

                ImageWrapper imageWrapper = (ImageWrapper) e.getData();
                mainFrame.displayNewImage(imageWrapper);
            })
        );

    }
    private void initControllers() {
        ImagePanelController imagePanelController = new ImagePanelController(mainFrame.getImageScrollPanel(), viewedImages);
        imagePanelController.init();

        ActionPanelController actionPanelController = new ActionPanelController(mainFrame.getActionPanel(), viewedImages);
        actionPanelController.init();

        MenuController menuController = new MenuController(mainFrame.getMenu(), viewedImages);
        menuController.init();
    }
}
