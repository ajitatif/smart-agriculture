package org.turkisi.smartagriculture.core.event;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.AfterClass;
import org.turkisi.smartagriculture.ServiceRegistry;
import org.turkisi.smartagriculture.daemon.DaemonQueueService;
import org.turkisi.smartagriculture.event.*;

import java.util.Arrays;
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
    private final TestDaemonResultRegister resultRegister = new TestDaemonResultRegister();
    private TestDaemon testDaemon = new TestDaemon(this);
    private EventDeliverer eventDeliverer = new EventDeliverer();

    TestDaemonResultRegister getResultRegister() {
        return resultRegister;
    }

    public ActOnEventSteps() {
        eventActionHandler = new EventActionHandler();
        eventDispatcher = new EventDispatcher();
        DaemonQueueService daemonQueueService = ServiceRegistry.getDaemonQueueService();
        daemonQueueService.createEventQueue("watering");
        eventActionHandler.addActionModule(testDaemon);
        eventDeliverer.registerEventListener("watering", eventActionHandler);
        new Thread(eventDeliverer).start();
    }

    @After
    public void tearDown() {
        eventDeliverer.shutdown();
    }

    @Given("^EventActionHandler has following ActionConditionMedium data$")
    public void eventactionhandler_has_following_AlarmCondition_data(List<ActionConditionMedium> arg1) throws Throwable {
        for (ActionConditionMedium medium : arg1) {
            ActionCondition actionCondition = new ActionCondition();
            actionCondition.setSource(medium.getSource());
            actionCondition.setActionCondition(medium.getActionCondition());
            Action action = new Action();
            String[] split = medium.getAction().split(" ");
            action.setDaemon(split[0]);
            action.setCommand(split[1]);
            if (split.length > 2) {
                action.setArgs(Arrays.copyOfRange(split, 3, split.length));
            } else {
                action.setArgs(new String[0]);
            }
            actionCondition.setAction(action);
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
        if (resultRegister.getCommand() == null) {
            synchronized (resultRegister) {
                if (resultRegister.getCommand() == null) {
                    resultRegister.wait();
                }
            }
        }
        assertThat(resultRegister.getCommand(), equalTo(arg0));
    }
}
