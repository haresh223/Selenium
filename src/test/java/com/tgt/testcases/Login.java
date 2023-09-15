package com.tgt.testcases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.Date;

public class Login {

    @Test(priority = 2)
     public void verifyLogin() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

        driver.get("https://tutorialsninja.com/demo");
        driver.findElement(By.xpath("//span[text()='My Account']")).click();          //text() function used
        driver.findElement(By.linkText("Login")).click();                                            //link text\
        driver.findElement(By.id("input-email")).sendKeys("hareshmca@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("Haresh@123");
        driver.findElement(By.xpath("//input[@value='Login']")).click();              // value attribute

        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(), "Edit account information not displayed");
        driver.quit();
    }

    @Test(priority = 1)
    public void verifyLoginWithInvalidData(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

        driver.get("https://tutorialsninja.com/demo");
        driver.findElement(By.xpath("//span[text()='My Account']")).click();          //text() function used
        driver.findElement(By.linkText("Login")).click();                                           //link text\
        driver.findElement(By.id("input-email")).sendKeys("hareshmca"+generateTimeStamp()+"@gmail.com");  // Dynamically generating timestamp
        driver.findElement(By.id("input-password")).sendKeys("abc");
        driver.findElement(By.xpath("//input[@value='Login']")).click();

        String actualWarningmMessage =  driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
        String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualWarningmMessage.contains(expectedWarningMessage), "Expected message is not displayed");
        driver.quit();
    }
    @Test(priority = 3)
   public void verifyLoginwithoutEmailAndPwd()  {
       WebDriver driver = new ChromeDriver();
       driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

       driver.get("https://tutorialsninja.com/demo");
       driver.findElement(By.xpath("//span[text()='My Account']")).click();          //text() function used
       driver.findElement(By.linkText("Login")).click();                                            //link text\
       driver.findElement(By.id("input-email")).sendKeys("");
       driver.findElement(By.id("input-password")).sendKeys("");
       driver.findElement(By.xpath("//input[@value='Login']")).click();              // value attribute

       //Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(), "Edit account information not displayed");
       driver.quit();

   }
    public String generateTimeStamp(){
        Date date = new Date();
        return date.toString().replace("" ," ").replace(":", " ");
    }

}
