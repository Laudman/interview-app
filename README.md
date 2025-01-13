# interview-app

Food and Drug Administration

## Overview

This application is designed to help manage and search for FDA drug records. It provides functionality to:

- Perform CRUD (Create, Read, Update, Delete) operations on drug records.
- Search paginated FDA drug results using external FDA APIs.
- Store drug-related data in a PostgreSQL database.

---

## Prerequisites

To run this application, ensure you have the following installed:

- **Java 17** or higher
- **Gradle** (if not using the wrapper)
- **PostgreSQL** (or Docker for running the database container)
- **Docker/Docker Compose** (optional, to run the database setup easily)

---

## Setup and Run Instructions

Follow these steps to set up and run the application:

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Laudman/interview-app.git
   cd interview-app
   ```

2. **Start PostgreSQL using Docker Compose (Optional)**  
   If you prefer to run the database in a Docker container, use the provided `docker-compose.yml` file:
   ```bash
   docker-compose up -d
   ```

   This will start the PostgreSQL database with the following default credentials:
    - Database: `int-app-db-test`
    - Username: `int-app-db-test`
    - Password: `int-app-db-test`

3. **Build the Application**  
   Build the application using Gradle:
   ```bash
   ./gradlew build
   ```
   *For Windows users:*
   ```bash
   gradlew.bat build
   ```

4. **Run the Application with Local Profile**  
   The application provides a **`local`** profile, which binds the application to **port 8080**. To run the application
   using this profile:
   ```bash
   ./gradlew bootRun --args='--spring.profiles.active=local'
   ```

   By default, the application runs on:  
   **[http://localhost:8080](http://localhost:8080)**

5. **Access API Documentation**  
   Swagger UI is available for testing and documentation at:  
   **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

---

## Endpoints Overview

### Drug Records API

Endpoints for managing drug records stored in the local database.

Base URL: `/v1/drug-record`

| Method   | Endpoint               | Description                                       |
|----------|------------------------|---------------------------------------------------|
| `GET`    | `/v1/drug-record`      | Fetch all drug records from the database.         |
| `GET`    | `/v1/drug-record/{id}` | Fetch a specific drug record by its ID.           |
| `POST`   | `/v1/drug-record`      | Add a new drug record to the database.            |
| `DELETE` | `/v1/drug-record/{id}` | Delete a drug record from the database by its ID. |

### FDA Search API

Endpoints for fetching and searching drug data directly from external FDA APIs.

Base URL: `/v1/fda`

| Method | Endpoint         | Description                      |
|--------|------------------|----------------------------------|
| `GET`  | `/v1/fda/search` | Search FDA data with pagination. |

#### Query Parameters for `/v1/fda/search`:

- **`page`** (`int`): Page number (default: `0`)
- **`size`** (`int`): Number of results per page (default: `5`)
- Additional filters can be passed through `FdaDrugFilterDTO`.

---

## Testing the Application

1. **Run Unit and Integration Tests**  
   Execute the test suite to verify functionality:
   ```bash
   ./gradlew test
   ```

2. **Integration with TestContainers**
    - The application uses `TestContainers` to spin up a PostgreSQL database in a Docker container for integration
      tests.
    - No local PostgreSQL setup is needed for tests.

---

## Example Usage

### Create a New Drug Record

```bash
curl -X POST -H "Content-Type: application/json" \
-d '{
    "name": "Paracetamol",
    "applicationNumber": "12345",
    "manufacturer": "Test Manufacturer"
}' http://localhost:8080/v1/drug-record
```

### Get All Drug Records

```bash
curl -X GET http://localhost:8080/v1/drug-record
```

### Delete a Drug Record

```bash
curl -X DELETE http://localhost:8080/v1/drug-record/12345
```

### Search FDA Database (Paginated)

```bash
curl -X GET 'http://localhost:8080/v1/fda/search?page=0&size=5'
```

---

## Technologies Used

- **Programming Language:** Java 17
- **Frameworks & Libraries:**
    - Spring Boot (Web, Data JPA, Validation)
    - SpringDoc for API documentation
    - MapStruct for object mapping
- **Database:** PostgreSQL (with support for Docker)
- **Testing:** JUnit 5 + TestContainers for integration testing
- **Build Tool:** Gradle

---
