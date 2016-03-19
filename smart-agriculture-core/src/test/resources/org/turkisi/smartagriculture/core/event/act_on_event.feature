Feature: Act on a detected event

  An event can be received/detected through any means imaginable. Smart Agriculture should be capable of taking specific actions
  for specific events.

  Scenario Outline: Detected event triggers an action.
    Given EventActionHandler has an ActionCondition with EventSource of <source> and ActionThreshold of <threshold>
    When Event type of <type> is detected with value of <value>
    Then The action trigger should be <trigger>

    Examples:
    | source    | threshold | type        | value | trigger |
    | Humidity  | "<=0.1"    | Humidity   | 0.5   | false   |
    | Humidity  | "<=0.1"    | Humidity   | 0.1   | true    |
    | Humidity  | "<=0.1"    | Humidity   | 0.09  | true    |
    | Humidity  | "<=0.1"    | Temperature| -1.2  | false   |
    | Humidity  | "<=0.1"    | Temperature| 12    | false   |
