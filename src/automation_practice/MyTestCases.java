package automation_practice;

import java.awt.RenderingHints.Key;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

public class MyTestCases {
	
	WebDriver driver = new ChromeDriver();
	String websiteURL = "https://codenboxautomationlab.com/practice/";
	
	Random rand = new Random();
	
	
	@BeforeTest
	public void setup() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(websiteURL);
	}
	
	
	@Test(priority = 1, enabled = false)
	public void radioButtonTest() throws InterruptedException {
		
		Thread.sleep(10000);
		
		WebElement  radiobtnGroup=driver.findElement(By.id("radio-btn-example"));
		List<WebElement> radiobtnList =  radiobtnGroup.findElements(By.tagName("input"));
		int radioListSize = radiobtnList.size();
		WebElement randRadiobtn =   radiobtnList.get(rand.nextInt(radioListSize));
		randRadiobtn.click();
		
		boolean ActualResult = randRadiobtn.isSelected();
		
		org.testng.Assert.assertEquals(ActualResult, true);
		
	}
	
	
	@Test(priority = 2, enabled = false)
	public void autoCompleteTest() throws InterruptedException {
		
		String[] country = {"ba","bi","di","en","ir","en"};
		
		WebElement autoCompleteField = driver.findElement(By.id("autocomplete"));
		String randomLetters= country[rand.nextInt(country.length)]; 
		autoCompleteField.sendKeys(randomLetters);
		
		Thread.sleep(1000);
		
		autoCompleteField.sendKeys(Keys.chord(Keys.ARROW_DOWN,Keys.ENTER));
		
//		WebElement countryElementList = driver.findElement(By.id("ui-id-1"));
//		List<WebElement> countryElements = countryElementList.findElements(By.className("ui-menu-item-wrapper"));
//		WebElement randCountry = countryElements.get(rand.nextInt(countryElements.size()));
//		randCountry.click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String autoCompleteData =  (String) js.executeScript("return arguments[0].value", autoCompleteField);
		
		boolean ActualResult = autoCompleteData.toLowerCase().contains(randomLetters);
		boolean ExpectedResult = true;
		
		org.testng.Assert.assertEquals(ActualResult, ExpectedResult);
	
		
		
	}
	
	
	@Test(priority = 3, enabled = false)
	public void staticList () throws InterruptedException {
		
		WebElement selectElement = driver.findElement(By.id("dropdown-class-example"));
		Select selector = new Select(selectElement);
		selectElement.click();
		Thread.sleep(1000);
		int randSelect = rand.nextInt(1,4);
		selector.selectByIndex(randSelect);
	}
	
	@Test(priority = 4, enabled = false)
	public void Checkbox() {
		WebElement divForThecheckBox = driver.findElement(By.id("checkbox-example"));

		List<WebElement> myCheckBox = divForThecheckBox.findElements(By.xpath("//input[@type='checkbox']"));

		for (int i = 0; i < myCheckBox.size(); i++) {

			myCheckBox.get(i).click();
		}

	}
	
	
	@Test(priority = 5, enabled = false)
	public void switchWindows() throws InterruptedException {

		driver.findElement(By.id("openwindow")).click();
		Thread.sleep(1000);
		
		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(windowHandles.get(1));
		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id=\"menu-item-9675\"]/a/span[1]")).click();
		
		// get back to the main webdriver the first chrome browswe
		driver.switchTo().window(windowHandles.get(0));
		
		Thread.sleep(3000);
		
		WebElement divForThecheckBox = driver.findElement(By.id("checkbox-example"));

		List<WebElement> myCheckBox = divForThecheckBox.findElements(By.xpath("//input[@type='checkbox']"));

		for (int i = 0; i < myCheckBox.size(); i++) {

			myCheckBox.get(i).click();
		}
		
		
	}
	
	@Test(priority = 6, enabled = false)
	public void switchTABS() throws InterruptedException {

		driver.findElement(By.id("opentab")).click();

		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

		driver.switchTo().window(windowHandles.get(1));
		Thread.sleep(3000);

		String ActualValue = driver.getTitle();

		String expectedValue = "Codenbox AutomationLab - YouTube";

		org.testng.Assert.assertEquals(ActualValue, expectedValue);

	}
	
	
	@Test(priority = 7 , enabled = false)
	public void alertTest() throws InterruptedException {
		
		WebElement alertInputField = driver.findElement(By.id("name"));
		alertInputField.sendKeys("Malek");
		
		// ****************** alert normal ***********************
		WebElement alertButton = driver.findElement(By.id("alertbtn"));
		alertButton.click();
		
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		
		
		// ******************confirm alert*********************
		
		WebElement confirmAlertButton = driver.findElement(By.id("confirmbtn"));
		confirmAlertButton.click();
		Thread.sleep(2000);
		driver.switchTo().alert().dismiss();
		
		alertInputField.sendKeys("Yasin");
		

		confirmAlertButton.click();
		
		Thread.sleep(2000);
		boolean ActualResult = driver.switchTo().alert().getText().contains("Yasin");
		boolean expectedResult = true;

		org.testng.Assert.assertEquals(ActualResult, expectedResult);
		driver.switchTo().alert().accept();
	}
	
	@Test(priority = 8, enabled = false)
	public void TableExample() {

		WebElement theTable = driver.findElement(By.id("product"));

		
		List<WebElement> allCoursesList = theTable.findElements(By.tagName("td"));

		String myString = theTable.findElements(By.tagName("td")).get(0).getText();
		System.out.println();

		driver.findElement(By.id("name")).sendKeys(myString);

		for (int i = 1; i < allCoursesList.size(); i = i + 3) {
			System.out.println(allCoursesList.get(i).getText());
		}

	}
	
	@Test(priority = 9, enabled = false)
	public void ElementDisplayed() throws InterruptedException {

		// hide element

		WebElement HideButton = driver.findElement(By.id("hide-textbox"));
		HideButton.click();

		WebElement TheElementThatWeWantToHide = driver.findElement(By.id("displayed-text"));

		boolean ActualResult = TheElementThatWeWantToHide.isDisplayed();
		boolean ExpectedResult = false;

		org.testng.Assert.assertEquals(ActualResult, ExpectedResult);

		Thread.sleep(2000);

		WebElement ShowButton = driver.findElement(By.id("show-textbox"));
		ShowButton.click();
		boolean ActualResult2 = TheElementThatWeWantToHide.isDisplayed();
		boolean ExpectedResult2 = true;
		org.testng.Assert.assertEquals(ActualResult2, ExpectedResult2);

	}

	@Test(priority = 10, enabled = false)
	public void Enabled_Disabled() {

		WebElement DisableButton = driver.findElement(By.id("disabled-button"));
		DisableButton.click();

		WebElement TheelementThatWeNeedToDisable = driver.findElement(By.id("enabled-example-input"));

		boolean ActualResult = TheelementThatWeNeedToDisable.isEnabled();
		boolean ExpectedResult = false;

		org.testng.Assert.assertEquals(ActualResult, ExpectedResult);

		WebElement EnableButton = driver.findElement(By.id("enabled-button"));

		EnableButton.click();
		boolean ActualResult2 = TheelementThatWeNeedToDisable.isEnabled();

		boolean ExpectedResult2 = true;
		org.testng.Assert.assertEquals(ActualResult2, ExpectedResult2);

	}
	
	
	@Test(priority = 11, enabled = false)
	public void MouseHover() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0,1750)");

		Actions action = new Actions(driver);

		WebElement Thetarget = driver.findElement(By.id("mousehover"));
		action.moveToElement(Thetarget).perform();
		;

		driver.findElement(By.linkText("Reload")).click();

	}
	
	
	@Test(priority = 12, enabled = false)
	public void calenderBtn() throws InterruptedException {

		driver.findElement(By.linkText("Booking Calendar")).click();

		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

		driver.switchTo().window(windowHandles.get(1));
		Thread.sleep(3000);

		List<WebElement> avilableDay = driver.findElements(By.xpath("//a[@href='javascript:void(0)']"));
		
		for(int i=1 ; i<avilableDay.size();i++) {
			avilableDay.get(i).click();
			Thread.sleep(1000);
			avilableDay = driver.findElements(By.xpath("//a[@href='javascript:void(0)']"));
		}

	}
	
	@Test(priority = 13, enabled = true)
	public void iFrame() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)driver ; 
		
		js.executeScript("window.scrollTo(0,2400)");
		
	WebElement myFrame = 	driver.findElement(By.id("courses-iframe"));
	
	
	Thread.sleep(3000);
	driver.switchTo().frame(myFrame);
	js.executeScript("window.scrollTo(0,1100)");

	
	String myText =driver.findElement(By.xpath("//*[@id=\"ct_heading-1b594e8\"]/div/h3/span")).getText();
	
	System.out.println(myText);
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[@id=\"ct_button-20c391b5\"]/a/span[2]")).click();
	Thread.sleep(2000);
	
	driver.findElement(By.xpath("//*[@id=\"ct-pagetitle\"]/div/ul/li[1]/a")).click();

	}

	
	
}
