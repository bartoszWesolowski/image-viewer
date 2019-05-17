package com.image.viever.view.actionpanel;

import com.image.viever.model.ImageGallery;
import com.image.viever.view.ImageGalleriesComboBox;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class GalleryPanel extends JPanel {

    private JButton addToGalleryButton = new JButton("Add to gallery");

    private ImageGalleriesComboBox imageGalleryJComboBox = ImageGalleriesComboBox.create();

    public GalleryPanel() {
        setLayout(new GridLayout(2, 1));

        add(addToGalleryButton);
        add(imageGalleryJComboBox);
    }


    public Optional<ImageGallery> getSelectedGallery() {
        ImageGallery selectedItem = imageGalleryJComboBox.getSelectedItem();
        return Optional.ofNullable(selectedItem);
    }

    public void refreshGalleries() {
        imageGalleryJComboBox.refresh();
        this.repaint();
    }

    public JButton getAddToGalleryButton() {
        return addToGalleryButton;
    }

    public JComboBox<ImageGallery> getImageGalleryJComboBox() {
        return imageGalleryJComboBox;
    }
}
