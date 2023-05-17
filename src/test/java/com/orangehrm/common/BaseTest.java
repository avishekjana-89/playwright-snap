package com.orangehrm.common;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.playwright.factory.PlaywrightFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.extent.reports.ReportManager.getTest;
import static com.extent.reports.ReportManager.endTest;

public class BaseTest {

    PlaywrightFactory playwrightFactory = new PlaywrightFactory();

    @BeforeSuite
    public void projectSetup() {
        PlaywrightAssertions.setDefaultAssertionTimeout(30_000);
    }

    @BeforeMethod(groups = {"smoke", "regression"})
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browserName) {
        playwrightFactory.initBrowser(browserName);
    }

    @AfterMethod(groups = {"smoke", "regression"})
    public void tearDown(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE){
            String methodName = result.getMethod().getMethodName();
            String imagePath = takeScreenshot(methodName);
            getTest().fail(MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
        }
        endTest();
        playwrightFactory.closeBrowser();
    }

    protected Playwright getPlaywright() {
        return playwrightFactory.tlPlaywright.get();
    }

    protected Browser getBrowser() {
        return playwrightFactory.tlBrowser.get();
    }

    protected BrowserContext getBContext() {
        return playwrightFactory.tlBrowserContext.get();
    }

    protected Page getPage() {
        return playwrightFactory.tlPage.get();
    }

    protected String takeScreenshot(String name){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
        String filePath = "screenshots/" + name + df.format(new Date()) + ".png";
        getPage().screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(filePath))
                .setFullPage(true));

        return filePath;
    }


}
