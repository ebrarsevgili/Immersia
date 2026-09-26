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
- Global exception handling

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

---

### 1. Clone the Repository

```bash
git clone https://github.com/ebrarsevgili/Immersia.git
cd Immersia