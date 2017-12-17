package com.klindziuk.mail.pageobject;

import com.klindziuk.mail.blocks.Folder;
import com.klindziuk.mail.blocks.Header;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Hp on 16/12/2017.
 */
public class ServicePage extends BasePage {
    private static final Logger LOGGER = Logger.getLogger(ServicePage.class);
    private Header header;
    private Folder folder;

    @FindBy(xpath = "//div[@class= 'b-toolbar__item']//a[@data-name = 'compose']")
    private WebElement writeMailButton;

    public ServicePage(WebDriver webDriver) {
        super(webDriver);
        header = PageFactory.initElements(webDriver, Header.class);
        folder = PageFactory.initElements(webDriver, Folder.class);
    }

    public Header header() {
        return header;
    }

    public Folder folder() {
        return folder;
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
