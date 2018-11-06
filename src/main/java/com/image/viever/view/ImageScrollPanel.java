package com.image.viever.view;

import com.image.viever.ImageWrapper;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class ImageScrollPanel extends JScrollPane {

    private ImagePanel imagePanel = new ImagePanel();

    public ImageScrollPanel() {
        setViewportView(imagePanel);
        setBorder(new EtchedBorder());
    }

    public void setDisplayedImage(ImageWrapper newImageVersion) {
        imagePanel.modifyDisplayedImage(newImageVersion);
        revalidate();
    }

    public void clearImage() {
        imagePanel.clearImage();
    }
}
