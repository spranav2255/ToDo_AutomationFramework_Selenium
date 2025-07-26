Feature: Login Feature

  Scenario: Login with valid credentials
    Given User is on Login Page
    When User enters username "admin" and password "admin"
    And Clicks on login button
    Then User should be navigated to dashboard

  Scenario: Login with invalid credentials
    Given User is on Login Page
    When User enters username "admin" and password "wrongpass"
    And Clicks on login button
    Then User should see an alert "Invalid credentials"
