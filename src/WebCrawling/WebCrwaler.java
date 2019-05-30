/*
 * 2019. 5. 28
 * 
 * author : moonhyun
 * last modified 2019. 5. 28
 * details : 실질적인 웹크롤러 역할을 하는 함수를 가진 클래스 
 * 
 */

package WebCrawling;
import java.util.HashMap;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebCrwaler{

	public static  WebDriver driver;
	public static  Actions act;
	public static  WebDriverWait wait;

	public static void Crwaling() throws InterruptedException{
		
		String downloadFilepath = "/Users/macbookpro/git/Team-WebProgramming/external_files/TimeTable_excel";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		
		System.out.println("launching chrome browser");
		/*
		 driverPath : Chrome driver location 
		 siteName : target web site
		 loginId : web site login ID
		 loginPw : web site login Password 
		*/
		String driverPath = "./external_files/"; 
		System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver");
		
		String siteName = "https://eis.cbnu.ac.kr/cbnuLogin";
		String loginId = "2012030014";  
		String loginPw = "Sjrnfl1!2!";
				
	
		driver = new ChromeDriver(cap);
		wait = new WebDriverWait(driver, 20);
		act = new Actions(driver);
		
		// site connecting 
		driver.get(siteName);
		driver.findElement(By.id("uid")).sendKeys(loginId);
		driver.findElement(By.name("pswd")).sendKeys(loginPw);
		driver.findElement(By.id("commonLoginBtn")).sendKeys(Keys.ENTER);
		System.out.println("connecting the site");

		
		
		// wait until web site loads
		System.out.println("Waiting");
		Thread.sleep(5000);
		
		// if ID is already logged in , reconnect the ID
		try {
			WebElement alreadyLogined = driver.findElement(By.id("mainframe_VFS_LoginFrame_ssoLoginTry_form_btn_yTextBoxElement"));
			act.moveToElement(alreadyLogined).click().build().perform();
			System.out.println("ID is already logged in, proceed Relogin");
			Thread.sleep(5000);
		}catch (NoSuchElementException e) {
			throw e;
		}
		
		finally {
		
		
		// click class / grade 
		WebElement elementLocator = driver.findElement(By.xpath("//*[@id=\'mainframe_VFS_HFS_SubFrame_form_tab_subMenu_tpg_bssMenu_grd_menu_body_gridrow_8_cell_8_0_controltreeTextBoxElement\']/div"));
		act.moveToElement(elementLocator).click().build().perform();;
		System.out.println("click 수업/성적 ");
		
		// click class informations
		// wait until target is visible 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'mainframe_VFS_HFS_SubFrame_form_tab_subMenu_tpg_bssMenu_grd_menu_body_gridrow_9_cell_9_0_controltreeTextBoxElement\']/div")));
		act.moveToElement(driver.findElement(By.xpath("//*[@id=\'mainframe_VFS_HFS_SubFrame_form_tab_subMenu_tpg_bssMenu_grd_menu_body_gridrow_9_cell_9_0_controltreeTextBoxElement\']/div"))).click().build().perform();;
		System.out.println("click 수업 정보 ");
		
		// click opened class search
		// wait until target is visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'mainframe_VFS_HFS_SubFrame_form_tab_subMenu_tpg_bssMenu_grd_menu_body_gridrow_15_cell_15_0_controltreeTextBoxElement\']/div")));
		WebElement LectureListSearch_xpath = driver.findElement(By.xpath(("//*[@id=\'mainframe_VFS_HFS_SubFrame_form_tab_subMenu_tpg_bssMenu_grd_menu_body_gridrow_15_cell_15_0_controltreeTextBoxElement\']/div")));
		act.moveToElement(LectureListSearch_xpath).click().build().perform();
		System.out.println("click 개설 강좌 조회");

		// wait until class information frame is loaded 
		System.out.println("wait until class information frame is loaded ");
		Thread.sleep(5000);
		
		// click search 
		WebElement Search = driver.findElement(By.id("mainframe_VFS_HFS_INVFS_WorkFrame_win_2275_form_div_work_btn_searchTextBoxElement"));
		act.moveToElement(Search).click().build().perform();
		System.out.println("click 조회");
		 
		// wait until class information is loaded
		System.out.println("wait until class information is loaded");
		Thread.sleep(12000);
		
		// context click 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mainframe_VFS_HFS_INVFS_WorkFrame_win_2275_form_div_work_grd_master")));
		WebElement context = driver.findElement(By.id("mainframe_VFS_HFS_INVFS_WorkFrame_win_2275_form_div_work_grd_master"));
		act.moveToElement(context).contextClick().build().perform();
		System.out.println("context click ");
		 
		// click export to excel
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mainframe_VFS_HFS_INVFS_WorkFrame_win_2275_form_div_work_grdMenu_600TextBoxElement")));
		WebElement excel = driver.findElement(By.id("mainframe_VFS_HFS_INVFS_WorkFrame_win_2275_form_div_work_grdMenu_600TextBoxElement"));
		act.moveToElement(excel).click().build().perform();
		System.out.println("click 엑셀로 내보내기");
		
		
		// wait until class information file is exported
		System.out.println("wait until class information file is exported");
		Thread.sleep(12000);
		
		
		driver.close();
		System.out.println("driver closed ");
		}
	}
}
