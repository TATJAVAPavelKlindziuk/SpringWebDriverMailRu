package com.klindziuk.mail.pageobject;

import com.klindziuk.mail.block.Folder;
import com.klindziuk.mail.block.Header;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

/**
 * Created by Hp on 16/12/2017.
 */
public class ServicePage extends BasePage {
    private static final Logger LOGGER = Logger.getLogger(ServicePage.class);

    @Name("Write mail button")
    @FindBy(xpath = "//div[@class= 'b-toolbar__item']//a[@data-name = 'compose']")
    private WebElement writeMailButton;

    public ServicePage(WebDriver webDriver) {
        super(webDriver);
        HtmlElementLoader.populatePageObject(this,webDriver);
    }

    public Header header() {
        return new Header(webDriver);
    }

    public Folder folder() {
        return new Folder(webDriver);
    }

    public void openNewMail()
    {
        LOGGER.info("Composing new mail...");
        writeMailButton.click();
    }

    public boolean isServicePageOpened() {
        return writeMailButton.isDisplayed();
    }

    @Override
    public void waitForPageLoaded() {
        waitForElementVisible(writeMailButton);
    }

}
