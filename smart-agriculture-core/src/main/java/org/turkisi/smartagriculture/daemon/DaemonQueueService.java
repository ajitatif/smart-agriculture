package org.turkisi.smartagriculture.daemon;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public interface DaemonQueueService {

    /**
     * Creates a new queue with given ID
     * @param queueId The queue ID
     */
    void createEventQueue(@NotNull String queueId);

    /**
     * Retrieves the event queue with given ID
     * @param queueId The queue ID
     * @return {@link EventQueue} with given ID, or null if no queue exists with given ID
     */
    EventQueue getEventQueue(@NotNull String queueId);

    /**
     * Retrieves all queue IDs
     * @return List of queue IDs registered within the service. Empty list if no queues are registered
     */
    List<String> getEventQueueIds();
}
