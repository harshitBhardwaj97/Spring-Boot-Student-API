# Student Management Spring Boot Application

## Overview

This Spring Boot application provides a set of RESTful APIs to manage student records. It includes operations to create, read, update, and delete students, as well as to search students by name or email. The application also integrates OpenAPI 3 for API documentation and Actuator for application monitoring.

## Technology Stack

- **Java Version:** 17
- **Build Tool:** Maven 3.9.6
- **IDE:** Spring Tool Suite
- **Persistence:** MySQL 8
- **Spring Boot:** Uses Spring Boot to build the application
- **OpenAPI 3:** For API documentation
- **Actuator:** For application monitoring

## API Documentation

The API documentation is available through Swagger UI. After running the app locally, you can access it by navigating to:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Endpoints

#### Create a New Student

- **Endpoint:** `POST /students`
- **Description:** Creates a new student record.
- **Request Body:** `StudentRequestDTO` (JSON)
  - Includes student details to be created. (All fields are required)
  - Name: Must be between 3 and 32 characters.
  - Email: Must be a valid email address.
  - Age: Must be between 10 and 40 years old.
  - Gender: Must be one of "MALE", "FEMALE", or "OTHER".
- **Responses:**
  - `201 Created` - Student created successfully.
  - `400 Bad Request` - Incorrect or incomplete student details supplied.

#### Get List of All Students

- **Endpoint:** `GET /students`
- **Description:** Retrieves a list of all students.
- **Responses:**
  - `200 OK` - Successfully fetched the list of students.

#### Get Student by ID

- **Endpoint:** `GET /students/{id}`
- **Description:** Retrieves a student by their ID.
- **Path Variable:** `id` (Long) - The ID of the student.
- **Responses:**
  - `200 OK` - Student with the supplied ID found successfully.
  - `400 Bad Request` - Invalid student ID supplied.
  - `404 Not Found` - Student not found with the supplied ID.

#### Get Student by Name

- **Endpoint:** `GET /students/searchByName?name=`
- **Description:** Retrieves a student by their name.
- **Request Parameter:** `name` (String) - The name of the student.
- **Responses:**
  - `200 OK` - Student with the supplied name found successfully.
  - `400 Bad Request` - Name parameter not supplied.
  - `404 Not Found` - Student not found with the supplied name.

#### Get Student by Email

- **Endpoint:** `GET /students/searchByEmail?email=`
- **Description:** Retrieves a student by their email.
- **Request Parameter:** `email` (String) - The email of the student.
- **Responses:**
  - `200 OK` - Student with the supplied email found successfully.
  - `400 Bad Request` - Email parameter not supplied.
  - `404 Not Found` - Student not found with the supplied email.

#### Update a Student

- **Endpoint:** `PUT /students/{id}`
- **Description:** Updates an existing student's details.
- **Path Variable:** `id` (Long) - The ID of the student to be updated.
- **Request Body:** `UpdateStudentRequestDTO` (JSON)
  - Includes student details to be updated. (At least one field is required)
  - Name: Must be between 3 and 32 characters.
  - Email: Must be a valid email address.
  - Age: Must be between 10 and 40 years old.
  - Gender: Must be one of "MALE", "FEMALE", or "OTHER".
- **Responses:**
  - `200 OK` - Student updated successfully.
  - `400 Bad Request` - Incorrect or incomplete student details supplied.
  - `404 Not Found` - Student not found with the supplied ID.

#### Delete Student by ID

- **Endpoint:** `DELETE /students/{id}`
- **Description:** Deletes a student by their ID.
- **Path Variable:** `id` (Long) - The ID of the student to be deleted.
- **Responses:**
  - `204 No Content` - Student with the supplied ID deleted successfully.
  - `400 Bad Request` - Invalid student ID supplied.
  - `404 Not Found` - Student not found with the supplied ID.

## Actuator Endpoints

Actuator endpoints are available for monitoring and management. Some of the default endpoints include:

- `/actuator/health` - Health check of the application.
- `/actuator/metrics` - Application metrics.

## Setting up the Project

To setup this project locally, you need to set up the following prerequisites and configurations:

#### Prerequisites

1. **Java 17:**
   Ensure you have Java 17 installed. You can download it from [Oracle](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) or use a distribution like [AdoptOpenJDK](https://adoptium.net/).

2. **MySQL 8:**
   Download [MySQL Community Server](https://dev.mysql.com/downloads/mysql/) and [MySQL Workbench](https://dev.mysql.com/downloads/workbench/) and configure the root user.

#### MySQL Database Setup

1. **Create the Database:**
   Ensure you have a MySQL server running, and create a database named `students`, if it does not already exist. The application will automatically create the database if it does not exist, thanks to the `createDatabaseIfNotExist=true` parameter in the `application.yml` configuration.

2. **Configure MySQL:**
   Update the `application.yml` file with your MySQL credentials. Ensure that the database URL, username, and password are correctly set.

## Running the Project

Once you have done all the above steps, then clone the repo, cd into the folder, and run **./mvnw clean install** followed by **./mvnw spring-boot:run** in terminal. If you have followed all the steps correctly,  and made sure that all the dependencies are at place, then the application will be launched on **8080 port of your localhost**. Happy Hacking !
