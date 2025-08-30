package storage

import domain.UserFactory
import domain.UserRole

fun main() {
    val adminUser = UserFactory.createUser("giovanna", "adminpass", UserRole.USER)
    MongoUserRepository.addNewUser(adminUser)

    print(MongoUserRepository.GetAllUsers().size)
}
