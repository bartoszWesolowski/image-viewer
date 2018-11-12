package com.image.viever.view.menu;

import com.image.viever.model.ImageGallery;
import com.image.viever.view.ImageGalleriesComboBox;

import javax.swing.*;
import java.awt.*;

public class CopyGalleryFrame extends JFrame {

    private JLabel destinationPathLabel = new JLabel("Select destination");

    private JButton selectDestinationPathButton = new JButton("Select destination path");

    private JButton cancelButton = new JButton("Cancel");

    private JButton saveButton = new JButton("Save");

    private JLabel galleryLabel = new JLabel("Select gallery");

    private JComboBox<ImageGallery> imageGalleryJComboBox = ImageGalleriesComboBox.create();

    public CopyGalleryFrame(){

        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new GridLayout(3, 1));
        contentPane.add(createDestinationDirectoryPanel());
        contentPane.add(createGallerySelectPanel());
        contentPane.add(createActionsPanel());


        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(d.width / 2 - this.getWidth() / 2, d.height / 2 - this.getHeight() / 2);
        this.pack();
    }

    public ImageGallery getSelectedItem() {
        return (ImageGallery) imageGalleryJComboBox.getSelectedItem();
    }

    private JPanel createDestinationDirectoryPanel(){
        JPanel jPanel = new JPanel(new GridLayout(2, 1));
        jPanel.add(destinationPathLabel);
        jPanel.add(selectDestinationPathButton);

        return jPanel;
    }

    private JPanel createGallerySelectPanel() {
        JPanel jPanel = new JPanel();
        jPanel.add(galleryLabel);
        jPanel.add(imageGalleryJComboBox);
        return jPanel;
    }
    private JPanel createActionsPanel() {
        JPanel jPanel = new JPanel();
        jPanel.add(saveButton);
        jPanel.add(cancelButton);
        return jPanel;
    }

    public JButton getSelectDestinationPathButton() {
        return selectDestinationPathButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JComboBox<ImageGallery> getImageGalleryJComboBox() {
        return imageGalleryJComboBox;
    }

    public JLabel getDestinationPathLabel() {
        return destinationPathLabel;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setDestinationPath(String destinationPath) {
        destinationPathLabel.setText(destinationPath);
        this.revalidate();
    }
}
