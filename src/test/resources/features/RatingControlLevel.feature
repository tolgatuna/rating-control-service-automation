Feature: Rating Control

  Scenario: Decision to read book when customer rating equal to the rating level of books
    Given I am customer who have set rating control level 12
    When I request to read book B1234 which is equal level
    Then I get decision to read the book

  Scenario: Decision to decline the read book when customer rating less than the rating level of book
    Given I am customer who have set rating control level 12
    When I request to read book BH1234 which is higher level
    Then I get decision not to read the book

  Scenario: Decision to decline the read book when rating level of book is not found
    Given I am customer who have set rating control level 12
    When I request to read book XX12UNKNOWN which is not exist
    Then I get decision not to read the book