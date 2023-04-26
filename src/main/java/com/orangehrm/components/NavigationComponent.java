package com.orangehrm.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.function.Function;

public class NavigationComponent {

    Page page;
    private final Function<String, Locator> menuFn = (menu -> page.getByText(menu));

    public NavigationComponent(Page page) {
        this.page = page;
    }

    public void menuClick(String menu) {
        Locator menuItem = menuFn.apply(menu);
        menuItem.click();
    }
}
