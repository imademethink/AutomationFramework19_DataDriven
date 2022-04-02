Feature: Data Driven operation - Job site

  @data_driven_job_site
  Scenario Outline: Data driven with data from external source
    When Fetch data from source "Candidate_data.xlsx" for case "<case_number>"
    When Perform login action
    Then Validate login result
    When Perform search action
    Then Validate search result
    When Perform job apply action
    Then Validate job apply result
  Examples:
    | case_number |
    # login cases
    |case_1     |
    |case_2     |
    # search without login
    |case_3     |
    |case_4     |
    |case_5     |
    # login and search and job apply
    |case_6     |
    |case_7     |
