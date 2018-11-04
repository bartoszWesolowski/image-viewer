package com.image.viever.view.menu;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ImageMenu extends JMenu {

    private JMenuItem changeSizeMenuItem = new JMenuItem("Change Size");

    private JMenuItem rotateBy90Degrees = new JMenuItem("Rotate by 90 degrees");

    private JMenuItem rotateBy180Degrees = new JMenuItem("Rotate by 180 degrees");

    private JMenuItem rotateMenuItem = new JMenuItem("Rotate...");

    private JMenuItem flipHorizontallyMenuItem = new JMenuItem("Flip horizontally");

    private JMenuItem flipVerticallyMenuItem = new JMenuItem("Flip vertically");

    public ImageMenu() {
        super("Image");
        add(changeSizeMenuItem);
        add(rotateBy90Degrees);
        add(rotateBy180Degrees);
        add(rotateMenuItem);
        add(flipHorizontallyMenuItem);
        add(flipVerticallyMenuItem);
    }
}
