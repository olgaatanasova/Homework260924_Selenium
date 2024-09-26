import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class BingSearchTest {


    @Test
    public void searchTermInBingTest() {
        // Set the path for the ChromeDriver (or any WebDriver)
        System.setProperty("webdriver.chrome.driver", GoogleSearchTest.pathToChromeDriver);

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Navigate to Bing
            driver.get("https://www.bing.com");

            // Step 2: Find the search box and search for 'Telerik Academy Alpha'
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("Telerik Academy Alpha");
            searchBox.submit();

            // Step 3: Wait until the search results appear
            //   Duration timeout = new Duration(10, 10);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement firstResult = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.b_algo h2 a"))
            );

            // Step 4: Validate the title of the first result
            String expectedTitle = "IT Career Start in 6 Months - Telerik Academy Alpha";
            if (firstResult.getText().contains(expectedTitle)) {
                System.out.println("Bing Test Passed: First result title is correct.");
            } else {
                System.out.println("Bing Test Failed: First result title is incorrect.");
            }
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
