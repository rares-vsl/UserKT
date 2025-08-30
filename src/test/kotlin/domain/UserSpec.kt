package domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class UserSpec :
    FreeSpec(
        {
            "User management" - {
                "should allow create user and potentially assign id later" {
                    val unassignedUser = UserFactory.createUser("newuser", "securepass", UserRole.USER)
                    unassignedUser.id shouldBe UserId.Unassigned

                    val idToAssign = "generated-id-123"

                    val assignedUser = unassignedUser.copy(id = UserId.Assigned(idToAssign))
                    assignedUser.id shouldBe UserId.Assigned(idToAssign)
                }
                "should support role-based permission checking workflow" - {
                    val adminUser = UserFactory.createUser("admin", "adminpass", UserRole.ADMIN)
                    val regularUser = UserFactory.createUser("user", "userpass", UserRole.USER)

                    adminUser.role.hasAdminPermission() shouldBe true
                    regularUser.role.hasUserPermission() shouldBe true
                    regularUser.role.hasAdminPermission() shouldBe false
                    adminUser.role.hasUserPermission() shouldBe false
                }
            }
        },
    )
