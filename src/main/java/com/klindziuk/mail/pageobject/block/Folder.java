package com.klindziuk.mail.pageobject.block;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Hp on 17/12/2017.
 */
public class Folder {
    private static final Logger LOGGER = Logger.getLogger(Folder.class);
    private WebDriver webDriver;

    @FindBy(xpath = "//div[@data-id = '500000']")
    private WebElement sentFolder;

    @FindBy(xpath = "//a[@data-mnemo = 'drafts']")
    private WebElement draftFolder;

    public Folder(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void openDraftTab() {
        LOGGER.info("Opening Draft tab...");
        draftFolder.click();
    }

    public void openSentTab()
    {
        LOGGER.info("Opening Sent tab...");
        sentFolder.click();
    }
}
