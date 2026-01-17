Feature: SauceDemo Purchase Flow

  Background:
    Given the application URL is initialized

  Scenario Outline: End-to-end purchase successfully
    When I login with username "<username>" and password "<password>"
    Then I should be logged in successfully
    When I add multiple items to cart
    And I proceed to checkout with firstName "John" lastName "Doe" postalCode "560001"
    Then I should see the confirmation message "THANK YOU FOR YOUR ORDER!"

    Examples:
      | username       | password     |
      | standard_user  | secret_sauce |
