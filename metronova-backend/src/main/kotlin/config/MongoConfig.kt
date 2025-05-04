package config

import com.typesafe.config.ConfigFactory
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase

object MongoConfig {
    private val config = ConfigFactory.load()

    // Carica le configurazioni in base all'ambiente
    private val environment = System.getProperty("env", "dev")  // Usa "dev" di default se non fornito
    private val mongoConfig = config.getConfig("ktor.mongo.$environment")

    val uri: String = mongoConfig.getString("uri")
    val databaseName: String = mongoConfig.getString("database")

    // Crea la connessione al database
    val client: MongoClient = MongoClients.create(uri)
    val database: MongoDatabase = client.getDatabase(databaseName)
}
