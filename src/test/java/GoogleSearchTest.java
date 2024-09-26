import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleSearchTest {

    //TODO: please change the following constants in order for the test to work on your local pc;

    public static final String pathToChromeDriver = "C:\\Users\\user\\Desktop\\QA preparation\\Module 3 QA Web Testing\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe";

    public static final String nameOfAcceptCookiesButtonBG = "Приемане на всички";

    public static final String nameOfAcceptCookiesButtonEN = "Accept all";

    @Test
    public void searchTermInChromeTest() {
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Navigate to Google
            driver.get("https://www.google.com");

            // Step 2: Accept or reject cookies (if the popup appears)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            try {
                WebElement acceptCookiesButton = wait.until(
                        ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='I agree' or text()='" + nameOfAcceptCookiesButtonBG + "']"))
                );
                acceptCookiesButton.click();
            } catch (Exception e) {
                System.out.println("No cookies popup, proceeding with search.");
            }

            // Step 3: Find the search box and search for 'Telerik Academy Alpha'
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("Telerik Academy Alpha");
            searchBox.submit();

            // Step 4: Wait until the search results appear
            WebElement firstResult = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3"))
            );

            // Step 5: Validate the title of the first result
            String expectedTitle = "IT Career Start in 6 Months - Telerik Academy Alpha";
            if (firstResult.getText().contains(expectedTitle)) {
                System.out.println("Google Test Passed: First result title is correct.");
            } else {
                System.out.println("Google Test Failed: First result title is incorrect.");
            }
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}

