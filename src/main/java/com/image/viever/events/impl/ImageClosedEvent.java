package com.image.viever.events.impl;

import com.image.viever.ImageWrapper;
import com.image.viever.events.Event;

import static com.image.viever.events.EventTypes.IMAGE_CLOSED;

public class ImageClosedEvent extends Event {

    public ImageClosedEvent(ImageWrapper closedImage) {
        super(IMAGE_CLOSED, closedImage);
    }
}
