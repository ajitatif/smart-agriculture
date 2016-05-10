package org.turkisi.smartagriculture.daemon;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class LocalDaemonQueueService implements DaemonQueueService {

    private Map<String, EventQueue> eventQueueMap = new HashMap<>();

    @Override
    public void createEventQueue(@NotNull String queueId) {
        eventQueueMap.put(queueId, new EventQueue());
    }

    @Override
    public EventQueue getEventQueue(@NotNull String queueId) {
        if (eventQueueMap.containsKey(queueId)) {
            return eventQueueMap.get(queueId);
        }
        return null;
    }

    @Override
    public List<String> getEventQueueIds() {
        return new ArrayList<>(eventQueueMap.keySet());
    }
}
