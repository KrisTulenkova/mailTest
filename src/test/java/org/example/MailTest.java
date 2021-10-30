package org.example;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class MailTest {
    private static LoginPage loginPage;
    private static InboxPage inboxPage;
    private static WebDriver driver;

    @BeforeClass
    public static void setup() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),options);
        loginPage = new LoginPage(driver);
        inboxPage = new InboxPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    public void startTest() {
        loginPage.clickEnterBtn();
        loginPage.inputLogin("tes711test");
        loginPage.clickLoginBtn();
        loginPage.inputPasswd("123456As!");
        loginPage.clickLoginBtn();
        inboxPage.clickMailBtn();
        inboxPage.inputMailAdress("tes711test@yandex.com");
        inboxPage.inputMailSubject("Simbirsoft Тестовое задание. Туленкова");
        inboxPage.inputMailText("Количество писем:" + inboxPage.counter("Simbirsoft Тестовое задание"));
        inboxPage.clickSendBtn();
        inboxPage.checkMailSent();
    }

    @AfterClass
    public static void quit() {
        driver.quit();
    }
}
