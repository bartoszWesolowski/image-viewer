package com.image.viever.view.actionpanel;

import com.image.viever.model.ImageGallery;
import com.image.viever.model.UserSettings;
import com.image.viever.model.UserSettingsManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class GalleryPanel extends JPanel {

    private UserSettingsManager userSettingsManager = UserSettingsManager.getInstance();

    private JButton addToGalleryButton = new JButton("Add to gallery");

    private JComboBox<ImageGallery> imageGalleryJComboBox;

    public GalleryPanel() {
        setLayout(new GridLayout(2, 1));

        setComboBox();

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


    private void setComboBox() {
        UserSettings userSettings = userSettingsManager.getSettings();
        List<ImageGallery> imageGalleries = userSettings.getImageGalleries();
        ImageGallery[] objects = imageGalleries.toArray(new ImageGallery[imageGalleries.size()]);
        imageGalleryJComboBox = new JComboBox<>(objects);
        imageGalleryJComboBox.setRenderer(new ImageGalleryItemRenderer());
    }

    private static class ImageGalleryItemRenderer extends BasicComboBoxRenderer {
        @Override
        public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
            ImageGallery selected = (ImageGallery) value;
            if (selected != null) {
                setText(selected.getLabel());
            }
            return this;
        }
    }
}
