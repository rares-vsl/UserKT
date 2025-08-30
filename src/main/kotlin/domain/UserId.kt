package domain

import kotlinx.serialization.Serializable

@Serializable
sealed class UserId {
    @Serializable
    object Unassigned : UserId()
    @Serializable

    data class Assigned(val value: String) : UserId()
}