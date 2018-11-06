package com.image.viever.view;

import com.image.viever.ImageWrapper;
import com.image.viever.view.actionpanel.ActionPanel;
import com.image.viever.view.actionpanel.ZoomPanel;
import com.image.viever.view.menu.MenuBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ImageViewerFrame extends JFrame {

    private ImageScrollPanel imageScrollPanel = new ImageScrollPanel();

    private MenuBar menu = new MenuBar();

    private JLabel fileNameLabel = new JLabel("No file");

    private JLabel statusLabel = new JLabel("Version 3.1");

    private ActionPanel actionPanel = new ActionPanel();

    private JPanel contentPanel;

    //TODO: group in methods
    public ImageViewerFrame() {
        super("Image viewer");
        this.contentPanel = (JPanel) this.getContentPane();
        this.contentPanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        this.contentPanel.setLayout(new BorderLayout(6, 6));

        this.setJMenuBar(menu);
        this.contentPanel.add(imageScrollPanel, BorderLayout.CENTER);
        this.contentPanel.add(fileNameLabel, BorderLayout.NORTH);
        this.contentPanel.add(statusLabel, BorderLayout.SOUTH);
        this.contentPanel.add(actionPanel, BorderLayout.EAST);


        this.pack();

        // place the frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(d.width / 2 - this.getWidth() / 2, d.height / 2 - this.getHeight() / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void displayNewImage(ImageWrapper image) {
        imageScrollPanel.displayNewImage(image);
    }

    public ImageScrollPanel getImageScrollPanel() {
        return imageScrollPanel;
    }

    public MenuBar getMenu() {
        return menu;
    }

    public JLabel getFileNameLabel() {
        return fileNameLabel;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public ActionPanel getActionPanel() {
        return actionPanel;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
