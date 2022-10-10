package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import com.graphbuilder.struc.LinkedList;
import io.github.bonigarcia.wdm.WebDriverManager;


public class SnapDealAdvancedUserInteract {

	public static void main(String[] args) throws InterruptedException, IOException {
	
		//https://www.snapdeal.com/
		
		// Chrome Setup and Disable Browser Notifications

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
			
		// Launch URL
				
	    ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.snapdeal.com/");
	    driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); 
		
		// Mouse hover to Men's Fashion
		
		WebElement ele1 = driver.findElement(By.xpath("//span[contains(text(),'Men')]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(ele1).perform();
		
		// Select category as Sports Shoes
		
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		Thread.sleep(3000);
		
		// No of results found
		
		String numResults = driver.findElement(By.xpath("//span[@class='category-name category-count']")).getText();
		System.out.println("No of items found is "+numResults);
		
	    // Select sub Category as Training shoes and sort by price low to high
		
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		Thread.sleep(3000);
		String trainShoeCount = driver.findElement(By.xpath("//span[@class='category-name category-count']")).getText();
		System.out.println("Training Shoes Count : "+trainShoeCount);
		driver.findElement(By.xpath("//div[contains(text(),'Popularity')]")).click();
		driver.findElement(By.xpath("//li[@data-sorttype='plth']")).click();
		Thread.sleep(5000);
		
	    // Verify whether its sorted properly by adding prices to list
		
		List<WebElement> elements = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		Thread.sleep(5000);
		List<Integer> priceList = new ArrayList<Integer>();
		System.out.println("Training Shoes from Price Low to high");
		
	    for (WebElement eachElement : elements) {
	    	
	    	String attribute = eachElement.getAttribute("data-price"); // data - price attribute has price amount
	    	int parseInt = Integer.parseInt(attribute);
	    	priceList.add(parseInt);
	    }
	    
	   Collections.sort(priceList);
	   System.out.println(priceList);
	   Thread.sleep(3000); 
	   
       //  Give the price range as 900 to 1200
	   
	   driver.findElement(By.xpath("//input[@name='fromVal']")).clear();
	   driver.findElement(By.xpath("//input[@name='fromVal']")).sendKeys("900",Keys.ENTER);
	   Thread.sleep(3000);
	   driver.findElement(By.xpath("//input[@name='toVal']")).clear();
	   driver.findElement(By.xpath("//input[@name='toVal']")).sendKeys("1200",Keys.ENTER);
	   Thread.sleep(3000);
	   
       // Click on GO after entering price range
	   
	   driver.findElement(By.xpath("//div[contains(text(),'GO')]")).click();
	   Thread.sleep(5000);
	  
       // Filter with color as Yellow (Color Navy is not available in below price range)
	   
	   driver.findElement(By.xpath("//label[@for='Color_s-Yellow']")).click();
	   Thread.sleep(3000);
       
	   // To check the applied filters - Shoe category
	   
	   String text1 = driver.findElement(By.xpath("//h1[@class='category-name']")).getText();
	   if(text1.contains("Training"))
	   {
		   System.out.println("Shoe category filter applied : Yes");
		   System.out.println("Shoe category : "+text1);
	   }
	   else
	   {
		   System.out.println("Shoe category filter applied :  No");
		   
	   }
	  
	   // To check the applied filters - Shoe price range
	   
	   String priceRange = driver.findElement(By.xpath("//a[@data-key='Price|Price']")).getText();
	   System.out.println("priceRange : "+priceRange);
	   
		if(priceRange.contains("Rs. 900 - Rs. 1200"))  
	   
		{
			System.out.println("Price range filter applied : Yes ");
		}
		
		else
		{
			System.out.println("Price range filter applied : No ");
		}
		
	
	    // To check the selected color as yellow
		
		
		boolean selected = driver.findElement(By.xpath("//input[@id='Color_s-Yellow']")).isSelected();
		System.out.println("Color selected checkbox : "+ selected);
	    String colorSelected = driver.findElement(By.xpath("//a[@data-key='Color_s|Color']")).getText();
		if(colorSelected.contains("Yellow"))
		{
	    System.out.println("colorSelected : "+colorSelected);
		}
		else
		{
		System.out.println("No color selected");
		}
		
		// Take the screenshot by clicking quickview of first result
		
		
		driver.findElement(By.xpath("//div[@class='clearfix row-disc']")).click();
		Thread.sleep(3000);
		
		// Use File class to copy the snap to destination folder
		
		File shoeSelectImg = driver.getScreenshotAs(OutputType.FILE);
		File destination1 = new File("./snaps/quickViewSelectedShoe.png");
		FileUtils.copyFile(shoeSelectImg, destination1);
		
		// Click on View Details button and Print the Cost and discount
		// Price in Rupees for selected product
		
		driver.findElement(By.xpath("//a[@class=' btn btn-theme-secondary prodDetailBtn']")).click();
		String finalPrice = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		System.out.println("Product final price is Rs."+finalPrice);
		
		// Print the Discount in %
		
		String discount = driver.findElement(By.xpath("//span[@class='pdpDiscount ']/span")).getText();
		System.out.println("Discount is "+discount+"%");
		
		// Select Shoe Size as 7
		
		driver.findElement(By.xpath("//div[text()='7']")).click();
		
		// Click on Add to Cart and capture the screenshot for view cart
		
		driver.findElement(By.xpath("//span[text()='add to cart']")).click();
		driver.findElement(By.xpath("//div[text()='View Cart']")).click();
		Thread.sleep(3000);
		File shopCartImg = driver.getScreenshotAs(OutputType.FILE);
		File destination2 = new File("./snaps/shopCartImg.png");
		FileUtils.copyFile(shopCartImg,destination2);
		
		
	}

}
