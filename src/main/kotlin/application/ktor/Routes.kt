package application.ktor

import domain.UserFactory
import domain.UserRole
import domain.UserService
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route

import presentation.dto.CreateUserRequest
import presentation.mapper.UserMapper

fun Route.userRoutes(userService: UserService) {
    route("/api/users") {
        post {
            val username = call.parameters["username"]
                ?: return@post call.respond(HttpStatusCode.BadRequest, "Missing username")
            val password = call.parameters["password"]
                ?: return@post call.respond(HttpStatusCode.BadRequest, "Missing password")
            val roleParam = call.parameters["role"] ?: "USER"
            val role = try {
                UserRole.valueOf(roleParam.uppercase())
            } catch (e: IllegalArgumentException) {
                return@post call.respond(HttpStatusCode.BadRequest, "Invalid role: $roleParam")
            }

            val request = CreateUserRequest(username, password, role)

            print(request)
            val newUser = UserFactory.createUser(
                username = request.username,
                password = request.password,
                role = request.role
            )
            print(newUser)

            val createdUser = userService.createUser(newUser)

            call.respond(HttpStatusCode.Created, UserMapper.toResponse(createdUser))
        }
    }
}

