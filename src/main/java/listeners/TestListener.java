package listeners;


import io.qameta.allure.Attachment;
import utils.Config;
import utils.DriverFactory;
import utils.ScreenshotUtil;
import utils.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.testng.*;

public class TestListener implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        // Suite level initialization (BeforeSuite equivalent)
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(Config.BASE_URL);
    }

    @Override
    public void onFinish(ISuite suite) {
        DriverFactory.quitDriver();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();
        String path = ScreenshotUtil.takeScreenshot(driver, result.getName());
        try {
            // Attach to Allure
            if (path != null) {
                byte[] bytes = java.nio.file.Files.readAllBytes(java.nio.file.Path.of(path));
                saveScreenshot(bytes);
            }
        } catch (Exception ignored) {}
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screen) { return screen; }

    // Other listener methods can be left empty or logged
}

