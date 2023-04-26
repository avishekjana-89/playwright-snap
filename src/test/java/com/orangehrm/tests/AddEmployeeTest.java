package com.orangehrm.tests;

import com.extent.reports.ReportManager;
import com.orangehrm.models.Employee;
import com.orangehrm.pages.AddEmployeePage;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.EmployeeListPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.rules.AddEmployeeRule;
import com.site.common.BaseTest;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;

import static com.extent.reports.ReportManager.getTest;
import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;

public class AddEmployeeTest extends BaseTest {

    @Test(dataProvider = "getData", groups = {"smoke", "regression"})
    public void addEmployeeTest(String flow, Employee employee) {
        getTest().info("Flow :: " + flow);
        getTest().info("Employee data :: " + employee);
        LoginPage loginPage = new LoginPage(getPage());
        DashboardPage dashboardPage = new DashboardPage(getPage());
        EmployeeListPage employeeListPage = new EmployeeListPage(getPage());
        AddEmployeePage addEmployeePage = new AddEmployeePage(getPage());

        loginPage.isDisplayed()
                .loginWithCreds();

        dashboardPage.isDisplayed()
                .getNavigationComponent()
                .menuClick("PIM");

        employeeListPage.isDisplayed()
                .clickAddEmployee();

        addEmployeePage.isDisplayed();
        AddEmployeeRule.mapper.get(flow).accept(employee, addEmployeePage);
        addEmployeePage.createEmployee();
        addEmployeePage.verifySuccessMessage();
    }

    @DataProvider(parallel = true)
    public Object[][] getData() {
        EasyRandomParameters parameters = new EasyRandomParameters()
                .seed(123L)
                .charset(StandardCharsets.UTF_8)
                .stringLengthRange(8, 20)
                .randomize(named("employeeId|username").and(inClass(Employee.class)),
                        () -> String.valueOf(System.nanoTime() / 10000));

        EasyRandom random = new EasyRandom(parameters);
        Employee empDetailsWithoutImg = random.nextObject(Employee.class);

        Employee empDetailsWithImg = random.nextObject(Employee.class);
        empDetailsWithImg.setImagePath("src/test/resources/image.jpg");

        Employee empDetailsWithLogin = random.nextObject(Employee.class);
        empDetailsWithLogin.setPassword("Admin@123");

        return new Object[][]{
                {"CreateEmployeeWithoutImage", empDetailsWithoutImg},
                {"CreateEmployeeWithImage", empDetailsWithImg},
                {"CreateEmployeeWithLoginDetails", empDetailsWithLogin}
        };
    }
}
