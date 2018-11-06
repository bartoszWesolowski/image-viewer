package com.image.viever.events.impl;

import com.image.viever.events.Event;
import com.image.viever.events.EventTypes;


public class ImageMdifiedEvent extends Event{

    public ImageMdifiedEvent(final Object data) {
        super(EventTypes.IMAGE_MODIFIED, data);
    }
}
