# Bug Reporting System

## Problem Statement

Develop a Bug Reporting System that allows users to report software bugs, track their status, and add comments. The system should include user authentication, enabling both users and administrators to manage bug reports efficiently. The backend will be developed using Spring Boot, and the frontend will be designed for Android.

## Architecture
The Bug Reporting System will follow a layered architecture, including:

**1. Presentation Layer (Android App):**
- User Authentication (Login, Registration)
- Bug Report Submission
- View Bug Report List
- View Bug Report Details
- Add Comments to Bug Reports

**2. Application Layer (Spring Boot):**
- Controllers for handling HTTP requests.
- Services for business logic.
- Repositories for data access.
- User Authentication
- Bug Report Management (Create, Read, Update, Delete)
- Commenting System

**3. Data Layer (MySQL Database):**
- Storing user information, bug reports, and comments.

## Class Diagram
The class diagram represents the structure of the backend system.

![Class Diagram](https://github.com/SaileshLimbu/cs489-final-project/blob/master/screenshot/classdiagram.png?raw=true "Class Diagram")
## ER Diagram
The Entity-Relationship diagram represents the database schema.

![ER Diagram](https://github.com/SaileshLimbu/cs489-final-project/blob/master/screenshot/erdiagram.png?raw=true "ER Diagram")
