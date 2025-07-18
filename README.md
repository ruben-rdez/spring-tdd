# Spring TDD Project

This project demonstrates Test-Driven Development (TDD) practices using Spring Boot, focusing on building a RESTful API for managing user profiles. The project utilizes modern testing practices including integration tests with a development database.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- JUnit 5
- Mockito
- AssertJ

## Project Structure

The project follows a layered architecture:

```
src/
├── main/java/com/spring/tdd/
│   ├── controller/    # REST controllers
│   ├── service/       # Business logic layer
│   ├── repository/    # Data access layer
│   ├── model/         # Domain entities
│   └── dto/           # Data Transfer Objects
└── test/java/com/spring/tdd/
    ├── controller/    # Controller tests
    └── service/       # Service layer tests
```

## Testing Strategy

The project implements different types of tests:

1. **Unit Tests**: Using Mockito for service layer testing
2. **Integration Tests**: Using a development database for integration testing
3. **API Tests**: Using MockMvc for testing REST endpoints

## API Endpoints

### Profile Management

#### Create Profile
- **POST** `/api/profiles`
- **Request Body**:
  ```json
  {
    "name": "string",
    "age": number
  }
  ```
- **Response**: 201 Created
  ```json
  {
    "id": number,
    "name": "string",
    "age": number
  }
  ```

#### Get Profile by ID
- **GET** `/api/profiles/{id}`
- **Response**: 200 OK
  ```json
  {
    "id": number,
    "name": "string",
    "age": number
  }
  ```

#### Get All Profiles
- **GET** `/api/profiles`
- **Response**: 200 OK
  ```json
  [
    {
      "id": number,
      "name": "string",
      "age": number
    }
  ]
  ```

## Setup and Running

### Prerequisites
- Java 17 or higher
- MySQL Database
- Maven

### Running Tests
```bash
./mvnw test
```

### Running the Application
```bash
./mvnw spring-boot:run
```

## Database Configuration

The application uses MySQL as its database. Make sure you have a MySQL instance running and configure the following properties in your `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/profiledb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

Make sure to create the database before running the application:
```sql
CREATE DATABASE profiledb;
```

## Development Approach

This project follows Test-Driven Development (TDD) principles:

1. Write a failing test
2. Write the minimum code to make the test pass
3. Refactor the code while keeping the tests green

## Error Handling

The API implements proper error handling:

- 404 Not Found: When requesting a non-existent profile
- 400 Bad Request: When submitting invalid profile data
- 201 Created: When successfully creating a profile
- 200 OK: When successfully retrieving profile(s)
