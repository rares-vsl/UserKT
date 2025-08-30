package application.ktor

import application.KtorUserService
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.routing.get
import kotlinx.serialization.json.Json

fun Application.userServiceModule() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }

    val userService = KtorUserService

    // Setup routing
    routing {
        // Health check endpoint
        get("/health") {
            call.respond(HttpStatusCode.OK, mapOf("status" to "healthy"))
        }

        // User routes with repository access for GetAllUsers
        userRoutes(userService)
    }


}

fun main() {
    val port = System.getenv("KTOR_PORT")?.toIntOrNull() ?: 8080

    embeddedServer(Netty, port = port, host = "0.0.0.0") {
        userServiceModule()
    }.start(wait = true)
}