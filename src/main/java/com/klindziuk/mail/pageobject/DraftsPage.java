package com.klindziuk.mail.pageobject;

import com.klindziuk.mail.pageobject.block.Folder;
import com.klindziuk.mail.pageobject.block.Header;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by Hp on 16/12/2017.
 */
public class DraftsPage extends BasePage {
    private static final Logger LOGGER = Logger.getLogger(DraftsPage.class);
    private static final String CONTAINER_PATH = "//div[@class ='b-datalist b-datalist_letters b-datalist_letters_to']";

    private Header header;
    private Folder folder;

    @FindBy(xpath = CONTAINER_PATH + "//div[@class = 'b-datalist__item__info']")
    private WebElement draftContainer;

    @FindBy(xpath = CONTAINER_PATH + "//div[@class = 'b-datalist__item__addr']")
    private List<WebElement> mails;

    @FindBy(xpath = CONTAINER_PATH + "//span[@class = 'b-datalist__item__subj__snippet']")
    private List<WebElement> messages;

    public DraftsPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
        header = PageFactory.initElements(webDriver, Header.class);
        folder = PageFactory.initElements(webDriver, Folder.class);
    }

    public Header header() {
        return header;
    }

    public Folder folder() {
        return folder;
    }

    public boolean isDraftContainerVisible() {
        return isElementVisible(draftContainer);
    }

    public boolean isMessagesPresent() {
        return messages.size() != 0;
    }

    public boolean isMailsPresent() {
        return mails.size() != 0;
    }

    public String getMessage(int index){
       return messages.get(index).getText();
    }

    public String getMail(int index){
       return mails.get(index).getText();
    }

    public void openMailFromDraft(int index){
        LOGGER.info("Open mail from draft with number: " + (index + 1));
        mails.get(index).click();
    }

    @Override
    public void waitForPageLoaded() {
        waitForElementVisible(draftContainer);
    }

    @Override
    public String getTitle() {
        // MOCK
        return "Drafts page";
    }
}
