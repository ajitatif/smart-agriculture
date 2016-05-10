package org.turkisi.smartagriculture.core.event;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.turkisi.smartagriculture.ServiceRegistry;
import org.turkisi.smartagriculture.daemon.DaemonQueue;
import org.turkisi.smartagriculture.daemon.DaemonQueueService;
import org.turkisi.smartagriculture.event.*;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class ActOnEventSteps {

    private EventActionHandler eventActionHandler;
    private EventDispatcher eventDispatcher;
    private DaemonQueue queue;

    public ActOnEventSteps() {
        eventActionHandler = new EventActionHandler();
        eventDispatcher = new EventDispatcher();
        DaemonQueueService daemonQueueService = ServiceRegistry.getDaemonQueueService();
        daemonQueueService.createEventQueue("watering");
        queue = daemonQueueService.getEventQueue("watering");
    }

    @Given("^EventActionHandler has following AlarmCondition data$")
    public void eventactionhandler_has_following_AlarmCondition_data(List<ActionCondition> arg1) throws Throwable {
        for (ActionCondition actionCondition : arg1) {
            eventActionHandler.addActionCondition(actionCondition);
        }
    }

    @When("^Event type of \"([^\"]*)\" is detected with value of '(-?\\d+\\.?\\d*)'$")
    public void event_type_is_detected_with_value_of(String arg0, double arg1) throws Throwable {
        Event event = new Event();
        event.setSource(EventSource.valueOf(arg0));
        event.setValue(String.valueOf(arg1));
        event.setDateGenerated(new Date());
        eventDispatcher.dispatch(event);
    }

    @Then("^The action command should be \"([^\"]*)\"$")
    public void theActionCommandShouldBe(String arg0) throws Throwable {
        assertThat(queue.poll().getCommand(), equalTo(arg0));
    }
}
