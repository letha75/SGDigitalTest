Feature: CareerSearch based on non existing keyword
  Career Search based on non existing keyword

  Scenario: Career Search based on non existing keyword
    Given User launch SGDigital homepage
    Then Careers menu item is displayed
    When User clicks on Careers link
    Then Careers Page is displayed with current vacancies listed
    And User enter following as keyword
      | Non existing Keyword |
      | abcdefg              |
    And I click enter key
    Then Vacancies based on the criteria is displayed
    Then Close Browser