package com.image.viever.view.actionpanel;

import javax.swing.*;

public class SwitchImagePanel extends JPanel {

    private JButton previousImageButton = new JButton("Previous");

    private JButton nextImageButton = new JButton("Next");

    public SwitchImagePanel() {
        add(previousImageButton);
        add(nextImageButton);
    }

    public JButton getPreviousImageButton() {
        return previousImageButton;
    }

    public JButton getNextImageButton() {
        return nextImageButton;
    }
}
