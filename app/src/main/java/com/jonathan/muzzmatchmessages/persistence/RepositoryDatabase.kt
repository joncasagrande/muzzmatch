package com.jonathan.muzzmatchmessages.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jonathan.muzzmatchmessages.model.ChatMessage
import com.jonathan.muzzmatchmessages.persistence.converter.DateTimeConverter
import com.jonathan.muzzmatchmessages.persistence.dao.ChatMessageDao

@Database(
    entities = [ChatMessage::class],
    version = 1
)
@TypeConverters(DateTimeConverter::class)
abstract class RepositoryDatabase: RoomDatabase() {
    abstract fun chatMessageDao(): ChatMessageDao
}
