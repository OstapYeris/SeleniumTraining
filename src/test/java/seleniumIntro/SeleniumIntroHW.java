package seleniumIntro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SeleniumIntroHW {
    WebDriver driver;

    @BeforeClass
    public void setUpHW() {
        System.setProperty("webdriver.chrome.driver", "/home/ostap/Documents/logos/AQA course/webDriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void loginWithInvalidCredentials() {
        driver.get("http://demo.guru99.com/test/newtours/");
        WebElement loginField = driver.findElement(By.name("userName"));
        loginField.sendKeys("WrongLogin");
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("WrongPassword");
        WebElement officialLink =
                new WebDriverWait(driver, 5)
                        .until(
                                ExpectedConditions.elementToBeClickable(
                                        By.xpath("//input[@type='submit']")
                                )

                        );
        officialLink.click();
        WebElement errorMessage = new WebDriverWait(driver, 4)
                .until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()[normalize-space()='Enter your userName and password correct']]"))));
        Assert.assertEquals(errorMessage.getText(), "Enter your userName and password correct");

    }

    @Test
    public void registrationProcedure() {
        driver.get("http://demo.guru99.com/test/newtours/");
        driver.findElement(By.xpath("//a[@href='register.php']")).click();
        WebElement firstNameFild = driver.findElement(By.name("firstName"));
        firstNameFild.sendKeys("Ostap");
        WebElement lastNameFild = driver.findElement(By.name("lastName"));
        lastNameFild.sendKeys("Yeris");
        WebElement phoneFild = driver.findElement(By.name("phone"));
        phoneFild.sendKeys("3333333333");
        WebElement emailFild = driver.findElement(By.xpath("//input[@id='userName']"));
        emailFild.sendKeys("ostap.yeris@gmail.com");
        WebElement addressFild = driver.findElement(By.name("address1"));
        addressFild.sendKeys("Hutorivka 30");
        WebElement cityFild = driver.findElement(By.name("city"));
        cityFild.sendKeys("Lviv");
        WebElement stateFild = driver.findElement(By.name("state"));
        stateFild.sendKeys("Lviv");
        WebElement postalCodeFild = driver.findElement(By.name("postalCode"));
        postalCodeFild.sendKeys("37030");
        WebElement countryFild = driver.findElement(By.name("country"));
        countryFild.isDisplayed();
        WebElement userNameFild = driver.findElement(By.name("email"));
        userNameFild.sendKeys("ostap");
        WebElement passwordFild = driver.findElement(By.name("password"));
        passwordFild.sendKeys("12345678");
        WebElement confirmPasswordFild = driver.findElement(By.name("confirmPassword"));
        confirmPasswordFild.sendKeys("12345678");
        WebElement officialLink =
                new WebDriverWait(driver, 5)
                        .until(
                                ExpectedConditions.elementToBeClickable(
                                        By.name("submit")
                                )
                        );
        officialLink.click();
        WebElement registrationMessage = new WebDriverWait(driver, 4)
                .until((ExpectedConditions.visibilityOfElementLocated(By.cssSelector("html>body>div:nth-of-type(2)>table>tbody>tr>td:nth-of-type(2)>table>tbody>tr:nth-of-type(4)>td>table>tbody>tr>td:nth-of-type(2)>table>tbody>tr:nth-of-type(3)>td>p:nth-of-type(2)>font"))));
        Assert.assertEquals(registrationMessage.getText(), "Thank you for registering. You may now sign-in using the user name and password you've just entered.");
        driver.findElement(By.xpath("(//a[@href='index.php'])[3]")).click();
    }

    @Test
    public void loginWithValidCredentials() {
        driver.get("http://demo.guru99.com/test/newtours/");
        WebElement loginField = driver.findElement(By.name("userName"));
        loginField.sendKeys("ostap");
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("12345678");
        WebElement officialLink =
                new WebDriverWait(driver, 5)
                        .until(
                                ExpectedConditions.elementToBeClickable(
                                        By.xpath("//input[@type='submit']")
                                )

                        );
        officialLink.click();
        WebElement loginMessage = new WebDriverWait(driver, 4)
                .until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Login Successfully']"))));
        Assert.assertEquals(loginMessage.getText(), "Login Successfully");

    }

    @AfterClass
    public void shutDown() {
        driver.quit();
    }
}
