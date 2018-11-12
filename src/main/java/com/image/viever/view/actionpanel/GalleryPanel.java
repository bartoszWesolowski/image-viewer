package com.image.viever.view.actionpanel;

import com.image.viever.model.ImageGallery;
import com.image.viever.model.UserSettings;
import com.image.viever.model.UserSettingsManager;
import com.image.viever.view.ImageGalleriesComboBox;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class GalleryPanel extends JPanel {

    private UserSettingsManager userSettingsManager = UserSettingsManager.getInstance();

    private JButton addToGalleryButton = new JButton("Add to gallery");

    private JComboBox<ImageGallery> imageGalleryJComboBox = ImageGalleriesComboBox.create();

    public GalleryPanel() {
        setLayout(new GridLayout(2, 1));

        add(addToGalleryButton);
        add(imageGalleryJComboBox);
    }


    public Optional<ImageGallery> getSelectedGallery() {
        ImageGallery selectedItem = (ImageGallery) imageGalleryJComboBox.getSelectedItem();
        return Optional.ofNullable(selectedItem);
    }


    public JButton getAddToGalleryButton() {
        return addToGalleryButton;
    }

    public JComboBox<ImageGallery> getImageGalleryJComboBox() {
        return imageGalleryJComboBox;
    }
}
