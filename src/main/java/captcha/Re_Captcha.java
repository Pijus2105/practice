package captcha;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

public class Re_Captcha {

	public static void main(String[] args) throws IOException, Exception {


		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(option);
		
        try {
        	driver.manage().window().maximize();
    		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
        
        
        driver.get("https://www.irctc.co.in/nget/train-search"); 
        
        driver.findElement(By.xpath("//a[@class='search_btn loginText ng-star-inserted']")).click();
        
        WebElement imgElement = driver.findElement(By.xpath("//img[@class='captcha-img']"));
        
        File src= imgElement.getScreenshotAs(OutputType.FILE);
        
        String path = "C:\\Users\\Elphill\\eclipse-workspace\\Captcha\\Captcha\\captcha.png";
       
        FileHandler.copy(src, new File(path));
        
        Thread.sleep(2000);
        
        ITesseract img = new Tesseract();
        
        String str=img.doOCR(new File(path));
        
        //String captcha = str.split("below")[1].replaceAll("[^a-zA-Z0-9]", " ");
        
       // driver.findElement(By.id("idvalue")).sendKeys(captcha);
        
        driver.findElement(By.id("captcha")).sendKeys(str);
        System.out.println("OCR is done");
        
        System.out.println(str);
        } catch (Exception e) {
        	System.out.println("Exception" + e.getMessage());
        }
        
        
	}

}
