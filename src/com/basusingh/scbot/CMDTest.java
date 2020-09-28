package com.basusingh.scbot;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.grid.internal.utils.configuration.GridHubConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class CMDTest {
	
	static String hubPort = "4499";
	static String fileLocation = "C:\\file.jar";
	static int nodeCount = 5;
	

	static String chromeDriverLocation = "C:\\chromedriver.exe";
	static String hubRegisterUrlForNode = "http://192.168.0.108:" + hubPort + "/wd/hub";
	static String chromeMaxInstance = "20";
	
	static WebDriver driver;
    static private Map<String, Object> vars;
    static JavascriptExecutor js;
    
    
    public static void main(String[] args) throws Exception {
    	
    	GridHubConfiguration hC = new GridHubConfiguration();
    	
    	System.out.println("Starting Hub");
    	Process p = Runtime.getRuntime().exec("java -jar " + fileLocation + " -role hub -port " + hubPort);
    	printResults(p);
    	
    	for(int i = 1; i<=nodeCount; i++) {
    		System.out.println("Starting Node: " + String.valueOf(i));
        	Runtime.getRuntime().exec("java -Dwebdriver.chrome.driver=" + chromeDriverLocation + " -jar " + fileLocation + " -role node -hub " + hubRegisterUrlForNode + " -browser browserName=\"chrome\",version=ANY,platform=WINDOWS,maxInstances=" + chromeMaxInstance);
    	}
    	
    	System.out.println("Setting up driver");
    	setUp();
    	
    	System.out.println("Launching Test");
    	Test();
    	
    	System.out.println("Finishing up");
    	afterTest();
    	
    	System.out.println("Destroying process");
    	p.destroy();
    	
    }
    
    public static void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }
    
    public static void printErrors(Process process) throws IOException{
    	String line = "";
    	BufferedReader errorReader = new BufferedReader(
    	        new InputStreamReader(process.getErrorStream()));
    	while ((line = errorReader.readLine()) != null) {
    	    System.out.println(line);
    	}
    	 
    	errorReader.close();
    }
    
    public static void setUp() throws MalformedURLException {
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    	System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        System.out.println("Chrome Browser Initiated");
        
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();            
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
        
        ChromeOptions cap = new ChromeOptions(); 
        cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                          UnexpectedAlertBehaviour.IGNORE);
        cap.setExperimentalOption("excludeSwitches", "enable-automation");
        
        driver = new RemoteWebDriver(new URL(hubRegisterUrlForNode), cap);
        //driver.get("https://www.soundcloud.com/");
    }

    public static void afterTest() {
        driver.quit();
    }
    

    public static void Test() {
    	 // Test name: 1
        // Step # | name | target | value
        // 1 | open | https://soundcloud.com/iamcardib/wap-feat-megan-thee-stallion | 
        driver.get("https://soundcloud.com/iamcardib/wap-feat-megan-thee-stallion");
        // 2 | setWindowSize | 1456x876 | 
        driver.manage().window().setSize(new Dimension(1456, 876));
        // 3 | mouseOver | css=.loginButton:nth-child(1) | 
        {
          WebElement element = driver.findElement(By.cssSelector(".loginButton:nth-child(1)"));
          Actions builder = new Actions(driver);
          builder.moveToElement(element).perform();
        }
        // 4 | click | css=.loginButton:nth-child(1) | 
        driver.findElement(By.cssSelector(".loginButton:nth-child(1)")).click();
        // 5 | mouseOver | css=.loginButton:nth-child(1) | 
        {
          WebElement element = driver.findElement(By.cssSelector(".loginButton:nth-child(1)"));
          Actions builder = new Actions(driver);
          builder.moveToElement(element).perform();
        }
        // 6 | mouseOut | css=.loginButton:nth-child(1) | 
        {
          WebElement element = driver.findElement(By.tagName("body"));
          Actions builder = new Actions(driver);
          builder.moveToElement(element, 0, 0).perform();
        }
        // 7 | selectFrame | index=0 | 
        driver.switchTo().frame(0);
        // 8 | type | id=sign_in_up_email | nk.vashisat@gmail.com
        driver.findElement(By.id("sign_in_up_email")).sendKeys("nk.vashisat@gmail.com");
        // 9 | sendKeys | id=sign_in_up_email | ${KEY_ENTER}
        driver.findElement(By.id("sign_in_up_email")).sendKeys(Keys.ENTER);
        // 10 | type | id=enter_password_field | 9871890142
        driver.findElement(By.id("enter_password_field")).sendKeys("9871890142");
        // 11 | sendKeys | id=enter_password_field | ${KEY_ENTER}
        driver.findElement(By.id("enter_password_field")).sendKeys(Keys.ENTER);
        // 12 | selectFrame | relative=parent | 
        driver.switchTo().defaultContent();
        // 13 | click | linkText=Settings and more | 
        driver.findElement(By.linkText("Settings and more")).click();
        // 14 | click | linkText=Sign out | 
        driver.findElement(By.linkText("Sign out")).click();
        // 15 | close |  | 
        driver.close();

    }
}