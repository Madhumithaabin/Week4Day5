package Assignments;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://dev96375.service-now.com/navpage.do");
		WebElement frame = driver.findElement(By.id("gsft_main"));
		driver.switchTo().frame(frame);

		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("Abinjenny@96");
		driver.findElement(By.id("sysverb_login")).click();
		driver.switchTo().defaultContent();
		driver.findElement(By.id("filter")).sendKeys("incident");
		driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
		WebElement frame2 = driver.findElement(By.id("gsft_main"));
		driver.switchTo().frame(frame2);
		driver.findElement(By.xpath("//button[text()='New']")).click();
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		driver.switchTo().defaultContent();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> searc = new ArrayList<String>(windowHandles);
		driver.switchTo().window(searc.get(1));
		driver.findElement(By.xpath("//a[@class='glide_ref_item_link']")).click();
		driver.switchTo().window(searc.get(0));
		WebElement frame3 = driver.findElement(By.id("gsft_main"));
		driver.switchTo().frame(frame3);
		// driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
		driver.findElement(By.xpath("(//input[@class='form-control'])[2]")).sendKeys("test");
		String incidentNum = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println(incidentNum);
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		driver.switchTo().defaultContent();
		WebElement frame4 = driver.findElement(By.id("gsft_main"));
		driver.switchTo().frame(frame4);

		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(incidentNum, Keys.ENTER);
		String incNum = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		if (incidentNum.equals(incNum))
			System.out.println("incident is created successful");
		else
			System.out.println("incident is not created");
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./snap/servicenow.png");
		FileUtils.copyFile(screenshotAs, dest);

//		driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
		driver.switchTo().defaultContent();
	}

}
