# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build and Development Commands

This is a Spring Boot 3.4.1 application using Java 17 and Gradle for a **soccer pitch management system**.

### Common Commands
- `./gradlew build` - Build the application
- `./gradlew test` - Run tests
- `./gradlew bootRun` - Run the application locally
- `./gradlew clean` - Clean build artifacts
- `./gradlew compileJava` - Compile Java sources only

### Database Setup
The application uses MySQL database. Ensure MySQL is running on localhost:3306 with:
- Database: `user_app5`
- Username: `salim`  
- Password: `salim`

The application uses `spring.jpa.hibernate.ddl-auto=create-drop` which recreates the database schema on each startup for development.

## Architecture Overview

This is a **soccer pitch booking and management system** built with Spring Boot. It allows users to create and manage soccer pitches, configure booking sessions, and handle user authentication.

### Core Domain Areas

**User Management:**
- User registration with email verification
- JWT-based authentication with custom UserPrincipal
- Role-based authorization (ADMIN/USER roles via RoleEntity)
- Password reset functionality with token-based flow
- Users can own multiple pitches

**Pitch Management:**
- Soccer pitch creation and management (HALF_PITCH/FULL_PITCH types)
- Location and naming for pitches
- Pitch ownership by users
- Automatic session configuration creation

**Session System:**
- Time-based booking slots (LocalTime startTime/endTime)
- Sessions belong to specific pitches
- Boolean active flag for enabling/disabling sessions
- Automatic session generation based on configuration

**Session Configuration:**
- Defines how sessions are created (CreationMode.DAILY)
- Configurable number of sessions per pitch
- One-to-one relationship with pitches

### Key Architectural Components

**Security Layer:**
- JWT token-based authentication with custom filter (CustomAuthorizationFilter)  
- UserPrincipal class for security context
- Method-level security with @PreAuthorize and @Secured
- Custom access denied and authentication entry point handlers
- BCrypt password encoding
- CORS configuration for development (allows all origins)

**Data Layer:**
- JPA entities with proper relationship management
- Soft delete capability (deletedAt timestamps)
- Automatic timestamp management (@CreationTimestamp, @UpdateTimestamp)
- ModelMapper for DTO/Entity conversions
- Repository pattern with Spring Data JPA
- Bidirectional relationship maintenance in entity setters

**Service Layer:**
- @Transactional methods for data consistency
- Business validation and error handling
- SecurityContext integration for user authentication
- Custom ApiException for consistent error responses

**API Layer:**
- REST controllers with proper HTTP method mapping
- Request/Response DTOs separate from entities
- Jakarta validation with @Valid
- Consistent error handling via CustomExceptionHandler

### Package Structure
- `controller/` - REST endpoints (UserController, PitchController, AuthController)
- `service/` - Business logic layer (UserService, PitchService, AuthenticationService)
- `repository/` - Data access layer (Spring Data JPA repositories)
- `entity/` - JPA entities representing database tables
- `dto/` - Data transfer objects for API communication
- `model/request/` - Request models for API endpoints
- `model/response/` - Response models for API endpoints
- `security/` - Security configuration, filters, and custom handlers
- `exception/` - Custom exceptions and global error handling
- `utils/` - Utility classes (token generation, helper methods)
- `enums/` - Enumeration types (PitchType, CreationMode)

### Entity Relationships

**User ↔ Pitch (One-to-Many):**
- Users can own multiple pitches
- Pitch owns the relationship (@JoinColumn on users_id)
- Cascade operations: PERSIST, MERGE, DETACH, REFRESH

**Pitch ↔ Session (One-to-Many):**  
- One pitch has many sessions
- Session owns the relationship (@JoinColumn on pitch_id)
- Cascade: ALL (sessions deleted when pitch is deleted)
- Bidirectional with helper methods for relationship maintenance

**Pitch ↔ SessionConfiguration (One-to-One):**
- Each pitch has one configuration
- Pitch owns the relationship (@JoinColumn on session_configuration_id)
- Cascade: ALL (configuration created/deleted with pitch)

**Relationship Management:**
- Entities have helper methods (addSession, removeSession) for maintaining bidirectional relationships
- setSessions() in PitchEntity automatically sets session.pitch references
- setPitch() in SessionEntity handles relationship cleanup and establishment

### Security Configuration
**Public Endpoints:**
- POST `/auth` - User authentication
- POST `/users/signup` - User registration  
- GET `/users/verify-email/**` - Email verification
- POST `/users/initiate-reset-password` - Password reset initiation
- POST `/users/reset-password` - Password reset completion

**Protected Endpoints:**
- POST `/pitch` - Create pitch (requires ROLE_USER)
- GET `/users` - List users (requires ROLE_ADMIN)
- All other endpoints require authentication

**JWT Configuration:**
- Custom authorization filter processes JWT tokens
- UserPrincipal provides authenticated user context
- Token secret configured in application.properties

### Current API Endpoints

**Pitch Management:**
- POST `/pitch` - Create new pitch with sessions

**User Management:**
- POST `/users/signup` - User registration
- GET `/users/verify-email` - Email verification
- POST `/users/initiate-reset-password` - Start password reset
- POST `/users/reset-password` - Complete password reset
- GET `/users` - Admin: list all users (currently returns empty list)

**Authentication:**
- POST `/auth` - User login

### Key Dependencies
- Spring Boot Starter Web, Security, Data JPA, Validation
- MySQL Connector J
- JWT library (io.jsonwebtoken:jjwt:0.9.1)
- ModelMapper for object mapping
- Lombok for reducing boilerplate
- Jackson XML support
- Jakarta Validation API

### Development Notes

**Entity Design Patterns:**
- All entities implement Serializable
- Use of @CreationTimestamp/@UpdateTimestamp for audit fields
- Lombok @Setter(AccessLevel.NONE) prevents manual timestamp modification
- Proper cascade configurations based on business relationships

**DTO Design:**
- Clean separation between request/response/internal DTOs
- No circular references in DTOs (removed SessionDto back-reference to PitchDto)
- Validation annotations on request DTOs

**Service Layer Patterns:**
- @Transactional for data consistency
- Security context integration for current user access
- Business validation before persistence
- Consistent exception handling with ApiException

**Testing:**
- Basic Spring Boot test exists (Userservice5ApplicationTests)
- Run tests with `./gradlew test`

### Developer Learning Approach

**IMPORTANT: This developer prefers hands-on guided learning.**

When working with this codebase, use the following teaching methodology:

1. **Guided Coding Challenges**: Instead of making changes directly, give the developer small, specific coding tasks
2. **Step-by-Step Learning**: Break complex tasks into small pieces (3-5 lines of code at a time)
3. **Explain-Then-Code Pattern**: 
   - Explain what needs to be done and why
   - Ask the developer to write the actual code
   - Review their code and provide feedback
   - Guide them through fixes if needed

**Example Approach:**
```
❌ Don't: "Let me fix the updatePitch method for you"
✅ Do: "Look at the updatePitch method and tell me what's wrong with it. 
       Then I'll guide you through writing the fix step by step."
```

**Learning Benefits:**
- Builds muscle memory through typing
- Develops problem-solving skills
- Creates confidence through successful code completion
- Ensures understanding rather than just copy-paste

### Known Areas for Development
- Complete updatePitch method in PitchService (PRIORITY: Use for guided learning)
- Add remaining CRUD operations for pitches
- Implement booking system for sessions
- Add search and filtering capabilities
- Implement proper pagination for list endpoints
- Add comprehensive validation messages
- Implement email functionality for verification/reset flows