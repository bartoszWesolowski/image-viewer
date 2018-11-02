package com.image.viever.view;


import com.image.viever.ImagePanel;
import com.image.viever.controller.ZoomController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Bartosz Wesolowski on 02.11.2018.
 */
public class ZoomPanel extends JPanel {

    private static ImageIcon zoomInIcon = new ImageIcon(ClassLoader.getSystemResource("zoomIn.png"));
    private static ImageIcon zoomOutIcon = new ImageIcon(ClassLoader.getSystemResource("zoomOut.png"));


    private JLabel zoomValueLabel = new JLabel();

    private final JButton zoomOutButton = new JButton(zoomOutIcon);

    private final JButton zoomInButton = new JButton(zoomInIcon);

    private final JButton zoomReset = new JButton("Reset");

    private final JSlider slider = new JSlider(10, 500, 100);

    public ZoomPanel() {
        setLayout(new GridLayout(0,1));


        zoomValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        zoomValueLabel.setVerticalAlignment(SwingConstants.CENTER);

        add(zoomValueLabel);
        add(zoomInButton);
        add(zoomOutButton);
        add(slider);
        add(zoomReset);
    }

    public void setZoom(int zoomValue) {
        zoomValueLabel.setText(String.format("Zoom value: %s", zoomValue));
        slider.setValue(zoomValue);
    }

    public JLabel getZoomValueLabel() {
        return zoomValueLabel;
    }

    public JButton getZoomOutButton() {
        return zoomOutButton;
    }

    public JButton getZoomInButton() {
        return zoomInButton;
    }

    public JButton getZoomReset() {
        return zoomReset;
    }

    public JSlider getSlider() {
        return slider;
    }
}
