# GeoApp

<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Launch Application](#launch-application)
* [Usage](#usage)
* [Exception Handling](#exception-handling)
* [Junit Coverage](#junit-coverage)
* [Swagger UI](#swagger-ui)
* [Possible Improvements](#possible-improvements)



<!-- ABOUT THE PROJECT -->
## About The Project

Create a Spring Boot service implementation which exposes an API which calls https://bpdts-test-app.herokuapp.com/, and returns people who are listed as either living in London, or whose current coordinates are within 50 miles of London.

### Built With
* Java 8
* Spring boot

<!-- GETTING STARTED -->
## Getting Started

Download the code as zip file, extract locally and import from IDE as Existing Maven project.

### Prerequisites

May need JRE 8 if you don't have it already. Other dependencies will be downloaded by maven.

### Launch Application

Run this GEOApp.java as java application
<!-- USAGE EXAMPLES -->
## Usage

Default http port used :8080
* To get the users living with in 50 miles of London http://localhost:8080/londonUsers
* To get all users from dwp-herokuapp api: http://localhost:8080/allUsers

#### Used Postman application for setting them and testing.

## Exception Handling
We couold create our custom exceptions to handle invalid request, no records found etc.

## Junit coverage
 There are 2 Test cases written
 * test Users In London With In Fifty Miles
 * test Get Distance In Miles for the given co-ordinates.
 
 ## Swagger UI
 
Swagger UI has been enabled for this project

* http://localhost:8080/swagger-ui.html
## Possible Improvements
We can add more logging to check the API usage for analysis and metrics.

 
