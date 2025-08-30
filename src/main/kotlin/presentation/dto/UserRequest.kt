package presentation.dto

import domain.UserRole
import kotlinx.serialization.Serializable

/**
 * Request DTO for creating users.
 */
@Serializable
data class CreateUserRequest(
    val username: String,
    val password: String,
    val role: UserRole
)

/**
 * Request DTO for updating username
 */
@Serializable
data class UpdateUsernameRequest(
    val newUsername: String
)

/**
 * Request DTO for updating password
 */
@Serializable
data class UpdatePasswordRequest(
    val newPassword: String
)