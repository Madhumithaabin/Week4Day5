package Assignments;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.findElement(By.id("username")).sendKeys("demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		WebElement login = driver.findElement(By.className("decorativeSubmit"));
		login.click();
		WebElement crm = driver.findElement(By.linkText("CRM/SFA"));
		crm.click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement((By.xpath("(//ul[@class='shortcuts']//a)[4]"))).click();
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[1]")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> winname = new ArrayList<String>(windowHandles);
		System.out.println("Number of window " + winname.size());
		driver.switchTo().window(winname.get(1));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		Thread.sleep(1000);
		driver.switchTo().window(winname.get(0));
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> winname1 = new ArrayList<String>(windowHandles1);
		driver.switchTo().window(winname1.get(1));
		Thread.sleep(1000);
//I have clicked second row since it shows error from and to id same cannot be merged
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[2]")).click();
		Thread.sleep(1000);
		driver.switchTo().window(winname1.get(0));
		String beftitle = driver.getTitle();
		System.out.println("Title before merge : " + beftitle);
		driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		String title = driver.getTitle();
		if (title.contains("View Contact | opentaps CRM")) {
			System.out.println("Title is verified : " + title);
		} else {
			System.out.println("Title is not verified : " + title);
		}

	}

}
