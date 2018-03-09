package com.klindziuk.mail.pageobject;

/**
 * Created by Hp on 16/12/2017.
 */
import com.klindziuk.mail.block.Folder;
import com.klindziuk.mail.block.Header;
import com.klindziuk.mail.constant.TimeConstant;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by Hp on 16/12/2017.
 */
public class SentPage extends BasePage {
    private static final String CONTAINER_PATH = "//div[@class ='b-datalist__body']";

    private Header header;
    private Folder folder;

    @FindBy(xpath = CONTAINER_PATH + "//div[@class = 'js-item-checkbox b-datalist__item__cbx']")
    private WebElement checkBox;

    @FindBy(xpath = CONTAINER_PATH + "//div[@class ='b-datalist__item__addr']")
    private List<WebElement> mails;

    @FindBy(xpath = CONTAINER_PATH + "//span[@class ='b-datalist__item__subj__snippet']")
    private List<WebElement> messages;

    public SentPage() {
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

    public boolean isPageContainsMail(String message) {
        pause(TimeConstant.SECONDS_3);
        for (WebElement webElement : messages) {
            if (webElement.getText().contains(message) || webElement.getText().equals(message)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void waitForPageLoaded() {
        waitForElementVisible(messages.get(0));
    }
}
