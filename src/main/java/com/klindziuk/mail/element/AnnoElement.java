package com.klindziuk.mail.element;

import com.klindziuk.mail.enumeration.SearchBy;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Hp on 18/01/2018.
 */
public class AnnoElement extends By {

    private static final String REPLACE_TOKEN = "?";

    private By locator;
    private SearchBy elementSearchCriteria;
    private String elementValue;

    public AnnoElement(final SearchBy elementSearchCriteria, final String elementValue) {

        if (!elementValue.contains(REPLACE_TOKEN)) {
            initElement(elementSearchCriteria, elementValue);
        }

        this.elementSearchCriteria = elementSearchCriteria;
        this.elementValue = elementValue;
    }

    public AnnoElement updateElement(final String... values) {
        initElement(elementSearchCriteria, replaceWithValues(elementValue, REPLACE_TOKEN, values));
        return this;
    }

    public By getLocator() {
        return locator;
    }

    @Override
    public List<WebElement> findElements(final SearchContext searchContext) {
        return null;
    }

    private void initElement(final SearchBy elementSearchCriteria, final String elementValue) {

        switch (elementSearchCriteria) {
            case ID:
                locator = By.id(elementValue);
                break;
            case XPATH:
                locator = By.xpath(elementValue);
                break;
            case LINK_TEXT:
                locator = By.linkText(elementValue);
                break;
            case CLASS_NAME:
                locator = By.className(elementValue);
                break;
            case CSS_SELECTOR:
                locator = By.cssSelector(elementValue);
                break;
            case TAG_NAME:
                locator = By.tagName(elementValue);
                break;
            case NAME:
                locator = By.name(elementValue);
                break;
            case PARTIAL_LINK_TEXT:
                locator = By.partialLinkText(elementValue);
                break;
        }
    }

    private String replaceWithValues(final String target, final String replaceToken, final String... values) {
        return String.format(target.replace("%", "%%").replace(replaceToken, "%s"), (Object[]) values);
    }
}
