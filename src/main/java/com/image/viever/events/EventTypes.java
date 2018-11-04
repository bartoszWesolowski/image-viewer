package com.image.viever.events;

/**
 * Created by Bartosz Wesolowski on 04.11.2018.
 */
public enum  EventTypes {
    INVALID_FILE_LOADED, IMAGE_CHANGED;

    public String getId() {
        return this.toString();
    }
}
