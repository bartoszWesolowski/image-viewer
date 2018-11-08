package com.image.viever.controller;

import com.image.viever.ImageWrapper;
import com.image.viever.controller.actionpanel.ActionPanelController;
import com.image.viever.controller.actionpanel.ZoomController;
import com.image.viever.controller.eventlisteners.ComponentResizedListener;
import com.image.viever.events.Event;
import com.image.viever.events.EventListener;
import com.image.viever.events.EventManager;
import com.image.viever.events.EventTypes;
import com.image.viever.events.impl.KeyPressedEvent;
import com.image.viever.model.ViewedImagesModel;
import com.image.viever.view.ImageViewerFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.image.viever.events.EventTypes.IMAGE_LOADED;

public class MainController {

    private Logger LOGGER = LoggerFactory.getLogger(MainController.class);

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

        mainFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {
            }

            @Override
            public void keyPressed(final KeyEvent e) {
                LOGGER.info("Key pressed:  {}", e.getKeyCode());
                eventManager.fireEvent(new KeyPressedEvent(e.getKeyCode()));
            }

            @Override
            public void keyReleased(final KeyEvent e) {

            }
        });
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
