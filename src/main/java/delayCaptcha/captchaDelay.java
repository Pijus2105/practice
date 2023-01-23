package delayCaptcha;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class captchaDelay {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        

        driver.get("https://old.reddit.com/login");
        
        WebElement username=driver.findElement(By.xpath("//input[@name='user']"));
        username.sendKeys("some_username_20");

        WebElement password=driver.findElement(By.xpath("//input[@name='passwd']"));
        password.sendKeys("SuperStrongP@ssw0rd");

        WebElement verifyPassword=driver.findElement(By.xpath("//input[@name='passwd2']"));
        verifyPassword.sendKeys("SuperStrongP@ssw0rd");

        WebElement email=driver.findElement(By.xpath("//input[@name='email']"));
        email.sendKeys("xyz@gmail.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));
       
        wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='recaptcha-checkbox-border']"))).click();

        System.out.println("Clicked the checkbox");

		
	}

}
