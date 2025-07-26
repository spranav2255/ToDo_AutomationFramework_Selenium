# ToDo React App – Test Automation Framework
This repository contains an automated test suite for a React-based ToDo web application using:
Selenium WebDriver, Cucumber (BDD), TestNG, Extent Reports, Maven.
It follows a modular, scalable Page Object Model (POM) architecture with multi-threaded execution support.

| Tool               | Purpose                       |
| ------------------ | ----------------------------- |
| Selenium WebDriver | Browser automation            |
| Cucumber           | BDD test scenarios            |
| TestNG             | Test runner                   |
| Extent Reports     | HTML report generation        |
| Maven              | Build & dependency management |
| WebDriverManager   | Automatic driver setup        |

## Prerequisites

Java 11+
Maven
Chrome browser
Internet access to download dependencies

## Starting the ToDo App (REQUIRED Before Tests)

# START THE CLIENT (React)
cd ../client (../client/client)
npm install axios
npm start


# START THE SERVER (Node.js)
cd ../server
node server.js
