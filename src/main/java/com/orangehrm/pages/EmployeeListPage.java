package com.orangehrm.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class EmployeeListPage implements BasePage {

    private final Locator employeeList;
    private final Locator addEmployee;
    Page page;

    public EmployeeListPage(final Page page) {
        this.page = page;
        this.employeeList = this.page.getByText("Employee List");
        this.addEmployee = this.page.getByText("Add Employee");
    }

    public void clickAddEmployee() {
        this.addEmployee.click();
    }

    @Override
    public EmployeeListPage isDisplayed() {
        assertThat(this.employeeList).isVisible();
        return this;
    }
}
