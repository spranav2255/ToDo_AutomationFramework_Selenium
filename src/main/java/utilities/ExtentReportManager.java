package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            cleanOldReports();

            File reportsDir = new File("Automation Reports");
            if (!reportsDir.exists()) {
                reportsDir.mkdirs();
            }

            String timestamp = new SimpleDateFormat("hh-mm_a_dd-MM-yyyy").format(new Date());
            String reportPath = "Automation Reports/Automation-Report-" + timestamp + ".html";

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setDocumentTitle("Automation Report");
            reporter.config().setReportName("ToDo React App Automation");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }

    private static void cleanOldReports() {
        try {
            Path reportDir = Paths.get("Automation Reports");
            if (Files.exists(reportDir)) {
                Files.walk(reportDir)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            }
        } catch (IOException e) {
            System.err.println("Error while deleting old reports: " + e.getMessage());
        }
    }
}
