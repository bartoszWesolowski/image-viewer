package com.image.viever.events.impl;

import com.image.viever.events.Event;
import com.image.viever.events.EventTypes;

public class ImageGalleryAddedEvent extends Event {
    public ImageGalleryAddedEvent(String galleryName) {
        super(EventTypes.IMAGE_GALLERY_ADDED, galleryName);
    }
}
