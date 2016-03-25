package org.turkisi.smartagriculture.core.event;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.turkisi.smartagriculture.event.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Date;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class ActOnEventSteps {

    private EventActionHandler eventActionHandler = new EventActionHandler();
    private Action action;

    @Given("^EventActionHandler has an ActionCondition with EventSource of Humidity, ActionThreshold of \"([^\"]*)\" Action Command of watering start$")
    public void eventactionhandler_has_an_ActionCondition_with_EventSource_of_Humidity_ActionThreshold_of_Action_Command_of_watering_start(String arg1) throws Throwable {
        ActionCondition startCondition = new ActionCondition();
        startCondition.setSource(EventSource.HUMIDTY);
        Action action = new Action();
        action.setDaemon("watering");
        action.setCommand("start");
        startCondition.setAction(action);
        startCondition.setActionCondition(arg1);
        eventActionHandler.addActionCondition(startCondition);
    }

    @Given("^an ActionCondition with EventSource of Humidity, ActionThreshold of \"([^\"]*)\" Action Command of watering stop$")
    public void an_ActionCondition_with_EventSource_of_Humidity_ActionThreshold_of_Action_Command_of_watering_stop(String arg1) throws Throwable {
        ActionCondition stopCondition = new ActionCondition();
        stopCondition.setSource(EventSource.HUMIDTY);
        Action action = new Action();
        action.setDaemon("watering");
        action.setCommand("stop");
        stopCondition.setAction(action);
        stopCondition.setActionCondition(arg1);
        eventActionHandler.addActionCondition(stopCondition);
    }

    @When("^Event type of \"([^\"]*)\" is detected with value of (\\d+)$")
    public void event_type_of_is_detected_with_value_of(String arg1, double arg2) throws Throwable {
        Event event = new Event();
        event.setSource(EventSource.valueOf(arg1));
        event.setValue(String.valueOf(arg2));

        action = eventActionHandler.getActionForEvent(event);
    }

    @Then("^The action command should be watering stop$")
    public void the_action_command_should_be_watering_stop() throws Throwable {
        assertThat(action.toString(), equalTo("watering stop"));
    }

    @Then("^The action command should be watering start$")
    public void the_action_command_should_be_watering_start() throws Throwable {
        assertThat(action.toString(), equalTo("watering stop"));
    }

}
