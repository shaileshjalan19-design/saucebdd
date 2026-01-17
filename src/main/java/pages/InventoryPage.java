package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class InventoryPage {
	 private WebDriver driver;
	    private By inventoryItems = By.cssSelector(".inventory_item");
	    private By addToCartButtons = By.cssSelector(".inventory_item .pricebar button");
	    private By cartIcon = By.xpath("//a[@data-test='shopping-cart-link']");

	    public InventoryPage(WebDriver driver){ this.driver = driver; }

	    public void addMultipleItems(int count){
	        List<?> buttons = driver.findElements(addToCartButtons);
	        for(int i=0; i<Math.min(count, buttons.size()); i++){
	            driver.findElements(addToCartButtons).get(i).click();
	        }
	    }

	    public void goToCart(){ 
	    	driver.findElement(cartIcon).click();
	    	}
	}
