
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.PageFactory;
import pages.*;

import java.util.concurrent.TimeUnit;
import org.junit.Assert;

import static java.util.concurrent.TimeUnit.SECONDS;

public class FirstTest {
    public static WebDriver driver;
    private static String baseUrl;
    public static String email = "y.emre_96@hotmail.com";
    public static String userPassword = "KStchpW9j4AM.6M";
    public static String search = "Bilgisayar";

    public static loginPage loginPages;
    public static homePage homePages;
    public static basketPage basketPages;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\YEK\\Desktop\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.n11.com/";

        driver.manage().timeouts().implicitlyWait(5, SECONDS);

        driver.manage().timeouts().pageLoadTimeout(60, SECONDS);

        loginPages= PageFactory.initElements(driver,loginPage.class);
        homePages=PageFactory.initElements(driver,homePage.class);
        basketPages=PageFactory.initElements(driver, basketPage.class);
    }

    @Test
    public void testU() throws Exception {

        driver.get(baseUrl);
        Assert.assertTrue(loginPages.isPageOpened());

        loginPages.Login();
        TimeUnit.SECONDS.sleep(2);
        loginPages.setEmail(email);
        TimeUnit.SECONDS.sleep(2);


        loginPages.setPassword(userPassword);

        TimeUnit.SECONDS.sleep(1);
        loginPages.clickOnLoginButton();

        Assert.assertTrue(loginPages.isLoginError());
        homePages.search(search);
        homePages.searchButton();
        Assert.assertTrue(homePages.isPageOpened(search));

        try{
            homePages.pagination2Button();
            homePages.is2PageOpened();
        }
        catch (Exception e){
            System.out.println("No 2nd page");
        }

        TimeUnit.SECONDS.sleep(5);
        if(driver.findElements(By.xpath("/html/body/div[4]/div/div/button/span")).isEmpty()==false)//Rastgele seçilecek ürün ile n11 anketinin üstüste çakışıp hata alınmaması için anketi kapatmak şartı.
        {
            driver.findElement(By.xpath("/html/body/div[4]/div/div/button/span")).click();

        }
        homePages.randomproductbtn();

        TimeUnit.SECONDS.sleep(10);//sayfanın tam yüklenmesi için 10 sn verdim aksi halde sepete ekle butonuna basamayabilir.

        basketPages.setBasketAddBtn();


        TimeUnit.SECONDS.sleep(3);
        basketPages.setBasketBtn();
        TimeUnit.SECONDS.sleep(2);

        Assert.assertTrue(basketPages.isPriceAssert());

        basketPages.setBasketUp2();
        if(driver.findElements(By.xpath("/html/body/div[4]/div")).isEmpty()==false)//Eğer seçilen üründen en fazla 1 adet varsa gelen uyarı mesajında tamam butonuna basar ancak sonraki adımda ürün sayısını 2 olarak onaylayamadığı için durur.
        {
            basketPages.setMessageokey();

        }
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(basketPages.isTwoProduct());

        TimeUnit.SECONDS.sleep(2);

        basketPages.setDeletePruduct();
        TimeUnit.SECONDS.sleep(1);

        driver.navigate().refresh();
        Assert.assertTrue(basketPages.isEmptyBasket());

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}











