package domain

object UserFactory {
    fun createUser(username: String,
                   password: String,
                   role: UserRole): User {
        return User(
            id = UserId.Unassigned,
            username=username,
            passwordHash= password,
            role = role
        )
    }

    fun createUser(id: UserId,
                   username: String,
                   password: String,
                   role: UserRole): User {
        return User(
            id = id,
            username=username,
            passwordHash= password,
            role = role
        )
    }
}