package com.image.viever.events.impl;

import com.image.viever.events.Event;
import com.image.viever.events.EventTypes;

public class ZoomChangedEvent extends Event {
    
    public ZoomChangedEvent(final int zoom) {
        super(EventTypes.ZOOM_CHANGED, zoom);
    }
}
