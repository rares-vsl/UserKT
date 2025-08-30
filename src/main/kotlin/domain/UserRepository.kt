package domain

interface UserRepository {

    /**
     * Returns all users in the system.
     */
    fun GetAllUsers(): List<User>

    /**
     * Finds a user by ID.
     * @return User if found, null otherwise
     */
    fun FindUserById(id: UserId): User?

    /**
     * Updates an existing user.
     */
    fun updateUser(user: User)

    /**
     * Adds a new user.
     */
    fun addNewUser(user: User)

    /**
     * Removes a user.
     */
    fun removeUser(user: User)
}