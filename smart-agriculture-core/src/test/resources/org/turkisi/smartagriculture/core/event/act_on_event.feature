Feature: Act on a detected event

  An event can be received/detected through any means imaginable. Smart Agriculture should be capable of taking specific actions
  for specific events.

  Scenario Outline: Detected event triggers an action.
    Given EventActionHandler has an ActionCondition with EventSource of <source> ActionThreshold of <threshold> Action of "watering start"
    And   an ActionCondition with EventSource of <source> ActionThreshold of <threshold> Action of "watering stop"
    When Event type of <type> is detected with value of <value>
    Then The action command should be <command>

    Examples:
    | source    | threshold | type        | value | command          |
    | Humidity  | "<=0.1"    | Humidity   | 0.5   | watering stop    |
    | Humidity  | "<=0.1"    | Humidity   | 0.1   | warering start   |
    | Humidity  | "<=0.1"    | Humidity   | 0.09  | watering start   |
    | Humidity  | "<=0.1"    | Temperature| -1.2  | watering stop    |
    | Humidity  | "<=0.1"    | Temperature| 12    | watering stop    |
