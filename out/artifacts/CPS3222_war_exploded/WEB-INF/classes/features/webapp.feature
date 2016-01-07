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