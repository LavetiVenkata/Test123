package business.libraries;

import java.io.File;
import java.util.concurrent.TimeUnit;

//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilBase.Utilities_Base;

public class CommonActions extends Utilities_Base{
	

	
	public static void executeKeywords (String testName) {
		try {
			rowCount = xls.getRowCount("TestCase");
			
			for (rowNum = 1; rowNum <= rowCount; rowNum ++) {
				testcaseName = xls.getCellData("TestCase", "TCID", rowNum);
				
				if (testcaseName.equalsIgnoreCase(testName)) {
					runModeVal = xls.getCellData("TestCase", "RunMode", rowNum);
					
					if (runModeVal.equalsIgnoreCase("Y")) {
						
						actionName = xls.getCellData("TestCase", "ActionKey", rowNum);
						objectXpath = xls.getCellData("TestCase", "Object", rowNum);
						testDataVal = xls.getCellData("TestCase", "TestData", rowNum);
						
						if (actionName.equalsIgnoreCase("openBrowser")) 
							result = openBrowser(testDataVal);
						
						if (actionName.equalsIgnoreCase("navigateUrl"))
							result = navigateUrl();
						
						if (actionName.equalsIgnoreCase("enterText"))
							result = enterText(objectXpath, testDataVal);
						
						if (actionName.equalsIgnoreCase("clickButton"))
							result = clickButton(objectXpath);
						
						if (actionName.equalsIgnoreCase("selectItem"))
							result = selectItem(objectXpath, testDataVal);
						
						if (actionName.equalsIgnoreCase("closeBrowser"))
							result = closeBrowser();
					}
					xls.setCellData("TestCase", "Result", rowNum, result);
				}
			}
			
		}catch (Exception a) {
			a.printStackTrace();
		}
	}
	
	//open browser
	public static String openBrowser(String browser) {
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "C:\\Workspace\\sumita workspace\\lib\\SeleniumDrivers\\chromedriver.exe");
				driver = new ChromeDriver();
					
			} else if (browser.equalsIgnoreCase("IE")) {
				//write ie code
			} else if (browser.equalsIgnoreCase("gecko")) {
				//write gecko code
			} else {
				System.out.println("INvalid browser selection");
			}
			System.out.println("User is able to open " + browser + " browser");
			return "PASS";
		}	catch(Exception g) {
			g.printStackTrace();
			System.out.println("User is not able to open " + browser + " browser");
			return "FAIL";
		}		
	}

	//launch the app
	public static String navigateUrl() {
		try {
			driver.get("https://www.amazon.com/");
			
			driver.manage().window().maximize();
			
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			return "PASS";
		}catch (Exception a) {
			a.printStackTrace();
			return "FAIL";
		}	
	}
	
	//enter text
	public static String enterText (String xpathValue, String enterValue) {
		try {
//			explicity wait
			WebDriverWait wait = new WebDriverWait (driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathValue)));
			
			element = driver.findElement(By.xpath(xpathValue));
			element.sendKeys(enterValue);
			return "PASS";
		}catch (Exception h) {
			h.printStackTrace();
			return "FAIL";
		}
	}

	//click button
	public static String clickButton (String xpathValue) {
		try {
			element = driver.findElement(By.xpath(xpathValue));
			element.click();
			return "PASS";
		}catch (Exception h) {
			h.printStackTrace();
			return "FAIL";
		}
	}
	
	//select items from drop down list
	public static String selectItem (String xpathValue, String selectValue) {
		try {
			element = driver.findElement(By.xpath(xpathValue));
			Select select = new Select (element);
			select.selectByVisibleText(selectValue);
			return "PASS";
		}catch (Exception h) {
			h.printStackTrace();
			return "FAIL";
		}
	}
	
	//take screen shot	
	public static String itemScreenshot () throws Exception {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		
		File source = ts.getScreenshotAs(OutputType.FILE);
		
	//	FileUtils.copyFile(source, new File("correct path.png"));
		
		System.out.println(" Screenshot taken");
		return "PASS";
		
		
	}
	
	//close the browser
	public static String closeBrowser () {
		try {
			driver.close();
			driver.quit();
			return "PASS";
		}catch (Exception g) {
			g.printStackTrace();
			return "FAIL";
		}
	}
	
	
}
