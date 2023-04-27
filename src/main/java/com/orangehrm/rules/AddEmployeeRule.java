package com.orangehrm.rules;

import com.orangehrm.models.Employee;
import com.orangehrm.pages.AddEmployeePage;

import java.util.Map;
import java.util.function.BiConsumer;

public class AddEmployeeRule {

    private static final BiConsumer<Employee, AddEmployeePage> createEmpWithoutImage = (emp, empPage) -> empPage.enterEmployeeNameDetails(emp);
    private static final BiConsumer<Employee, AddEmployeePage> imageUpload = (emp, empPage) -> empPage.uploadPhoto(emp);
    private static final BiConsumer<Employee, AddEmployeePage> createEmpWithImage = createEmpWithoutImage.andThen(imageUpload);
    private static final BiConsumer<Employee, AddEmployeePage> loginDetails = (emp, empPage) -> empPage.enableLogin().getLoginComponent().createLoginDetails(emp);
    private static final BiConsumer<Employee, AddEmployeePage> createEmpWithLogin = createEmpWithoutImage.andThen(loginDetails);

    public static Map<String, BiConsumer<Employee, AddEmployeePage>> mapper = Map.of(
            "CreateEmployeeWithoutImage", createEmpWithoutImage,
            "CreateEmployeeWithImage", createEmpWithImage,
            "CreateEmployeeWithLoginDetails", createEmpWithLogin
    );
}
