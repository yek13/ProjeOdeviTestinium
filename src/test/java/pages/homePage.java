package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.Random;

public class homePage {
    public static WebDriver driver;
    public homePage(WebDriver webDriver) {
        driver = webDriver;
    }
    @FindBy(how=How.XPATH, using="//div[contains(@class,'resultText')]/h1")
    WebElement searchconfirmation;
    @FindBy(how=How.XPATH, using="//*[@id=\'searchData\']")
    WebElement search;
    @FindBy(how=How.XPATH, using="//*[@id=\'header\']/div/div/div[2]/div[1]/a/span")
    WebElement searchbtn;
    @FindBy(how=How.XPATH, using="//*[@id=\'contentListing\']/div/div/div[2]/div[4]/a[2]")
    WebElement pagination2btn;


    Random r = new Random();
    public void search(String searchvalue){
        search.clear();
        search.sendKeys(searchvalue);
    }

    public void searchButton()
    {
        searchbtn.click();
    }
    public void pagination2Button(){

        pagination2btn.click();
    }
    public void randomproductbtn(){


        List<WebElement> element = driver.findElements(By.xpath("//*[starts-with(@id,'p')]/div[1]/a/h3"));
        int randomProduct = r.nextInt(element.size());
        element.get(randomProduct).click();

    }
    public boolean isPageOpened(String searchvalue){

        return searchconfirmation.getText().contains(searchvalue);
    }

    public boolean is2PageOpened() {
        if(driver.findElements(By.xpath("//*[@id=\'contentListing\']/div/div/div[2]/div[4]/a[2]")).isEmpty())
        {
            return false;
        }
        return true;
    }

}
