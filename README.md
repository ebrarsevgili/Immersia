# 🎬 Immersia

### Full-Stack Movie Discovery Platform

Immersia is a full-stack movie discovery platform built with **React, Java Spring Boot, and PostgreSQL**.

The application allows users to discover movies, search for titles, view detailed movie information, manage favorites and watchlists, and securely access their personal account through JWT-based authentication.

---

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Quick Start](#-quick-start)
- [Environment Variables](#-environment-variables)
- [Repository Structure](#-repository-structure)
- [Architecture Overview](#-architecture-overview)
- [Authentication](#-authentication)
- [TMDB Integration](#-tmdb-integration)
- [API Endpoints](#-api-endpoints)
- [Database](#-database)
- [Testing](#-testing)
- [Screenshots](#-screenshots)
- [Future Improvements](#-future-improvements)
- [Author](#-author)

---

## ✨ Features

### Movie Discovery

- Browse movies retrieved from TMDB
- Discover popular movies
- Search for movies
- View detailed movie information
- View similar movies
- Display movie ratings, release years, posters, and descriptions

### User Features

- User registration
- User login
- JWT-based authentication
- Personal account page
- Favorite movie management
- Watchlist management
- Add and remove movies from favorites
- Add and remove movies from watchlist

### Backend

- RESTful API
- JWT authentication and authorization
- PostgreSQL database integration
- JPA / Hibernate persistence
- TMDB API integration
- Service and repository layer architecture

### Testing

- Unit tests
- Controller tests
- Service tests
- JWT tests
- Security integration tests
- MockMvc-based API testing

---

## 🛠️ Tech Stack

### Frontend

- React
- JavaScript
- Vite
- React Router
- Lucide React
- CSS

### Backend

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT
- REST API

### Database

- PostgreSQL

### External API

- TMDB API

### Testing

- JUnit
- Mockito
- MockMvc
- Spring Boot Test

### Development Tools

- Git
- GitHub
- IntelliJ IDEA
- Visual Studio Code

---

## 🚀 Quick Start

### Prerequisites

Before running Immersia, make sure you have:

- Java 17+
- Node.js
- npm
- PostgreSQL
- A TMDB API key

### 1. Clone the Repository

```bash
git clone https://github.com/ebrarsevgili/Immersia.git
cd Immersia
2. Backend Setup

Navigate to the backend directory:

cd backend

Configure the required environment variables before starting the application.

Then run the backend:

mvnw.cmd spring-boot:run

The backend will run on:

http://localhost:8080
3. Frontend Setup

Open a new terminal and navigate to the frontend directory:

cd frontend

Install the dependencies:

npm install

Start the development server:

npm run dev

The frontend will normally run on:

http://localhost:5173
🔐 Environment Variables

Immersia uses environment variables for sensitive configuration values.

The backend requires:

DB_PASSWORD=your_database_password
TMDB_API_KEY=your_tmdb_api_key
JWT_SECRET=your_jwt_secret
Environment Variable Descriptions
Variable	Description
DB_PASSWORD	PostgreSQL database password
TMDB_API_KEY	API key used to access TMDB
JWT_SECRET	Secret used to sign JWT tokens

Never commit real API keys, passwords, or JWT secrets to GitHub.

🏗️ Repository Structure
Immersia/
│
├── frontend/
│   ├── public/
│   ├── src/
│   │   ├── components/
│   │   │   ├── HeroSlider.jsx
│   │   │   ├── MovieCard.jsx
│   │   │   └── Navbar.jsx
│   │   │
│   │   ├── pages/
│   │   │   ├── Account.jsx
│   │   │   ├── Favorites.jsx
│   │   │   ├── Home.jsx
│   │   │   ├── Login.jsx
│   │   │   ├── MovieDetail.jsx
│   │   │   ├── Movies.jsx
│   │   │   ├── Register.jsx
│   │   │   └── Watchlist.jsx
│   │   │
│   │   ├── App.jsx
│   │   └── index.css
│   │
│   ├── package.json
│   └── vite.config.js
│
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/immersia/immersiabackend/
│   │   │   │       ├── config/
│   │   │   │       ├── controller/
│   │   │   │       ├── dto/
│   │   │   │       ├── exception/
│   │   │   │       ├── model/
│   │   │   │       ├── repository/
│   │   │   │       └── service/
│   │   │   │
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   │
│   │   └── test/
│   │       └── java/
│   │
│   ├── pom.xml
│   └── mvnw.cmd
│
└── README.md
🔄 Architecture Overview

Immersia follows a client-server architecture.

┌───────────────────────┐
│    React Frontend     │
│        Vite           │
└───────────┬───────────┘
            │
            │ REST API
            ▼
┌───────────────────────┐
│    Spring Boot API    │
│                       │
│  Controllers          │
│  Services             │
│  Repositories         │
│  Spring Security      │
│  JWT Authentication   │
└───────┬─────────┬─────┘
        │         │
        │         │
        ▼         ▼
┌────────────┐  ┌────────────┐
│ PostgreSQL │  │  TMDB API  │
└────────────┘  └────────────┘
Frontend

The React frontend is responsible for:

User interface
Routing
Movie browsing
Movie details
Authentication state
Favorites and watchlist interactions
Backend

The Spring Boot backend is responsible for:

REST API endpoints
Authentication and authorization
JWT validation
Database operations
TMDB API communication
Business logic
Database

PostgreSQL stores application-specific user data such as:

Users
Favorites
Watchlists

Movie information is retrieved from TMDB.

🔐 Authentication

Immersia uses JWT-based authentication with Spring Security.

The authentication flow is:

User
  │
  ▼
Register / Login
  │
  ▼
Spring Boot
  │
  ▼
JWT Token
  │
  ▼
Frontend stores token
  │
  ▼
Authenticated API Requests
  │
  ▼
JWT Authentication Filter
  │
  ▼
Protected Resources

Protected resources include:

Favorites
Watchlist
Account information
Authenticated endpoints
🎥 TMDB Integration

Immersia uses the TMDB API as its movie data source.

The frontend does not communicate directly with TMDB.

Instead:

React
  │
  ▼
Spring Boot
  │
  ▼
TMDB API
  │
  ▼
Spring Boot
  │
  ▼
React

The backend retrieves movie information from TMDB and exposes application-specific REST endpoints to the frontend.

This keeps the TMDB API integration inside the backend.

📡 API Endpoints
Authentication
Method	Endpoint	Description
POST	/api/auth/register	Register a new user
POST	/api/auth/login	Authenticate a user
GET	/api/auth/me	Get the current user
GET	/api/auth/test	Test JWT authentication
Movies
Method	Endpoint	Description
GET	/api/movies	Get paginated movies
GET	/api/movies/{id}	Get movie details
GET	/api/movies/{id}/similar	Get similar movies
GET	/api/movies/search	Search for movies
Favorites
Method	Endpoint	Description
GET	/api/favorites	Get the user's favorite movies
POST	/api/favorites?movieId={movieId}	Add a movie to favorites
DELETE	/api/favorites/{id}	Remove a movie from favorites
Watchlist
Method	Endpoint	Description
GET	/api/watchlist	Get the user's watchlist
POST	/api/watchlist?movieId={movieId}	Add a movie to the watchlist
DELETE	/api/watchlist/{id}	Remove a movie from the watchlist
🗄️ Database

Immersia uses PostgreSQL with Spring Data JPA and Hibernate.

Main Application Data
User
 │
 ├── Favorites
 │      └── Movie ID
 │
 └── Watchlist
        └── Movie ID

The database stores application-specific user data.

Movie metadata such as titles, posters, descriptions, ratings, and similar movies is retrieved from TMDB.

🧪 Testing

The backend contains unit, controller, service, and integration tests.

Testing technologies:

JUnit
Mockito
MockMvc
Spring Boot Test

Tests cover areas including:

User registration
User login
JWT generation
JWT authentication
Protected endpoints
Favorite operations
Watchlist operations
Movie endpoints
Service layer logic
Controller layer behavior
Run Tests

From the backend directory:

mvnw.cmd test
📸 Screenshots

Screenshots of the application will be added here.

Home

Add screenshot here.

Movie Details

Add screenshot here.

Login / Register

Add screenshot here.

Account

Add screenshot here.

🔮 Future Improvements

Planned improvements include:

Docker and Docker Compose
Improved movie filtering
Advanced search functionality
Additional user account settings
Production deployment
Improved UI/UX
Additional automated tests
👩‍💻 Author

Ebrar Sevgili

Software Engineering Student

GitHub

📄 License

This project is created for educational and portfolio purposes.
```
