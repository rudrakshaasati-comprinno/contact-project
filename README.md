Contact Record Management System

This project is a full-stack Contact Record Management System developed using Angular for the frontend and Java Spring Boot for the backend.
It has been implemented according to the technical assignment guidelines provided by Comprinno Technology.

The system enables users to securely register, authenticate, and manage their personal contact records with pagination support, strong validations, and interactive API documentation using Swagger.

✨ Core Functionalities
🔐 User Authentication & Authorization

New user registration

Secure user login

JWT-based authentication mechanism

Token-based access control for protected APIs

📞 Contact Operations

Create new contact entries

Fetch contact list with pagination

Modify existing contact details

Remove contacts

Contacts are isolated per user using JWT security

👤 Profile Management

View logged-in user profile

Update user name

Update account password

Email field is non-editable for integrity

✅ Validations Implemented

Mandatory field checks

Proper email format validation

Minimum password length enforcement

Phone number validation

📘 API Documentation

Swagger UI integrated with backend

All REST APIs are documented and accessible via browser

APIs can be tested directly without Postman

✔️ All assignment requirements have been successfully completed

🧰 Technology Stack
Frontend

Angular

Bootstrap

TypeScript

HTML & CSS

Backend

Java Spring Boot

Spring Security

Spring Data JPA

JWT Authentication

PostgreSQL

Swagger (OpenAPI 3.0)

📄 Swagger API Documentation

Swagger has been integrated for API visualization and testing.

Swagger UI URL:

http://localhost:8080/swagger-ui/index.html

🔍 Swagger Capabilities in This Project

Displays all backend REST endpoints

Shows request and response structures

Allows API testing directly from browser

Supports JWT Bearer Token authorization

🔑 Using JWT Token in Swagger

Call the login API:

/api/users/login


Copy the JWT token from the response

Click the Authorize button in Swagger UI

Enter:

Bearer <JWT_TOKEN>


Access all secured APIs seamlessly

🔐 Security Implementation Details

JWT token generated upon successful login

Token stored in browser localStorage

Angular HttpInterceptor automatically attaches token to requests

Backend extracts user identity from token

Stateless authentication architecture

All protected APIs secured via Spring Security

📑 Pagination Design

Server-side pagination using Spring Data JPA

Pageable interface implemented in backend

Frontend sends page index and page size

Pagination controls available in UI

🔗 REST API Overview
Authentication APIs
Method	Endpoint	Description
POST	/api/users/register	Register a new user
POST	/api/users/login	Authenticate user
Profile APIs
Method	Endpoint	Description
GET	/api/users/profile	Fetch user profile
PUT	/api/users/profile	Update profile details
Contact APIs
Method	Endpoint	Description
GET	/api/contacts?page=0&size=5	Fetch contact list
POST	/api/contacts	Add new contact
PUT	/api/contacts/{id}	Update contact
DELETE	/api/contacts/{id}	Delete contact
▶️ Running the Application
Backend Setup
cd backend
mvn spring-boot:run


Backend runs at:

http://localhost:8080


Swagger UI:

http://localhost:8080/swagger-ui/index.html

Frontend Setup
cd frontend
npm install
npm start
ng serve 


Frontend runs at:

http://localhost:4200

🧪 API Testing

APIs were tested using:

Swagger UI

Postman

Authorization header format:

Authorization: Bearer <JWT_TOKEN>

🖥️ User Interface Pages

Login page (with registration link)

Registration page

Dashboard with contacts and pagination

Add contact screen

Edit contact screen

Profile management page

Logout functionality
