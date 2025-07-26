package utilities;

import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String scenarioName, String stepName, boolean isFailed) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);

        String timestamp = new SimpleDateFormat("HHmmss").format(new Date());
        String safeStepName = stepName.replaceAll("[^a-zA-Z0-9]", "_");
        String safeScenarioName = scenarioName.replaceAll("[^a-zA-Z0-9]", "_");

        String fileName = safeScenarioName + "_" + safeStepName + "_" + timestamp + ".png";

        String folder = isFailed ? "fail" : "pass";
        String destDir = "Automation Reports/screenshots/" + folder;

        File dir = new File(destDir);
        if (!dir.exists()) dir.mkdirs();

        String relativePath = "screenshots/" + folder + "/" + fileName;
        String absolutePath = "Automation Reports/" + relativePath;

        try {
            FileUtils.copyFile(src, new File(absolutePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return relativePath; // Return relative path for hyperlink to work
    }
}
