package magenta2;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddRandomItem {
	Random rand = new Random();

	WebDriver driver = new ChromeDriver();
	String url = "https://magento.softwaretestingboard.com/";

	@BeforeTest
	public void mybeforetest() {


	}

	@Test(invocationCount = 5)
	public void mytest() {
		driver.get(url);
		driver.manage().window().maximize();

		WebElement allproducts = driver.findElement(By.className("product-items"));
		List<WebElement> item = allproducts.findElements(By.tagName("li"));
		int myindex = rand.nextInt(item.size());
		item.get(myindex).click();
		if (driver.getCurrentUrl().contains("fusion") || driver.getCurrentUrl().contains("push")) {
			WebElement addbutton = driver.findElement(By.id("product-addtocart-button"));
			addbutton.click();
		} else {

			WebElement size = driver.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));

			List<WebElement> allsizes = size.findElements(By.tagName("div"));
			int randomsize = rand.nextInt(allsizes.size());
			allsizes.get(randomsize).click();
			WebElement color = driver.findElement(By.xpath("//div[@class='swatch-attribute color']//div[@role='listbox']"));
			List<WebElement> allcolors = color.findElements(By.tagName("div"));
			int randcolor = rand.nextInt(allcolors.size());
			allcolors.get(randcolor).click();
			WebElement addbutton = driver.findElement(By.id("product-addtocart-button"));
			addbutton.click();

		}

	}

}
