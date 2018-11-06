package com.image.viever.view.menu;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ImageMenu extends JMenu {

    private JMenuItem rotateBy90Degrees = new JMenuItem("Rotate by 90 degrees");

    private JMenuItem rotateBy180Degrees = new JMenuItem("Rotate by 180 degrees");

    private JMenuItem flipHorizontallyMenuItem = new JMenuItem("Flip horizontally");

    private JMenuItem flipVerticallyMenuItem = new JMenuItem("Flip vertically");

    public ImageMenu() {
        super("Image");
        add(rotateBy90Degrees);
        add(rotateBy180Degrees);
        add(flipHorizontallyMenuItem);
        add(flipVerticallyMenuItem);
    }

    public JMenuItem getRotateBy90Degrees() {
        return rotateBy90Degrees;
    }

    public JMenuItem getRotateBy180Degrees() {
        return rotateBy180Degrees;
    }

    public JMenuItem getFlipHorizontallyMenuItem() {
        return flipHorizontallyMenuItem;
    }

    public JMenuItem getFlipVerticallyMenuItem() {
        return flipVerticallyMenuItem;
    }
}
