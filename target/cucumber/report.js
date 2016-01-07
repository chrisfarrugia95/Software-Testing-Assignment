$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("webapp.feature");
formatter.feature({
  "line": 1,
  "name": "BDD",
  "description": "",
  "id": "bdd",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 4,
  "name": "Successful Login",
  "description": "",
  "id": "bdd;successful-login",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "I am a user trying to log in",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I login using valid credentials",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "I should be taken to the chat page",
  "keyword": "Then "
});
formatter.match({
  "location": "LogIn.I_am_a_user_trying_to_log_in()"
});
formatter.result({
  "duration": 3539250501,
  "status": "passed"
});
formatter.match({
  "location": "LogIn.I_login_using_valid_credentials()"
});
formatter.result({
  "duration": 33089899,
  "status": "passed"
});
formatter.match({
  "location": "LogIn.I_should_be_taken_to_the_chat_page()"
});
formatter.result({
  "duration": 1982083,
  "status": "passed"
});
});