#laguage: en

Feature: see each pull request
    i with user need send for 
    pull request por user.


Scenario: disply information the user profile 
    Given i search the informations per user 
    When are displeyer profile user
    Then i must see all informarion about user especific request

Scenario: disply information the pull request
    Given what i see the dados of user 
    And begin a search all pull request this user
    Then i must see informarion abous all pull request 



