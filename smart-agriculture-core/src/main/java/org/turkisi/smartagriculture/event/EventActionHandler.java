package org.turkisi.smartagriculture.event;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class EventActionHandler {

    private List<ActionCondition> actionConditions = new ArrayList<ActionCondition>();

    public synchronized boolean addActionCondition(ActionCondition condition) {
        return actionConditions.add(condition);
    }


}
