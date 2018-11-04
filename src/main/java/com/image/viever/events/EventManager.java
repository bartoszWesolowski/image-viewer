package com.image.viever.events;

import java.util.LinkedList;
import java.util.List;

public class EventManager {

    private static final EventManager INSTANCE = new EventManager();

    public static final EventManager getInstance() {
        return INSTANCE;
    }

    private EventManager() {
        //hides public constructor
    }

    private List<EventListener> eventListeners = new LinkedList<>();

    public void fireEvent(Event event) {
        eventListeners.stream()
            .filter(eventListener -> eventListener.accepts(event))
            .forEach(eventListener -> eventListener.handle(event));
    }
    public boolean add(final EventListener eventListener) {
        return eventListeners.add(eventListener);
    }

    public boolean remove(final Object o) {
        return eventListeners.remove(o);
    }
}
