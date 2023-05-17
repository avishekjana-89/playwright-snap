package com.orangehrm.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage implements BasePage {
    private final Locator username;
    private final Locator password;
    private final Locator loginBtn;
    Page page;


    public LoginPage(final Page page) {
        this.page = page;
        username = page.getByPlaceholder("Username");
        password = page.getByPlaceholder("Password");
        loginBtn = page.getByRole(AriaRole.BUTTON);
        this.page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Override
    public LoginPage isDisplayed() {
        assertThat(username).isVisible();
        return this;
    }

    public void loginWithCreds() {
        this.username.fill("Admin");
        this.password.fill("admin123");
        this.loginBtn.click();
    }
}
