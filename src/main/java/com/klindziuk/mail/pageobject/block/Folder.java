package com.klindziuk.mail.pageobject.block;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

/**
 * Created by Hp on 17/12/2017.
 */
@Block(@FindBy(xpath = "//form1"))
public class Folder {
    private static final Logger LOGGER = Logger.getLogger(Folder.class);
    private WebDriver webDriver;

    @Name("Sent folder")
    @FindBy(xpath = "//div[@data-id = '500000']")
    private WebElement sentFolder;

    @Name("Draft folder")
    @FindBy(xpath = "//a[@data-mnemo = 'drafts']")
    private WebElement draftFolder;

    public Folder(WebDriver webDriver) {
        HtmlElementLoader.populatePageObject(this,webDriver);
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
