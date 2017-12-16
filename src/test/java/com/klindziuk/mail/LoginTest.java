package com.klindziuk.mail;

import com.klindziuk.mail.pageobject.DraftsPage;
import com.klindziuk.mail.pageobject.LoginPage;
import com.klindziuk.mail.pageobject.ServicePage;
import com.klindziuk.mail.pageobject.WriteMailPage;
import com.klindziuk.mail.util.BrowserDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

/**
 * Created by Hp on 16/12/2017.
 */
public class LoginTest {
    protected static final String BASE_URL = "https://mail.ru/";
    private WebDriver webDriver;
    private LoginPage loginPage;
    private ServicePage servicePage;
    private WriteMailPage writeMailPage;
    private DraftsPage draftsPage;

    @BeforeTest
    public void setUp() {
        webDriver = BrowserDriver.CHROME.selectDriver();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.navigate().to(BASE_URL);
        loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        servicePage = PageFactory.initElements(webDriver, ServicePage.class);
        writeMailPage = PageFactory.initElements(webDriver, WriteMailPage.class);
        draftsPage = PageFactory.initElements(webDriver, DraftsPage.class);
    }

    @Test
    public void checkLogin() {
        final String login = "vshub_vvk-3_2014";
        final String password = "VVK-3_2014";
        final String recipient = "fennya@gmail.com";
        final String subject = "test";
        final String text = "text for test";
        SoftAssert softAssert = new SoftAssert();

        loginPage.waitForPageLoaded();

        softAssert.assertTrue(loginPage.isLoginFieldVisible());
        softAssert.assertTrue(loginPage.isPasswordFieldVisible());
        softAssert.assertTrue(loginPage.isSubmitButtonVisible());
        softAssert.assertAll();

        loginPage.loginAs(login, password);
        servicePage.waitForPageLoaded();
        softAssert.assertTrue(servicePage.isServicePageOpened());
        softAssert.assertAll();

        servicePage.openNewMail();
        writeMailPage.waitForPageLoaded();
        softAssert.assertTrue(writeMailPage.isRecipientFieldVisible());
        softAssert.assertTrue(writeMailPage.isSubjectFieldVisible());
        softAssert.assertAll();

        writeMailPage.saveAsDraftMail(recipient, subject, text);
        writeMailPage.openDraftTab();

        draftsPage.waitForPageLoaded();
        softAssert.assertTrue(draftsPage.isDraftContainerVisible());
        softAssert.assertEquals(draftsPage.getMail(0),recipient);
        softAssert.assertEquals(draftsPage.getMessage(0),text);
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDrown() {
        webDriver.close();
        BrowserDriver.CHROME.killDriver();
    }
}
