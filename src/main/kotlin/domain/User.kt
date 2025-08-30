package domain

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class User (
    val id: UserId,
    var username: String,
    var passwordHash: String,
    var role: UserRole
)