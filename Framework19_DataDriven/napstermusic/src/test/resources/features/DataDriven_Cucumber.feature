Feature: Data Driven operation - E-commerce

  @data_driven_cucumber
  Scenario Outline: Data driven with data from cucumber
    When Prepare sample test data
    When Perform login operation using "<login_type>"
    Then Validate login "<login_result>"
    When Perform search operation using "<search_type>"
    Then Validate search "<search_result>"
    When Perform checkout operation using "<checkout_type>"
    Then Validate checkout "<checkout_result>"
    When Perform payment operation using "<payment_type>"
    Then Validate payment "<payment_result>"
  Examples:
    |login_type| login_result | search_type | search_result | checkout_type | checkout_result | payment_type | payment_result |
    # login cases
    |valid     | valid       | NA          | NA            | NA            | NA              | NA           | NA             |
    |invalid   | invalid     | NA          | NA            | NA            | NA              | NA           | NA             |
    # search without login
    |NA        | NA          | valid       | valid         | NA            | NA              | NA           | NA             |
    |NA        | NA          | invalid     | invalid       | NA            | NA              | NA           | NA             |
    # search with login
    |valid     | valid       | valid       | valid         | NA            | NA              | NA           | NA             |
    |valid     | valid       | invalid     | invalid       | NA            | NA              | NA           | NA             |
    # login and search and checkout
    |valid     | valid       | valid       | valid         | valid         | valid          | NA           | NA             |
    |valid     | valid       | invalid     | invalid       | NA            | NA             | NA           | NA             |
    # login and search and checkout and payment
    |valid     | valid       | valid       | valid         | valid         | valid          | valid        | valid          |



