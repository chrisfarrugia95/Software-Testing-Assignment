package step_definitions;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import cucumber.api.java.en.And;
import junit.framework.Assert;
import web_App.LoginServlet;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.List;

public class LogIn {

    HtmlPage page;

    //1,2
    @cucumber.api.java.en.Given("^I am a user trying to log in$")
    public void I_am_a_user_trying_to_log_in() throws Throwable {

        WebClient webClient = new WebClient(BrowserVersion.CHROME_16);
        page = webClient.getPage("http://localhost:8080/login");
    }


    //1
    @When("^I login using valid credentials$")
    public void I_login_using_valid_credentials() throws Throwable {

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

    //1
    @Then("^I should be taken to the chat page$")
    public void I_should_be_taken_to_the_chat_page() throws Throwable {

        Assert.assertTrue(page.getWebResponse().getContentAsString().contains("Kellimnifejtrid Chat Page"));
    }



    //2
    @When("^I login using invalid credentials$")
    public void iLoginUsingInvalidCredentials() throws Throwable {

        List<HtmlForm> lis = new ArrayList<HtmlForm>();
        lis = page.getForms();
        HtmlForm form = lis.get(0);

        HtmlSubmitInput submit = form.getInputByValue("Log in");
        HtmlTextInput username = form.getInputByName("username");
        HtmlTextInput password = form.getInputByName("password");

        username.setValueAttribute("notadmin");
        password.setValueAttribute("notadmin");

        page = submit.click();
    }

    //2
    @Then("^I should see an error message$")
    public void iShouldSeeAnErrorMessage() throws Throwable {

        Assert.assertTrue(page.getWebResponse().getContentAsString().contains("Invalid Username or Password"));
    }

    //2
    @And("^I should remain on the login page$")
    public void iShouldRemainOnTheLoginPage() throws Throwable {
        Assert.assertTrue(page.getWebResponse().getContentAsString().contains("Kellimnifejtrid Login Page"));
    }
}
