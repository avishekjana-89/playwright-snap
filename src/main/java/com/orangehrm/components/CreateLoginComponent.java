package com.orangehrm.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.orangehrm.models.Employee;

public class CreateLoginComponent {

    private final Locator username;
    private final Locator password;
    private final Locator confirmPassword;
    Page page;

    public CreateLoginComponent(final Page page) {
        this.page = page;
        this.username = this.page.locator("//label[text()='Username']/../..//input");
        this.password = this.page.locator("//label[text()='Password']/../..//input");
        this.confirmPassword = this.page.locator("//label[text()='Confirm Password']/../..//input");
    }

    public void createLoginDetails(final Employee employee) {
        this.username.fill(employee.getUsername());
        this.password.fill(employee.getPassword());
        this.confirmPassword.fill(employee.getPassword());
    }

}
