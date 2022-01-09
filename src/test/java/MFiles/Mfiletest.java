package MFiles;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Mfiletest {
	WebDriver driver;
	WebDriverWait wait;
	HSSFWorkbook wb;
	HSSFRow row2;
	String randomString;
	File file = new File("Userinput\\input.xls");
	
	
	@BeforeClass
	public void pchrome() {// chrome driver
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void pfirefox() {// firefox driver
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}

	@Test
	public void pIE() {// IE driver
		WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();
	}

	@Test
	public void pEdge() {// Edge driver
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	}

	@Test
	public void getFiles() throws IOException, InterruptedException {
		FileInputStream inputStream = new FileInputStream(file);
		wb = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = wb.getSheet("media");
		row2 = sheet.getRow(1);
		HSSFCell cell0 = row2.getCell(0);
		HSSFCell cell1 = row2.getCell(1);
		HSSFCell cell2 = row2.getCell(2);

		String link = cell0.getStringCellValue();
		String email = cell1.getStringCellValue();
		String pwd = cell2.getStringCellValue();
		wait = new WebDriverWait(driver, 120);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("" + link);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("" + email);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("" + pwd);
		driver.findElement(By.xpath("//input[@id='sign-in-btn']")).click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
   		List<WebElement> user = driver.findElements(
		 By.xpath("//h6[contains(text(),'')]"));
   		
   		String selectLinkNewTab= Keys.chord(Keys.CONTROL, Keys.RETURN);
   		for(int i=0; i<=user.size()-1;i++) {
   			List<WebElement> users = driver.findElements(
   				 By.xpath("//h6[contains(text(),'')]"));
   			users.get(i).sendKeys(selectLinkNewTab);
   			List<WebElement> section = driver.findElements(By.xpath("//div[contains(@class,'score-percentage')]"));
			innerloop:
			for (int j=0; j <= section.size()-1; j++) {
				section.get(j).click();
				driver.findElement(By.xpath("//button[contains(@class,'download')]")).click();
				section.get(j).click();
				
				if (j == section.size()-1) {
					driver.navigate().back();
					//driver.get("" + link);
					//driver.navigate().refresh();
					driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
					((JavascriptExecutor) driver).executeScript("scroll(0,300)");
					//wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(usertable)));
					break innerloop;
				}
   		}
   	/*	outerloop:
		 for(int i=0; i<=user.size()-1;i++) {
			 List<WebElement> users = driver.findElements(
					 By.xpath("//h6[contains(text(),'')]"));
			 //WebDriverWait wait = new WebDriverWait(driver, 60);
				
			Thread.sleep(3000);
			//JavascriptExecutor js = (JavascriptExecutor) driver;
			((JavascriptExecutor) driver).executeScript("scroll(0,300)");
			users.get(i).click();
			Thread.sleep(2000);
			List<WebElement> section = driver.findElements(By.xpath("//div[contains(@class,'score-percentage')]"));
			innerloop:
			for (int j=0; j <= section.size()-1; j++) {
				section.get(j).click();
				driver.findElement(By.xpath("//button[contains(@class,'download')]")).click();
				section.get(j).click();
				
				if (j == section.size()-1) {
					driver.navigate().back();
					//driver.get("" + link);
					//driver.navigate().refresh();
					driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
					((JavascriptExecutor) driver).executeScript("scroll(0,300)");
					//wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(usertable)));
					break innerloop;
				}

			}
		if(i==users.size()-1) {
			break outerloop;
		}
		}
   		
	WebElement nextPage= driver.findElement(By.xpath("//li[contains(text(),'⟩')]"));
			
  while(nextPage.isEnabled()) {
	  WebElement next= driver.findElement(By.xpath("//li[contains(text(),'⟩')]")); 
	  next.click();
	  WebElement activePage= driver.findElement(By.xpath("//li[contains(@class,'page-item active')]")); 
	  System.out.println("Next Page No" + activePage.getText());
	  List<WebElement> userList = driver.findElements(
				 By.xpath("//h6[contains(text(),'')]"));
	  outerloop:
		  
			 for(int i=0; i<=userList.size()-1;i++) {
				 List<WebElement> users = driver.findElements(
						 By.xpath("//h6[contains(text(),'')]"));
				 //WebDriverWait wait = new WebDriverWait(driver, 60);
					
				Thread.sleep(3000);
				//JavascriptExecutor js = (JavascriptExecutor) driver;
				((JavascriptExecutor) driver).executeScript("scroll(0,300)");
				users.get(i).click();
				Thread.sleep(2000);
				List<WebElement> section = driver.findElements(By.xpath("//div[contains(@class,'score-percentage')]"));
				innerloop:
				for (int j=0; j <= section.size()-1; j++) {
					section.get(j).click();
					driver.findElement(By.xpath("//button[contains(@class,'download')]")).click();
					section.get(j).click();
					
					if (j == section.size()-1) {
						driver.navigate().back();
						//driver.get("" + link);
						//driver.navigate().refresh();
						driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
						((JavascriptExecutor) driver).executeScript("scroll(0,300)");
						//wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(usertable)));
						break innerloop;
					}

				}
			if(i==users.size()-1) {
				break outerloop;
			}
			}
	  if(!next.isEnabled()){
		  break;
	  }
	  
  }
		/*
		 * while(Element.isEnabled());{ Element.click(); //List<WebElement> user=
		 * driver.findElements(By.xpath(
		 * "//div[contains(@class,'user-detail-block')]/input/following-sibling::a/div[@class='title']"
		 * )); for(j=1;j<=user.size();j++) {
		 * 
		 * user.get(j).click();
		 * 
		 * 
		 * for( k=1;k<=section.size();k++) { if((section.size()==0)){
		 * driver.navigate().back();
		 * 
		 * } else { section.get(k).click();
		 * driver.findElement(By.xpath("//button[contains(@class,'download')]")).click()
		 * ; section.get(k).click(); } if(k>section.size()) {
		 * 
		 * driver.navigate().back(); }} }
		 * 
		 * }
		 */

	}

}
}