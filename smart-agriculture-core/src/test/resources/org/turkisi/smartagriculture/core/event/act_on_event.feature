Feature: Act on a detected event

  An event can be received/detected through any means imaginable. Smart Agriculture should be capable of taking specific actions
  for specific events.

  Scenario Outline: Detected event triggers an action.
    Given EventActionHandler has following ActionConditionMedium data
    | source    | actionCondition   | action          |
    | HUMIDITY  | <=0.1             | watering start  |
    | HUMIDITY  | >0.1              | watering stop   |
    When Event type of "<type>" is detected with value of '<value>'
    Then The action command should be "<command>"

    Examples:
    | type        | value | command          |
    | HUMIDITY    | 0.5   | watering stop    |
    | HUMIDITY    | 0.1   | watering start   |
    | HUMIDITY    | 0.09  | watering start   |
    | TEMPERATURE | 0.0   | watering stop    |
    | TEMPERATURE | 12.0  | watering stop    |
