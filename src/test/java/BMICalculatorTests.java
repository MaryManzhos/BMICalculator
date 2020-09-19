import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BMICalculatorTests {



    @BeforeMethod
    public void setUp(){

    }

    @Test
    public void checkCategotyNormal(){
        //set system property
        System.setProperty("webdriver.chrome.driver","src/test/Resources/chromedriver");

        //open browser
        WebDriver driver = new ChromeDriver();

        //open https://healthunify.com/bmicalculator/
        driver.get("https://healthunify.com/bmicalculator/");

        // Input Weight
        WebElement inputWeight = driver.findElement(By.name("wg"));
        inputWeight.sendKeys("54");

        //input height
        WebElement inputHeight = driver.findElement(By.name("ht"));
        inputHeight.sendKeys("170");

        // click button Calculate
        WebElement buttonCalculate = driver.findElement(By.name("cc"));
        buttonCalculate.click();

        // Check category is Normal
        WebElement inputCategory = driver.findElement(By.name("desc"));
        String factCategory = inputCategory.getAttribute("value");

        assertEquals(factCategory, "Your category is Normal", "Category is not Normal");

        driver.quit();

    }
}
