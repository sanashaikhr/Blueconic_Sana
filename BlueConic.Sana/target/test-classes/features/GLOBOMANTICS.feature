@sana
Feature: Conference section of Globomantics

  
  Scenario Outline: Verify that I as a user is  able to filter sessions by day
  
    Given I start the browser
    When I navigate to "http://localhost:1337/"
    And I click on the "Conference" tab
    And I click on the "View Sessions" button
    Then I should be able filter session by "<day>"
    
    Examples:
    | day      |
    | Friday   |
    
    
    
    Scenario Outline: Verify that I as a user is  able to create a new session
  
    Given I start the browser
    When I navigate to "http://localhost:1337/"
    And I click on the "Conference" tab
    And I click on the "View Sessions" button
    And I click on the "Submit a Session!" button
    Then I should be able to create a session with details as  "<Title>", "<Description>", "<Day>" and "<Level>" 
    
    Examples:
    | Title              | Description      |Day        |Level                         |
    | Blue Conic session |Test session      |Thursday   |Introductory and overview     |
   

 