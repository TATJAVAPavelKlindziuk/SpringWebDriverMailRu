package com.klindziuk.mail.pageobject;

import com.klindziuk.mail.util.JavaScriptUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

/**
 * Created by Hp on 16/12/2017.
 */
public class LoginPage extends BasePage {
    private static final Logger LOGGER = Logger.getLogger(LoginPage.class);

    @Name("Login field") @FindBy(id = "mailbox:login") private WebElement loginField;

    @Name("Password field") @FindBy(id = "mailbox:password") private WebElement passwordField;

    @Name("Submit button") @FindBy(id = "mailbox:submit") private WebElement submitButton;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        HtmlElementLoader.populatePageObject(this, webDriver);
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
        waitForPageLoaded();
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        submitButton.click();
    }

    private void scrollDownABit() {
        JavaScriptUtil.scrollDownPage(webDriver, 0, 10);
    }

    @Override
    public void waitForPageLoaded() {
        LOGGER.info("Wait for main page loaded...");
        waitForElementVisible(loginField);
        waitForElementVisible(passwordField);
        waitForElementVisible(submitButton);
    }
}
