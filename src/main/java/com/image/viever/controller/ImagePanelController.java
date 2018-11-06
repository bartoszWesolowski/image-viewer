package com.image.viever.controller;

import com.image.viever.events.Event;
import com.image.viever.events.EventListener;
import com.image.viever.events.EventManager;
import com.image.viever.events.EventTypes;
import com.image.viever.view.ImageScrollPanel;

import static com.image.viever.events.EventTypes.IMAGE_CLOSED;
import static com.image.viever.events.EventTypes.ZOOM_CHANGED;

public class ImagePanelController {

    private final EventManager eventManager = EventManager.getInstance();

    private ImageScrollPanel imageScrollPanel;

    public ImagePanelController(final ImageScrollPanel imageScrollPanel) {
        this.imageScrollPanel = imageScrollPanel;
    }

    public void init() {
        eventManager.add(
                IMAGE_CLOSED.eventListenerForType(e -> imageScrollPanel.clearImage())
        );
        eventManager.add(ZOOM_CHANGED.eventListenerForType(e -> {
            int zoom = (int) e.getData();
            imageScrollPanel.updateZoom(zoom);
        }));
    }
}
