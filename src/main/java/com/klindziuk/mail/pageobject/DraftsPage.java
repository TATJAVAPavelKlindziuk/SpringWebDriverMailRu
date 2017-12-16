package com.klindziuk.mail.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Hp on 16/12/2017.
 */
public class DraftsPage extends BasePage {

    @FindBy(xpath = "//div[@class ='b-datalist b-datalist_letters b-datalist_letters_to']" +
            "//div[@class = 'b-datalist__item__info']")
    private WebElement draftContainer;

    public DraftsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isDraftContainerVisible() {
        return draftContainer.isDisplayed() && draftContainer.isEnabled();
    }

    @Override
    public void waitForPageLoaded() {
        waitForElementDisplayed(draftContainer);
    }
}
