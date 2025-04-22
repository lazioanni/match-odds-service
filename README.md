# match-odds-service
A Spring Boot service for managing matches and match odds.

# Technologies Used
- Java 17
- Spring Boot 3.4.4
- Lombok
- SLF4J (for logging events)
- JUnit (for testing)
- Swagger (for documentation)

## Application Structure

### Controller layer
- MatchController → Handles CRUD services for matches.
- MatchOddsController → Handles CRUD services for match odds.

### Service layer
- MatchService → Provides business logic for matches.
- MatchOddsService → Provides business logic for match odds.

### Repository layer
- MatchRepository → Provides DAO services for matches.
- MatchOddsRepository → Provides DAO services for match odds.

### DTO
- MatchDTO → Transfer data between the Controller and Service layers.
- MatchOddsSTO → Transfer data between the Controller and Service layers.

### Enums
- Specifier → Provides different types of Specifier.
- Sport → Provides different types of Sports.

### Testing
The application includes testing using the BDD approach.## Local Database Setup

## Database Setup

### Database Setup with PostgreSQL (Locally)

For my local setup, I have configured the Spring Boot application to work with a PostgreSQL database, which I set up using **pgAdmin**.

#### Steps for My Local Database Setup:
1. I installed **PostgreSQL** and **pgAdmin** on my machine.
2. I opened **pgAdmin** and connected to my local PostgreSQL server.
3. I created a new database called `match`.