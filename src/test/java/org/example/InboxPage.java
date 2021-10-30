package org.example;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InboxPage {
    private final WebDriver driver;

    public InboxPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[@href = \"#compose\"]")
    private WebElement mailBtn;

    @FindBy(xpath = "//*[@class=\"composeYabbles\"]")
    private WebElement mailAdress;

    @FindBy(xpath = "//*[@role=\"textbox\"]")
    private WebElement mailText;

    @FindBy(xpath = "//*[@name=\"subject\"]")
    private WebElement mailSubject;

    @FindBy(xpath = "//button[contains(@class, 'circle')]")
    private WebElement sendBtn;

    public void clickMailBtn() {
        mailBtn.click();
    }

    public void inputMailAdress(String mail) {
        mailAdress.click();
        mailAdress.sendKeys(mail);
    }

    public void inputMailSubject(String subject) {
        mailSubject.click();
        mailSubject.sendKeys(subject);
    }

    public void inputMailText(String text) {
        mailText.sendKeys(text);
    }

    public void clickSendBtn() {
        sendBtn.click();
    }


    public int counter(String text) {
        List<WebElement> a = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
        return a.size();
    }

    public void checkMailSent() {
        Assert.assertTrue(String.valueOf(By.xpath("//*[contains(text(),'Message sent')]")), true);
    }
}

