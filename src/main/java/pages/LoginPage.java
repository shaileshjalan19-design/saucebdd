package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	    private WebDriver driver;
	    private By userField = By.id("user-name");
	    private By passField = By.id("password");
	    private By loginBtn = By.id("login-button");

	    public LoginPage(WebDriver driver){ this.driver = driver; }

	    public void open(String url){ driver.get(url); }

	    public void login(String username, String password){
	        driver.findElement(userField).sendKeys(username);
	        driver.findElement(passField).sendKeys(password);
	        driver.findElement(loginBtn).click();
	    }

	    public String getTitle(){ return driver.getTitle(); }
	}
