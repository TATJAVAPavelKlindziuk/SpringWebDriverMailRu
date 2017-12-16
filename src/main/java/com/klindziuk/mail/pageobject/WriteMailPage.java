package com.klindziuk.mail.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Hp on 16/12/2017.
 */
public class WriteMailPage extends BasePage {

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

    @FindBy(xpath = "//a[@data-mnemo = 'drafts']")
    private WebElement draftTile;

    public WriteMailPage(WebDriver webDriver) {
        super(webDriver);
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
    }

    public void writeMail(String recipient, String subject, String text) {
        recipientField.sendKeys(recipient);
        subjectField.sendKeys(subject);
        //switch to inframe
        webDriver.switchTo().frame(textAreaIframe);
        textArea.click();
        textArea.clear();
        textArea.sendKeys(text);
        // switch to default content
        webDriver.switchTo().defaultContent();
    }

    public void openDraftTab(){
        draftTile.click();
    }

    public void waitForPageLoaded() {
        waitForElementDisplayed(recipientField);
        waitForElementDisplayed(subjectField);
    }
}
