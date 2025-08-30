package presentation.dto

import domain.UserId
import domain.UserRole
import kotlinx.serialization.Serializable

/**
 * Response DTO for user data.
 * Excludes sensitive information like passwords.
 */
@Serializable
data class UserResponse(
    val id: String,
    val username: String,
    val role: UserRole
)
