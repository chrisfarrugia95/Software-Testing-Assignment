Feature: BDD

  Scenario: Successfull Login
    Given  I am a user trying to log in
    When I login using valid credentials
    Then I should be taken to the chat page

  Scenario: Unsuccessfull Login
    Given I am a user trying to log in
    When I login using invalid credentials
    Then I should see an error message
    And I should remain on the login page

  Scenario: Sending a message
    Given I am a logged in user
    And I am on the chat page
    When I send a valid message
    Then the message should appear in my chat window

  Scenario: Sending a parental-lock message
    Given I am a logged in user
    And I am on the chat page
    When I send a message with <parental-locked-text>
    Then an error will tell me that the message was not sent
    And the message should not appear in my text window

  Scenario: Logging out
    Given I am a logged in user
    And I am on the chat page
    When I send click on “Log out”
    Then I should be logged out