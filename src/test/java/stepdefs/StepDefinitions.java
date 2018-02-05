package com.klindziuk.mail.test.cucumber.stepdefs;

import com.klindziuk.mail.constant.MailConstants;
import com.klindziuk.mail.model.User;
import com.klindziuk.mail.pageobject.DraftsPage;
import com.klindziuk.mail.pageobject.LoginPage;
import com.klindziuk.mail.pageobject.SentPage;
import com.klindziuk.mail.pageobject.ServicePage;
import com.klindziuk.mail.pageobject.WriteMailPage;
import com.klindziuk.mail.util.BrowserDriver;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

@ContextConfiguration(locations = { "file:src/test/resources/springcontext.xml" })
public class StepDefinitions extends AbstractTestNGSpringContextTests {
    private LoginPage loginPage;
    private ServicePage servicePage;
    private WriteMailPage writeMailPage;
    private DraftsPage draftsPage;
    private SentPage sentPage;
    WebDriver webDriver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        webDriver = BrowserDriver.getDriver();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        loginPage = new LoginPage(webDriver);
        servicePage = new ServicePage(webDriver);
        writeMailPage = new WriteMailPage(webDriver);
        draftsPage = new DraftsPage(webDriver);
        sentPage = new SentPage(webDriver);
    }

    @Given("^I am on the \"([^\"]*)\" page on URL \"([^\"]*)\"$")
    public void i_am_on_the_page_on_URL(String arg1, String arg2) throws Throwable {
        System.out.println(arg2);
        webDriver.navigate().to(arg2);
        throw new PendingException();
    }

    @Then("^I should see \"([^\"]*)\" message$")
    public void i_should_see_message(String arg1) throws Throwable {
        SoftAssert softAssert = new SoftAssert();
        checkLoginPage(softAssert);
        throw new PendingException();
    }

    private void checkLoginPage(SoftAssert softAssert) {
        loginPage.waitForPageLoaded();
        softAssert.assertTrue(loginPage.isLoginFieldVisible());
        softAssert.assertTrue(loginPage.isPasswordFieldVisible());
        softAssert.assertTrue(loginPage.isSubmitButtonVisible());
        softAssert.assertAll();
    }
}
