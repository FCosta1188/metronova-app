package com.fcosta

import com.fcosta.model.UserPreferences
import config.MongoConfig
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import repository.UserPreferencesRepository

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(ContentNegotiation) {
        jackson()
    }

    routing {
        get("/") {
            call.respondText("Welcome to the world of TOMORROOOWW")
        }

        route("/preferences") {
            val userPreferencesRepository = UserPreferencesRepository(MongoConfig.database)

            post {
                val userPreferences = UserPreferences(
                    userId = "user123",
                    theme = "dark",
                    language = "en"
                )
                userPreferencesRepository.save(userPreferences)
                call.respond(HttpStatusCode.Created, "User preferences saved")
            }

            get("/{userId}") {
                val userId = call.parameters["userId"] ?: throw IllegalArgumentException("Missing or malformed userId")
                val preferences = userPreferencesRepository.findByUserId(userId)
                if (preferences != null) {
                    call.respond(preferences)
                } else {
                    call.respond(HttpStatusCode.NotFound, "User preferences not found")
                }
            }
        }
    }
}
