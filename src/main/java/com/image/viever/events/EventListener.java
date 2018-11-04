package com.image.viever.events;

public interface EventListener {

    boolean accepts(Event event);

    void handle(Event event);
}
