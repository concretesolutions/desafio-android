#laguage: en

Feature: Show all github java user
    i'm owner this project and want a
    list with all user the application
    and must show all user.
    

Scenario: list requets all

    Context: screnn home app 

        Given the vizualization information request
        And i must go through the list
        Then i must see all list with informations necessary 

    
Scenario: pull request a repository

    Context: screnn home app

        Given begin the search about any repository 
        And select a user under list 
        Then i must see all information of user

