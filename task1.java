package tasks;

import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;

public class task1 {
	static String browser;
	static WebDriver driver;
	public static void init() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Browser: ");
		browser = sc.next();
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Tools\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else {
			System.setProperty("webdriver.gecko.driver","C:\\Tools\\driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		sc.close();
	}
	public static void main(String[] args) throws InterruptedException {
		init();
		driver.get("http://www.w3schools.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		System.out.println("Title of the Page: "+driver.getTitle());
		String text = driver.findElement(By.xpath("//h1[contains(text(),'Learn to Code')]")).getText();
		System.out.println(text);
		String txtColor = driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[1]/div[1]/h3[1]")).getCssValue("color");
		String hex = Color.fromString(txtColor).asHex();
		if (hex.equals("#ffc0c7")) {
			System.out.println("Color matches for the text -"+txtColor);
		}
		else
			System.out.println("color doesnt match");
		driver.findElement(By.id("search2")).sendKeys("Java");
		Thread.sleep(2000);
		driver.findElement(By.id("learntocode_searchbtn")).click();
		String javaTitle = driver.getTitle();
		if (javaTitle.equals("Java Tutorial")) {
			System.out.println("Navigated to correct page");
		}
		else
			System.out.println("Instead of Java Tutorial navigated to "+javaTitle);
		driver.quit();
	}
}
