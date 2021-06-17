package com.jonathan.muzzmatchmessages.repo

import androidx.lifecycle.LiveData
import com.jonathan.muzzmatchmessages.model.ChatMessage
import com.jonathan.muzzmatchmessages.persistence.dao.ChatMessageDao
import com.jonathan.muzzmatchmessages.util.PERSONAL_USER_ID
import com.jonathan.muzzmatchmessages.util.SARA_USER
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MessagesRepository(
    private val chatMessageDao: ChatMessageDao,
) {
    val date = Calendar.getInstance()
    private val listMessage = mutableListOf<ChatMessage>(
        ChatMessage(null, "text1", PERSONAL_USER_ID, date.time),
        ChatMessage(null, "BTW I FORGOT TO TELL THAT...", PERSONAL_USER_ID, date.time),
        ChatMessage(null, "BTW I FORGOT TO TELL THAT...", SARA_USER, date.time),
        ChatMessage(null, "Really how could you do that", SARA_USER, date.time),
        ChatMessage(
            null,
            "i cant believe taht you forgot to tell me this, this is so important. I'll write some more things just to prove",
            SARA_USER,
            date.time
        )
    )

    suspend fun insertPreFilledMessages(){
        chatMessageDao.insert(listMessage)
    }

    suspend fun getMessages(): LiveData<List<ChatMessage>> {
        return chatMessageDao.getListLiveData()
    }

    suspend fun persistMessage(chatMessage: ChatMessage) {
        return chatMessageDao.insert(chatMessage)
    }
}
