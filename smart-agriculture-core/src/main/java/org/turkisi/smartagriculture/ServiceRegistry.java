package org.turkisi.smartagriculture;

import org.turkisi.smartagriculture.daemon.DaemonQueueService;
import org.turkisi.smartagriculture.daemon.LocalDaemonQueueService;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class ServiceRegistry {

    private static DaemonQueueService daemonQueueService = new LocalDaemonQueueService();

    public static DaemonQueueService getDaemonQueueService() {
        return daemonQueueService;
    }
}
