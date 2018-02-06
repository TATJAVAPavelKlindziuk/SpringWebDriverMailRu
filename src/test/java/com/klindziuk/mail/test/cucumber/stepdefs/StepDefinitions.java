package com.klindziuk.mail.test.cucumber.stepdefs;

import com.klindziuk.mail.constant.MailConstants;
import com.klindziuk.mail.pageobject.DraftsPage;
import com.klindziuk.mail.pageobject.LoginPage;
import com.klindziuk.mail.pageobject.SentPage;
import com.klindziuk.mail.pageobject.ServicePage;
import com.klindziuk.mail.pageobject.WriteMailPage;
import com.klindziuk.mail.util.BrowserDriver;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

@ContextConfiguration(locations = { "file:src/test/resources/springcontext.xml" })
public class StepDefinitions extends AbstractTestNGSpringContextTests {
    private static final Logger LOGGER = Logger.getLogger(StepDefinitions.class);
    private LoginPage loginPage;
    private ServicePage servicePage;
    private WriteMailPage writeMailPage;
    private DraftsPage draftsPage;
    private SentPage sentPage;
    private WebDriver webDriver;
    private SoftAssert softAssert;
    private final int firstElementIndex = 0;

    @Before()
    public void setUp() {
        webDriver = BrowserDriver.getDriver();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        loginPage = new LoginPage(webDriver);
        servicePage = new ServicePage(webDriver);
        writeMailPage = new WriteMailPage(webDriver);
        draftsPage = new DraftsPage(webDriver);
        sentPage = new SentPage(webDriver);
        softAssert = new SoftAssert();
    }

    // Background: Scenario is started
    @Given("^Scenario is started$")
    public void scenario_started() throws Throwable {
        LOGGER.info("New Scenario is started");
    }

    // Scenario: User navigates to mail service home page
    @Given("^I am on the \"([^\"]*)\"$")
    public void i_am_on_the(String arg1) throws Throwable {
        webDriver.navigate().to(arg1);
    }

    @Then("^I should see login form$")
    public void i_should_see_login_form() throws Throwable {
        checkLoginPage(softAssert);
    }

    // Scenario Outline: Successful login
    @When("^I login with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_login_with(String arg1, String arg2) throws Throwable {
        loginPage.loginAs(arg1, arg2);
    }

    @Then("^I am on the Service page$")
    public void i_am_on_the_service_page() throws Throwable {
        servicePage.waitForPageLoaded();
        softAssert.assertTrue(servicePage.isServicePageOpened(), "Service Page is not opened");
    }

    // Scenario Outline: Mail creation
    @Given("^I create mail with \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_create_mail_with(String arg1, String arg2, String arg3) throws Throwable {
        servicePage.openNewMail();
        checkWriteMailPage(softAssert);
        writeMailPage.saveAsDraftMail(arg1, arg2, arg3);
    }

    @And("^Open drafts tab$")
    public void open_drafts_tab() throws Throwable {
        writeMailPage.folder().openDraftTab();
        draftsPage.waitForPageLoaded();
        softAssert.assertTrue(draftsPage.isDraftContainerVisible());
    }

    @Then("^Content should be \"([^\"]*)\" and addressee should be \"([^\"]*)\"$$")
    public void content_should_be_and_addressee_should_be(String arg1, String arg2) throws Throwable {

        softAssert.assertEquals(draftsPage.getMail(firstElementIndex), MailConstants.NO_RECIPIENT,
                String.format("Email '%s' should be equal to '%s'", draftsPage.getMail(firstElementIndex),
                        MailConstants.NO_RECIPIENT));
        softAssert.assertEquals(draftsPage.getMessage(firstElementIndex), MailConstants.TEXT,
                String.format("Text '%s' should be equal to '%s'", draftsPage.getMessage(firstElementIndex),
                        MailConstants.TEXT));
    }

    // Scenario: Sent mail
    @When("^I open mail from draft page$")
    public void open_mail_from_draft_tab() throws Throwable {
        draftsPage.openMailFromDraft(firstElementIndex);
        checkWriteMailPage(softAssert);
    }

    @And("^Push sent mail button$")
    public void push_sent_mail_button() throws Throwable {
        writeMailPage.pushSentMailButton();
    }

    @Then("^Mail should be absent in draft container$")
    public void mail_should_be_absent() throws Throwable {
        writeMailPage.folder().openDraftTab();
        softAssert.assertTrue(draftsPage.isDraftContainerVisible(), "Draft container is visible");
        softAssert.assertTrue(draftsPage.isMailsPresent(), "Mail is present in drafts after send");
        softAssert.assertTrue(draftsPage.isMessagesPresent(), "Mail is present in drafts after send");
    }

    // Scenario: Successful logout
    @When("^I push logout button$")
    public void push_logout_button() throws Throwable {
        sentPage.header().LogOut();
    }

    @Then("^I should be logged off$")
    public void should_be_logged_off() throws Throwable {
        checkLoginPage(softAssert);
        softAssert.assertAll();
    }

    private void checkLoginPage(SoftAssert softAssert) {
        loginPage.waitForPageLoaded();
        softAssert.assertTrue(loginPage.isLoginFieldVisible());
        softAssert.assertTrue(loginPage.isPasswordFieldVisible());
        softAssert.assertTrue(loginPage.isSubmitButtonVisible());
        softAssert.assertAll();
    }

    private void checkWriteMailPage(SoftAssert softAssert) {
        writeMailPage.waitForPageLoaded();
        softAssert.assertTrue(writeMailPage.isRecipientFieldVisible(), "Recipient field is not visible");
        softAssert.assertTrue(writeMailPage.isSubjectFieldVisible(), "Subject field is not visible");
    }
}
