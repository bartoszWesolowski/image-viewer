package com.image.viever.view.actionpanel;

import com.image.viever.ImageWrapper;
import com.image.viever.model.ImageGallery;
import com.image.viever.model.UserSettings;
import com.image.viever.model.UserSettingsManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.util.*;
import java.util.List;

public class AddToGalleryFrame extends JFrame {

    private UserSettingsManager userSettingsManager = UserSettingsManager.getInstance();

    private ImageWrapper imageToAdd;

    private JLabel imagePathLabel = new JLabel();

    private JButton addButton = new JButton("Add");

    private JButton cancelButton = new JButton("Cancel");

    private JComboBox<ImageGallery> imageGalleryJComboBox;

    public AddToGalleryFrame(final ImageWrapper imageToAdd){
        this.imageToAdd = imageToAdd;

        imagePathLabel.setText(imageToAdd.getOriginalFile().getAbsolutePath());
        setCombobox();

        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new GridLayout(4, 1));
        contentPane.add(imagePathLabel);
        contentPane.add(imageGalleryJComboBox);
        contentPane.add(addButton);
        contentPane.add(cancelButton);


        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(d.width / 2 - this.getWidth() / 2, d.height / 2 - this.getHeight() / 2);
        this.pack();
    }

    public ImageGallery getSelectedItem() {
        return (ImageGallery) imageGalleryJComboBox.getSelectedItem();
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JComboBox<ImageGallery> getImageGalleryJComboBox() {
        return imageGalleryJComboBox;
    }

    private void setCombobox() {
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
