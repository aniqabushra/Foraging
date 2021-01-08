# Stainable Forages Planing

## Requirements

### Items

* **Name** is required and cannot be null.
* Catagery is required.
* Doller/Kg is required,and 0<(inediable,poison)<7500
* ID must be SyetemDenerated

### Foragers

* First name is required.
* Last name is required.
* State is required.
* The combination of first name, last name, and state cannot be duplicated.
* Forager ID is a system-generated GUID (globally unique identifier).

### Forages

Item is required and must exist.

* Forager is required and must exist.

* Date is required and must not be in the future.

* Kilograms must be a positive number not more than 250.

* The combination of date, Item, and Forager cannot be duplicated. (Can't forage the same item on the same day. It
  should be tracked as one Forage.)
  Forage ID is a system-generated GUID (globally unique identifier).

## Working Features

* Add an Item.
* View Items.
* View Foragers.
* Add a Forage.
* View Forages by date.

### Incomplete Features

* Add a Forager.
* Create a report that displays the kilograms of each Item collected on one day.
* Create a report that displays the total value of each Category collected on one day.

## How will I review the provided data?

#### Reviewing App

* [] Run the app to see if its working ,also to see what features did thay add and what more I can add
    * [] Mark Down any errors spoted.
    * [] check validation

#### Reviewing Data

* [] Start reviwing the Data from ForageFileRepository.
    * []  Read Every file in data package to get the understanding of code
* [] Run The tests in data,
    * test for Each fileRepository to make sure its working fine
        * Look for Before Each setup() is working fine.
        * Look for any missing tests if so add that into todo list.
* [] Look each file for its validataion

#### Reviewing Domain

* [] Start reviwing Service Classes
    * [] Read Each methods
    * [] Check Validation method, if its working properly if not mark down as an Todo list
    * [] Mark any improvement or addtion needed
* [] Start Reviewing the Domain Test
    * [] Look for test data file, if not add that in Todo list
    * [] Look for complete Validation test if missing any add that in my Todo list

#### Reviewing Model

[] Start reviwing Model Pakage

* [] review each class and its functionality
* [] Mark any improvement or addtion needed

#### Reviewing UI

[] Start reviwing UI Pakage

* [] Review each class and its functionality.
* [] Check for Wroking Features
* [] Marks any bugs present

## Documenting Bugs

* After reviewing all pakages,I will pull up my TODO list and see what is missng and what is working wrong.
* Document Each prvious bug and its effects.
* Fix the bugs.

## Implementing New Features

### Add a Forager

* To add Forager I need firstName ,Lastname and state
    * Create a Forager **ID** by using GUID
        * Will do more research on it.
    * Create a method Result < Forager> addForager(Forager forager) in Forager Service, and also test it
    * Create A new case in dislayMenu loop in Controller

    * create a method addForager() in controller
        * display Header
    * Create a method makeForager() in View * Prompt for firstName ,LastName, and State, will make a helper method for
      that * validate the input if true then add then result List

### Create a Report that Display the Kg of each items collected in one Day

* I will use streams to extract the data
    * First I will filter the data in one day
    * then apply another filter on items to get the Kg
    * will Do more research on it.

### Create a Report that Display the Total value of each Catogory collected in one Day

I will use streams to extract the data

* First I will filter the data in one day
* Then apply GroupingBy method with key as catagory and value will be Collectors.counting
* still Unsure, will read lessons to get an idea

# TIME EXPECTATIONS 

* Reviewing Previous code 
    * Expected : 4 hrs
    * Actual   : 
* Fixing Bugs
    * Expected : 3 hrs
    * Actual   :
* Adding New Fearures
    * Expected : 4 hrs
    * Actual   :
* Research Topics
    * Expected : 2 hrs
    * Actual   :
* Total Hours
    * **Expected : 13 hrs**
    * **Actual   :**
      
         
      
