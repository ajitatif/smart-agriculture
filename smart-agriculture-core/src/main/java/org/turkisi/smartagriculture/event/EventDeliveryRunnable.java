package org.turkisi.smartagriculture.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.turkisi.smartagriculture.daemon.EventQueue;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * A runnable implementation which defines an event delivery thread.
 * {@link EventDeliverer} creates an EventDeliveryRunnable for each {@link EventQueue}, which in turn,
 * creates a {@link java.util.concurrent.ThreadPoolExecutor}
 *
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
class EventDeliveryRunnable implements Runnable {

    private Logger logger = LogManager.getLogger();

    private EventQueue eventQueue;
    private Collection<EventListener> eventListeners;
    private ExecutorService eventDeliveryExecutorService = Executors.newCachedThreadPool();

    public EventDeliveryRunnable(EventQueue eventQueue, Set<EventListener> eventListeners) {
        this.eventQueue = eventQueue;
        this.eventListeners = eventListeners;
    }

    @Override
    public void run() {
        try {
            final Event event = eventQueue.take();
            for (final EventListener eventListener : eventListeners) {
                FutureTask<Void> futureTask = new FutureTask<>(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        eventListener.onEvent(event);
                        return null;
                    }
                });
                eventDeliveryExecutorService.execute(futureTask);
            }
        } catch (InterruptedException e) {
            logger.warn("Event Delivery Thread interrupted!");
        }
    }
}
