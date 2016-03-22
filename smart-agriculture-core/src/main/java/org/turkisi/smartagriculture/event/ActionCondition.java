package org.turkisi.smartagriculture.event;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class ActionCondition {
    private EventSource source;
    private String actionCondition;
    private Action action;

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

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
