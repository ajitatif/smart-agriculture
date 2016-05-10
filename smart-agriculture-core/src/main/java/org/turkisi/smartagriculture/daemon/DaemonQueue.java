package org.turkisi.smartagriculture.daemon;

import org.turkisi.smartagriculture.event.Event;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public abstract class DaemonQueue<T> extends SynchronousQueue<T> {

}
