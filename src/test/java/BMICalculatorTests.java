import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class BMICalculatorTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){

        //set system property
        System.setProperty("webdriver.chrome.driver","src/test/Resources/chromedriver");

        //open browser
        driver = new ChromeDriver();

        //open https://healthunify.com/bmicalculator/
        driver.get("https://healthunify.com/bmicalculator/");

    }

    @Test
    public void checkCategoryStarvation(){

        WebElement inputWeight = driver.findElement(By.name("wg"));
        WebElement inputHeight = driver.findElement(By.name("ht"));
        WebElement buttonCalculate = driver.findElement(By.name("cc"));
        WebElement inputCategory = driver.findElement(By.name("desc"));

        // Input Weight
        inputWeight.sendKeys("20");
        //input height
        inputHeight.sendKeys("170");
        // click button Calculate
        buttonCalculate.click();
        // Check category is Normal
        String factCategory = inputCategory.getAttribute("value");
        assertEquals(factCategory, "Your category is Starvation", "Category is not Starvation");

    }

    @Test
    public void checkCategoryUnderweight(){

        WebElement inputWeight = driver.findElement(By.name("wg"));
        WebElement inputHeight = driver.findElement(By.name("ht"));
        WebElement buttonCalculate = driver.findElement(By.name("cc"));
        WebElement inputCategory = driver.findElement(By.name("desc"));

        // Input Weight
        inputWeight.sendKeys("50");
        //input height
        inputHeight.sendKeys("170");
        // click button Calculate
        buttonCalculate.click();
        // Check category is Normal
        String factCategory = inputCategory.getAttribute("value");
        assertEquals(factCategory, "Your category is Underweight", "Category is not Underweight");

    }

    @Test
    public void checkCategoryNormal(){

        WebElement inputWeight = driver.findElement(By.name("wg"));
        WebElement inputHeight = driver.findElement(By.name("ht"));
        WebElement buttonCalculate = driver.findElement(By.name("cc"));
        WebElement inputCategory = driver.findElement(By.name("desc"));

        // Input Weight
        inputWeight.sendKeys("54");
        //input height
        inputHeight.sendKeys("170");
        // click button Calculate
        buttonCalculate.click();
        // Check category is Normal
        String factCategory = inputCategory.getAttribute("value");
        assertEquals(factCategory, "Your category is Normal", "Category is not Normal");

    }

    @Test
    public void checkConverseInchToSM(){

        WebElement selectInch1 = driver.findElement(By.name("opt2"));
        WebElement selectInch2 = driver.findElement(By.name("opt3"));
        WebElement inputHeight = driver.findElement(By.name("ht"));

        Select selectInch1_1 = new Select(selectInch1);
        Select selectInch1_2 = new Select(selectInch2);

        // Select Inch1
        selectInch1.click();
        List<WebElement> options1 = selectInch1_1.getOptions();
        options1.get(1).click();

        // Select Inch1
        selectInch2.click();
        List<WebElement> options2 = selectInch1_2.getOptions();
        options2.get(0).click();

        // Check value in input Height
        String factValue = inputHeight.getAttribute("value");
        assertEquals(factValue, "61", "Is not 61");

    }

    @Test
    public void checkCalculateUnits(){

        WebElement inputWeight = driver.findElement(By.name("wg"));
        WebElement inputHeight = driver.findElement(By.name("ht"));
        WebElement buttonCalculate = driver.findElement(By.name("cc"));
        WebElement inputSIUnits = driver.findElement(By.name("si"));
        WebElement inputUSUnits = driver.findElement(By.name("us"));
        WebElement inputUKUnits = driver.findElement(By.name("uk"));

        //Input Weight
        inputWeight.sendKeys("50");

        //Input Height
        inputHeight.sendKeys("170");

        //Click Button
        buttonCalculate.click();

        // Check Units
        String factValueSI = inputSIUnits.getAttribute("value");
        String factValueUS = inputUSUnits.getAttribute("value");
        String factValueUK = inputUKUnits.getAttribute("value");
        assertEquals(factValueSI, "17.3", "Fact result is different from expect");
        assertEquals(factValueUS, "17.59", "Fact result is different from expect");
        assertEquals(factValueUK, "109.86", "Fact result is different from expect");

    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
