package step_definitions;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import junit.framework.Assert;
import web_App.LoginServlet;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.List;


public class SendingMess {

    private HtmlPage page;
    private String validMess = "This is a valid message";

    //Scenarios 3,4
    @Given("^I am on the chat page$")
    public void iAmALoggedInUser() throws Throwable {

        WebClient webClient = new WebClient(BrowserVersion.CHROME_16);
        page = webClient.getPage("http://localhost:8080/login");

        List<HtmlForm> lis = new ArrayList<HtmlForm>();
        lis = page.getForms();
        HtmlForm form = lis.get(0);

        HtmlSubmitInput submit = form.getInputByValue("Log in");
        HtmlTextInput username = form.getInputByName("username");
        HtmlTextInput password = form.getInputByName("password");

        username.setValueAttribute("admin");
        password.setValueAttribute("admin");

        page = submit.click();

    }


    //Scenario 3
    @When("^I send a valid message$")
    public void iSendAValidMessage() throws Throwable {

        List<HtmlForm> lis = new ArrayList<HtmlForm>();
        lis = page.getForms();
        HtmlForm chatform = lis.get(1);

        HtmlSubmitInput send = chatform.getInputByValue("Send");
        HtmlTextInput message = chatform.getInputByName("chat");

        message.setValueAttribute(validMess);

        page = send.click();
    }


    //Scenarios 3
    @Then("^the message should appear in my chat window$")
    public void theMessageShouldAppearInMyChatWindow() throws Throwable {

        List<?> divs = page.getByXPath("//div");
        //Get the area in represented by the chat window
        HtmlDivision chat_window = (HtmlDivision) divs.get(1);
        Assert.assertTrue(chat_window.getTextContent().contains(validMess));
    }

    //Scenario 4
    @When("^I send a message with \"([^\"]*)\"$")
    public void iSendAMessageWithParentalLockedText(String arg1) throws Throwable {

        List<HtmlForm> lis = new ArrayList<HtmlForm>();
        lis = page.getForms();
        HtmlForm chatform = lis.get(1);

        HtmlSubmitInput send = chatform.getInputByValue("Send");
        HtmlTextInput message = chatform.getInputByName("chat");

        message.setValueAttribute(arg1);

        page = send.click();
    }


    //Scenario 4
    @Then("^an error will tell me that the message was not sent$")
    public void anErrorWillTellMeThatTheMessageWasNotSent() throws Throwable {

        Assert.assertTrue(page.getWebResponse().getContentAsString().contains("Parental-Lock Violated"));

    }


    //Scenario 4
    @And("^the message should not appear in my text window$")
    public void theMessageShouldNotAppearInMyTextWindow() throws Throwable {

        List<?> divs = page.getByXPath("//div");
        //Get the area in represented by the chat window
        HtmlDivision chat_window = (HtmlDivision) divs.get(1);
        Assert.assertFalse(chat_window.getTextContent().contains("Invalid Username or Password"));

    }



}
