package com.image.viever.view.actionpanel;

import javax.swing.*;
import java.awt.*;

public class ActionPanel extends JPanel {

    private ZoomPanel zoomPanel = new ZoomPanel();

    private SwitchImagePanel switchImagePanel = new SwitchImagePanel();

    public ActionPanel() {
        setLayout(new BorderLayout());
        add(switchImagePanel, BorderLayout.NORTH);
        add(zoomPanel, BorderLayout.CENTER);
    }

    public ZoomPanel getZoomPanel() {
        return zoomPanel;
    }

    public SwitchImagePanel getSwitchImagePanel() {
        return switchImagePanel;
    }
}
