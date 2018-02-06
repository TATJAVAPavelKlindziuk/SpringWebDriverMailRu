package com.klindziuk.mail.pageobject;

import com.klindziuk.mail.pageobject.block.Folder;
import com.klindziuk.mail.pageobject.block.Header;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import java.util.List;

/**
 * Created by Hp on 16/12/2017.
 */
public class DraftsPage extends BasePage {

    private static final Logger LOGGER = Logger.getLogger(DraftsPage.class);
    private static final String CONTAINER_PATH = "//div[@class ='b-datalist b-datalist_letters b-datalist_letters_to']";

    @Name("Draft Container")
    @FindBy(xpath = CONTAINER_PATH + "//div[@class = 'b-datalist__item__info']")
    private WebElement draftContainer;

    @Name("Mails")
    @FindBy(xpath = CONTAINER_PATH + "//div[@class = 'b-datalist__item__addr']")
    private List<WebElement> mails;

    @Name("Messages")
    @FindBy(xpath = CONTAINER_PATH + "//span[@class = 'b-datalist__item__subj__snippet']")
    private List<WebElement> messages;

    public DraftsPage(WebDriver webDriver) {
        super(webDriver);
        HtmlElementLoader.populatePageObject(this,webDriver);
    }

    public Header header() {
        return new Header(webDriver);
    }

    public Folder folder() {
        return new Folder(webDriver);
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
}
