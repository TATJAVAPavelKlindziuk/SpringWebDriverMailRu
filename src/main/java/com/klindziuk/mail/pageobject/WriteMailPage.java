package com.klindziuk.mail.pageobject;

import com.klindziuk.mail.block.Folder;
import com.klindziuk.mail.block.Header;
import com.klindziuk.mail.constant.TimeConstants;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Hp on 16/12/2017.
 */
public class WriteMailPage extends BasePage {
    private static final Logger LOGGER = Logger.getLogger(Folder.class);
    private Header header;
    private Folder folder;

    @FindBy(xpath = "//textarea[@data-original-name = 'To']")
    private WebElement recipientField;

    @FindBy(name = "Subject")
    private WebElement subjectField;

    @FindBy(xpath = "//iframe[starts-with(@id,'toolkit-')]")
    private WebElement textAreaIframe;

    @FindBy(id = "tinymce")
    private WebElement textArea;

    @FindBy(xpath = "//div[@data-name = 'saveDraft']")
    private WebElement saveAsDraftButton;

    @FindBy(xpath = "//div[@data-name = 'send']")
    private WebElement sendMailButton;

    public WriteMailPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
        header = PageFactory.initElements(webDriver, Header.class);
        folder = PageFactory.initElements(webDriver, Folder.class);
    }

    public Header header() {
        return header;
    }

    public Folder folder() {
        return folder;
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
