package week4.day2;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTableHTMLpageRowsCols {

	public static void main(String[] args) {
		

    // Chrome Setup and Disable Browser Notifications

	WebDriverManager.chromedriver().setup();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
		
	// Launch URL
			
    ChromeDriver driver = new ChromeDriver(options);
	driver.get("https://html.com/tags/table/");
    driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); 
		
		
	// Total no of tables in the page
	
	List<WebElement> list1 = driver.findElements(By.tagName("table"));
	System.out.println("No of tables : "+list1.size());	
	//Name of table1
	
	String text = driver.findElement(By.xpath("//div[@class='render']//caption")).getText();
	System.out.println("Table 1 Name is "+"'"+text+"'");
	
	// Number of columns in table1
	
	List<WebElement> list2 = driver.findElements(By.xpath("//table[1]/thead/tr/th"));
	
	// Another path to find columns
	
	//List<WebElement> list2 = driver.findElements(By.xpath("//div[@class='render']/table//th"));
	
		System.out.println("Total no of columns in table1 is "+list2.size());
		
    // Number of rows in table1
		List<WebElement> list3 = driver.findElements(By.xpath("//div[@class='render']/table//tr"));
		System.out.println("Total no of rows in table1 is  "+list3.size());
	}

}
