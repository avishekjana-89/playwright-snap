package com.site.common;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.playwright.factory.PlaywrightFactory;
import org.testng.annotations.*;

import static com.playwright.factory.PlaywrightFactory.browserMap;

public class BaseTest {
    private final ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    private final ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private final ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    private final ThreadLocal<Page> tlPage = new ThreadLocal<>();

    @BeforeSuite
    public void projectSetup() {
        PlaywrightAssertions.setDefaultAssertionTimeout(30_000);
    }

    @BeforeMethod(groups = {"smoke", "regression"})
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browserName) {
        Playwright playwright = PlaywrightFactory.getPlaywright();
        tlPlaywright.set(playwright);

        Browser browser = PlaywrightFactory.getBrowser(playwright, browserMap.get(browserName));
        tlBrowser.set(browser);

        BrowserContext browserContext = PlaywrightFactory.getBrowserContext(browser, PlaywrightFactory.contextFunction);
        tlBrowserContext.set(browserContext);

        Page page = PlaywrightFactory.getPage(browserContext);
        tlPage.set(page);
    }

    @AfterMethod(groups = {"smoke", "regression"})
    public void tearDown() {
        if (tlPage.get() != null) {
            tlPage.get().close();
            tlPage.remove();
        }
        if (tlBrowserContext.get() != null) {
            tlBrowserContext.get().close();
            tlBrowserContext.remove();
        }
        if (tlBrowser.get() != null) {
            tlBrowser.get().close();
            tlBrowser.remove();
        }
        if (tlPlaywright.get() != null) {
            tlPlaywright.get().close();
            tlPlaywright.remove();
        }
    }

    protected Playwright getPlaywright() {
        return tlPlaywright.get();
    }

    protected Browser getBrowser() {
        return tlBrowser.get();
    }

    protected BrowserContext getBContext() {
        return tlBrowserContext.get();
    }

    protected Page getPage() {
        return tlPage.get();
    }


}
