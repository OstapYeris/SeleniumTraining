package seleniumIntro;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class SeleniumIntro {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/ostap/Documents/logos/AQA course/webDriver/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void exampleTest() {
        driver.get("https://google.com");
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Selenium Webdriver" + Keys.ENTER);
        WebElement officialLink =
                new WebDriverWait(driver, 5)
                        .until(
                                ExpectedConditions.elementToBeClickable(
                                        By.xpath("//span[text()='WebDriver :: Documentation for Selenium']")
                                )

                        );
//        WebElement officialLink = driver.findElement(By.xpath("//span[text()='Selenium Projects - Selenium WebDriver']"));
        officialLink.click();
//        WebElement webDriverTitle = driver.findElement(By.id("webdriver"));

        WebElement webDriverTitle = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("webdriver")));

        Assert.assertEquals(webDriverTitle.getText(), "WebDriver");

    }

    @Test
    public void testHotLine() {
        driver.get("https://hotline.ua/login/");
        WebElement loginField = driver.findElement(By.name("login"));
        loginField.sendKeys("WrongLogin");
        WebElement passField = driver.findElement(By.name("password"));
        passField.sendKeys("WrongPass");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
//        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-field']//div[1]"));

        WebElement errorMessage = new WebDriverWait(driver, 4)
                .until((ExpectedConditions.visibilityOfElementLocated(By.className("error-field"))));
        Assert.assertEquals(errorMessage.getText(), "Введіть email або номер телефону");
    }

    @AfterClass
    public void shutDown() {
        driver.quit();
    }
}
