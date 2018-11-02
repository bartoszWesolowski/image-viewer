package com.image.viever.filters;

import com.image.viever.OFImage;
import com.image.viever.filters.Filter;

import java.awt.Color;

/**
 * An image filter to mirror (flip) the image horizontally.
 * 
 * @author Wojciech Cichoradzki.
 * @version 1.0
 */
public class FlipVerticallyFilter extends Filter {
	/**
	 * Constructor for objects of class MirrorFilter.
     * @param name The name of the filter.
	 */
	public FlipVerticallyFilter(String name)
    {
        super(name);
	}

    /**
     * Apply this filter to an image.
     * 
     * @param  image  The image to be changed by this filter.
     */
    public void apply(OFImage image)
    {
        int height = image.getHeight();
        int width = image.getWidth();
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height / 2; y++) {
                Color up = image.getPixel(x, y);
                image.setPixel(x, y, image.getPixel(x,height - 1 - y));
                image.setPixel(x,height - 1 - y,up);
            }
        }
    }
}
