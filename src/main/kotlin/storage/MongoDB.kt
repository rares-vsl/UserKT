package storage

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import domain.UserId
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import org.bson.UuidRepresentation

object MongoDB {
    private const val DATABASE_NAME = "userservice"
    private const val CONNECTION_STRING = "mongodb://localhost:27017" // Update with your MongoDB connection string

    val database by lazy {
        val clientSettings = MongoClientSettings
            .builder()
            .applyConnectionString(ConnectionString(CONNECTION_STRING))
            .uuidRepresentation(UuidRepresentation.STANDARD)
            .build()

        KMongo
            .createClient(clientSettings)
            .coroutine
            .getDatabase(DATABASE_NAME)
    }
}
