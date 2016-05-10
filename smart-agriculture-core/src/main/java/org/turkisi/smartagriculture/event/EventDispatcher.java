package org.turkisi.smartagriculture.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.turkisi.smartagriculture.ServiceRegistry;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class EventDispatcher {

    private static final int DEFAULT_THREADS = 5;

    private static Logger logger = LogManager.getLogger();

    private ExecutorService dispatchExecutorService = Executors.newFixedThreadPool(DEFAULT_THREADS);

    public void dispatch(final Event event) {

        FutureTask<Void> future = new FutureTask<Void>(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                List<String> queueIds = ServiceRegistry.getDaemonQueueService().getEventQueueIds();
                for (String queueId : queueIds) {
                    try {
                        ServiceRegistry.getDaemonQueueService().getEventQueue(queueId).put(event);
                    } catch (InterruptedException e) {
                        logger.warn("Interrupted while waiting to put the event to queue: " + queueId);
                    }
                }
                logger.debug("Event {} dispatched to {} queues", event.getId(), queueIds.size());
                return null;
            }
        });

        try {
            dispatchExecutorService.execute(future);
        } catch (RejectedExecutionException e) {
            logger.error("Executor rejected execution. It may be overloaded. Max thread count");
            throw e;
        }
    }
}
