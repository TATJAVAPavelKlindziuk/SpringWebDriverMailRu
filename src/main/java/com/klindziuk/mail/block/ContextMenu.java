package com.klindziuk.mail.block;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContextMenu {
	private static final Logger LOGGER = Logger.getLogger(Folder.class);
	private WebDriver webDriver;

	@FindBy(className = "b-dropdown__list__item__text")
	private WebElement sentFolder;

	public ContextMenu(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void openSentTab()
	{
		LOGGER.info("Verify Context Menu....");
		sentFolder.click();
	}
}
