# cctv [![CircleCI](https://circleci.com/gh/swapab/cctv/tree/master.svg?style=svg)](https://circleci.com/gh/swapab/cctv/tree/master)

## Table of contents
* [General Info](#general-info)
* [Technologies](#technologies)
* [Setup/Test](#setup/test)
* [Approach and Design choices](#approach-and-design-choices)
* [Challenges](#challenges)

## General Info :

* `cctv` stands for **C**redit **C**ard **T**ransactions **V**anta

* `cctv` is a RESTful Web service 

*  The service enables us to Register Customer Accounts

*  And issue credit cards to those customers

## Technologies
```
* Java : 8
* Spring Boot: 2.2.2.RELEASE
* Gradle : 6.0.1
* Kotlin : 1.3.50
* Target-JVM : 1.8
```

## Setup -> Test -> Run

```
$ git clone https://github.com/swapab/cctv.git
$ cd cctv
$ ./gradlew clean build
$ java -jar build/libs/cctv-0.0.1-SNAPSHOT.jar
```

## Approach and Design choices

* Domain Centric Design: Inspired by Uncle Bob Martin's Clean Architecture.

* What is Clean Architecture?
  - Just another design architecture to better manage layers and embrace SOLID principles.

* Domains defined:
  1. `User` : This domain deals with register-user, add-money, get-user 
  2. `CreditCard` : Validate and Issue credit-cards to users
  3. `Transaction` : Perform transactions per credit-card

* The datastore have been designed to mimic a relational datastore with indexing. The indexes are the primary keys.
Example `userId` in `User` domain

* Why Spring Boot?
  - Quick and easy to bootstrap new Java applications. Easy to test.

* Why Kotlin for testing?
  - To expedite writing tests since Kotlin is concise and allows functional approach of writing code,
  - Java requires a little more of boilerplate code for testing compared to Kotlin
  - And I have been writing Kotlin for over a year now so I am efficient and fast

* The API naming is resourceful per domain.

## Challenges

* The first challenge was to design data-store(s) to enable simple and efficient data storage and retrieval.
This could have been made possible with various collections(namely: Map, Set, List etc). I chose `HashMap`, `Set` and `Array`.

* The choice has been made purely on the basis of:
  a. KISS - keep is short and simple(simple and stupid)
  b. Facilitate easy write and read of data
  
* Try to keep objects/data immutable. Avoid in place update.
  - immutable objects are easy to construct and test
  - they are free of any side effects
  - and are thread safe

## What could be improved?
