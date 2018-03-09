package com.klindziuk.mail.pageobject;

import com.klindziuk.mail.util.JavaScriptUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Hp on 16/12/2017.
 */
public class LoginPage extends BasePage {
    private static final Logger LOGGER = Logger.getLogger(LoginPage.class);

    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(id = "mailbox:password")
    private WebElement passwordField;

    @FindBy(id = "mailbox:submit")
    private WebElement submitButton;

    public LoginPage() {
        PageFactory.initElements(this.webDriver, this);
    }

    public boolean isLoginFieldVisible() {
        return isElementVisible(loginField);
    }

    public boolean isPasswordFieldVisible() {
        return isElementVisible(passwordField);
    }

    public boolean isSubmitButtonVisible() {
        return isElementVisible(submitButton);
    }

    public void loginAs(String login, String password) {
        LOGGER.info("Enter credentials: " + login + " / " + password);
        JavaScriptUtil.highlightElement(loginField);
        loginField.sendKeys(login);

        JavaScriptUtil.highlightElement(passwordField);
        passwordField.sendKeys(password);

        JavaScriptUtil.unHighlightElements(loginField, passwordField);
        submitButton.click();
    }

    public String getPageUrl(){
        scrollDownABit();
       return JavaScriptUtil.getPageUrl();
    }

    public void scrollDownABit(){
         JavaScriptUtil.scrollDownPage(0,10);
    }

    @Override
    public void waitForPageLoaded() {
        LOGGER.info("Wait for Login/Main page loaded...");
        waitForElementVisible(loginField);
        waitForElementVisible(passwordField);
        waitForElementVisible(submitButton);
    }
}
