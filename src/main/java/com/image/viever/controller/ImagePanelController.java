package com.image.viever.controller;

import com.image.viever.events.Event;
import com.image.viever.events.EventListener;
import com.image.viever.events.EventManager;
import com.image.viever.events.EventTypes;
import com.image.viever.view.ImageScrollPanel;

public class ImagePanelController {

    private final EventManager eventManager = EventManager.getInstance();

    private ImageScrollPanel imageScrollPanel;

    public ImagePanelController(final ImageScrollPanel imageScrollPanel) {
        this.imageScrollPanel = imageScrollPanel;
    }

    public void init() {
        eventManager.add(new EventListener() {
            @Override
            public boolean accepts(final Event event) {
                return event.getId() == EventTypes.IMAGE_CLOSED;
            }

            @Override
            public void handle(final Event event) {
                imageScrollPanel.clearImage();
            }
        });
    }
}
