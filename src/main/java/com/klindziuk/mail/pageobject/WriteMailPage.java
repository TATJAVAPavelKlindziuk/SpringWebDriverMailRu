package com.klindziuk.mail.pageobject;

import com.klindziuk.mail.pageobject.block.Folder;
import com.klindziuk.mail.pageobject.block.Header;
import com.klindziuk.mail.constant.TimeConstants;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

/**
 * Created by Hp on 16/12/2017.
 */
public class WriteMailPage extends BasePage {
    private static final Logger LOGGER = Logger.getLogger(Folder.class);

    @Name("Recipient field")
    @FindBy(xpath = "//textarea[@data-original-name = 'To']")
    private WebElement recipientField;

    @Name("Subject field")
    @FindBy(name = "Subject")
    private WebElement subjectField;

    @Name("Text area Iframe")
    @FindBy(xpath = "//iframe[starts-with(@id,'toolkit-')]")
    private WebElement textAreaIframe;

    @Name("Text area")
    @FindBy(id = "tinymce")
    private WebElement textArea;

    @Name("Save draft button")
    @FindBy(xpath = "//div[@data-name = 'saveDraft']")
    private WebElement saveAsDraftButton;

    @Name("Send mail button")
    @FindBy(xpath = "//div[@data-name = 'send']")
    private WebElement sendMailButton;

    public WriteMailPage(WebDriver webDriver) {
        super(webDriver);
        HtmlElementLoader.populatePageObject(this,webDriver);
    }

    public Header header() {
        return new Header(webDriver);
    }

    public Folder folder() {
        return new Folder(webDriver);
    }

    public boolean isRecipientFieldVisible() {
        return recipientField.isDisplayed() && recipientField.isEnabled();
    }

    public boolean isSubjectFieldVisible() {
        return subjectField.isDisplayed() && subjectField.isEnabled();
    }

    public void saveAsDraftMail(String recipient, String subject, String text) {
        writeMail(recipient, subject, text);
        saveAsDraftButton.click();
        LOGGER.info("Save mail as draft");
        pause(TimeConstants.SECONDS_3);
    }

    public void sendMail(String recipient, String subject, String text) {
        writeMail(recipient, subject, text);
        pushSentMailButton();
    }

    public void pushSentMailButton() {
        sendMailButton.click();
        webDriver.switchTo().alert().dismiss();
        LOGGER.info("Sending mail to recipient");
        pause(TimeConstants.SECONDS_3);
    }

    public void waitForPageLoaded() {
        waitForElementVisible(recipientField);
        waitForElementVisible(subjectField);
    }

    private void writeMail(String recipient, String subject, String text) {
        LOGGER.info("Writing new mail...");
        recipientField.sendKeys(recipient);
        webDriver.switchTo().alert().dismiss();
        pause(TimeConstants.SECONDS_5);

        subjectField.sendKeys(subject);
        pause(TimeConstants.SECONDS_2);
        // switch to inframe
        webDriver.switchTo().frame(textAreaIframe);
        textArea.click();
        textArea.clear();
        textArea.sendKeys(text);
        // switch to default content
        webDriver.switchTo().defaultContent();
    }
}
