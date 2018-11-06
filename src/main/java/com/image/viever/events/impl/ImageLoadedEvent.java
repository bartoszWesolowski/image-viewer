package com.image.viever.events.impl;

import com.image.viever.ImageWrapper;
import com.image.viever.events.Event;
import com.image.viever.events.EventTypes;

public class ImageLoadedEvent extends Event {
    public ImageLoadedEvent(final ImageWrapper data) {
        super(EventTypes.IMAGE_LOADED, data);
    }
}
