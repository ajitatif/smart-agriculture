Feature: Act on a detected event

  An event can be received/detected through any means imaginable. Smart Agriculture should be capable of taking specific actions
  for specific events.

  Scenario Outline: Detected event triggers an action.
    Given EventActionHandler has an ActionCondition with EventSource of Humidity, ActionThreshold of <thresholdStart> Action Command of watering start
    And an ActionCondition with EventSource of Humidity, ActionThreshold of <thresholdStop> Action Command of watering stop
    When Event type of "<type>" is detected with value of <value>
    Then The action command should be <command>

    Examples:
    | thresholdStart    | thresholdStop | type        | value | command          |
    | "<=0.1"           | ">0.1"        | HUMIDITY    | 0.5   | watering stop    |
    | "<=0.1"           | ">0.1"        | HUMIDITY    | 0.1   | watering start   |
    | "<=0.1"           | ">0.1"        | HUMIDITY    | 0.09  | watering start   |
    | "<=0.1"           | ">0.1"        | TEMPERATURE | -1.2  | watering stop    |
    | "<=0.1"           | ">0.1"        | TEMPERATURE | 12    | watering stop    |
