
package magenta2;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;

public class AddRandomItem {
	Random rand = new Random();

	WebDriver driver = new ChromeDriver();
	String url = "https://magento.softwaretestingboard.com/";

	@BeforeTest
	public void mybeforetest() {
		driver.get(url);
		driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test(invocationCount = 1, priority = 1)
	public void mytest() throws InterruptedException {

		WebElement allproducts = driver.findElement(By.className("product-items"));
		List<WebElement> item = allproducts.findElements(By.tagName("li"));
		int myindex = rand.nextInt(item.size());
		item.get(myindex).click();
		if (driver.getCurrentUrl().contains("fusion") || driver.getCurrentUrl().contains("push")) {
			WebElement addbutton = driver.findElement(By.id("product-addtocart-button"));
			addbutton.click();
		} else {

			WebElement size = driver
					.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));

			List<WebElement> allsizes = size.findElements(By.tagName("div"));
			int randomsize = rand.nextInt(allsizes.size());
			allsizes.get(randomsize).click();
			WebElement color = driver
					.findElement(By.xpath("//div[@class='swatch-attribute color']//div[@role='listbox']"));
			List<WebElement> allcolors = color.findElements(By.tagName("div"));
			int randcolor = rand.nextInt(allcolors.size());
			allcolors.get(randcolor).click();
			WebElement addbutton = driver.findElement(By.id("product-addtocart-button"));
			addbutton.click();


		}

	}

	@Test(priority = 2)
	public void checkout() {
		String mycheckoutpage = "https://magento.softwaretestingboard.com/checkout/cart/";
		driver.get(mycheckoutpage);
		WebElement proceed = driver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']"));
		proceed.click();
	}
	@Test (priority = 3)
	public void signup () throws InterruptedException {
		
		String signup = "https://magento.softwaretestingboard.com/checkout/#shipping";
		driver.get(signup);
		Thread.sleep(2000);
		
		WebElement email = driver.findElement(By.id("customer-email"));
		WebElement firstname = driver.findElement(By.name("firstname"));
		WebElement lastname = driver.findElement(By.name("lastname"));
		WebElement streetaddress = driver.findElement(By.name("street[0]"));
		WebElement city = driver.findElement(By.name("city"));
		WebElement state = driver.findElement(By.name("region_id"));
		WebElement zip = driver.findElement(By.name("postcode"));

		WebElement country = driver.findElement(By.name("country_id"));
		
		Select select = new Select(country);
		
		WebElement phonenumber = driver.findElement(By.name("telephone"));

		email.sendKeys("dalyakh@hotmail.com");
		firstname.sendKeys("dalya");
		lastname.sendKeys("kh");
		streetaddress.sendKeys("suaad alqawaqneh");
		city.sendKeys("amman");
		state.sendKeys("amman");
		zip.sendKeys("23136");
		select.selectByVisibleText("Jordan");

		phonenumber.sendKeys("0797886534");
		
		WebElement next = driver.findElement(By.cssSelector(".button.action.continue.primary"));
		next.click();
		
		
	}

}
