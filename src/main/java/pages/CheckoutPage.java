package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

	    private WebDriver driver;
	    private By firstName = By.id("first-name");
	    private By lastName = By.id("last-name");
	    private By postalCode = By.id("postal-code");
	    private By continueBtn = By.id("continue");
	    private By finishBtn = By.id("finish");
	    private By completeHeader = By.cssSelector(".complete-header");

	    public CheckoutPage(WebDriver driver){ this.driver = driver; }

	    public void provideInfo(String fn, String ln, String postal){
	        driver.findElement(firstName).sendKeys(fn);
	        driver.findElement(lastName).sendKeys(ln);
	        driver.findElement(postalCode).sendKeys(postal);
	        driver.findElement(continueBtn).click();
	    }

	    public void finish(){ driver.findElement(finishBtn).click(); }

	    public String getCompleteMessage(){ return driver.findElement(completeHeader).getText(); }
	}


