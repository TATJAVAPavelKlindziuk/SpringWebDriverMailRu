package com.klindziuk.mail.pageobject;

import com.klindziuk.mail.block.Folder;
import com.klindziuk.mail.block.Header;
import com.klindziuk.mail.constant.TimeConstant;
import com.klindziuk.mail.util.JavaScriptUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoAlertPresentException;
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

    public WriteMailPage() {
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
        pause(TimeConstant.SECONDS_3);
    }

    public void sendMail(String recipient, String subject, String text) {
        LOGGER.info(String.format("Sending mail to '%s'", recipient));
        writeMail(recipient, subject, text);
        pushSentMailButton();
    }

    public void pushSentMailButton() {
        JavaScriptUtil.highlightElement(sendMailButton);
        sendMailButton.click();
        handleAlert();
        LOGGER.info("Sending mail to recipient");
        pause(TimeConstant.SECONDS_3);
    }

    public void waitForPageLoaded() {
        waitForElementVisible(recipientField);
        waitForElementVisible(subjectField);
    }

    private void writeMail(String recipient, String subject, String text) {
        LOGGER.info("Writing new mail...");
        JavaScriptUtil.highlightElement(recipientField);
        recipientField.sendKeys(recipient);

        handleAlert();
        pause(TimeConstant.SECONDS_2);

        JavaScriptUtil.highlightElement(subjectField);
        subjectField.sendKeys(subject);
        pause(TimeConstant.SECONDS_2);

        setTextToFrame(text);
        JavaScriptUtil.unHighlightElements(subjectField, recipientField);
    }

    private void handleAlert() {
        LOGGER.warn("Trying to handle alert...");
        try {
            webDriver.switchTo().alert().dismiss();
        } catch (NoAlertPresentException noAex) {
            LOGGER.info("Alert missed, use normal flow");
        }
    }

    private void setTextToFrame(String text) {
        LOGGER.info("Switching to Iframe");
        webDriver.switchTo().frame(textAreaIframe);
        JavaScriptUtil.highlightElement(textArea);
        textArea.click();
        textArea.clear();
        textArea.sendKeys(text);
        LOGGER.info("Switching from Iframe");
        JavaScriptUtil.unHighlightElements(textArea);
        webDriver.switchTo().defaultContent();
    }
}
