package com.image.viever;import java.awt.*;
import java.awt.image.*;
import java.io.File;


/**
 * OFImage is a class that defines an image in OF (Objects First) format.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 2.0
 */
public class ImageWrapper extends BufferedImage
{

    private File orginalFile;

    /**
     * Create an OFImage copied from a BufferedImage.
     * @param image The image to copy.
     */
    public ImageWrapper(BufferedImage image, File orginalFile)
    {
    	super(image.getColorModel(), image.copyData(null), 
               image.isAlphaPremultiplied(), null);
        this.orginalFile = orginalFile;
    }


    public static ImageWrapper clone(ImageWrapper image)
    {
       return new ImageWrapper(image, image.getOrginalFile());
    }

    /**
     * Set a given pixel of this image to a specified color. The
     * color is represented as an (r,g,b) value.
     * @param x The x position of the pixel.
     * @param y The y position of the pixel.
     * @param col The color of the pixel.
     */
    public void setPixel(int x, int y, Color col)
    {
        int pixel = col.getRGB();
        setRGB(x, y, pixel);
    }
    
    /**
     * Get the color value at a specified pixel position.
     * @param x The x position of the pixel.
     * @param y The y position of the pixel.
     * @return The color of the pixel at the given position.
     */
    public Color getPixel(int x, int y)
    {
        int pixel = getRGB(x, y);
        return new Color(pixel);
    }

    public File getOrginalFile() {
        return orginalFile;
    }
}
