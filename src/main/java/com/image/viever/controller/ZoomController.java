package com.image.viever.controller;

import com.image.viever.ImagePanel;
import com.image.viever.view.ZoomPanel;

import javax.naming.ldap.PagedResultsControl;
import javax.swing.*;

/**
 * Created by Bartosz Wesolowski on 02.11.2018.
 */
public class ZoomController {

    private static final int ZOOM_STEP = 10;

    private static final int ZOOM_INITIAL_VALUE = 100;

    private int zoomValue = ZOOM_INITIAL_VALUE;

    private ZoomPanel zoomPanel;

    private ImagePanel imagePanel;

    private JScrollPane scroolPanel;

    //TODO: FIX ARGUMENTS PASSED IN CONSTRUCTOR HERE
    public ZoomController(final ZoomPanel zoomPanel, final ImagePanel imagePanel, final JScrollPane scroolPanel) {
        this.zoomPanel = zoomPanel;
        this.imagePanel = imagePanel;
        this.scroolPanel = scroolPanel;
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

    private void updateZoom() {
        zoomPanel.setZoom(zoomValue);
        imagePanel.zoom(zoomValue);
        scroolPanel.getViewport().revalidate();
    }

}
