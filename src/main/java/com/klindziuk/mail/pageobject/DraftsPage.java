package com.klindziuk.mail.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Hp on 16/12/2017.
 */
public class DraftsPage extends BasePage {
    private static final String CONTAINER_PATH = "//div[@class ='b-datalist b-datalist_letters b-datalist_letters_to']";

    @FindBy(xpath = CONTAINER_PATH + "//div[@class = 'b-datalist__item__info']")
    private WebElement draftContainer;

    @FindBy(xpath = CONTAINER_PATH + "//div[@class = 'b-datalist__item__addr']")
    private List<WebElement> mails;

    @FindBy(xpath = CONTAINER_PATH + "//span[@class = 'b-datalist__item__subj__snippet']")
    private List<WebElement> messages;

    public DraftsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isDraftContainerVisible() {
        return draftContainer.isDisplayed() && draftContainer.isEnabled();
    }

    public String getMessage(int index){
       return messages.get(index).getText();
    }

    public String getMail(int index){
       return mails.get(index).getText();
    }

    @Override
    public void waitForPageLoaded() {
        waitForElementDisplayed(draftContainer);
    }
}
