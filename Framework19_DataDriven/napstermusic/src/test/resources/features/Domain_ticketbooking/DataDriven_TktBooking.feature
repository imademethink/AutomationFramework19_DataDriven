Feature: Data Driven operation - Ticket booking site

  @data_driven_property_renting_site
  Scenario Outline: Data driven with data from external source
    When Fetch data from source "PropertyBuyer_data.xlsx" for case "<case_number>"
    When Perform login action
    Then Validate login result
    When Perform search action
    Then Validate search result
    When Perform contact initiation action
    Then Validate contact initiation result
  Examples:
    | case_number |
    # login cases
    |case_1     |
    |case_2     |
    # search without login
    |case_3     |
    |case_4     |
    |case_5     |
    # login and search and contact initiation
    |case_6     |
    |case_7     |
