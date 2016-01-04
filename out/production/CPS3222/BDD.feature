Feature: BDD

  Scenario 1: Successful Login
    Given  I am a user trying to log in
    When I login using valid credentials
    Then I should be taken to the chat page

