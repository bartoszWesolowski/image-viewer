package com.image.viever.view;

import com.image.viever.ImagePanel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * Created by Bartosz Wesolowski on 04.11.2018.
 */
public class ImageScrollPanel extends JScrollPane {

    private ImagePanel imagePanel = new ImagePanel();

    public ImageScrollPanel(final ImagePanel imagePanel) {
        super(imagePanel);
        this.imagePanel = imagePanel;
    }

    public ImageScrollPanel() {
        setViewportView(imagePanel);
        setBorder(new EtchedBorder());
    }

    public void updateZoom(int zoom) {
        imagePanel.zoom(zoom);
        getViewport().revalidate();
    }
}
