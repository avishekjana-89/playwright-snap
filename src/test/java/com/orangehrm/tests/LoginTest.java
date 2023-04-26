package com.orangehrm.tests;

import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.site.common.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(groups = "regression")
    public void loginTest() {
        LoginPage loginPage = new LoginPage(getPage());
        DashboardPage dashboardPage = new DashboardPage(getPage());

        loginPage.isDisplayed()
                .loginWithCreds();

        dashboardPage.isDisplayed();
    }
}
