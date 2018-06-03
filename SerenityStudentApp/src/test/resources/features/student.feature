Feature: Testing different request on the application
Scenario: check if the student application can be accessed by users
When the user send a get request to the end point then must get back a valid status code 200

Scenario Outline: Create a new student & verify if the student is added
     When I create a new student by providing the information firstName <firstName> lastName <lastName> email <email> programme <programme> courses <courses>
    Then I verify that the student with <email> is created

    Examples: 
      | firstName | lastName | email                                    | programme        | courses |
      | Declan    | Smith    | nnon.ante.bibendum@risusDonecegestas.edu | Computer Science | Java    |
      | Mark    | Taylor    | nnon2.ante.bibendum@risusDonecegestas.edu | Computer Science | Java    |