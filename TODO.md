# TODO List - Soccer Pitch Management System

This file tracks development tasks and improvements for the soccer pitch booking system.

## Priority Tasks

### 🔧 Core Issues (High Priority)
- [ ] **Complete the updatePitch method** in PitchService.java
  - Current status: Method exists but returns null and has commented-out exception
  - Location: `src/main/java/com/example/userservice5/service/PitchService.java:67-72`
  - Need to implement proper update logic with validation

- [ ] **Fix type conversion issues in CreatePitchRequest**
  - Issue: CreatePitchRequest.type is String but PitchEntity.type is PitchType enum
  - Location: `src/main/java/com/example/userservice5/model/request/CreatePitchRequest.java:28`
  - Solution: Either change field to PitchType or add conversion logic

- [ ] **Add missing CRUD operations for pitches**
  - Current: Only CREATE (POST) exists
  - Missing: GET /pitch/{id}, GET /pitch, PUT /pitch/{id}, DELETE /pitch/{id}
  - Need proper HTTP status codes (201 for creation, not 200)

### 👥 Partner Role Implementation (Medium Priority)
- [ ] **Add PARTNER role to the system**
  - Create createPartner() method in UserService
  - Add ROLE_PARTNER to security configuration
  - Partners can own pitches but have limited admin privileges

- [ ] **Update security configuration for PARTNER role**
  - Add partner-specific endpoints: /partner/pitches, /partner/dashboard
  - Configure permissions: partners can manage their own pitches only
  - Implement role-based data filtering

- [ ] **Create partner endpoints and data filtering**
  - GET /partner/pitches - show only partner's pitches
  - GET /partner/bookings - show only bookings for partner's pitches
  - Use repository queries like findByUserDetailId(partnerId)

### ⚽ Booking System (Medium Priority)  
- [ ] **Create BookingEntity and repository**
  - BookingEntity with player (UserEntity), session, booking date, status
  - BookingRepository with ownership-based queries
  - Relationship: User (many) ↔ Session (many) through Booking

- [ ] **Add booking endpoints**
  - POST /bookings - create new booking
  - GET /bookings/my - user's own bookings
  - DELETE /bookings/{id} - cancel booking
  - GET /sessions/{id}/availability - check session availability

- [ ] **Connect sessions to bookings**
  - Modify SessionEntity to support booking status
  - Add availability checking logic
  - Handle booking conflicts and time validation

### ✅ Validation & Error Handling (Low Priority)
- [ ] **Add comprehensive validation**
  - Complete validation in CreatePitchRequest
  - Add validation to SessionModel and other DTOs
  - Implement proper error messages for validation failures

- [ ] **Improve error handling patterns**
  - Review CustomExceptionHandler implementation
  - Add specific exception types for business logic errors
  - Ensure consistent error response format

## Recent Progress ✅

### Completed Tasks
- [x] **Remove unnecessary DTO relationship loop in PitchService**
  - Removed confusing loop that set SessionDto.pitch back-references
  - Cleaned up service method from lines 49-53

- [x] **Add proper relationship helper methods to entities**  
  - Added setSessions() in PitchEntity with bidirectional relationship management
  - Added setPitch() in SessionEntity with relationship cleanup
  - Added addSession() and removeSession() helper methods

- [x] **Remove back-reference from SessionDto**
  - Removed private PitchDto pitch field from SessionDto
  - Removed getter/setter methods for cleaner DTO design
  - DTOs now follow proper parent→child reference pattern only

- [x] **Create comprehensive CLAUDE.md file**
  - Documented soccer pitch management system architecture
  - Explained entity relationships and security patterns
  - Added build commands and development guidance

## Technical Debt & Code Quality

### Known Issues
- ModelMapper usage in controllers (should be in service layer)
- Hard-coded session configuration values (DAILY mode, 10 sessions)
- Missing pagination for list endpoints
- No soft delete implementation for pitches
- JWT token secret should be externalized from application.properties

### Architecture Improvements
- Consider implementing PermissionEvaluator for fine-grained access control
- Add caching for frequently accessed data (pitch listings)
- Implement audit logging for booking operations
- Add email notifications for booking confirmations

## Development Notes

### Current Entity Relationships
```
UserEntity (1) → owns → (Many) PitchEntity
PitchEntity (1) → has → (Many) SessionEntity  
PitchEntity (1) → has → (1) SessionConfigurationEntity
```

### Planned Relationships (Booking System)
```
UserEntity (1) → makes → (Many) BookingEntity
SessionEntity (1) → has → (Many) BookingEntity
BookingEntity → links User and Session for specific dates
```

### Security Model
- ROLE_USER: Can book sessions, view own bookings
- ROLE_PARTNER: Can manage own pitches, view bookings for own pitches
- ROLE_ADMIN: Full system access

## Testing Strategy
- Add unit tests for service layer methods
- Add integration tests for REST endpoints
- Test security configurations with different roles
- Test booking conflict scenarios

---

**Last Updated**: Generated during learning session about soccer pitch management system architecture and Spring Boot patterns.

**Next Session**: Start with fixing the updatePitch method or implementing PARTNER role system.