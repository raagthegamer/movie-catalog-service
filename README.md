# 🎬 Movie Catalog Microservice

A containerized **Spring Boot microservice** for managing movies, built with **PostgreSQL**, **Gradle**, and **Docker Compose**.  
Supports full CRUD operations, validation, and error handling — and is ready to be extended into a microservice architecture.

---

## 🧰 Tech Stack

- **Java 21**
- **Spring Boot 3** (Web, JPA, Validation)
- **PostgreSQL 17**
- **Gradle 8.14.3**
- **Docker & Docker Compose**
- **Swagger / OpenAPI** (for API documentation)

---

## 🏗️ Architecture

This project follows a backend service pattern that can scale into a microservice ecosystem:

- A **Spring Boot** application exposing REST endpoints for managing movies.
- A **PostgreSQL** database running in a separate container.
- **Docker Compose** orchestrates both services, handling networking and environment configuration.

```
 ┌─────────────────────┐       ┌─────────────────────┐
 │   movies-service    │◄────► │     PostgreSQL      │
 │ (Spring Boot REST)  │       │ (Containerized DB)  │
 └─────────────────────┘       └─────────────────────┘
```

---

## 🚀 Getting Started

### Prerequisites

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/)
- (Optional) [Java 21](https://adoptium.net/) if you want to run the app locally without Docker.

---

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/raagthegamer/movie-catalog-service.git
cd movie-catalog-service
```

---

### 2️⃣ Create `.env` File

Create a `.env` file in the root directory to store database credentials:

```env
DB_USER=postgres
DB_PASSWORD=password
DB_NAME=movies_db
DB_PORT=5432
DB_HOST=postgres
```

> ⚠️ Make sure `.env` is added to `.gitignore` so credentials aren’t committed.

---

### 3️⃣ Start Services with Docker Compose

```bash
docker-compose up --build
```

- The backend will start on [http://localhost:8080](http://localhost:8080)
- PostgreSQL will be available at `localhost:5432`

---

## 🌐 API Endpoints

| Method | Endpoint             | Description                  |
|-------|-----------------------|-------------------------------|
| GET   | `/movies`            | Get all movies               |
| GET   | `/movies/{id}`      | Get a movie by ID           |
| POST  | `/movies`           | Add a new movie             |
| PUT   | `/movies/{id}`     | Update an existing movie    |
| DELETE| `/movies/{id}`     | Delete a movie              |

Swagger/OpenAPI docs will be available at:  
👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🧪 Testing the API

### Add a Movie
```bash
curl -X POST http://localhost:8080/movies   -H "Content-Type: application/json"   -d '{"title": "The Dark Knight", "genre": "Action", "rating": 4.9}'
```

### Get All Movies
```bash
curl http://localhost:8080/movies
```

### Update a Movie
```bash
curl -X PUT http://localhost:8080/movies/1   -H "Content-Type: application/json"   -d '{"title": "The Dark Knight Rises", "genre": "Action", "rating": 4.7}'
```

---

## 🛠️ Development Notes

- **Validation**: Uses `@Valid` and Jakarta Validation annotations to enforce required fields and rating bounds.
- **Global Error Handling**: Implemented with `@ControllerAdvice` for clean error responses.
- **Persistence**: Uses Spring Data JPA with PostgreSQL for movie storage.
- **Docker**: Multi-stage build for lean images, and `docker-compose.yml` for orchestration.

---

## 🛣️ Roadmap / Future Enhancements

- [ ] Add authentication & JWT
- [ ] Implement search & filtering service
- [ ] Add unit and integration tests with Testcontainers
- [ ] Add a React frontend and integrate via Docker Compose
- [ ] Deploy to AWS ECS or Render

---

## 📝 License

This project is licensed under the MIT License.

---

## 🙌 Credits

Built with ☕ & ❤️ by Raag Joshi(https://www.linkedin.com/in/raag-joshi)