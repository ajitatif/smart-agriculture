package org.turkisi.smartagriculture.event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class EventActionHandler implements EventListener {

    private static final int DEFAULT_THREADS = 1;

    private List<ActionCondition> actionConditions = new ArrayList<ActionCondition>();

    private ExecutorService queuePollExecutorService = Executors.newFixedThreadPool(DEFAULT_THREADS);

    public synchronized boolean addActionCondition(ActionCondition condition) {
        return actionConditions.add(condition);
    }

    private Action getActionForEvent(Event event) {
        for (ActionCondition actionCondition : actionConditions) {

        }
        return new Action();
    }

    @Override
    public void onEvent(Event event) {

    }
}
