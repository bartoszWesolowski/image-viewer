package com.image.viever.events;

/**
 * Created by Bartosz Wesolowski on 04.11.2018.
 */
public enum  EventTypes {
    IMAGE_MODIFIED, INVALID_FILE_LOADED, IMAGE_LOADED, IMAGE_CLOSED;

    public String getId() {
        return this.toString();
    }
}
