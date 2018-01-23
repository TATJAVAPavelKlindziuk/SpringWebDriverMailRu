package com.klindziuk.mail.pageobject;

import com.klindziuk.mail.block.Folder;
import com.klindziuk.mail.block.Header;
import com.klindziuk.mail.constant.TimeConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import java.util.List;

/**
 * Created by Hp on 16/12/2017.
 */
public class SentPage extends BasePage {
    private static final String CONTAINER_PATH = "//div[@class ='b-datalist__body']";

    @Name("Checkbox")
    @FindBy(xpath = CONTAINER_PATH + "//div[@class = 'js-item-checkbox b-datalist__item__cbx']")
    private WebElement checkBox;

    @Name("Mails")
    @FindBy(xpath = CONTAINER_PATH + "//div[@class ='b-datalist__item__addr']")
    private List<WebElement> mails;

    @Name("Messages")
    @FindBy(xpath = CONTAINER_PATH + "//span[@class ='b-datalist__item__subj__snippet']")
    private List<WebElement> messages;

    public SentPage(WebDriver webDriver) {
        super(webDriver);
        HtmlElementLoader.populatePageObject(this,webDriver);
    }

    public Header header() {
        return new Header(webDriver);
    }

    public Folder folder() {
        return new Folder(webDriver);
    }

    public boolean isPageContainsMail(String message) {
        pause(TimeConstants.SECONDS_3);
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
