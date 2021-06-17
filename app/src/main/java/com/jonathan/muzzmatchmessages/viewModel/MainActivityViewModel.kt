package com.jonathan.muzzmatchmessages.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jonathan.muzzmatchmessages.model.ChatMessage
import com.jonathan.muzzmatchmessages.repo.MessagesRepository
import com.jonathan.muzzmatchmessages.util.PERSONAL_USER_ID
import com.jonathan.muzzmatchmessages.util.SARA_USER
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*


class MainActivityViewModel(
    private val messageRepository: MessagesRepository
) : ViewModel() {

    private var _messages: MutableLiveData<List<ChatMessage>> = MutableLiveData()
    var messages: LiveData<List<ChatMessage>> = _messages

    private var _addMessage: MutableLiveData<ChatMessage> = MutableLiveData()
    val addMessage: LiveData<ChatMessage> = _addMessage


    suspend fun initMessage() {
        withContext(Dispatchers.IO) {
            messageRepository.insertPreFilledMessages()
        }
    }

    suspend fun loadMessage() {
        withContext(Dispatchers.IO) {
            _messages = messageRepository.getMessages() as MutableLiveData<List<ChatMessage>>
        }
    }

    suspend fun addMessage(messageText: String, checked: Boolean) {
        val userID = if (!checked) PERSONAL_USER_ID else SARA_USER

        val message = ChatMessage(null,messageText, userID, Calendar.getInstance().time)
        withContext(Dispatchers.IO) {
            messageRepository.persistMessage(message) as MutableLiveData<ChatMessage>
        }

        _addMessage.value = message
    }
}