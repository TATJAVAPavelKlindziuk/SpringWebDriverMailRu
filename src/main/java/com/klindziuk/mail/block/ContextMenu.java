package com.klindziuk.mail.block;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

@Block(@FindBy(xpath = "//form1"))
public class ContextMenu {
	private static final Logger LOGGER = Logger.getLogger(Folder.class);
	private WebDriver webDriver;

	@Name("Sent folder")
	@FindBy(className = "b-dropdown__list__item__text")
	private WebElement sentFolder;

	public ContextMenu(WebDriver webDriver) {
		HtmlElementLoader.populatePageObject(this,webDriver);
	}

	public void openSentTab()
	{
		LOGGER.info("Verify Context Menu....");
		sentFolder.click();
	}
}
