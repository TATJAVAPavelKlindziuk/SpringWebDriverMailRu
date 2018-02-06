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
public class Header {
    private static final Logger LOGGER = Logger.getLogger(Header.class);
    private WebDriver webDriver;

    @Name("Exit button")
    @FindBy(xpath = "//a[@id = 'PH_logoutLink']")
    private WebElement exitButton;

    public Header(WebDriver webDriver) {
        HtmlElementLoader.populatePageObject(this,webDriver);
    }

    public void LogOut()
    {
        LOGGER.info("Logging out...");
        exitButton.click();
    }
}
