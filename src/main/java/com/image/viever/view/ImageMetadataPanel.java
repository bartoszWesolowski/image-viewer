package com.image.viever.view;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.image.viever.ImageWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.IOException;

public class ImageMetadataPanel extends JPanel {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageMetadataPanel.class);

    private JTextArea metadataTextArea = new JTextArea();

    private JScrollPane scroll = new JScrollPane(metadataTextArea);

    public ImageMetadataPanel() {
        metadataTextArea.setRows(5);
        metadataTextArea.setText("");
        add(scroll);
    }

    public void setMetadata(ImageWrapper image) {
        metadataTextArea.setText("");
        String format = String.format("Size: %s MB\n", image.getSize());
        metadataTextArea.append(format);
        //TODO: move to separate view instead to text area
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(image.getOriginalFile());
            for (Directory directory : metadata.getDirectories()) {
                directory.getTags().forEach(tag -> metadataTextArea.append(tag.toString() + "\n"));
            }
        } catch (ImageProcessingException | IOException e) {
            LOGGER.error("Fail to extract image metadata.", e);
        }

    }
}
