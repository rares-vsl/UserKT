package domain

interface UserService {
    /**
     * Creates a new user.
     */
    fun createUser(user: User): User

    fun getAllUsers(): List<User>

    /**
     * Retrieves a user by ID.
     * @return User if found, null otherwise
     */
    fun getUser(id: UserId): User?

    /**
     * Updates a user's username.
     */
    fun updateUsername(id: UserId, newUsername: String): User

    /**
     * Updates a user's password.
     * IMPORTANT: newPassword should be pre-hashed for security
     */
    fun updatePassword(id: UserId, newPassword: String): User

    /**
     * Deletes a user by ID.
     * @return true if user was deleted, false if user didn't exist
     */
    fun deleteUser(id: UserId): Boolean
}