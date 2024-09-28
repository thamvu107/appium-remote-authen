package listeners;


import driver.ThreadSafeDriver;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class TestListener implements ITestListener {
  @Override
  public void onTestStart(ITestResult result) {
    log.info("Test Started: " + result.getMethod().getMethodName());
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    log.info("Test Passed: " + result.getMethod().getMethodName());
  }

  @Override
  public void onTestFailure(ITestResult result) {
    log.atError().log("Test Failed: " + result.getMethod().getMethodName());
    captureLocalScreenShot(result);
  }

  public void captureLocalScreenShot(ITestResult result) {
    boolean testIsFailed = result.getStatus() == ITestResult.FAILURE;
    if (testIsFailed) {
      log.atError().log("Test failed: " + result.getMethod().getMethodName());
      final AppiumDriver driver = ThreadSafeDriver.getDriver();

      String method = result.getMethod().getRealClass().getSimpleName() + "-" + result.getMethod().getMethodName();
      File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
      String destDir = "target" + File.separator + "screenshots" + File.separator + "failures";

      // To create folder to store screenshots
      new File(destDir).mkdirs();
      // Set file name with combination of test class name + date time.
      String destFile = method + " - " + dateFormat.format(new Date()) + ".png";

      String screenshotLocation = Paths.get(destDir, destFile).toString();
      log.atInfo().log("screenshotLocation " + screenshotLocation);
      System.out.println("screenshotLocation " + screenshotLocation);

      try {
        FileUtils.copyFile(scrFile, new File(screenshotLocation));
        Path screenshotContentPath = Paths.get(screenshotLocation);
        InputStream inputStream = Files.newInputStream(screenshotContentPath);
        Allure.addAttachment(result.getName(), inputStream);
      } catch (IOException e) {
        log.atError().setMessage(e.getMessage()).log();
        e.printStackTrace();
      }
    }
  }

}
