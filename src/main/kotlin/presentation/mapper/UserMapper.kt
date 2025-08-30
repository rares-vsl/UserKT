package presentation.mapper

import domain.User
import domain.UserFactory
import domain.UserId
import domain.UserRole
import presentation.dto.CreateUserRequest
import presentation.dto.UserResponse

object UserMapper {
    fun toDomain(request: CreateUserRequest): User {
        return UserFactory.createUser(
            username = request.username,
            password = request.password,
            role = request.role
        )
    }

    /**
     * Converts domain User to response DTO.
     * Excludes password for security.
     */
    fun toResponse(user: User): UserResponse = UserResponse(
        id = (user.id as UserId.Assigned).value,
        username = user.username,
        role = user.role,
    )

    /**
     * Converts list of domain Users to list of response DTOs
     */
    fun toResponseList(users: List<User>): List<UserResponse> = users.map { toResponse(it) }
}
