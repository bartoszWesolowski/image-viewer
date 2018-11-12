package com.image.viever.utils;


import java.awt.*;

public class SwingHelper {

    public static void centralizeOnScreen(Component component) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        component.setLocation(d.width / 2 - component.getWidth() / 2, d.height / 2 - component.getHeight() / 2);
    }
}
