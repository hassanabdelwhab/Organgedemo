package demopro;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddUsertest {

	WebDriver driver;
	
	@BeforeSuite
	public void SetUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
	}
	
	@Test (priority =0)
	public void login() {
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login"); // Open URl
		driver.manage().window().maximize();                           // Maximize the window 
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");    // Enter user name 
		driver.findElement(By.id("txtPassword")).sendKeys("admin123"); //Enter pass
		driver.findElement(By.id("btnLogin")).click();                 //click  at login
		driver.manage().timeouts().implicitlyWait(4000 ,TimeUnit.SECONDS); // wait 
		
	}
	
	@Test (priority =1)
	public void Addusers() {
		
		driver.findElement(By.xpath("//b[contains(.,'Admin')]")).click();      //click at admmin
		driver.findElement(By.id("menu_admin_UserManagement")).click();        // click at usermangment 
		driver.findElement(By.id("menu_admin_viewSystemUsers")).click();     	//click at users 
		driver.findElement(By.id("btnAdd")).click();							//click at Add
		driver.findElement(By.id("systemUser_employeeName_empName")).sendKeys("Hassan Mohammed Abdelwhab"); //Enter Employee Name
		driver.findElement(By.id("systemUser_userName")).sendKeys("Hassan2");          //Enter user name
		driver.findElement(By.id("systemUser_password")).sendKeys("OrangeHrm@123");    // Enter Pass
		driver.findElement(By.id("systemUser_confirmPassword")).sendKeys("OrangeHrm@123"); //Confirm pass
		driver.findElement(By.id("btnSave")).click();							//clcik at save
		driver.findElement(By.id("searchSystemUser_userName")).sendKeys("Hassan2");  //search by name
		driver.findElement(By.id("searchBtn")).click();							//click at search 
		driver.findElement(By.xpath("//a[contains(.,'Hassan2')]")).click();		//open search result 	
		String expectedName = "Hassan2";
		String actualName = driver.findElement(By.id("systemUser_userName")).getAttribute("value");
		Assert.assertEquals(actualName, expectedName);
		
	}
	
	
	@AfterSuite
	public void quite() {
		driver.quit();
	}
	
	
	
}
