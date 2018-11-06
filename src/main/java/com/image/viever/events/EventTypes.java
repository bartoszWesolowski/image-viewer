package com.image.viever.events;


import java.util.function.Consumer;

public enum  EventTypes {
    IMAGE_MODIFIED, INVALID_FILE_LOADED, IMAGE_LOADED, IMAGE_CLOSED,
    ZOOM_CHANGED;

    public String getId() {
        return this.toString();
    }

    public EventListener eventListenerForType(Consumer<Event> handler) {
        final EventTypes currentType = this;
        return new EventListener() {
            @Override
            public boolean accepts(final Event event) {
                return event.getId() == currentType;
            }

            @Override
            public void handle(final Event event) {
                handler.accept(event);
            }
        };
    }
}
