package com.jonathan.muzzmatchmessages.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jonathan.muzzmatchmessages.model.ChatMessage

@Dao()
interface ChatMessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(messages: List<ChatMessage>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(message: ChatMessage)

    @Update
    suspend fun update(chatMessage: ChatMessage)

    @Query("SELECT * FROM chat_message LIMIT 1")
    suspend fun getListLiveData(): LiveData<List<ChatMessage>>
}