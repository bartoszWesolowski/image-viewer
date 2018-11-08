package com.image.viever.events.impl;

import com.image.viever.events.Event;
import com.image.viever.events.EventTypes;

public class KeyPressedEvent extends Event {

    public KeyPressedEvent(int code) {
        super(EventTypes.KEY_PRESSED, code);
    }
}
