package org.protasov.ii.testbase;

import org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public abstract class BaseTest {

    protected static WebDriver driver;

    @BeforeSuite
    @Step("=====Browser Session Started=====")
    protected void getFireFoxWebDriver(){
        //Reporter.log("=====Browser Session Started=====", true);
        TestSettings settings = new TestSettings();
        System.setProperty(settings.getDriverName(), settings.getDriverPath());
        driver = new FirefoxDriver();
        Reporter.log("=====WebDriver created=====", true);
    }
    @Step("=====WebDriver created=====")

    @AfterSuite(alwaysRun = true)
    protected void closeDriver(){
        driver.close();
        Reporter.log("=====WebDriver closed=====", true);
    }
    @Step("=====WebDriver closed=====")
    @AfterMethod
    //созранять в лог testng
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            Reporter.log("=====Making error ScreenShot=====", true);
            System.out.println(testResult.getStatus());
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("errorScreenshots\\" + testResult.getName() + "-"
                    + Arrays.toString(testResult.getParameters()) +  ".jpg"));
        }
    }

    protected void loadPage(String url){
        driver.navigate().to(url);
    }
}
