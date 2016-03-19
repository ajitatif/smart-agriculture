package org.turkisi.smartagriculture.core.event;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class ActOnEventSteps {
    @Given("^EventActionHandler has an ActionCondition with EventSource of Humidity and ActionThreshold of \"([^\"]*)\"$")
    public void eventactionhandler_has_an_ActionCondition_with_EventSource_of_Humidity_and_ActionThreshold_of(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Event type of Humidity is detected with value of (\\d+)\\.(\\d+)$")
    public void event_type_of_Humidity_is_detected_with_value_of(int arg1, int arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^The action trigger should be false$")
    public void the_action_trigger_should_be_false() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^The action trigger should be true$")
    public void the_action_trigger_should_be_true() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Event type of Temperature is detected with value of -(\\d+)\\.(\\d+)$")
    public void event_type_of_Temperature_is_detected_with_value_of(int arg1, int arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Event type of Temperature is detected with value of (\\d+)$")
    public void event_type_of_Temperature_is_detected_with_value_of(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
