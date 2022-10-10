package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebtableChittorgarh {

	public static void main(String[] args) throws InterruptedException {

	// Chrome Setup and Disable Browser Notifications

	WebDriverManager.chromedriver().setup();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
		
	// Launch URL
			
    ChromeDriver driver = new ChromeDriver(options);
	driver.get("https://www.chittorgarh.com/");
    driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	
	// Close Zerodha Ad
	
    driver.findElement(By.xpath("//flashcard-title[contains(text(),'Zerodha')]/following-sibling::flashcard-close")).click();
		
	// Click on Stock Market
	
	driver.findElement(By.xpath("//a[@title='Stock Market']")).click();
	
	// Click on NSE Bulk Deals
	
	driver.findElement(By.xpath("//a[text()='NSE Bulk Deals']")).click();
	Thread.sleep(3000);
	
	// To get all Security details
	
	// Find No of rows and copy all in a list and iterate through all rows wrt to column3 
	
	List<WebElement> secNameList1 = driver.findElements(By.xpath("//h2[contains(text(),'NSE')]/following::tbody/tr/td[3]"));
	int size1 = secNameList1.size();
	System.out.println("Original list size ---->"+ size1);
	
	// Create a List to store the Names fetched from WebTable
	
	List<String> secNameList2 = new ArrayList<String>();
	
	for (int i = 0; i < size1; i++) {
		
	    String text = secNameList1.get(i).getText();
	    secNameList2.add(text);
	}
	
	// Load the elements in a set in order to remove dupliactes
	
	Set<String> secNameListFinal = new LinkedHashSet<String>(secNameList2);
	int size2 = secNameListFinal.size();
	
	// Compare the size of list and set to check for duplicates
	
	if(size1==size2)
	{
	 
		System.out.println("No duplicates found");	
		
	}
	else
	{
		System.out.println((size1-size2)+ " duplicates found");
	}
	
	// Print the Security names in Set after removing duplicates by iterating through each element
	
    System.out.println("********************************************************");
	System.out.println("****************Security Name List**********************");
	for (String eleName : secNameListFinal) {
		
		System.out.println(eleName);
		
	}
	
  }
	
}