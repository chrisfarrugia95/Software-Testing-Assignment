Feature: BDD


  Scenario: Successful Login
    Given  I am a user trying to log in
    When I login using valid credentials
    Then I should be taken to the chat page


  Scenario: Unsuccessful Login
    Given I am a user trying to log in
    When I login using invalid credentials
    Then I should see an error message
    And I should remain on the login page


  Scenario: Sending a Message
    Given I am on the chat page
    When I send a valid message
    Then the message should appear in my chat window



  Scenario Outline: Sending a parental-locked Message
    Given I am on the chat page
    When I send a message with <parental_locked_text>
    Then an error will tell me that the message was not sent
    And the message should not appear in my text window
    Examples:
      | parental_locked_text |
      | "Fudge"                |
      | "Yikes"                |
      | "Pudding"              |


