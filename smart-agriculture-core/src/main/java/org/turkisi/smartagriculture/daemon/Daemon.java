package org.turkisi.smartagriculture.daemon;

/**
 * Defines a module which is used to take specific action groups.
 * Example action groups could be watering start, watering stop etc.
 *
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public interface Daemon {

    /**
     * Name of the module which is the very first argument of an action;
     * such as "watering" in "watering start" action.
     */
    String getDaemonName();

    /**
     * Takes the action with given arguments
     * @param command Action command name, such as "start" in "watering start" action
     * @param args Action arguments and parameters after module name is omitted; such as "now" in "watering start now" action
     */
    void invokeAction(String command, String[] args);

    /**
     * Returns the priority; lower the number, higher the priority. Daemons with equal priorities are not guaranteed
     * to run in any order
     */
    int getPriority();
}
