package org.turkisi.smartagriculture.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.turkisi.smartagriculture.daemon.Daemon;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service for taking actions for received events
 *
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class EventActionHandler implements EventListener {

    private static Logger logger = LogManager.getLogger();

    private Set<ActionCondition> actionConditions = new HashSet<>();

    private Map<String, List<Daemon>> actionModules = new HashMap<>();

    private Pattern PATTERN_ACTIONCONDITION = Pattern.compile("^([<=>!]{1,2})(.+)$");

    /**
     * Adds a new alarm condition
     * @return true if action condition was not present
     */
    public synchronized boolean addActionCondition(ActionCondition condition) {
        return actionConditions.add(condition);
    }

    /**
     * Adds a new action module
     * @return true id action module was not present
     */
    public synchronized boolean addActionModule(Daemon module) {
        if (!actionModules.containsKey(module.getDaemonName())) {
            actionModules.put(module.getDaemonName(), new ArrayList<Daemon>());
        }
        List<Daemon> modules = this.actionModules.get(module.getDaemonName());
        boolean retval = true;
        if (modules.contains(module)) {
            modules.remove(module);
            retval = false;
        }
        modules.add(module);
        if (retval) {
            Collections.sort(modules, new Comparator<Daemon>() {
                @Override
                public int compare(Daemon o1, Daemon o2) {
                    return Integer.compare(o1.getPriority(), o2.getPriority());
                }
            });
        }
        return retval;
    }

    @Override
    public void onEvent(Event event) {
        for (ActionCondition actionCondition : actionConditions) {
            if (evaluateEvent(event, actionCondition)) {
                Action action = actionCondition.getAction();
                String daemonName = action.getDaemon();
                if (actionModules.containsKey(daemonName)) {
                    for (Daemon daemon : actionModules.get(daemonName)) {
                        daemon.invokeAction(action.getCommand(), action.getArgs());
                    }
                }
            }
        }
    }

    private boolean evaluateEvent(Event event, ActionCondition actionCondition) {

        return actionCondition.getSource().equals(event.getSource())
                && evaluateActionCondition(actionCondition.getActionCondition(), event.getValue());

    }

    private boolean evaluateActionCondition(String actionCondition, String value) {
        String normalizedCondition = actionCondition.replaceAll("\\s", "");
        Matcher matcher = PATTERN_ACTIONCONDITION.matcher(normalizedCondition);
        if (matcher.matches()) {
            String operator = matcher.group(1);
            String operand = matcher.group(2);
            if ("==".equals(operator)) {
                return operand.equals(value);
            } else if ("!=".equals(operator)) {
                return !operand.equals(value);
            } else if ("<".equals(operator)) {
                return compareNumericValues(value, operand) < 0;
            } else if ("<=".equals(operator)) {
                return compareNumericValues(value, operand) <= 0;
            } else if (">".equals(operator)) {
                return compareNumericValues(value, operand) > 0;
            } else if (">=".equals(operator)) {
                return compareNumericValues(value, operand) >= 0;
            } else {
                logger.warn("Invalid operator {}, no action will take place", operator);
            }
        }

        return false;
    }

    private int compareNumericValues(String v1, String v2) {
        return Double.valueOf(v1).compareTo(Double.valueOf(v2));
    }
}
