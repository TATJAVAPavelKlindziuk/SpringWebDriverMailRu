package com.klindziuk.mail.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Hp on 16/12/2017.
 */
public class ServicePage extends BasePage {

    @FindBy(xpath = "//div[@class= 'b-toolbar__item']//a[@data-name = 'compose']")
    private WebElement writeMailButton;

    public ServicePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openNewMail() {
        writeMailButton.click();
    }

    public boolean isServicePageOpened() {
        return writeMailButton.isDisplayed();
    }

    @Override
    public void waitForPageLoaded() {
        waitForElementDisplayed(writeMailButton);
    }

}
