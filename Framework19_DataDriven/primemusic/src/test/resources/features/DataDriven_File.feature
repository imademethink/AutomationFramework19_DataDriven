Feature: Data Driven operation - E-commerce

  @data_driven_external_source
  Scenario Outline: Data driven with data from external source
    When Fetch data from source "Ecommerce_data.xlsx" for case "<case_number>"
#    When Fetch data from source "Ecommerce_data.csv" for case "<case_number>"
#    When Fetch data from source "Ecommerce_data.sql" for case "<case_number>"
    When Perform login action
    Then Validate login result
    When Perform search action
    Then Validate search result
    When Perform checkout action
    Then Validate checkout result
    When Perform payment action
    Then Validate payment result
  Examples:
    | case_number |
    # login cases
    |case_1     |
    |case_2     |
    # search without login
    |case_3     |
    |case_4     |
    |case_5     |
    # login and search and checkout
    |case_6     |
    |case_7     |
    # login and search and checkout and payment
    |case_8     |
    |case_9     |





  @data_and_action_driven_external_source
  Scenario Outline: Data driven with action and data from external source
    When Perform action and fetch data from "Ecommerce_data.xlsx" for case "<case_number>"
    Examples:
      | case_number |
    # login cases
      |case_1     |
      |case_2     |
    # search without login
      |case_3     |
      |case_4     |
      |case_5     |
    # login and search and checkout
      |case_6     |
      |case_7     |
    # login and search and checkout and payment (cheque)
      |case_8     |
      |case_9     |
    # login and search and checkout and payment (bank)
      |case_10     |
