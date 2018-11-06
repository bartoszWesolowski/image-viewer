package com.image.viever;import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

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
public class ImagePanel extends JComponent
{
    // The current width and height of this panel
    private int width, height;

    // An internal image buffer that is used for painting. For
    // actual display, this image buffer is then copied to screen.
    private ImageWrapper currentlyDisplayedImage;
    //Saved copy of an originalImage size image (filters are applied) - used for zooming
    private ImageWrapper originalImage;

    /**
     * Create a new, empty ImagePanel.
     */
    public ImagePanel()
    {
        width = 360;    // arbitrary size for empty panel
        height = 240;
        currentlyDisplayedImage = null;
        originalImage = null;
    }

    /**
     * Set the image that this panel should show.
     * 
     * @param image  The image to be displayed.
     */
    public void modifyDisplayedImage(ImageWrapper image)
    {
        if(image != null) {
            width = image.getWidth();
            height = image.getHeight();
            currentlyDisplayedImage = image;
            repaint();
        }
    }

    public void displayNewImage(ImageWrapper newImage) {
        this.originalImage = newImage;
        this.currentlyDisplayedImage = this.originalImageToSizeOfCurrentImagePanel();
        this.modifyDisplayedImage(currentlyDisplayedImage);

    }
    /**
     * Saves originalImage images, gets called after opening new image
     */
    public void saveOriginal(){
    	originalImage = currentlyDisplayedImage;
    }
    

    /**
     * Clear the image on this panel.
     */
    public void clearImage()
    {
        if (currentlyDisplayedImage != null) {
            Graphics imageGraphics = currentlyDisplayedImage.getGraphics();
            imageGraphics.setColor(Color.LIGHT_GRAY);
            imageGraphics.fillRect(0, 0, width, height);
            repaint();
        }
    }
    /**
     * creates a resized instance of the originalImage picture and displays it
     * @param factor
     */
    public void zoom(double factor){
        if (originalImage == null) {
            return;
        }
    	double k=(factor)/100;
    	int newWidth = new Double(originalImage.getWidth() * k).intValue();
    	int newHeight = new Double(originalImage.getHeight() * k).intValue();
    	BufferedImage resized = new BufferedImage(newWidth, newHeight, originalImage.getType());
    	Graphics2D g = resized.createGraphics();
    	g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
    	    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    	g.drawImage(originalImage, 0, 0, newWidth, newHeight, 0, 0, originalImage.getWidth(),
    	    originalImage.getHeight(), null);
    	g.dispose();
    	ImageWrapper img = new ImageWrapper(resized, currentlyDisplayedImage.getOriginalFile());
    	modifyDisplayedImage(img);
    }

    public void adjustToCurrentImagePanelSize() {
        this.currentlyDisplayedImage = this.originalImageToSizeOfCurrentImagePanel();
        this.modifyDisplayedImage(currentlyDisplayedImage);
    }

    //TODO:Resizing should work better and should be faster! Fix this.
    private ImageWrapper originalImageToSizeOfCurrentImagePanel() {
        if (originalImage != null) {
            BufferedImage resized = new BufferedImage(getWidth(), getHeight(), originalImage.getType());
            Graphics2D g = resized.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(originalImage, 0, 0, width, height, 0, 0, originalImage.getWidth(),
                    originalImage.getHeight(), null);
            g.dispose();
            return new ImageWrapper(resized, originalImage.getOriginalFile());
        }
        return null;
    }

    /**
     * Tell the layout manager how big we would like to be.
     * (This method gets called by layout managers for placing
     * the components.)
     * 
     * @return The preferred dimension for this component.
     */
    @Override
    public Dimension getPreferredSize()
    {
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
    public void paintComponent(Graphics g)
    {
        Dimension size = getSize();
        g.clearRect(0, 0, size.width, size.height);
        if(currentlyDisplayedImage != null) {
            g.drawImage(currentlyDisplayedImage, 0, 0, null);
        }
    }

    private void displayOriginal() {
        this.modifyDisplayedImage(this.originalImage);
    }
}
