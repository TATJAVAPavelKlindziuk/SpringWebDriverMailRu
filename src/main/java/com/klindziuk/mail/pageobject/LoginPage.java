package com.klindziuk.mail.pageobject;

import com.klindziuk.mail.annotation.Anno;
import com.klindziuk.mail.element.AnnoElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.klindziuk.mail.enumeration.SearchBy.ID;

/**
 * Created by Hp on 16/12/2017.
 */
public class LoginPage extends BasePage {
    private static final Logger LOGGER = Logger.getLogger(LoginPage.class);

    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @Anno(searchBy = ID, value = "mailbox:password")
    private AnnoElement passwordField;

    @Anno(searchBy = ID,  value = "mailbox:submit")
    private AnnoElement submitButton;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    public boolean isLoginFieldVisible() {
        return isElementVisible(loginField);
    }

    public boolean isPasswordFieldVisible() {
        return isElementVisible(findElement(passwordField));
    }

    public boolean isSubmitButtonVisible() {
        return isElementVisible(findElement(submitButton));
    }

    public void loginAs(String login, String password) {
        LOGGER.info("Enter credentials: " + login + " / " + password);
        waitForPageLoaded();
        loginField.sendKeys(login);
        findElement(passwordField).sendKeys(password);
        findElement(submitButton).click();
    }

    @Override
    public void waitForPageLoaded() {
        LOGGER.info("Wait for main page loaded...");
        waitForElementVisible(loginField);
        waitForElementVisible(findElement(passwordField));
        waitForElementVisible(findElement(submitButton));
    }
}
