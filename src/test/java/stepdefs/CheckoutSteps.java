package stepdefs;

import pages.*;
import utils.DriverFactory;
import io.cucumber.java.en.*;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

public class CheckoutSteps {
    private WebDriver driver = DriverFactory.getDriver();
    private CartPage cart = new CartPage(driver);
    private CheckoutPage checkout = new CheckoutPage(driver);

    @When("I proceed to checkout with firstName {string} lastName {string} postalCode {string}")
    public void proceed_checkout(String fn, String ln, String pc) {
        cart.clickCheckout();
        checkout.provideInfo(fn, ln, pc);
        checkout.finish();
    }

    @Then("I should see the confirmation message {string}")
    public void verify_confirmation(String expected) {
        String actual = checkout.getCompleteMessage();
        Assert.assertEquals(actual.trim().toUpperCase(), expected.trim().toUpperCase());
    }
}

