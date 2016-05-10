package org.turkisi.smartagriculture.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.turkisi.smartagriculture.ServiceRegistry;
import org.turkisi.smartagriculture.daemon.DaemonQueueService;
import org.turkisi.smartagriculture.daemon.EventQueue;

import java.util.*;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class EventDeliverer implements Runnable {

    private static Logger logger = LogManager.getLogger();

    private volatile boolean run = true;

    private DaemonQueueService daemonQueueService = ServiceRegistry.getDaemonQueueService();

    private Map<String, Set<EventListener>> eventListenerMap = new HashMap<>();

    @Override
    public void run() {
        while (run) {
            List<String> queueIds = daemonQueueService.getEventQueueIds();
            for (String queueId : queueIds) {
                Set<EventListener> eventListeners = eventListenerMap.get(queueId);
                new Thread(new EventDeliveryRunnable(daemonQueueService.getEventQueue(queueId), eventListeners)).start();
            }
        }
    }

    public synchronized void shutdown() {
        run = false;
    }

    public synchronized void registerEventListener(String queueId, EventListener eventListener) {
        if (!eventListenerMap.containsKey(queueId)) {
            eventListenerMap.put(queueId, new HashSet<EventListener>());
        }
        eventListenerMap.get(queueId).add(eventListener);
    }
}
