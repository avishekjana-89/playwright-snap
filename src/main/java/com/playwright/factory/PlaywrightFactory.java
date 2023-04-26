package com.playwright.factory;

import com.microsoft.playwright.*;

import java.util.Map;
import java.util.function.Function;

public final class PlaywrightFactory {

    private final String headlessProp = System.getProperty("headless");
    private final boolean headless = "true".equalsIgnoreCase(headlessProp);
    public final ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    public final ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    public final ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    public final ThreadLocal<Page> tlPage = new ThreadLocal<>();

    private final Function<Playwright, Browser> chromium = (playwright -> playwright.chromium().launch(
            new BrowserType.LaunchOptions().setHeadless(headless)
    ));

    private final Function<Playwright, Browser> firefox = (playwright -> playwright.firefox().launch(
            new BrowserType.LaunchOptions().setHeadless(headless)
    ));

    private final Function<Playwright, Browser> webkit = (playwright -> playwright.webkit().launch(
            new BrowserType.LaunchOptions().setHeadless(headless)
    ));

    private final Function<Playwright, Browser> chrome = (playwright -> playwright.chromium().launch(
            new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headless)
    ));

    private final Function<Playwright, Browser> msEdge = (playwright -> playwright.chromium().launch(
            new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(headless)
    ));

    Map<String, Function<Playwright, Browser>> browserMap = Map.of(
            "chromium", chromium,
            "firefox", firefox,
            "webkit", webkit,
            "chrome", chrome,
            "msedge", msEdge
    );

    Function<Browser, BrowserContext> contextFunction = (Browser::newContext);

    Browser getBrowser(Playwright playwright, Function<Playwright, Browser> function) {
        return function.apply(playwright);
    }

    BrowserContext getBrowserContext(Browser browser, Function<Browser, BrowserContext> function) {
        return function.apply(browser);
    }

    public void initBrowser(String browserName){
        Playwright playwright = Playwright.create();
        tlPlaywright.set(playwright);

        Browser browser = getBrowser(playwright, browserMap.get(browserName));
        tlBrowser.set(browser);

        BrowserContext browserContext = getBrowserContext(browser, contextFunction);
        tlBrowserContext.set(browserContext);

        Page page = browserContext.newPage();
        tlPage.set(page);
    }

    public void closeBrowser(){
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

}
