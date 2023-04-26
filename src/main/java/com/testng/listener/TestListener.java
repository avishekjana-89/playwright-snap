package com.testng.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.extent.reports.ReportManager.*;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        initReport();
    }

    @Override
    public void onFinish(ITestContext context) {
        flushReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("OnStartTest");
        String testName = result.getMethod().getMethodName();
        createTest(testName);
        getTest().assignCategory(result.getMethod().getGroups());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        getTest().pass("Test is passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        getTest().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        getTest().skip("Test is skipped.");
    }
}
