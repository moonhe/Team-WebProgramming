import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {

	public static WebDriver driver;
	public static Actions act;
	public static WebDriverWait wait;

	public static void main (String[] args) throws InterruptedException{
		System.out.println("launching chrome browser");
		
		String driverPath = "./external_files/";
		System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver");
		String siteName = "https://eis.cbnu.ac.kr/cbnuLogin";
		String loginId = "2012030014";
		String loginPw = "sjrnfl1!2!";
				
	
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 20);
		act = new Actions(driver);
		
		System.out.println("ds");
		driver.get(siteName);
		driver.findElement(By.id("uid")).sendKeys(loginId);
		driver.findElement(By.name("pswd")).sendKeys(loginPw);
		driver.findElement(By.id("commonLoginBtn")).sendKeys(Keys.ENTER);
		System.out.println("connecting the site");

		 
		 System.out.println("대기 ");
		 Thread.sleep(5000);
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\'mainframe_waitwindowImageElement\']/img")));

		 WebElement elementLocator = driver.findElement(By.xpath("//*[@id=\'mainframe_VFS_HFS_SubFrame_form_tab_subMenu_tpg_bssMenu_grd_menu_body_gridrow_8_cell_8_0_controltreeTextBoxElement\']/div"));
		 act.moveToElement(elementLocator).click().build().perform();;
		 System.out.println("수업/성적 클릭 ");
		

		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'mainframe_VFS_HFS_SubFrame_form_tab_subMenu_tpg_bssMenu_grd_menu_body_gridrow_9_cell_9_0_controltreeTextBoxElement\']/div")));
		 act.moveToElement(driver.findElement(By.xpath("//*[@id=\'mainframe_VFS_HFS_SubFrame_form_tab_subMenu_tpg_bssMenu_grd_menu_body_gridrow_9_cell_9_0_controltreeTextBoxElement\']/div"))).click().build().perform();;
		 System.out.println("수업 정보 클릭 ");
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'mainframe_VFS_HFS_SubFrame_form_tab_subMenu_tpg_bssMenu_grd_menu_body_gridrow_15_cell_15_0_controltreeTextBoxElement\']/div")));
		 WebElement LectureListSearch_xpath = driver.findElement(By.xpath(("//*[@id=\'mainframe_VFS_HFS_SubFrame_form_tab_subMenu_tpg_bssMenu_grd_menu_body_gridrow_15_cell_15_0_controltreeTextBoxElement\']/div")));
		 act.moveToElement(LectureListSearch_xpath).click().build().perform();
		 System.out.println("개설 강좌 조회 클릭  ");

		 System.out.println("대기 ");
		 Thread.sleep(5000);
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mainframe_VFS_HFS_INVFS_WorkFrame_win_2275_form_div_work_btn_searchTextBoxElement")));
		 WebElement Search = driver.findElement(By.id("mainframe_VFS_HFS_INVFS_WorkFrame_win_2275_form_div_work_btn_searchTextBoxElement"));
		 act.moveToElement(Search).click().build().perform();
		 System.out.println("조회 버튼 클릭  ");
		 
		 Thread.sleep(12000);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mainframe_VFS_HFS_INVFS_WorkFrame_win_2275_form_div_work_grd_master")));
		 WebElement export = driver.findElement(By.id("mainframe_VFS_HFS_INVFS_WorkFrame_win_2275_form_div_work_grd_master"));
		 act.moveToElement(export).contextClick().build().perform();
		 System.out.println("마우스 우 버튼 클릭  ");
		 
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mainframe_VFS_HFS_INVFS_WorkFrame_win_2275_form_div_work_grdMenu_600TextBoxElement")));
		 WebElement excel = driver.findElement(By.id("mainframe_VFS_HFS_INVFS_WorkFrame_win_2275_form_div_work_grdMenu_600TextBoxElement"));
		 act.moveToElement(excel).click().build().perform();
		 System.out.println("엑셀로 내보내기");
	
	}
}
