package au.edu.unimelb.swen.oop.mokepon.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class EventBus {

    private HashMap<Class<?>, ArrayList<Consumer<?>>> subscriberListMap = new HashMap<>();

    public <T> void subscribe(Class<T> eventType, Consumer<T> handler) {
        subscriberListMap.computeIfAbsent(eventType, _ -> new ArrayList<>()).add(handler);
    }

    @SuppressWarnings("unchecked")
    public <T> void publish(T event) {
        ArrayList<Consumer<?>> handlerList = subscriberListMap.get(event.getClass());
        if (handlerList == null) return;

        handlerList.forEach(handler -> {
            ((Consumer<T>)handler).accept(event);
        });
    }

}
