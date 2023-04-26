package com.playwright.factory;

import com.microsoft.playwright.*;

import java.util.Map;
import java.util.function.Function;

public final class PlaywrightFactory {

    private static final String headlessProp = System.getProperty("headless");
    private static final boolean headless = "true".equalsIgnoreCase(headlessProp);

    private static final Function<Playwright, Browser> chromium = (playwright -> playwright.chromium().launch(
            new BrowserType.LaunchOptions().setHeadless(headless)
    ));

    private static final Function<Playwright, Browser> firefox = (playwright -> playwright.firefox().launch(
            new BrowserType.LaunchOptions().setHeadless(headless)
    ));

    private static final Function<Playwright, Browser> webkit = (playwright -> playwright.webkit().launch(
            new BrowserType.LaunchOptions().setHeadless(headless)
    ));

    private static final Function<Playwright, Browser> chrome = (playwright -> playwright.chromium().launch(
            new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headless)
    ));

    private static final Function<Playwright, Browser> msEdge = (playwright -> playwright.chromium().launch(
            new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(headless)
    ));
    public static Map<String, Function<Playwright, Browser>> browserMap = Map.of(
            "chromium", chromium,
            "firefox", firefox,
            "webkit", webkit,
            "chrome", chrome,
            "msedge", msEdge
    );
    public static Function<Browser, BrowserContext> contextFunction = (Browser::newContext);

    public static Playwright getPlaywright() {
        return Playwright.create();
    }

    public static Browser getBrowser(Playwright playwright, Function<Playwright, Browser> function) {
        return function.apply(playwright);
    }

    public static BrowserContext getBrowserContext(Browser browser, Function<Browser, BrowserContext> function) {
        return function.apply(browser);
    }

    public static Page getPage(BrowserContext browserContext) {
        return browserContext.newPage();
    }

}
