package repository

import com.fcosta.model.UserPreferences
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document
import org.litote.kmongo.getCollection

class UserPreferencesRepository(private val database: MongoDatabase) {

    private val collection: MongoCollection<UserPreferences> =
        database.getCollection()

    // Esempio di operazione per trovare preferenze utente
    fun findByUserId(userId: String): UserPreferences? {
        return collection.find(Document("userId", userId)).first()
    }

    // Esempio di operazione per aggiungere preferenze
    fun save(userPreferences: UserPreferences) {
        collection.insertOne(userPreferences)
    }

    // Esempio di operazione per aggiornare preferenze
    fun update(userPreferences: UserPreferences) {
        collection.replaceOne(Document("userId", userPreferences.userId), userPreferences)
    }
}
