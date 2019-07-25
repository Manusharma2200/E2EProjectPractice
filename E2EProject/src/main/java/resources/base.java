package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class base {

	public static WebDriver driver;
	public Properties prop;

	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\kanik\\Desktop\\Eclipse Projects\\E2EProject\\src\\main\\java\\resources\\data.properties");

		prop.load(fis);
		String browserName = prop.getProperty("browser"); 
		System.out.println(browserName);

		if (browserName.equals("chrome")) {
			// execute in chrome driver
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\kanik\\Desktop\\Manu\\Java Installations\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			

		} else if (browserName.equals("firefox")) {
			// firefox code
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\kanik\\Desktop\\Manu\\Java Installations\\geckodriver\\geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browserName.equals("IE")) {
			// IE code
			System.setProperty("webdriver.ie.driver",
					"C:\\Users\\kanik\\Desktop\\Manu\\Java Installations\\IEDriver\\MicrosoftWebDriver.exe");

			driver = new InternetExplorerDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// just before returning the driver object we are placing implicit wait on it. 
		return driver;

	}

	public void getScreenshot(String result) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C://test//" + result + "screenshot.png"));

	}

}
