package stepdefs;

import pages.*;
import utils.DriverFactory;
import utils.ExcelReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage login;
    private InventoryPage inventory;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        login = new LoginPage(driver);
        inventory = new InventoryPage(driver);
    }

    @Given("the application URL is initialized")
    public void url_initialized() {
        // driver already navigated in suite-level onStart. But ensure present
        driver.get(utils.Config.BASE_URL);
    }

    @When("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String username, String password) {
        login.login(username, password);
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        // verify by title or presence of inventory
        Assert.assertTrue(driver.getTitle().contains("Swag Labs"), "Login failed or title mismatch");
    }

    @When("I add multiple items to cart")
    public void add_multiple_items() {
        inventory.addMultipleItems(2);
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        inventory.goToCart();
    }

    @After
    public void tearDown() {
        // keep browser open until suite finish; do nothing here
    }

    // If you want TestNG DataProvider method in this class (optional)
    @DataProvider(name="loginData")
    public static Object[][] loginData() throws Exception {
        return ExcelReader.readSheet("src/test/resources/testdata/users.xlsx", "Sheet1");
    }
}

