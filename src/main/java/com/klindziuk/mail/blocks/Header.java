package com.klindziuk.mail.blocks;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Hp on 17/12/2017.
 */
public class Header {
    private static final Logger LOGGER = Logger.getLogger(Header.class);
    private WebDriver webDriver;

    @FindBy(xpath = "//a[@id = 'PH_logoutLink']")
    private WebElement exitButton;

    public Header(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void LogOut()
    {
        LOGGER.info("Logging out...");
        exitButton.click();
    }
}
