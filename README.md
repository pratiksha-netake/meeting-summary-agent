# Meeting Summary Agent

## Project Overview

The Meeting Summary Agent is a full-stack web application that helps users manage meeting transcripts and notes, generate summaries, track action items, and view meeting statistics.

## Features

### User Registration and Login
Users can create an account and securely log in to access the application.

### Upload Meeting Transcript
Users can upload meeting transcripts in TXT, DOCX, or PDF format for processing.

### Manual Meeting Notes
Users can enter meeting title and notes manually without uploading a transcript.

### Meeting Summary
The application generates a structured summary from the meeting content.

### Key Discussion Points
Important topics and discussion points from the meeting are identified and presented to the user.

### Action Items
The application identifies tasks that need to be completed after the meeting.

### Decisions
Important decisions made during the meeting are extracted and displayed.

### Assign Action Items
Action items can be assigned to team members and their status can be updated.

### Search and Filter
Users can search meeting records to quickly find specific meetings.

### Meeting History
Users can view previously created meetings and access their meeting information and summaries.

### Download Report
Users can download the meeting summary as a PDF report.

### Dashboard Statistics
The dashboard displays meeting-related statistics to provide an overview of the user's meetings.

### Responsive User Interface
The application provides a responsive interface that works across different screen sizes.

### RESTful APIs
The backend provides RESTful APIs built with Spring Boot for communication between the frontend and backend.

### MySQL Database
Meeting, user, summary, and action-item data are stored and managed using MySQL.

### Exception Handling and Validation
The application uses input validation and custom exception handling to manage invalid requests and application errors.



# Technology Stack


##Backend Technologies:

-Java
-Spring Boot
-Spring Web / Spring MVC
-Spring Data JPA
-Hibernate ORM
-Spring Security
-JWT
-Jakarta Validation
-REST API
-Exception Handling
-Dependency Injection


## Frontend Technologies
-React.js
-JavaScript
-React Router DOM
-Axios
-HTML
-CSS

##Authentication and Security
-Spring Security
-Authentication
-Password hashing/encoding
-JWT


## Database

-MySQL
-SQL
-MySQL Workbench
-MySQL Connector/J
-JDBC



##Validation & Exception Handling
-Jakarta Bean Validation
-@Valid
-Custom Exceptions
-ResourceNotFoundException
-InvalidFileException
-Exception Handling

##File & Report Processing
-Multipart File Upload
-Spring MultipartFile
-TXT/PDF/DOC/DOCX file handling
-PDF Report Generation
-PDF File Download

##Tools
-Git
-GitHub
-Maven
-Postman
-MySQL Workbench
-Eclipse
-VS Code



# Backend Setup Instructions

## Requirements

- Java JDK 17+
- Maven
- MySQL Server
- MySQL Workbench
- Git
- IDE — IntelliJ IDEA / VS Code / Eclipse

## Backend Setup

### 1. Create Spring Boot Project

Create a Spring Boot project using Spring Initializr.

Add the following dependencies:

- Spring Web
- Spring Data JPA
- Spring Security
- MySQL Driver
- Validation

### 2. Open the Project

Open the Spring Boot project in your preferred IDE.

### 3. Create the Database

Open MySQL Workbench and create the database.

### 4. Configure `application.properties`

Configure the following properties:

- Database URL
- Database username
- Database password
- `show-sql`

### 5. Create Packages and Files

Create the required backend packages and Java files.

### 6. Run the Backend

Run the backend using:

```bash
mvn spring-boot:run



# Frontend Setup Instructions

## Requirements

- Node.js
- npm
- Git
- IDE — IntelliJ IDEA / VS Code / Eclipse

## Frontend Setup

### 1. Create React Project

Create the React project using Vite.

### 2. Open the Project

Open the React project in your preferred IDE.

### 3. Install Dependencies

Install the required dependencies:

```bash
npm install

###5. Configure Backend API

###6. run the frontend using npm run dev

###7. Test the Application



## Database Configuration

### 1. Open MySQL Workbench

Open MySQL Workbench and connect to your MySQL Server.

### 2. Create Database

Create the database used by the application:

```sql
CREATE DATABASE meetingsummary_db;

###3 Configure application.properties
MySQL database configuration:url username 

###4. Start the Backend
###5 . Verify the Database


# API Endpoints

## Authentication

- `POST /auth/register` — Register a new user
- `POST /auth/login` — User login

## Meetings

- `POST /api/meetings` — Create a meeting
- `GET /api/meetings/{id}` — Get meeting by ID
- `POST /api/meetings/add` — Add meeting manually
- `POST /api/meetings/upload` — Upload meeting transcript
- `GET /api/meetings/history` — Get meeting history
- `GET /api/meetings/view/{id}` — View meeting details
- `GET /api/meetings/download/{id}` — Download meeting PDF report

## Action Items

- `POST /api/action-items/assign` — Assign an action item
- `PUT /api/action-items/{id}/status` — Update action item status

## Dashboard

- `GET /api/dashboard/statistics` — Get dashboard statistics

## Summaries

- `GET /api/summaries/test` — Test summary API
- `POST /api/summaries/generate/{meetingId}` — Generate meeting summary
- `GET /api/summaries/download/{meetingId}` — Download summary PDF


# Application Screenshots

## Register Page

![Register Page](screenshots/register.jpeg)

## Login Page

![Login Page](screenshots/login.jpeg)

## Home Page

![Home Page](screenshots/HomePage.jpeg)

## Transcript Upload

![Transcript Upload](screenshots/UploadFile.jpeg)

## Manual Add Meeting

![Manual Meeting](screenshots/ManualMeetings.jpeg)

## Meeting History

![Meeting History](screenshots/History.jpeg)

## Summary Generation

![Summary Generation](screenshots/Summary.jpeg)

## Search Meeting

![Search Meeting](screenshots/Search.jpeg)

## Assign Action Item

![Assign Action Item](screenshots/ActionItem.jpeg)

## Assign Item

![Assign Item](screenshots/AssignItem.jpeg)

## Update Action Item

![Update Action Item](screenshots/UpdateActionItem.jpeg)




































