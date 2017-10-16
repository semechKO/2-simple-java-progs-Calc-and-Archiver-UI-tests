package test;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Class DistilleryTests contains several tests for https://distillery.com/work/ page.
 */
public class DistilleryTests {

    private static WebDriver driver = null;
    private static WebDriverWait wait;
    private final String image_id = "bg-logo-farm";
    private final String number_xpath = "html/body/footer/div[1]/div[2]/div[2]/a[4]/span";
    private final String expected_number = "(310) 776-6234";

    @DataProvider
    public Object[][] elemData() {
        return new Object[][] {
                {"mobileMenuIconBlock"},
                {"svg-cizo-hover"},
                {"footer--social-dr"},
                {"main-logo"},
        };
    }
    @BeforeSuite
    public void setUpDriver(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
    }
    @BeforeMethod()
    public void setUp(){
        driver.get("https://distillery.com/work/");
    }
    //Method testElementClick checks if elements with id from elemData are clickable.
    @Test(dataProvider = "elemData")
    public void testElementClick(String id) {
        //Act object is initialized to set offset for click which is necessary for some elements
        Actions act = new Actions(driver);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
            act.moveToElement(driver.findElement(By.id(id))).moveByOffset(15, 0).click().perform();
            Assert.assertTrue(true);
        } catch (TimeoutException e){
            Assert.assertTrue(false, "Element with id " + id + " is not clickable");
        }
    }
    //Method testImageLoading checks if image with image_id is loaded.
    @Test
    public void testImageLoading(){
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(image_id)));
            Assert.assertTrue(true);
        } catch (TimeoutException e){
            Assert.assertTrue(false,"Element with id" + image_id + " is not loaded." );
        }
    }
    //Method testNumberCheck checks if number from footer is the same as expected number.
    @Test
    public void testNumberCheck(){
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(number_xpath)));
            Assert.assertEquals(driver.findElement(By.xpath(number_xpath)).getText(),expected_number, "Number" +
                    "in footer and expected number aren't the same.");
        } catch (TimeoutException e){
            Assert.assertTrue(false,"Element wasn't found.");
        }
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}
