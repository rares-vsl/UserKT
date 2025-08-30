package application

import domain.User
import domain.UserId
import domain.UserService
import storage.MongoUserRepository

object KtorUserService : UserService {
    private val repository = MongoUserRepository

    override fun createUser(user: User): User {
        repository.addNewUser(user)

        return repository.GetAllUsers().find { it.username == user.username }
            ?: throw RuntimeException("Failed to create user")
    }

    override fun getAllUsers(): List<User> {
        return repository.GetAllUsers()
//        return repository.GetAllUsers().map { it.toDomain() }
    }

    override fun getUser(id: UserId): User? = repository.FindUserById(id)

    override fun updateUsername(id: UserId, newUsername: String): User {
        val existingUser = repository.FindUserById(id)
            ?: throw IllegalArgumentException(
                "User with id $id not found"
            )

        val updatedUser = existingUser.copy(username = newUsername)
        repository.updateUser(updatedUser)

        return updatedUser
    }

    override fun updatePassword(id: UserId, newPassword: String): User {
        val existingUser = repository.FindUserById(id)
            ?: throw IllegalArgumentException(
                "User with id $id not found"
            )

        val updatedUser = existingUser.copy(passwordHash = newPassword)
        repository.updateUser(updatedUser)

        return updatedUser
    }

    override fun deleteUser(id: UserId): Boolean {
        val existingUser = repository.FindUserById(id) ?: return false
        repository.removeUser(existingUser)
        return true
    }


}