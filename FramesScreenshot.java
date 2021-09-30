package Assignments;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesScreenshot {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://leafground.com/pages/frame.html");
		WebElement findElement2 = driver.findElement(By.xpath("//div[@id='wrapframe']/iframe"));
		driver.switchTo().frame(findElement2);
		WebElement findElement = driver.findElement(By.xpath("//button[@id='Click']"));

		File screenshotAs = findElement.getScreenshotAs(OutputType.FILE);
		File deslocation = new File("./snaps/frame.png");
		FileUtils.copyFile(screenshotAs, deslocation);
		driver.switchTo().defaultContent();
		List<WebElement> findElements = driver.findElements(By.tagName("iframe"));
		System.out.print("Number of frames in the page is : " + findElements.size());
	}

}
