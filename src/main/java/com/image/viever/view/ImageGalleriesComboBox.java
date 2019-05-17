package com.image.viever.view;

import com.image.viever.model.ImageGallery;
import com.image.viever.model.UserSettings;
import com.image.viever.model.UserSettingsManager;
import com.image.viever.view.actionpanel.GalleryPanel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class ImageGalleriesComboBox extends JComboBox<ImageGallery> {

    private static UserSettingsManager userSettingsManager = UserSettingsManager.getInstance();

    public static  ImageGalleriesComboBox create() {
        ImageGallery[] options = getOptions();
        return new ImageGalleriesComboBox(options);
    }

    private ImageGalleriesComboBox(ImageGallery[] options) {
        super(options);
        setRenderer(new ImageGalleryItemRenderer());
    }

    private static ImageGallery[] getOptions() {
        UserSettings userSettings = userSettingsManager.getSettings();
        List<ImageGallery> imageGalleries = userSettings.getImageGalleries();
        return imageGalleries.toArray(new ImageGallery[imageGalleries.size()]);

    }

    @Override
    public ImageGallery getSelectedItem() {
        ImageGallery selectedItem = (ImageGallery) super.getSelectedItem();
        return Optional.ofNullable(selectedItem)
                .flatMap(selected -> userSettingsManager.getGallery(selected.getId()))
                .orElse(null);
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

    public void refresh() {
        ImageGallery[] options = getOptions();
        this.setModel(new DefaultComboBoxModel(options));
    }
}
