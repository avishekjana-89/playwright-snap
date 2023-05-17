package com.orangehrm.pages;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.orangehrm.components.CreateLoginComponent;
import com.orangehrm.models.Employee;
import org.testng.Assert;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AddEmployeePage implements BasePage {

    private final Locator firstName;
    private final Locator middleName;
    private final Locator lastName;
    private final Locator employeeId;
    private final Locator imageIcon;
    private final Locator saveBtn;
    private final Locator toastTitle;
    private final Locator toastMessage;
    private final Locator loginToggle;
    private final CreateLoginComponent component;
    Page page;

    public AddEmployeePage(final Page page) {
        this.page = page;
        this.firstName = this.page.getByPlaceholder("First Name");
        this.middleName = this.page.getByPlaceholder("Middle Name");
        this.lastName = this.page.getByPlaceholder("Last Name");
        this.employeeId = this.page.locator("//label[text()='Employee Id']/../..//input");
        this.imageIcon = this.page.locator("button.employee-image-action");
        this.saveBtn = this.page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save"));
        this.toastTitle = this.page.locator("p.oxd-text--toast-title");
        this.toastMessage = this.page.locator("p.oxd-text--toast-message");
        this.loginToggle = this.page.locator("//p[text()='Create Login Details']/..//span");
        this.component = new CreateLoginComponent(page);
    }

    public CreateLoginComponent getLoginComponent() {
        return this.component;
    }

    public void enterEmployeeNameDetails(final Employee emp) {
        this.firstName.fill(emp.getFirstName());
        this.middleName.fill(emp.getMiddleName());
        this.lastName.fill(emp.getLastName());
        this.employeeId.clear();
        this.employeeId.fill(emp.getEmployeeId());
    }

    public void uploadPhoto(final Employee emp) {
        FileChooser fileChooser = this.page.waitForFileChooser(this.imageIcon::click);
        fileChooser.setFiles(Paths.get(emp.getImagePath()));
    }

    public AddEmployeePage enableLogin() {
        this.loginToggle.click();
        return this;
    }

    public void createEmployee() {
        this.saveBtn.click();
    }

    public void verifySuccessMessage() {
        Assert.assertEquals(this.toastTitle.textContent(), "Success");
        Assert.assertEquals(this.toastMessage.textContent(), "Successfully Saved");
    }

    @Override
    public AddEmployeePage isDisplayed() {
        assertThat(this.firstName).isVisible();
        return this;
    }
}
