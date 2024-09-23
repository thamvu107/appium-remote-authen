package learning.findBy;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
  @FindBy(xpath = "//android.widget.TextView[@text='Login']")
  @AndroidFindBy
  @iOSXCUITFindBy
  public static WebElement navLoginBtn;
}
