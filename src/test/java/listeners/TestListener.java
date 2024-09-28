package listeners;


import driver.ThreadSafeDriver;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
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

public class TestListener implements ITestListener {


  @Override
  public void onTestFailure(ITestResult result) {
    captureLocalScreenShot(result);
  }

  public void captureLocalScreenShot(ITestResult result) {
    boolean testIsFailed = result.getStatus() == ITestResult.FAILURE;
    if (testIsFailed) {

//      String destDir =  System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + "failures";
      String destDir =  "target" + File.separator + "screenshots" + File.separator + "failures";
      // To create folder to store screenshots
      new File(destDir).mkdirs();

      String method = result.getMethod().getRealClass().getSimpleName() + "-" + result.getMethod().getMethodName();
      DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
      // Set file name with combination of test class name + date time.
      String destFile = method + " - " + dateFormat.format(new Date()) + ".png";

      String screenshotLocation = Paths.get(destDir, destFile).toString();

      File scrFile = ((TakesScreenshot) ThreadSafeDriver.getDriver()).getScreenshotAs(OutputType.FILE);

      try {
        FileUtils.copyFile(scrFile, new File(screenshotLocation));
        Path screenshotContentPath = Paths.get(screenshotLocation);
        InputStream inputStream = Files.newInputStream(screenshotContentPath);
        Allure.addAttachment(result.getName(), inputStream);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
