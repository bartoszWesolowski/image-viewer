package com.image.viever.controller.actionpanel;

import com.image.viever.events.Event;
import com.image.viever.events.EventListener;
import com.image.viever.events.EventManager;
import com.image.viever.events.EventTypes;
import com.image.viever.events.impl.ZoomChangedEvent;
import com.image.viever.view.ImageViewerFrame;
import com.image.viever.view.actionpanel.ZoomPanel;
import com.image.viever.view.ImageScrollPanel;

public class ZoomController {

    private static final int ZOOM_STEP = 10;

    private static final int ZOOM_INITIAL_VALUE = 100;

    private int zoomValue = ZOOM_INITIAL_VALUE;

    private ZoomPanel zoomPanel;

    public ZoomController(final ZoomPanel zoomPanel) {
        this.zoomPanel = zoomPanel;
    }

    public void init() {

        zoomPanel.getZoomInButton().addActionListener(e -> {
            zoomValue += ZOOM_STEP;
            updateZoom();
        });

        zoomPanel.getZoomOutButton().addActionListener(e -> {
            zoomValue -= ZOOM_STEP;
            updateZoom();
        });

        zoomPanel.getZoomReset().addActionListener(e -> {
            zoomValue = ZOOM_INITIAL_VALUE;
            updateZoom();
        });

        zoomPanel.getSlider().addChangeListener(e -> {
            zoomValue = zoomPanel.getSlider().getValue();
            updateZoom();
        });
    }

    public void addFileChangedListener() {
        EventManager.getInstance().add(new EventListener() {
            @Override
            public boolean accepts(final Event event) {
                return event.getId() == EventTypes.IMAGE_LOADED;
            }

            @Override
            public void handle(final Event event) {
                zoomValue = ZOOM_INITIAL_VALUE;
            }
        });
    }

    private void updateZoom() {
        zoomPanel.setZoom(zoomValue);
        EventManager.getInstance().fireEvent(new ZoomChangedEvent(zoomValue));
    }

}
