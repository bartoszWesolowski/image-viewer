package com.image.viever.view.menu;

import javax.swing.*;

public class GalleriesMenu extends JMenu {

    private JMenuItem copyGallery = new JMenuItem("Copy");

    public GalleriesMenu() {
        super("Galleries");
        add(copyGallery);
        copyGallery.setToolTipText("Copy all images from gallery to selected directory");
    }

    public JMenuItem getCopyGallery() {
        return copyGallery;
    }
}
