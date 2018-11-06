package com.image.viever.view;

import com.image.viever.ImagePanel;
import com.image.viever.ImageWrapper;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class ImageScrollPanel extends JScrollPane {

    private ImagePanel imagePanel = new ImagePanel();

    public ImageScrollPanel() {
        setViewportView(imagePanel);
        setBorder(new EtchedBorder());
    }

    public void updateZoom(int zoom) {
        imagePanel.zoom(zoom);
    }

    public void adjustImageToCurrentWindowSize() {
        imagePanel.adjustToCurrentImagePanelSize();
    }

    public void displayNewImage(ImageWrapper imageWrapper) {
        imagePanel.displayNewImage(imageWrapper);
    }

    public void clearImage() {
        imagePanel.clearImage();
    }
}
