import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import org.w3c.dom.html.HTMLFormElement;
import sun.security.util.PendingException;

import javax.servlet.*;
import java.lang.Object;
import org.mockito.Mockito;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

public class LogIn {

    LoginServlet l;
    HtmlPage login;

    @cucumber.api.java.en.Given("^I am a user trying to log in$")
    public void I_am_a_user_trying_to_log_in() throws Throwable {
        l = new LoginServlet();
        final WebClient webClient = new WebClient();

        /*String url = "localhost:8080/login";
        URL site = new URL(url);
        HttpURLConnection con = (HttpURLConnection) site.openConnection();
        con.setRequestMethod("Get");*/

        login = webClient.getPage("localhost:8080/login");







    }


    @When("^I login using valid credentials$")
    public void I_login_using_valid_credentials() throws Throwable {

        HtmlForm form = login.getFormByName("login");
        HtmlSubmitInput submit = form.getInputByName("Log in");
        HtmlTextInput username = form.getInputByName("username");
        HtmlTextInput password = form.getInputByName("password");

        username.setValueAttribute("admin");
        password.setValueAttribute("admin");

        HtmlPage next = submit.click();



    }

    @Then("^I should be taken to the chat page$")
    public void I_should_be_taken_to_the_chat_page() throws Throwable {
        Assert.assertEquals(l.lastPage, l.chatPage());
    }
}
