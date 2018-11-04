package com.image.viever.controller;

import com.image.viever.view.ImageViewerFrame;
import com.image.viever.view.ZoomPanel;
import com.image.viever.view.ImageScrollPanel;

/**
 * Created by Bartosz Wesolowski on 02.11.2018.
 */
public class ZoomController {

    private static final int ZOOM_STEP = 10;

    private static final int ZOOM_INITIAL_VALUE = 100;

    private int zoomValue = ZOOM_INITIAL_VALUE;

    private ZoomPanel zoomPanel;

    private ImageScrollPanel imageScrollPanel;

    //TODO: FIX ARGUMENTS PASSED IN CONSTRUCTOR HERE


    public ZoomController(ImageViewerFrame mainFrame) {
        this(mainFrame.getZoomPanel(), mainFrame.getImageScrollPanel());
    }

    public ZoomController(final ZoomPanel zoomPanel, final ImageScrollPanel imageScrollPanel) {
        this.zoomPanel = zoomPanel;
        this.imageScrollPanel = imageScrollPanel;
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
        //TODO: implement me
    }

    private void updateZoom() {
        zoomPanel.setZoom(zoomValue);
        imageScrollPanel.updateZoom(zoomValue);
    }

}
