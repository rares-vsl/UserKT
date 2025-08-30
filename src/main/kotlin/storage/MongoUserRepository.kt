package storage

import domain.User
import domain.UserRepository
import domain.UserId
import kotlinx.coroutines.runBlocking
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq
import java.util.UUID

object MongoUserRepository : UserRepository {
    private val collection: CoroutineCollection<User> = MongoDB.database.getCollection()

    override fun GetAllUsers(): List<User> = runBlocking {
        collection.find().toList()
    }

    override fun FindUserById(id: UserId): User? = runBlocking {
        collection.findOne(User::id eq id)
    }

    override fun updateUser(user: User) {
        runBlocking {
            collection.save(user)
        }
    }

    override fun addNewUser(user: User) {
        val idToAssign = UUID.randomUUID().toString()

        val assignedUser = user.copy(id = UserId.Assigned(idToAssign))

        runBlocking {
            collection.insertOne(assignedUser)
        }
    }

    override fun removeUser(user: User) {
        runBlocking {
            collection.deleteOne(User::id eq user.id)
        }
    }
}
