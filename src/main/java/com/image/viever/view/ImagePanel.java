package com.image.viever.view;

import com.image.viever.ImageWrapper;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * An ImagePanel is a Swing component that can display an OFImage.
 * It is constructed as a subclass of JComponent with the added functionality
 * of setting an OFImage that will be displayed on the surface of this
 * component.
 *
 * @author Michael Kölling and David J. Barnes.
 * @version 1.0
 */
@SuppressWarnings("serial")
//TODO: Refacor this class (so many null checks...)
public class ImagePanel extends JComponent {

    public static final Logger LOGGER = LoggerFactory.getLogger(ImagePanel.class);

    // The current width and height of this panel
    private int width, height;

    // An internal image buffer that is used for painting. For
    // actual display, this image buffer is then copied to screen.
    private ImageWrapper currentlyDisplayedImage;

    /**
     * Create a new, empty ImagePanel.
     */
    public ImagePanel() {
        width = 360;    // arbitrary size for empty panel
        height = 240;
    }

    /**
     * Set the image that this panel should show.
     *
     * @param image The image to be displayed.
     */
    public void modifyDisplayedImage(ImageWrapper image) {
        if (image != null) {
            currentlyDisplayedImage = image;
            width = currentlyDisplayedImage.getWidth();
            height = currentlyDisplayedImage.getHeight();
            repaint();
        }
    }

    /**
     * Saves originalImage images, gets called after opening new image
     */
    public void saveOriginal() {
        //TODO:remove
    }


    /**
     * Clear the image on this panel.
     */
    public void clearImage() {
        if (currentlyDisplayedImage != null) {
            Graphics imageGraphics = currentlyDisplayedImage.getGraphics();
            imageGraphics.setColor(Color.LIGHT_GRAY);
            imageGraphics.fillRect(0, 0, width, height);
            currentlyDisplayedImage.flush();
            currentlyDisplayedImage = null;
            repaint();
        }
    }
    /**
     * Tell the layout manager how big we would like to be.
     * (This method gets called by layout managers for placing
     * the components.)
     *
     * @return The preferred dimension for this component.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    /**
     * This component needs to be redisplayed. Copy the internal image
     * to screen. (This method gets called by the Swing screen painter
     * every time it want this component displayed.)
     *
     * @param g The graphics context that can be used to draw on this component.
     */
    @Override
    public void paintComponent(Graphics g) {
        Dimension size = getSize();
        Point centered = getPointForCenetredImageDraw();

        g.clearRect(0, 0, size.width, size.height);
        if (currentlyDisplayedImage != null) {
            g.drawImage(currentlyDisplayedImage, centered.x, centered.y, null);
        }
    }

    private Point getPointForCenetredImageDraw() {
        int x = 0;
        int y = 0;
        Dimension size = getSize();
        if (currentlyDisplayedImage != null) {
            if (currentlyDisplayedImage.getWidth() <= size.getWidth()) {
                x = (int) (size.getWidth() - currentlyDisplayedImage.getWidth()) / 2;
            }
            if (currentlyDisplayedImage.getHeight() <= size.getHeight()) {
                y = (int) (size.getHeight() - currentlyDisplayedImage.getHeight()) / 2;
            }
        }
        return new Point(x, y);
    }
}
