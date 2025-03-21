package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener{
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	
	@Override
	public void onStart(ITestContext context) 
	{	
		extentReport =ExtentReporter.generateExtentReport();
		System.out.println("Execution of the Project Tests Started");
	}

	@Override
	public void onTestStart(ITestResult result) 
	{	
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName() + " ---> Started Executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		extentTest.log(Status.PASS, result.getName() + " ---> Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{	
		System.out.println("Screenshot Taken");
		
		WebDriver driver = null;
		try 
		{
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} 
		catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) 
		{
			e.printStackTrace();
		}
		
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, result.getName());
		
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName() + " ---> Test Failed");	
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName() + " ---> Test Skipped");
	}

	@Override
	public void onFinish(ITestContext context) 
	{
		System.out.println("Finished Executing Project Tests");
		extentReport.flush();
		
		String pathExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathExtentReport);
		try 
		{
			Desktop.getDesktop().browse(extentReport.toURI());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
