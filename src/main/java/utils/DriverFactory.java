package utils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static void initDriver() {

        // Dynamically build ChromeDriver path (no hardcoding)
        String driverPath = System.getProperty("user.dir") + "\\BrowserJars\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        // Chrome preferences to disable password manager & autofill
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.profile_enabled", false);
        prefs.put("autofill.credit_card_enabled", false);

        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized");

        // Initialize ChromeDriver with options
        WebDriver driver = new ChromeDriver(options);

        // Store in ThreadLocal (for parallel execution)
        tlDriver.set(driver);

        // Browser housekeeping
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}
