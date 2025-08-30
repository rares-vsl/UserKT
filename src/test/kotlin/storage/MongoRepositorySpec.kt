package storage

import domain.User
import domain.UserFactory
import domain.UserId
import domain.UserRole
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.runBlocking

class MongoRepositorySpec :
    FreeSpec(
        {
            val adminUser = UserFactory.createUser("admin", "adminpass", UserRole.ADMIN)
            val regularUser = UserFactory.createUser("testuser1", "userpass", UserRole.USER)

            beforeTest {
                // Clean the collection before each test
                runBlocking {
                    MongoDB.database.getCollection<User>().deleteMany("{}")
                }
            }

            afterTest {
                // Clean up after each test
                runBlocking {
                    MongoDB.database.getCollection<User>().deleteMany("{}")
                }
            }

            "MongoUserRepository" - {
                "addNewUser" - {
                    "should add a new user with assigned ID" {
                        MongoUserRepository.addNewUser(adminUser)

                        val allUsers = MongoUserRepository.GetAllUsers()
                        allUsers shouldHaveSize 1

                        val savedUser = allUsers.first()

                        print(savedUser.toString())

                        savedUser.username shouldBe adminUser.username
                        savedUser.passwordHash shouldBe adminUser.passwordHash
                        savedUser.role shouldBe adminUser.role
                        savedUser.id shouldNotBe UserId.Unassigned
                    }

                    "should add multiple users with different IDs" {
                        MongoUserRepository.addNewUser(adminUser)
                        MongoUserRepository.addNewUser(regularUser)

                        val allUsers = MongoUserRepository.GetAllUsers()
                        allUsers shouldHaveSize 2

                        val user1 = allUsers.find { it.username == adminUser.username }
                        val user2 = allUsers.find { it.username == regularUser.username }

                        user1.shouldNotBeNull()
                        user2.shouldNotBeNull()
                        user1.id shouldNotBe user2.id // Should have different IDs
                    }
                }
            }
        },
    )
