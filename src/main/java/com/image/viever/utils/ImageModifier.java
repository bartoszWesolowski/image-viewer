package com.image.viever.utils;

import com.image.viever.ImageWrapper;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageModifier {

    public static final Logger LOGGER = LoggerFactory.getLogger(ImageModifier.class);

    public ImageWrapper resize(ImageWrapper original, double scale) {
        ImageWrapper img = original;
        try {
            StopWatch stopWatch = StopWatch.createStarted();
            BufferedImage bufferedImage = Thumbnails.of(original)
                    .scale(scale)
                    .asBufferedImage();
            LOGGER.debug("Zoom operation took: {} ms" + stopWatch.getTime());
            img = new ImageWrapper(bufferedImage, original.getOriginalFile());
        } catch (IOException e) {
            LOGGER.error("Failed to resize image: ", e);
        }
        return img;
    }

    /**
     * Resizez the image to to make it fit in the container rectangle. It Keeps the original image ratio.
     * @param original
     * @param containerSize
     * @return
     */
    public ImageWrapper adjustToSizeOfContainer(ImageWrapper original, Dimension containerSize) {
        ImageWrapper resized = original;
        int smallerHeight = containerSize.height < original.getHeight() ? containerSize.height : original.getHeight();
        int smallerWidth = containerSize.width < original.getWidth() ? containerSize.width : original.getWidth();
        try {

            StopWatch stopWatch = StopWatch.createStarted();
            BufferedImage bufferedImage = Thumbnails.of(original)
                    .size(smallerWidth, smallerHeight)
                    .keepAspectRatio(true)
                    .asBufferedImage();
            LOGGER.debug("Rescaling image to the size of panel took: {} ms." + stopWatch.getTime());
            resized = new ImageWrapper(bufferedImage, original.getOriginalFile());
        } catch (IOException e) {
            LOGGER.error("Failed to resize image.", e);
        }
        return resized;
    }
}
