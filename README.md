# Simple CRUD API in Spring Boot with Reactive Programming

This README provides detailed instructions on setting up and running a simple CRUD API using Spring Boot with reactive programming. The API performs CRUD operations on customer data and uses MySQL as the database, Logback for logging, and Liquibase for database migrations.

## Prerequisites

Before setting up the project, ensure you have the following installed:

- Java Development Kit (JDK) version 8 or later
- Maven
- MySQL server
- Your favorite IDE (IntelliJ IDEA, Eclipse, etc.)

## Setup

1. **Clone the Repository:** Clone or download the repository containing the Spring Boot project.

2. **Configure MySQL:**

   - Make sure MySQL server is installed and running on port 3306.
   - Create a database named `reactive`.

3. **Configure Spring Boot Application:**
   - Open the `application.properties` file located in `src/main/resources`.
   - Set the MySQL database URL, username, and password for both R2DBC and Liquibase configurations according to your MySQL setup.

```properties
spring.r2dbc.url=r2dbc:mysql://localhost:3306/reactive
spring.r2dbc.username=root
spring.r2dbc.password=root1234

spring.liquibase.url=jdbc:mysql://localhost:3306/reactive
spring.liquibase.user=root
spring.liquibase.password=root1234
spring.liquibase.enabled=true
spring.liquibase.change-log=classpath:changelog/changelog-master.xml
spring.liquibase.parameters.key=LiquibaseWithSpringBoot
```

## Run the Application

1. Open a terminal or command prompt.
2. Navigate to the project directory.
3. Run the following Maven command to build and start the Spring Boot application:

```bash
mvn spring-boot:run
```

## API Endpoints

Once the application is running, you can access the following endpoints to perform CRUD operations on customer data:

- **Create Customer:** `POST /rest/api/create`
- **Get All Customers:** `GET /rest/api/get`
- **Get Customer by ID:** `GET /rest/api/get/{id}`
- **Update Customer Email:** `PUT /rest/api/update/{id}?email={email}}`
- **Delete Customer:** `DELETE /rest/api/delete/{id}`
- **Delete All Customer:** `DELETE /rest/api/delete`

## Testing the API

You can use tools like Postman or cURL to test the API endpoints. Here's an example cURL command to create a new customer:

```bash
curl --location 'http://localhost:8080/rest/api/create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 1,
    "name": "test user",
    "phone":"92123456789",
    "email": "testUser@test.com",
    "address": "test",
    "city":"test",
    "state":"test",
    "country":"test"
}'
```

## Additional Notes

- This README assumes basic knowledge of Spring Boot, MySQL, and reactive programming concepts.
- Feel free to customize the application according to your requirements and best practices.
- For production deployment, consider using a proper database setup, secure configuration, and other necessary optimizations.
