package org.turkisi.smartagriculture.core.event;

import org.turkisi.smartagriculture.event.EventSource;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class ActionConditionMedium {
    private EventSource source;
    private String actionCondition;
    private String action;

    public EventSource getSource() {
        return source;
    }

    public void setSource(EventSource source) {
        this.source = source;
    }

    public String getActionCondition() {
        return actionCondition;
    }

    public void setActionCondition(String actionCondition) {
        this.actionCondition = actionCondition;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
