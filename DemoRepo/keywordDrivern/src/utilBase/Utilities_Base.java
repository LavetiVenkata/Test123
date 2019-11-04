package utilBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Xls_Reader.Xls_Reader;

public class Utilities_Base {

	public static WebDriver driver;
	public static WebElement element;
	public static Xls_Reader xls = new Xls_Reader ("C:\\Workspace\\madhaviTraining\\keywordDrivern\\src\\configData\\ConfigData.xlsx");
	public static int rowCount;
	public static int rowNum;
	public static String testcaseName;
	public static String runModeVal;
	public static String actionName;
	public static String objectXpath;
	public static String testDataVal;
	public static String result;
	
	
}
