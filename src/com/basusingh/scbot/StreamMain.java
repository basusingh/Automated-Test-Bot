package com.basusingh.scbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.openqa.grid.common.RegistrationRequest;
import org.openqa.grid.internal.utils.SelfRegisteringRemote;
import org.openqa.grid.internal.utils.configuration.GridHubConfiguration;
import org.openqa.grid.internal.utils.configuration.GridNodeConfiguration;
import org.openqa.grid.web.Hub;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.server.SeleniumServer;

public class StreamMain {

	private GridHubConfiguration gridHubConfig;
	private Hub myHub;
	private GridNodeConfiguration gridNodeConfig;
	private RegistrationRequest req;
	private static RegistrationRequest req1;
	private SelfRegisteringRemote remote;
	
	public static final int MIN_PORT_NUMBER = 1100;
	public static final int MAX_PORT_NUMBER = 49151;
	private static final ServerSocket LOCK;
	private static AtomicInteger currentMinPort = new AtomicInteger(MIN_PORT_NUMBER);

	
	private String hub_role = "hub";
	private String node_role = "node";
	
	//Input from user
	private String hub_host;
	private Integer hub_port;
	private String node_host = "localhost";
	private int[] node_port;
	
	WebDriver driver;
	
	int threadCount;

	//Get the host for hub and node, port for hub and node
	//Returns true if everything runs okay
	//Returns false if there's an error
	public boolean setUp(String hubHost, int hubPort, String nodeHost, int[] nodePort, int tCount) {
		hub_host = hubHost;
		node_host = nodeHost;
		
		try {
			if(!isPortAvailable(hubPort)) {
				int p = getNextAvailableManual(hubPort);
				hub_port = p;
			} else {
				hub_port = hubPort;
			}
			
			//Check each available port of node and change it if not available
			//If a port is not free,then a next port is selected starting from 
			for(int i = 0; i<nodePort.length; i++) {
				if(!isPortAvailable(nodePort[i])) {
					nodePort[i] = getNextAvailableManual(nodePort[nodePort.length-1]);
				}
			}
			node_port = nodePort;
			threadCount = tCount;
			
			ConfigHub();
			StartHub();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public void startBot() {
		
		for(int i = 0; i < node_port.length; i++) {
			System.out.println("Starting node: " + (i+1));
			ConfigNode(node_port[i]);
		}
		

		for(int i = 0; i < threadCount; i++) {
			final int j = i;
			Thread t = new Thread() {
				public void run() {
					try {
						System.out.println("Starting task: " + (j+1));
						InvokeBrowser(node_port[j]);
					} catch (MalformedURLException e) {
						System.out.println("Error executing browser task: " + (j+1));
						e.printStackTrace();
					}
				}
			};
			t.start();
		}
		
	}
	

	public void InvokeBrowser(int nPort) throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
		options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.addArguments("–start-maximized");
		options.addArguments("--user-agent=" + "Mozilla/5.0 (iPhone; CPU iPhone OS 7_0 like MacOS X; en-us) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53");
		options.addArguments("--disable-notifications");
		//options.addArguments("disable-infobars");
		
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

		System.out.println("Setting up driver");
		String node_URL = "http://" + node_host + ":" + nPort + "/wd/hub";
		driver = new RemoteWebDriver(new URL(node_URL), options);
		System.out.println("Starting task");
		sampleTest();

	}
	
	private String getRandomUserAgent() {
		
	}
	
	
	public void sampleTest() {
   	 // Test name: 1
       // Step # | name | target | value
       // 1 | open | https://soundcloud.com/iamcardib/wap-feat-megan-thee-stallion | 
       driver.get("https://soundcloud.com/iamcardib/wap-feat-megan-thee-stallion");
       // 2 | setWindowSize | 1456x876 | 
       //driver.manage().window().setSize(new Dimension(1456, 876));
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
       try {
    	   Thread.sleep(1000);
       } catch(Exception e) {
    	   e.printStackTrace();
       }
       driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
       
       // 9 | sendKeys | id=sign_in_up_email | ${KEY_ENTER}
       driver.findElement(By.id("sign_in_up_email")).sendKeys(Keys.ENTER);
       try {
    	   Thread.sleep(5000);
       } catch(Exception e) {
    	   e.printStackTrace();
       }
       
       driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
       
       // 10 | type | id=enter_password_field | 9871890142
       driver.findElement(By.id("enter_password_field")).sendKeys("9871890142");
       
       driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
       try {
    	   Thread.sleep(1000);
       } catch(Exception e) {
    	   e.printStackTrace();
       }
       // 11 | sendKeys | id=enter_password_field | ${KEY_ENTER}
       driver.findElement(By.id("enter_password_field")).sendKeys(Keys.ENTER);
       try {
    	   Thread.sleep(2000);
       } catch(Exception e) {
    	   e.printStackTrace();
       }
       // 12 | selectFrame | relative=parent | 
       driver.switchTo().defaultContent();
       // 13 | click | linkText=Settings and more | 
       driver.findElement(By.linkText("Settings and more")).click();
       // 14 | click | linkText=Sign out | 
       driver.findElement(By.linkText("Sign out")).click();
       // 15 | close |  | 
       //driver.close();
       driver.quit();

   }
	

	public void RegisterNStartNode(RegistrationRequest req1) {
		remote = new SelfRegisteringRemote(req1);
		remote.setRemoteServer(new SeleniumServer(gridNodeConfig));
		remote.startRemoteServer();
		remote.startRegistrationProcess();

	}

	public void RegisterRequest() {
		req = new RegistrationRequest(gridNodeConfig);
		req.getConfiguration();
		req.validate();
		req1 = req;
		RegistrationRequest.build(gridNodeConfig);
		RegisterNStartNode(req1);
	}

	public void ConfigNode(int nPort) { // NodeConfig
		gridNodeConfig = new GridNodeConfiguration();
		gridNodeConfig.hub = "http://" + hub_host + ":" + hub_port + "/grid/register";
		gridNodeConfig.host = hub_host;
		gridNodeConfig.port = nPort;
		gridNodeConfig.role = node_role;
		RegisterRequest();
	}

	public void ConfigHub() { // HubConfig
		gridHubConfig = new GridHubConfiguration();
		gridHubConfig.role = hub_role;
		gridHubConfig.host = hub_host;
		gridHubConfig.port = hub_port;
	}

	public void StartHub() {
		myHub = new Hub(gridHubConfig);
		myHub.start();
	}
	
	public void closeHub() {
		String closeUrl = "http://" + hub_host + ":" + hub_port +"/lifecycle-manager?action=shutdown";
		
		try {
			URL obj = new URL(closeUrl);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// print result
				System.out.println(response.toString());
			} else {
				System.out.println("GET request not worked");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	//Get the next available port starting from current minPort
	
	public static synchronized int getNextAvailable() {
			        int next = getNextAvailable(currentMinPort.get());
			        currentMinPort.set(next + 1);
			        return next;
	}
	
	public static synchronized int getNextAvailableManual(int port) {
		int next = getNextAvailable(port);
        currentMinPort.set(next + 1);
        return next;
	}
	
	
	//Get the available port from fromPort variable
	public static synchronized int getNextAvailable(int fromPort) {
		        if (fromPort < currentMinPort.get() || fromPort > MAX_PORT_NUMBER) {
		            throw new IllegalArgumentException("From port number not in valid range: " + fromPort);
		        }
		
		        for (int i = fromPort; i <= MAX_PORT_NUMBER; i++) {
			            if (isPortAvailable(i)) {
			                return i;
			            }
		        }
		
			    throw new NoSuchElementException("Could not find an available port above " + fromPort);
		}
	
	
	//Check if a port is available
	public static boolean isPortAvailable(int port) {
	    if (port < MIN_PORT_NUMBER || port > MAX_PORT_NUMBER) {
	        throw new IllegalArgumentException("Invalid start port: " + port);
	    }

	    ServerSocket ss = null;
	    DatagramSocket ds = null;
	    try {
	        ss = new ServerSocket(port);
	        ss.setReuseAddress(true);
	        ds = new DatagramSocket(port);
	        ds.setReuseAddress(true);
	        return true;
	    } catch (IOException e) {
	    } finally {
	        if (ds != null) {
	            ds.close();
	        }

	        if (ss != null) {
	            try {
	                ss.close();
	            } catch (IOException e) {
	                /* should not be thrown */
	            }
	        }
	    }

	    return false;
	}

}