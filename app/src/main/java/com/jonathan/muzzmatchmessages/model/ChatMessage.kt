package com.jonathan.muzzmatchmessages.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "chat_message")
data class ChatMessage(
    @ColumnInfo @PrimaryKey(autoGenerate=true) val id: Long?,
    val content: String,
    val userId: String,
    val dateTime: Date
)
