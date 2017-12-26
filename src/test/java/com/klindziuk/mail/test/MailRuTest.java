package com.klindziuk.mail.test;

import com.klindziuk.mail.pageobject.*;
import com.klindziuk.mail.util.BrowserDriver;
import com.klindziuk.mail.constants.MailConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

/**
 * Created by Hp on 16/12/2017.
 */
@ContextConfiguration(locations = { "file:src/test/resources/springcontext.xml" })
public class MailRuTest extends AbstractTestNGSpringContextTests {
    private LoginPage loginPage;
    private ServicePage servicePage;
    private WriteMailPage writeMailPage;
    private DraftsPage draftsPage;
    private SentPage sentPage;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        WebDriver webDriver = BrowserDriver.getDriver();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.navigate().to(MailConstants.MAIL_URL);
        loginPage = new LoginPage(webDriver);
        servicePage = new ServicePage(webDriver);
        writeMailPage = new WriteMailPage(webDriver);
        draftsPage = new DraftsPage(webDriver);
        sentPage = new SentPage(webDriver);
    }

    @Test
    public void checkLogin() {
        final int firstElementIndex = 0;
        SoftAssert softAssert = new SoftAssert();

        // Step 1 : Login to the mail box
        checkLoginPage(softAssert);
        loginPage.loginAs(MailConstants.LOGIN, MailConstants.PASSWORD);

        // Step 2 : Verify that the login is successful
        servicePage.waitForPageLoaded();
        softAssert.assertTrue(servicePage.isServicePageOpened(), "Service Page is not opened");

        // Step 3-4 : Create a new mail (fill addressee, subject and body fields),save the mail as a draft
        servicePage.openNewMail();
        checkWriteMailPage(softAssert);
        writeMailPage.saveAsDraftMail(MailConstants.RECIPIENT, MailConstants.SUBJECT, MailConstants.TEXT);

        // Step 5 : Verify, that the mail presents in ‘Drafts’ folder.
        writeMailPage.folder().openDraftTab();
        draftsPage.waitForPageLoaded();
        softAssert.assertTrue(draftsPage.isDraftContainerVisible());

        // Step 6 : Verify the draft content (addressee, subject and body – should be the same as in Step 3)
        softAssert.assertEquals(draftsPage.getMail(firstElementIndex), MailConstants.RECIPIENT,
                String.format("Email '%s' should be equal to '%s'", draftsPage.getMail(firstElementIndex),
                        MailConstants.RECIPIENT));
        softAssert.assertEquals(draftsPage.getMessage(firstElementIndex), MailConstants.TEXT,
                String.format("Text '%s' should be equal to '%s'", draftsPage.getMessage(firstElementIndex),
                        MailConstants.TEXT));

        // Step 7 : Send the mail
        draftsPage.openMailFromDraft(firstElementIndex);
        checkWriteMailPage(softAssert);
        writeMailPage.pushSentMailButton();

        // Step 8 : Verify, that the mail disappeared from ‘Drafts’ folder.
        writeMailPage.folder().openDraftTab();
        softAssert.assertFalse(draftsPage.isDraftContainerVisible(), "Draft container is visible");
        softAssert.assertFalse(draftsPage.isMailsPresent(), "Mail is present in drafts after send");
        softAssert.assertFalse(draftsPage.isMessagesPresent(),"Mail is present in drafts after send");

        // Step 9 : Verify, that the mail is in ‘Sent’ folder.
        writeMailPage.folder().openSentTab();
        softAssert.assertTrue(sentPage.isPageContainsMail(MailConstants.TEXT), "Mail is not present in sent tab");

        // Step 10 : Log off
        sentPage.header().LogOut();

        // Step 11 : Verify that log off is successful
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

    private void checkWriteMailPage(SoftAssert softAssert){
        writeMailPage.waitForPageLoaded();
        softAssert.assertTrue(writeMailPage.isRecipientFieldVisible(), "Recipient field is not visible");
        softAssert.assertTrue(writeMailPage.isSubjectFieldVisible(), "Subject field is not visible");
    }
}
