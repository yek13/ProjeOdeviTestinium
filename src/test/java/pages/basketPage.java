package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class basketPage {
    public static WebDriver driver;
    public String p;


    public basketPage(WebDriver webDriver) {
        driver = webDriver;
    }

    @FindBy(how=How.XPATH, using="//*[@id=\'contentProDetail\']/div/div[3]/div[2]/div[3]/div[3]/a[2]")
    WebElement basketAddBtn;

    @FindBy(how=How.XPATH, using="//*[@id=\'header\']/div/div/div[2]/div[2]/div[4]/a")
    WebElement basketBtn;
    @FindBy(how=How.XPATH, using="//*[@id=\'newCheckout\']/div/div[1]/div[2]/div[1]/section/table[2]/tbody/tr/td[3]/div[1]/div/span[1]")
    WebElement basketUp2;
    @FindBy(how=How.XPATH, using="//*[@id=\'newCheckout\']/div/div[1]/div[2]/div[1]/section/table[2]/tbody/tr/td[1]/div[3]/div[2]/span[1]")
    WebElement deletePruduct;
    @FindBy(how=How.XPATH, using=".//div[contains(@class,'newPrice')]/ancestor::div[contains(@class,'priceDetail')]")
    WebElement productPrice;

    @FindBy(how=How.XPATH, using="/html/body/div[4]/div/div/span")
    WebElement messageokey;
    public void setBasketAddBtn(){
        p=productPrice.getText();//Ürün fiyatını karşılaştırmak için sepet sayfasına gitmeden önce fiyat nesnesini almak lazım.

        basketAddBtn.click();

    }
    public void setBasketBtn()
    {

        basketBtn.click();
    }
    public void setBasketUp2()
    {
        basketUp2.click();
    }
    public void setDeletePruduct(){
        deletePruduct.click();
    }
    public boolean isPriceAssert(){
        String priceBasket=driver.findElement(By.xpath("//*[@class='priceArea']")).getText();

        return p.contains(priceBasket);
    }
    public boolean isEmptyBasket() {
        if(driver.findElements(By.xpath("//*[@id='wrapper']/div[2]/div/div[1]/div[1]/h2")).isEmpty())
        {
            return false;
        }
        return true;
    }
    public boolean isTwoProduct() {
        if(driver.findElements(By.xpath("//input[@name='quantity' and @value='2'] ")).isEmpty())
        {
            return false;
        }
        return true;
    }
    public void setMessageokey(){
        messageokey.click();

    }
}
