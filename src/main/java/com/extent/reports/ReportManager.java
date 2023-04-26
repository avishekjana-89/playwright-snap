package com.extent.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManager {
    private static ExtentReports extent = null;
    private static final ThreadLocal<ExtentTest> tlExtentTest = new ThreadLocal<>();

    public static void initReport(){
        if(extent != null)
            return;

        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("Report.html");
        spark.config(
                ExtentSparkReporterConfig.builder()
                        .theme(Theme.DARK)
                        .documentTitle("MyReport")
                        .reportName("ExtentReport")
                        .timeStampFormat("MMM dd, yyyy HH:mm:ss a")
                        .build()
        );
        extent.attachReporter(spark);
    }

    public static void flushReport(){
        extent.flush();
    }

    public static void createTest(String name){
        tlExtentTest.set(extent.createTest(name));
    }

    public static ExtentTest getTest(){
        return tlExtentTest.get();
    }

}
