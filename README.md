# 🎬 Immersia

### Full-Stack Movie Discovery Platform

Immersia is a full-stack movie discovery platform built with React, Java
Spring Boot, and PostgreSQL.

The application allows users to discover movies, search for titles, view
detailed movie information, manage favorites and watchlists, and
securely access their personal account through JWT-based authentication.

------------------------------------------------------------------------

## 📋 Table of Contents

-   [Features](#features)
-   [Tech Stack](#tech-stack)
-   [Quick Start](#quick-start)
-   [Environment Variables](#environment-variables)
-   [Repository Structure](#repository-structure)
-   [Architecture Overview](#architecture-overview)
-   [Authentication](#authentication)
-   [TMDB Integration](#tmdb-integration)
-   [API Endpoints](#api-endpoints)
-   [Database](#database)
-   [Testing](#testing)
-   [Screenshots](#screenshots)
-   [Future Improvements](#future-improvements)
-   [Author](#author)
-   [License](#license)

------------------------------------------------------------------------

## ✨ Features

### Movie Discovery

-   Browse movies retrieved from TMDB
-   Discover popular movies
-   Search for movies
-   View detailed movie information
-   View similar movies
-   Display movie ratings, release years, posters, and descriptions

### User Features

-   User registration
-   User login
-   JWT-based authentication
-   Personal account page
-   Favorite movie management
-   Watchlist management
-   Add and remove movies from favorites
-   Add and remove movies from watchlist

### Backend

-   RESTful API
-   JWT authentication and authorization
-   PostgreSQL database integration
-   JPA / Hibernate persistence
-   TMDB API integration
-   Service and repository layer architecture

------------------------------------------------------------------------

## 🛠️ Tech Stack

### Frontend

-   React
-   JavaScript
-   Vite
-   React Router
-   Lucide React
-   CSS

### Backend

-   Java
-   Spring Boot
-   Spring Security
-   Spring Data JPA
-   Hibernate
-   JWT
-   REST API

### Database

-   PostgreSQL

### External API

-   TMDB API

### Testing

-   JUnit
-   Mockito
-   MockMvc
-   Spring Boot Test

### Development Tools

-   Git
-   GitHub
-   IntelliJ IDEA
-   Visual Studio Code

------------------------------------------------------------------------

## 🚀 Quick Start

### Prerequisites

Before running Immersia, make sure you have:

-   Java 17+
-   Node.js
-   npm
-   PostgreSQL
-   A TMDB API key

### Backend

Navigate to the backend directory:

``` bash
cd backend
```

Make sure PostgreSQL is running and configure the required environment
variables.

Start the Spring Boot application:

``` bash
mvnw.cmd spring-boot:run
```

The backend runs on:

``` text
http://localhost:8080
```

### Frontend

Open a new terminal and navigate to the frontend directory:

``` bash
cd frontend
```

Install dependencies:

``` bash
npm install
```

Start the development server:

``` bash
npm run dev
```

The frontend normally runs on:

``` text
http://localhost:5173
```

------------------------------------------------------------------------

## 🔐 Environment Variables

The backend uses environment variables for sensitive configuration
values.

Required variables:

``` text
DB_PASSWORD=your_database_password
TMDB_API_KEY=your_tmdb_api_key
JWT_SECRET=your_jwt_secret
```

  Variable         Description
  ---------------- --------------------------------
  `DB_PASSWORD`    PostgreSQL database password
  `TMDB_API_KEY`   API key used to access TMDB
  `JWT_SECRET`     Secret used to sign JWT tokens

Never commit real API keys, passwords, or JWT secrets to GitHub.

------------------------------------------------------------------------

## 🏗️ Repository Structure

``` text
Immersia/
├── frontend/
│   ├── public/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── App.jsx
│   │   └── index.css
│   ├── package.json
│   └── vite.config.js
│
├── backend/
│   ├── src/
│   │   ├── main/
│   │   └── test/
│   ├── pom.xml
│   └── mvnw.cmd
│
└── README.md
```

------------------------------------------------------------------------

## 🔄 Architecture Overview

Immersia follows a client-server architecture.

``` text
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
        ▼         ▼
┌────────────┐  ┌────────────┐
│ PostgreSQL │  │  TMDB API  │
└────────────┘  └────────────┘
```

------------------------------------------------------------------------

## 🔐 Authentication

Immersia uses JWT-based authentication with Spring Security.

The authentication flow is:

``` text
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
Authenticated API Request
  │
  ▼
JWT Authentication Filter
  │
  ▼
Protected Resource
```

Protected resources include:

-   Favorites
-   Watchlist
-   Account information
-   Other authenticated endpoints

------------------------------------------------------------------------

## 🎥 TMDB Integration

Immersia uses the TMDB API as its movie data source.

The frontend does not communicate directly with TMDB.

The backend acts as the API layer between the frontend and TMDB:

``` text
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
```

The backend retrieves movie information from TMDB and exposes
application-specific REST endpoints to the frontend.

------------------------------------------------------------------------

## 📡 API Endpoints

### Authentication

  Method   Endpoint               Description
  -------- ---------------------- -------------------------
  POST     `/api/auth/register`   Register a new user
  POST     `/api/auth/login`      Authenticate a user
  GET      `/api/auth/me`         Get the current user
  GET      `/api/auth/test`       Test JWT authentication

### Movies

  Method   Endpoint                     Description
  -------- ---------------------------- ----------------------
  GET      `/api/movies`                Get paginated movies
  GET      `/api/movies/{id}`           Get movie details
  GET      `/api/movies/{id}/similar`   Get similar movies
  GET      `/api/movies/search`         Search for movies

### Favorites

  Method   Endpoint                             Description
  -------- ------------------------------------ --------------------------------
  GET      `/api/favorites`                     Get the user's favorite movies
  POST     `/api/favorites?movieId={movieId}`   Add a movie to favorites
  DELETE   `/api/favorites/{id}`                Remove a movie from favorites

### Watchlist

  ------------------------------------------------------------------------------------
  Method                  Endpoint                             Description
  ----------------------- ------------------------------------ -----------------------
  GET                     `/api/watchlist`                     Get the user's
                                                               watchlist

  POST                    `/api/watchlist?movieId={movieId}`   Add a movie to the
                                                               watchlist

  DELETE                  `/api/watchlist/{id}`                Remove a movie from the
                                                               watchlist
  ------------------------------------------------------------------------------------

------------------------------------------------------------------------

## 🗄️ Database

Immersia uses PostgreSQL with Spring Data JPA and Hibernate.

``` text
User
 │
 ├── Favorites
 │      └── Movie ID
 │
 └── Watchlist
        └── Movie ID
```

The database stores application-specific user data.

Movie metadata such as titles, posters, descriptions, ratings, and
similar movies is retrieved from TMDB.

------------------------------------------------------------------------

## 🧪 Testing

The backend contains unit, controller, service, and integration tests.

### Testing Technologies

-   JUnit
-   Mockito
-   MockMvc
-   Spring Boot Test

### Test Coverage

Tests cover areas including:

-   User registration
-   User login
-   JWT generation
-   JWT authentication
-   Protected endpoints
-   Favorite operations
-   Watchlist operations
-   Movie endpoints
-   Service layer logic
-   Controller layer behavior

### Run Tests

From the `backend` directory:

``` bash
mvnw.cmd test
```

------------------------------------------------------------------------

## 📸 Screenshots

Screenshots of the application will be added here.

### Home

*Add screenshot here.*

### Movie Details

*Add screenshot here.*

### Login / Register

*Add screenshot here.*

### Account

*Add screenshot here.*

------------------------------------------------------------------------

## 🔮 Future Improvements

Planned improvements include:

-   Docker and Docker Compose
-   Improved movie filtering
-   Advanced search functionality
-   Additional user account settings
-   Production deployment
-   Improved UI/UX
-   Additional automated tests

------------------------------------------------------------------------

## 👩‍💻 Author

**Ebrar Sevgili**

Software Engineering Student

[GitHub](https://github.com/ebrarsevgili)

------------------------------------------------------------------------

## 📄 License

This project is created for educational and portfolio purposes.

All rights reserved unless otherwise stated.
