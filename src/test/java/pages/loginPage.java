package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class loginPage {
    public static WebDriver driver;
    public loginPage(WebDriver webDriver) {
        driver = webDriver;
    }
    public static String expectedTitles = "n11.com - Alışverişin Uğurlu Adresi";


    @FindBy(how=How.XPATH, using="//*[@id=\'header\']/div/div/div[2]/div[2]/div[2]/div/div/a[1]")
    WebElement login;
    @FindBy(how=How.XPATH, using="//*[@id=\'email\']")
    WebElement emailTextBox;
    @FindBy(how=How.XPATH, using="//*[@id=\'password\']")
    WebElement passwordTextBox;
    @FindBy(how=How.XPATH, using="//*[@id=\'loginButton\']")
    WebElement signinButton;



    public void Login(){
        login.click();
    }
    public boolean isLoginError(){
        if(driver.findElements(By.xpath("//*[@id=\'loginForm\']/div[2]/div[2]/div")).isEmpty())
        {
            return true;
        }
        return false;
    }
    public void setEmail(String strEmail){
        emailTextBox.clear();
        emailTextBox.sendKeys(strEmail);
    }
    public void setPassword(String strPassword){

        passwordTextBox.clear();
        passwordTextBox.sendKeys(strPassword);
    }
    public void clickOnLoginButton(){
        signinButton.click();
    }
    public boolean isPageOpened(){
        //Assertion
        return driver.getTitle().contains(expectedTitles);
    }
}
