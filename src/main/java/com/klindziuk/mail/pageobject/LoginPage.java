package com.klindziuk.mail.pageobject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Hp on 16/12/2017.
 */
public class LoginPage extends BasePage {

    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(id = "mailbox:password")
    private WebElement passwordField;

    @FindBy(id = "mailbox:submit")
    private WebElement submitButton;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isLoginFieldVisible(){
        return loginField.isDisplayed();
    }

    public boolean isPasswordFieldVisible(){
        return loginField.isDisplayed();
    }

    public boolean isSubmitButtonVisible(){
        return loginField.isDisplayed();
    }

    public void loginAs(String login, String password){
        waitForPageLoaded();
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        submitButton.click();
    }

    @Override
    public void waitForPageLoaded() {
        waitForElementDisplayed(loginField);
        waitForElementDisplayed(passwordField);
        waitForElementDisplayed(submitButton);
    }
}
