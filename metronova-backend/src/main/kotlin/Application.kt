package com.fcosta

import io.ktor.server.application.*
import io.github.cdimascio.dotenv.dotenv

val dotenv = dotenv()
val mongoUri = dotenv["MONGODB_URI"]

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureMonitoring()
    configureHTTP()
    configureSerialization()
    configureRouting()
}
