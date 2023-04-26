package com.orangehrm.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.orangehrm.components.NavigationComponent;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DashboardPage extends BasePage {

    private final Locator header;
    private final NavigationComponent component;
    Page page;

    public DashboardPage(Page page) {
        this.page = page;
        this.header = this.page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Dashboard"));
        this.component = new NavigationComponent(page);
    }

    public NavigationComponent getNavigationComponent() {
        return this.component;
    }

    @Override
    public DashboardPage isDisplayed() {
        assertThat(this.header).isVisible();
        return this;
    }
}
