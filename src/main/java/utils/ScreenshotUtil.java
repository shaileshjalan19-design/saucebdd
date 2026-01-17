package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScreenshotUtil {
    public static String takeScreenshot(WebDriver driver, String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path target = Path.of("target/screenshots", name + "_" + System.currentTimeMillis() + ".png");
            Files.createDirectories(target.getParent());
            Files.copy(src.toPath(), target);
            return target.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
