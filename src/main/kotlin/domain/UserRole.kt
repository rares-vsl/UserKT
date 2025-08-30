package domain

import kotlinx.serialization.Serializable

@Serializable
enum class UserRole {
    ADMIN,
    USER;

    fun hasAdminPermission(): Boolean = this == ADMIN
    fun hasUserPermission(): Boolean = this == USER
}