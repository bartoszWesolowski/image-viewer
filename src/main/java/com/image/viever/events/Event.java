package com.image.viever.events;

public class Event {

    private EventTypes id;

    private Object data;

    public Event(final EventTypes id, final Object data) {
        this.id = id;
        this.data = data;
    }

    public EventTypes getId() {
        return id;
    }

    public Object getData() {
        return data;
    }
}
