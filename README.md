# 📚 Library Project

<a href="https://github.com/NNG-2/library/actions/workflows/maven.yml">
<img src="https://github.com/NNG-2/library/actions/workflows/maven.yml/badge.svg" alt="build"> 
</a>

## About
System for managing libraries/readers/books etc.  

## Prerequisites
 - `java` (version 17)
 - `docker`
 - `docker-compose`

## How to run
```shell
$ mvn compile package
$ docker-compose up -d
```

## Project requirements
 - Develop a system for library subscription
 - Track the financial performance of the library
 - Each book should have a title, author, and genre
 - Books have a deposit value and rental cost
 - The rental cost depends on the book and the rental period
 - Readers should be registered with personal data (name, address, phone number, category)
 - Readers can apply to the library multiple times
 - Record reader requests and book issues (date of issue and expected return date)
 - Implement a system of fines for book damage
 - Implement a system of discounts for certain categories of readers
 - Generate a report on the available book collection
 - Generate a report on issued books, including overdue books
 - Generate a report on the financial status of the subscription.
