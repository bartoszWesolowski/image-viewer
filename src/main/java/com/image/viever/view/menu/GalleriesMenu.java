package com.image.viever.view.menu;

import javax.swing.*;

public class GalleriesMenu extends JMenu {

    private JMenuItem copyGallery = new JMenuItem("Copy");

    private JMenuItem createGallery = new JMenuItem("Add gallery");

    public GalleriesMenu() {
        super("Galleries");
        add(copyGallery);
        add(createGallery);
        copyGallery.setToolTipText("Copy all images from gallery to selected directory");
        createGallery.setToolTipText("Create new gallery");
    }

    public JMenuItem getCopyGallery() {
        return copyGallery;
    }

    public JMenuItem getCreateGallery() {
        return createGallery;
    }
}
