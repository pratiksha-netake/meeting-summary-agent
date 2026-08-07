\# Meeting Summary Agent



\## Project Overview



Meeting Summary Agent is a web application that helps users manage meetings by uploading transcripts or adding notes manually. The application generates meeting summaries, extracts discussion points, decisions, and action items.



The system provides secure user authentication, meeting management, AI-based summary generation, and PDF report generation.



\---



\# Features



\## User Registration and Login



\- Implemented secure authentication using Spring Security and JWT.

\- User registration with email validation.

\- Password encryption using BCrypt Password Encoder.

\- Generates JWT token after successful login.

\- Protects APIs using JWT authentication.

\- Stores user information securely in MySQL database.



\---



\## Upload Meeting Transcript (TXT, DOCX, PDF)



\- Allows users to upload meeting transcript files.

\- Supports TXT, DOCX, and PDF formats.

\- Extracts text from uploaded files for processing.

\- Stores transcript information in the database.



\---



\## Enter Meeting Notes Manually



\- Allows users to create meetings without uploading files.

\- Users can add meeting title and notes manually.

\- Stores meeting details for summary generation.



\---



\## Generate Meeting Summary



\- Generates summarized meeting content.

\- Extracts important information from meeting discussions.

\- Provides quick understanding of meeting outcomes.



\---



\## Extract Key Discussion Points



\- Identifies important topics discussed during meetings.

\- Displays major discussion areas from meeting content.



\---



\## Extract Decisions Made During Meeting



\- Captures important decisions taken during meetings.

\- Maintains records of finalized outcomes.



\---



\## Identify Action Items



\- Extracts tasks and follow-up activities.

\- Helps users track pending work after meetings.



\---



\## Assign Action Items to Team Members



\- Allows assigning tasks to team members.

\- Helps manage responsibilities and task tracking.



\---



\## View Meeting Summary History



\- Displays previous uploaded transcripts and manually created meetings.

\- Allows searching and accessing previous meeting records.



\---



\## Download Summary Report



\- Generates PDF reports of meeting summaries.

\- Includes summary, discussion points, decisions, and action items.



\---



\## Dashboard with Meeting Statistics



\- Provides meeting activity overview.

\- Displays total meetings, transcripts, summaries, and action items count.



\---



\# Exception Handling and Validation



\- Implemented Global Exception Handling using `@ControllerAdvice`.

\- Created centralized error responses.

\- Used validation annotations:

&#x20; - `@NotBlank`

&#x20; - `@Email`

&#x20; - `@Valid`



Handled exceptions:



\- Resource Not Found Exception

\- Invalid Input Exception

\- Duplicate Data Exception

\- Runtime Exception



\---



\# RESTful APIs using Spring Boot



\- Developed REST APIs for frontend-backend communication.

\- Followed Controller, Service, Repository architecture.

\- Implemented CRUD operations for meetings, summaries, transcripts, and users.



\---



\# Technology Stack



\## Backend



\- Java

\- Spring Boot

\- Spring MVC

\- Spring Security

\- JWT Authentication

\- Spring Data JPA

\- Hibernate

\- REST APIs



\## Frontend



\- React.js

\- Axios

\- CSS



\## Database



\- MySQL



\## Tools



\- Git \& GitHub

\- Maven

\- Postman



\---



\# Backend Setup Instructions



\## Create Spring Boot Project



Create project using Spring Initializr.



Add dependencies:



\- Spring Web

\- Spring Security

\- Spring Data JPA

\- MySQL Driver

\- Validation



\---



\## Configure Database



Create MySQL database:





Update `application.properties`:



\# Backend Run Instructions



Build the project using Maven:



Run Spring Boot application:



Backend server will start on:API Endpoints

Authentication APIs

Method	Endpoint	Description

POST	/auth/register	Register a new user account

POST	/auth/login	Login user and generate JWT token

Meeting Transcript APIs

Method	Endpoint	Description

POST	/api/transcripts/upload	Upload meeting transcript files (TXT, DOCX, PDF)

Manual Meeting Notes APIs

Method	Endpoint	Description

POST	/api/notes/add	Add meeting details manually and create notes

GET	/api/notes/history	View manually created meeting notes history

GET	/api/notes/combined-history	View complete meeting history (manual notes + uploaded transcripts)

GET	/api/notes/search?keyword={keyword}	Search meeting records by keyword

GET	/api/notes/download/{meetingId}	Download meeting report as PDF

Meeting Summary APIs

Method	Endpoint	Description

GET	/api/summaries/test	Check summary API status

POST	/api/summaries/generate/{meetingId}	Generate AI meeting summary

GET	/api/summaries/download/{meetingId}	Download generated summary report

Action Items APIs

Method	Endpoint	Description

POST	/api/action-items/assign	Assign action items to team members

PUT	/api/action-items/{id}/status?status={status}	Update action item status

Dashboard APIs

Method	Endpoint	Description

GET	/api/dashboard/statistics	Get meeting statistics for dashboard

Security

All application APIs are protected using JWT Authentication.

User receives JWT token after successful login.

Token must be sent in request header:

Authorization: Bearer <JWT\_TOKEN>

API Flow

Register User

→ POST /auth/register

Login User and get JWT Token

→ POST /auth/login

Upload Transcript / Add Manual Notes

→ /api/transcripts/upload

→ /api/notes/add

Generate Summary

→ /api/summaries/generate/{meetingId}

View History

→ /api/notes/combined-history

Download Report

→ /api/notes/download/{meetingId} or /api/summaries/download/{meetingId}

Manage Action Items

→ /api/action-items/\*

&#x20;                                 Meeting Summary Agent





I Developed Meeting Summary Agent Web Application Including Functionality



User Registration and Login

Implemented secure authentication using Spring Security and JWT Authentication.

User registration with email and password validation.

Password encryption using BCrypt Password Encoder.

Login authentication using Spring Security.

Generated JWT token after successful login

Protected REST APIs using JWT token validation.

Stored user details securely in MySQL database.



MySQL Database Integration

Integrated MySQL database with Spring Boot using Spring Data JPA and Hibernate.

Stored user details,meeting notes,transcripts ,actionItems and summaries in database tables.

Used JPA repositories for database operation.

configured database connections using spring Boot properties.

Managed entity relationships.



Upload Meeting Transcript (TXT, DOCX, PDF)

Allows users to upload meeting transcript files in TXT, DOCX, and PDF formats.

Extracts text content from uploaded files for further processing.

Stores uploaded transcript details for future reference.



Enter Meeting Notes Manually

Allows users to add meeting details and notes manually without uploading a file.

Stores manually created meeting notes in the database.

Maintains meeting records for summary generation and future reference.



Generate Meeting Summary

Generates concise summaries from meeting transcripts and notes.

Extracts important information and key points from meeting content.

Helps users quickly understand meeting discussions and outcomes.



Extract Key Discussion Points

Identifies and displays important topics discussed during the meeting.

Helps users quickly review major discussion areas.

Organizes important points from transcripts and meeting notes.



Identify Action Items

Extracts tasks and follow-up activities from meeting discussions.

Helps identify pending work items and responsibilities.

Allows users to track important actions after the meeting.



Extract Decisions Made During the Meeting

Identifies important decisions finalized during the meeting.

Captures key outcomes and agreements from discussions.

Helps users maintain a clear record of meeting decisions.



Assign Action Items to Team Members

Allows users to assign extracted action items to team members.



Helps manage task ownership and responsibilities.

Tracks assigned tasks for better meeting follow-up.



Download Summary Report

Allows users to download generated meeting summaries as PDF reports.



Provides a structured report containing summary details, discussion points, decisions, and action items.

Helps users save and share meeting outcomes for future reference.



View Meeting Summary History



Allows users to view previously created meeting summaries and notes.

Maintains a history of uploaded transcripts and manually created meetings.

Helps users search and access past meeting records easily.

Dashboard with Meeting Statistics



Provides a centralized dashboard to view meeting-related information.

Displays total meetings, uploaded transcripts, generated summaries, and action items statistics.

Helps users quickly track meeting activities and summary progress.



Proper Exception Handling and Input Valid

Implemented Global Exception Handling using @ControllerAdvice to handle application errors centrally.

Created custom error response for different exceptions.

Used validation annotation like @NotBlank ,@Email for request validation.

Handled exceptions such as :

Resource Not Found Exception

Invalid Input

Duplicate Data Exception etc.



RESTful APIs using Spring Boot



Developed RESTful APIs using Spring Boot for communication between frontend and backend.

Implemented CRUD operations for meetings, summaries, transcripts, and user management.

Used Controller, Service, Repository layers for clean API architecture.



MySQL Database Integration



Integrated MySQL database with Spring Boot using Spring Data JPA and Hibernate.

Stored user details, meeting notes, transcripts, summaries, and action items in relational tables.

Used JPA repositories for database operations and data management.



Technology Stack



Backend



Java

Spring Boot

Spring Security

JWT Authentication

Spring Data JPA / Hibernate

RESTful APIs

Spring MVC



Frontend



React.js

Axios

CSS



Database



MySQL



Development Tools



Git \& GitHub

Maven

Postman



setup instructions

Backend Setup Instructions (Spring Boot)

Create a Spring Boot application using Spring Initializr.

Add required dependencies:

Spring Web

Spring Security

Spring Data JPA

MySQL Driver

Validation

JWT Authentication



Configure Database

Create MySQL database.

Configure database connection in application.properties.

spring.datasource.url=jdbc:mysql://localhost:3306/meeting\_summary

spring.datasource.username=root

spring.datasource.password=your\_password



spring.jpa.hibernate.ddl-auto=update



Version Control Setup (Git \& GitHub)



Initialize Git repository



git init



Check repository status



git status



Add all backend files



git add .



Commit changes



git commit -m "Initial Spring Boot backend setup"



Create main branch



git branch -M main



Add GitHub repository URL



git remote add origin



Verify remote repository



git remote -v



Push backend code to GitHub



git push -u origin main



Database Configuration (MySQL)



Integrated MySQL database with Spring Boot using Spring Data JPA and Hibernate.

Created database tables automatically using JPA entity mapping.

Configured database connection details in application.properties.

spring.datasource.url=jdbc:mysql://localhost:3306/meeting\_summary

spring.datasource.username=root

spring.datasource.password=your\_password



spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

spring.jpa.properties.hibernate.format\_sql=true



Authentication APIs

Method Endpoint Description

POST /auth/register Register a new user account

POST /auth/login Login user and generate JWT token

Meeting Transcript APIs

Method Endpoint Description

POST /api/transcripts/upload Upload meeting transcript files (TXT, DOCX, PDF)

Manual Meeting Notes APIs

Method Endpoint Description

POST /api/notes/add Add meeting details manually and create notes

GET /api/notes/history View manually created meeting notes history

GET /api/notes/combined-history View complete meeting history (manual notes + uploaded transcripts)

GET /api/notes/search?keyword={keyword} Search meeting records by keyword

GET /api/notes/download/{meetingId} Download meeting report as PDF

Meeting Summary APIs

Method Endpoint Description

GET /api/summaries/test Check summary API status

POST /api/summaries/generate/{meetingId} Generate AI meeting summary

GET /api/summaries/download/{meetingId} Download generated summary report

Action Items APIs

Method Endpoint Description

POST /api/action-items/assign Assign action items to team members

PUT /api/action-items/{id}/status?status={status} Update action item status

Dashboard APIs

Method Endpoint Description

GET /api/dashboard/statistics Get meeting statistics for dashboard

Security

All application APIs are protected using JWT Authentication.

User receives JWT token after successful login.

Token must be sent in request header:

Authorization: Bearer <JWT\_TOKEN>

API Flow

Register User

→ POST /auth/register

Login User and get JWT Token

→ POST /auth/login

Upload Transcript / Add Manual Notes

→ /api/transcripts/upload

→ /api/notes/add

Generate Summary

→ /api/summaries/generate/{meetingId}

View History

→ /api/notes/combined-history

Download Report

→ /api/notes/download/{meetingId} or /api/summaries/download/{meetingId}

Manage Action Items

→ /api/action-items/\* give me simple and look like manmade file stay as a simple file dont change just add importatnt point or removed unnecessary points



I cleaned your README content to make it look more like a student/developer-created project README. I removed repeated sections, unnecessary explanations, and kept only important points.



\# Meeting Summary Agent



\## Project Overview



Meeting Summary Agent is a web application that helps users manage meetings by uploading transcripts or adding notes manually. The application generates meeting summaries, extracts discussion points, decisions, and action items.



The system provides secure user authentication, meeting management, AI-based summary generation, and PDF report generation.



\---



\# Features



\## User Registration and Login



\- Implemented secure authentication using Spring Security and JWT.

\- User registration with email validation.

\- Password encryption using BCrypt Password Encoder.

\- Generates JWT token after successful login.

\- Protects APIs using JWT authentication.

\- Stores user information securely in MySQL database.



\---



\## Upload Meeting Transcript (TXT, DOCX, PDF)



\- Allows users to upload meeting transcript files.

\- Supports TXT, DOCX, and PDF formats.

\- Extracts text from uploaded files for processing.

\- Stores transcript information in the database.



\---



\## Enter Meeting Notes Manually



\- Allows users to create meetings without uploading files.

\- Users can add meeting title and notes manually.

\- Stores meeting details for summary generation.



\---



\## Generate Meeting Summary



\- Generates summarized meeting content.

\- Extracts important information from meeting discussions.

\- Provides quick understanding of meeting outcomes.



\---



\## Extract Key Discussion Points



\- Identifies important topics discussed during meetings.

\- Displays major discussion areas from meeting content.



\---



\## Extract Decisions Made During Meeting



\- Captures important decisions taken during meetings.

\- Maintains records of finalized outcomes.



\---



\## Identify Action Items



\- Extracts tasks and follow-up activities.

\- Helps users track pending work after meetings.



\---



\## Assign Action Items to Team Members



\- Allows assigning tasks to team members.

\- Helps manage responsibilities and task tracking.



\---



\## View Meeting Summary History



\- Displays previous uploaded transcripts and manually created meetings.

\- Allows searching and accessing previous meeting records.



\---



\## Download Summary Report



\- Generates PDF reports of meeting summaries.

\- Includes summary, discussion points, decisions, and action items.



\---



\## Dashboard with Meeting Statistics



\- Provides meeting activity overview.

\- Displays total meetings, transcripts, summaries, and action items count.



\---



\# Exception Handling and Validation



\- Implemented Global Exception Handling using `@ControllerAdvice`.

\- Created centralized error responses.

\- Used validation annotations:

&#x20; - `@NotBlank`

&#x20; - `@Email`

&#x20; - `@Valid`



Handled exceptions:



\- Resource Not Found Exception

\- Invalid Input Exception

\- Duplicate Data Exception

\- Runtime Exception



\---



\# RESTful APIs using Spring Boot



\- Developed REST APIs for frontend-backend communication.

\- Followed Controller, Service, Repository architecture.

\- Implemented CRUD operations for meetings, summaries, transcripts, and users.



\---



\# Technology Stack



\## Backend



\- Java

\- Spring Boot

\- Spring MVC

\- Spring Security

\- JWT Authentication

\- Spring Data JPA

\- Hibernate

\- REST APIs



\## Frontend



\- React.js

\- Axios

\- CSS



\## Database



\- MySQL



\## Tools



\- Git \& GitHub

\- Maven

\- Postman



\---



\# Backend Setup Instructions



\## Create Spring Boot Project



Create project using Spring Initializr.



Add dependencies:



\- Spring Web

\- Spring Security

\- Spring Data JPA

\- MySQL Driver

\- Validation



\---



\## Configure Database



Create MySQL database:





meeting\_summary





Update `application.properties`:





spring.datasource.url=jdbc:mysql://localhost:3306/meeting\_summary

spring.datasource.username=root

spring.datasource.password=your\_password



spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true





\---



\# Git \& GitHub Setup



Initialize repository:





git init





Check files:





git status





Add files:





git add .





Commit changes:





git commit -m "Initial backend setup"





Create main branch:





git branch -M main





Add remote repository:





git remote add origin <github\_repository\_url>





Check remote:





git remote -v





Push project:





git push -u origin main





\---



\# Database Configuration



\- Integrated MySQL using Spring Data JPA and Hibernate.

\- Entity classes automatically create database tables.

\- Used JPA repositories for database operations.



Database properties:





spring.datasource.url=jdbc:mysql://localhost:3306/meeting\_summary

spring.datasource.username=root

spring.datasource.password=your\_password



spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true





\---



\# API Endpoints



\## Authentication APIs



| Method | Endpoint | Description |

|---|---|---|

| POST | /auth/register | Register new user |

| POST | /auth/login | Login and generate JWT token |



\---



\## Transcript APIs



| Method | Endpoint | Description |

|---|---|---|

| POST | /api/transcripts/upload | Upload TXT, DOCX, PDF transcript |



\---



\## Manual Meeting Notes APIs



| Method | Endpoint | Description |

|---|---|---|

| POST | /api/notes/add | Create manual meeting notes |

| GET | /api/notes/history | View manual meeting history |

| GET | /api/notes/combined-history | View all meetings |

| GET | /api/notes/search?keyword=value | Search meetings |

| GET | /api/notes/download/{id} | Download meeting report |



\---



\## Summary APIs



| Method | Endpoint | Description |

|---|---|---|

| POST | /api/summaries/generate/{id} | Generate meeting summary |

| GET | /api/summaries/download/{id} | Download summary PDF |



\---



\## Action Item APIs



| Method | Endpoint | Description |

|---|---|---|

| POST | /api/action-items/assign | Assign action item |

| PUT | /api/action-items/{id}/status | Update action status |



\---



\## Dashboard APIs



| Method | Endpoint | Description |

|---|---|---|

| GET | /api/dashboard/statistics | Get meeting statistics |



\---



\# Security



All protected APIs require JWT authentication.



After login, user receives JWT token.



Send token in request header:





Authorization: Bearer <JWT\_TOKEN>





\---



\# Application Flow





Register User

|

↓

POST /auth/register



Login User

|

↓

POST /auth/login



Receive JWT Token



&#x20; |

&#x20; ↓



Upload Transcript

OR

Add Manual Meeting Notes



&#x20; |

&#x20; ↓



Generate Summary



&#x20; |

&#x20; ↓



View Meeting History



&#x20; |

&#x20; ↓



Download PDF Report



&#x20; |

&#x20; ↓



Manage Action Items



This version is shorter, cleaner, and suitable for a GitHub submission README.



Meeting Summary Agent

Project Overview



Meeting Summary Agent is a web application that helps users manage meetings by uploading transcripts or adding notes manually. The application generates meeting summaries, extracts discussion points, decisions, and action items.



The system provides secure user authentication, meeting management, AI-based summary generation, and PDF report generation.



Features

User Registration and Login

Implemented secure authentication using Spring Security and JWT.

User registration with email validation.

Password encryption using BCrypt Password Encoder.

Generates JWT token after successful login.

Protects APIs using JWT authentication.

Stores user information securely in MySQL database.

Upload Meeting Transcript (TXT, DOCX, PDF)

Allows users to upload meeting transcript files.

Supports TXT, DOCX, and PDF formats.

Extracts text from uploaded files for processing.

Stores transcript information in the database.

Enter Meeting Notes Manually

Allows users to create meetings without uploading files.

Users can add meeting title and notes manually.

Stores meeting details for summary generation.

Generate Meeting Summary

Generates summarized meeting content.

Extracts important information from meeting discussions.

Provides quick understanding of meeting outcomes.

Extract Key Discussion Points

Identifies important topics discussed during meetings.

Displays major discussion areas from meeting content.

Extract Decisions Made During Meeting

Captures important decisions taken during meetings.

Maintains records of finalized outcomes.

Identify Action Items

Extracts tasks and follow-up activities.

Helps users track pending work after meetings.

Assign Action Items to Team Members

Allows assigning tasks to team members.

Helps manage responsibilities and task tracking.

View Meeting Summary History

Displays previous uploaded transcripts and manually created meetings.

Allows searching and accessing previous meeting records.

Download Summary Report

Generates PDF reports of meeting summaries.

Includes summary, discussion points, decisions, and action items.

Dashboard with Meeting Statistics

Provides meeting activity overview.

Displays total meetings, transcripts, summaries, and action items count.

Exception Handling and Validation

Implemented Global Exception Handling using @ControllerAdvice.

Created centralized error responses.

Used validation annotations:

@NotBlank

@Email

@Valid



Handled exceptions:



Resource Not Found Exception

Invalid Input Exception

Duplicate Data Exception

Runtime Exception

RESTful APIs using Spring Boot

Developed REST APIs for frontend-backend communication.

Followed Controller, Service, Repository architecture.

Implemented CRUD operations for meetings, summaries, transcripts, and users.

Technology Stack

Backend

Java

Spring Boot

Spring MVC

Spring Security

JWT Authentication

Spring Data JPA

Hibernate

REST APIs

Frontend

React.js

Axios

CSS

Database

MySQL

Tools

Git \& GitHub

Maven

Postman

Backend Setup Instructions

Create Spring Boot Project



Create project using Spring Initializr.



Add dependencies:



Spring Web

Spring Security

Spring Data JPA

MySQL Driver

Validation

Configure Database



Create MySQL database: i have added this give me remaning



Continue your README from Configure Database section:



\## Configure Database



Create MySQL database:





meeting\_summary





Update `application.properties`:





spring.datasource.url=jdbc:mysql://localhost:3306/meeting\_summary

spring.datasource.username=root

spring.datasource.password=your\_password



spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

spring.jpa.properties.hibernate.format\_sql=true





\---



\# Backend Run Instructions



Navigate to backend project folder:





cd meeting-summary-agent





Build the project using Maven:





mvn clean install





Run Spring Boot application:





mvn spring-boot:run





Backend server will start on:





http://localhost:8080





\# Git \& GitHub Setup



Initialize Git repository:



Check repository status:



Add project files:



Create first commit:



Create main branch:



Add GitHub repository:



Verify remote repository:



Push code to GitHub:





\---



\# Database Configuration



\- Integrated MySQL database using Spring Data JPA and Hibernate.

\- Database tables are created automatically using JPA entity mapping.

\- Used Repository interfaces for database operations.

\- Managed relationships between entities.



\---



\# API Endpoints



\## Authentication APIs



| Method | Endpoint | Description |

|--------|----------|-------------|

| POST | `/auth/register` | Register new user |

| POST | `/auth/login` | Login user and generate JWT token |



\---



\## Transcript APIs



| Method | Endpoint | Description |

|--------|----------|-------------|

| POST | `/api/transcripts/upload` | Upload TXT, DOCX, PDF transcript |



\---



\## Manual Meeting Notes APIs



| Method | Endpoint | Description |

|--------|----------|-------------|

| POST | `/api/notes/add` | Create meeting notes manually |

| GET | `/api/notes/history` | View manual meeting history |

| GET | `/api/notes/combined-history` | View uploaded and manual meeting history |

| GET | `/api/notes/search?keyword=value` | Search meetings |

| GET | `/api/notes/download/{meetingId}` | Download meeting PDF report |



\---



\## Meeting Summary APIs



| Method | Endpoint | Description |

|--------|----------|-------------|

| GET | `/api/summaries/test` | Check summary API status |

| POST | `/api/summaries/generate/{meetingId}` | Generate meeting summary |

| GET | `/api/summaries/download/{meetingId}` | Download summary PDF |



\---



\## Action Item APIs



| Method | Endpoint | Description |

|--------|----------|-------------|

| POST | `/api/action-items/assign` | Assign action item |

| PUT | `/api/action-items/{id}/status?status=value` | Update action item status |



\---



\## Dashboard APIs



| Method | Endpoint | Description |

|--------|----------|-------------|

| GET | `/api/dashboard/statistics` | Get meeting statistics |



\---



\# Security



\- Implemented JWT based authentication.

\- All protected APIs require JWT token.

\- Token is generated after successful login.

\- JWT token must be sent in request header.



\## Application Screenshots



\### Register Page



!\[Register Page](https://raw.githubusercontent.com/pratiksha-netake/meeting-summary-agent/main/screenshots/register.jpeg)





\### Login Page



!\[Login Page](https://raw.githubusercontent.com/pratiksha-netake/meeting-summary-agent/main/screenshots/login.jpeg)





\### HomePage



!\[HomePage](https://raw.githubusercontent.com/pratiksha-netake/meeting-summary-agent/main/screenshots/HomePage.jpeg)





\### Transcript Upload



!\[Transcript Upload](https://raw.githubusercontent.com/pratiksha-netake/meeting-summary-agent/main/screenshots/UploadFile.jpeg)





\### Manual Add Meetings



!\[Manual Meetings](https://raw.githubusercontent.com/pratiksha-netake/meeting-summary-agent/main/screenshots/ManualMeetings.jpeg)





\### History Meeting



!\[History Meeting](https://raw.githubusercontent.com/pratiksha-netake/meeting-summary-agent/main/screenshots/History.jpeg)





\### Summary Generation



!\[Summary Generation](https://raw.githubusercontent.com/pratiksha-netake/meeting-summary-agent/main/screenshots/Summary.jpeg)





\### Search Meeting



!\[Search Meeting](https://raw.githubusercontent.com/pratiksha-netake/meeting-summary-agent/main/screenshots/Search.jpeg)





\### Assign Action Item



!\[Assign Action Item](https://raw.githubusercontent.com/pratiksha-netake/meeting-summary-agent/main/screenshots/ActionItem.jpeg)





\### Assign Item



!\[Assign Item](https://raw.githubusercontent.com/pratiksha-netake/meeting-summary-agent/main/screenshots/AssignItem.jpeg)





\### Update Action Item



!\[Update Action Item](https://raw.githubusercontent.com/pratiksha-netake/meeting-summary-agent/main/screenshots/UpdateActionItem.jpeg)





































