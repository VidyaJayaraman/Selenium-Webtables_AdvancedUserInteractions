package week4.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DragDropMouseActionSelenium {

	public static void main(String[] args) throws InterruptedException {
		
	// Chrome Setup and Disable Browser Notifications

	WebDriverManager.chromedriver().setup();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
		
	// Launch Erail URL
						
    ChromeDriver driver = new ChromeDriver();
	driver.get("https://www.leafground.com/drag.xhtml");
    driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	
	
    // Draggable - find source and destination webelements
		
	Actions builder = new Actions(driver);
	WebElement ele1 = driver.findElement(By.xpath("//div[@id='form:conpnl_header']"));	
	WebElement ele2 = driver.findElement(By.xpath("//div[@id='form:restrictPanel']"));
	builder.dragAndDrop(ele1, ele2).perform();
	Point loc1 = ele1.getLocation();
	Point loc2 = ele2.getLocation();
	System.out.println("Before Drag : " + loc1);
	System.out.println("After Drag : "+loc2);
	
	// Confirm the drag operation with getLocation check 
	
	if(loc1.equals(loc2))
	{
		System.out.println("Not Dragged");
	}
	else 
	{
	  System.out.println("Dragged Successfully");	
	}
	
	
	// Draggable Columns - find source and destination webelements
	
	WebElement ele3 = driver.findElement(By.xpath("//th[@aria-label='Category']"));
	WebElement ele4 = driver.findElement(By.xpath("//th[@aria-label='Name']"));
	
	Point loc3 = ele3.getLocation();
	Point loc4 = ele4.getLocation();
	
	System.out.println("Category Cols location before drag "+loc3);
	System.out.println("Name Cols location before drag "+loc4);
	
	builder.dragAndDrop(ele3, ele4).perform();
	
	Point loc5 = ele3.getLocation();
	Point loc6 = ele4.getLocation();
	
	System.out.println("Category Cols loc aft drag "+loc5);
	System.out.println("Name Cols loc aft drag "+loc6);
	
		// Confirm the drag operation with getLocation check 
	
	if(loc3.equals(loc5))
	{
		System.out.println("Column not Dragged");
	}
	else 
	{
	  System.out.println("Columns dragged Successfully");	
	}
	
	// Droppable - find source and destination webelements

	//drag - source & drop - target
	
	WebElement ele5 = driver.findElement(By.xpath("//p[text()='Drag to target']"));
	WebElement ele6 = driver.findElement(By.xpath("//span[text()='Droppable Target']"));
	builder.dragAndDrop(ele5, ele6).perform();
	Thread.sleep(3000);
	String text1 = driver.findElement(By.xpath("//div[@id='form:drop_content']/p")).getText();
	System.out.println(text1);
	
		// Check the text in drop box as "dropped" to confirm the action
	
	if(text1.contains("Dropped"))
	{
		System.out.println("Droppable successful");
	}
	else 
	{
	  System.out.println("Droppable Not successful");	
	}
	
	//Draggable Rows


	WebElement ele7 = driver.findElement(By.xpath("//h5[text()='Draggable Rows']/following::td[text()='Bamboo Watch']"));
	WebElement ele8 = driver.findElement(By.xpath("//h5[text()='Draggable Rows']/following::td[text()='Black Watch']"));
	
	Point loc7 = ele7.getLocation();
	Point loc8 = ele8.getLocation();
	
	System.out.println("Row1 location before drag "+loc7);
	System.out.println("Row2 location before drag "+loc8);
	
    builder.dragAndDrop(ele8, ele7).perform();
    
	Thread.sleep(3000);
	
	Point loc9 = ele7.getLocation();
	Point loc10 = ele8.getLocation();
	
	System.out.println("Row1 location aft drag "+loc9);
	System.out.println("Row2 location aft drag "+loc10);
	
		// Confirm the drag operation with getLocation check 
	
	if(loc7.equals(loc9))
	{
		System.out.println("Rows not Dragged");
	}
	else 
	{
	  System.out.println("Rows dragged Successfully");	
	}
	
	
	
	}

}
